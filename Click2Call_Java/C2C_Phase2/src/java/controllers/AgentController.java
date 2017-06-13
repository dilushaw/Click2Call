/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uom.dialog.click2call.data.Agents;
import uom.dialog.click2call.data.Company;
import uom.dialog.click2call.data.Users;
import uom.dialog.click2call.dto.AgentDTO;
import uom.dialog.click2call.dto.CompanyDTO;
import uom.dialog.click2call.dto.UserDTO;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.manager.AgentManager;
import uom.dialog.click2call.manager.CompanyManager;
import uom.dialog.click2call.manager.UserManager;
import uom.dialog.click2call.translator.DomainToFormTranslator;
import uom.dialog.click2call.translator.FormToDomainTranslator;
import uom.dialog.click2call.utils.C2CEncryptor;
import uom.dialog.click2call.utils.Constants;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 *
 * @author Hasala ©Dialog - University of Moratuwa Mobile Communications Research Laboratory This is
 * the Controller Class for Agent related activities
 */
@Controller
public class AgentController {

    private static final String CREATEAGENT_FORM = "createAgent";
    private static final String VIEW_AGENT = "viewAgent";
private static final String AGENT_DASHBOARD = "agent_dashboard";
    private static final String EDIT_AGENT = "editAgent";
    @Autowired
    AgentManager agentManager;
    @Autowired
    UserManager userManager;
    @Autowired
    CompanyManager companyManager;
    @Autowired
    DomainToFormTranslator domainToFormTranslator;
    @Autowired
    FormToDomainTranslator formToDomainTranslator;

    /* Displays Create Agent */
    @RequestMapping(value = "/createAgent", method = RequestMethod.GET)
    public String showCreateAgentForm(HttpServletRequest request, Map map, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "Agents");
        session.setAttribute("SubMenu", "Create Agent");
        try {
            Users companyAdmin = userManager.findUserByUserNameAndCompany((String) request.getSession().getAttribute("userName"), (String) request.getSession().getAttribute("company"));
            Company currentCompany = companyAdmin.getCompany();
            int maxAgents = Integer.parseInt(currentCompany.getMaxAgents());
            int agentCount = agentManager.findAgentsByCompanyId(currentCompany.getCompanyId()).size();
            if ((agentCount == maxAgents) || (agentCount > maxAgents))/* Check if the maximum agent count is exceeded */ {
                model.addAttribute(Constants.ERRORMESSAGE, "You are allowed to create only "+maxAgents+" agents for your company!");
                return Constants.ERROR_POPUP;
            }
            AgentDTO createAgentDTO = new AgentDTO();
            Users admin = userManager.findUserByUserNameAndCompany((String) request.getSession().getAttribute("userName"), (String) request.getSession().getAttribute("company"));
            createAgentDTO.setCompanyName(admin.getCompany().getCompanyName());
            createAgentDTO.setCompanyId(admin.getCompany().getCompanyId());
            map.put(CREATEAGENT_FORM, createAgentDTO);
            return CREATEAGENT_FORM;
        } catch (Click2CallException ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        } catch (Exception ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        }

    }
    /* Processes Create Agent */

    @RequestMapping(value = "/createAgent", method = RequestMethod.POST)
    public String submitAgent(HttpServletRequest request, @Valid @ModelAttribute("createAgent") AgentDTO createAgentDTO, BindingResult result,
            Map map, Model model) {


        try {
            Users companyAdmin = userManager.findUserByUserNameAndCompany((String) request.getSession().getAttribute("userName"), (String) request.getSession().getAttribute("company"));
            Company currentCompany = companyAdmin.getCompany();
            int maxAgents = Integer.parseInt(currentCompany.getMaxAgents());
            List<Agents> agentList = agentManager.findAgentsByCompanyId(currentCompany.getCompanyId());
            int agentCount = agentList.size();
            if ((agentCount == maxAgents) || (agentCount > maxAgents))/* Check if the maximum agent count is exceeded */ {
                model.addAttribute(Constants.ERRORMESSAGE, "You have exceeded the maximum number of agents");
                return Constants.ERROR_POPUP;
            } else {
                Agents agent = formToDomainTranslator.translateCreateAgentForm(createAgentDTO);
                agent.setCompany(companyAdmin.getCompany());
                agentManager.saveAgent(agent);
                //below: set agent to current company and update
                Set<Agents> agents = currentCompany.getAgentses();
                agents.add(agent);
                currentCompany.setAgentses(agents);
                companyManager.simpleUpdate(currentCompany);
                model.addAttribute(Constants.SUCCESSMESSAGE, "You have successfully created a new agent");
                return CREATEAGENT_FORM;
            }

        } catch (Click2CallException ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        } catch (Exception ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        }


    }
    /* Displays View Agent */

    @RequestMapping(value = "/viewAgent", method = RequestMethod.GET)
    public String showViewAgent(HttpServletRequest request, Map map, Model model) throws Click2CallException {
        try {
            Agents agent = agentManager.findAgentByID(Integer.parseInt(request.getParameter("agentId")));
            System.out.println("inside view agent");
            HttpSession session = request.getSession();
            session.setAttribute("MenuTab", "Agents");
            session.setAttribute("SubMenu", "View Agent");
            //Get the agent phone number end encrypt it and use to identify agent when generating the call by set as a parameter in iframe url.
            String phoneNumEnc = C2CEncryptor.encrypt(agent.getNumber());
            map.put("agent", agent);
            map.put("agentKey", phoneNumEnc);

        } catch (Exception ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        }
        return VIEW_AGENT;
    }

    /* Displays Edit Agent */
    @RequestMapping(value = "/editAgent", method = RequestMethod.GET)
    public String showEditAgent(HttpServletRequest request, Map map, Model model) throws Click2CallException {
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "Agents");
        session.setAttribute("SubMenu", "Edit Agent");
        try {
            AgentDTO editAgent;
            Agents agent = agentManager.findAgentByID(Integer.parseInt(request.getParameter("agentId")));
            editAgent = domainToFormTranslator.translateDataToEditAgent(agent);

            map.put(EDIT_AGENT, editAgent);
        } catch (Exception ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        }
        return EDIT_AGENT;
    }
    /* Processes Edit Agent */

    @RequestMapping(value = "/editAgent", method = RequestMethod.POST)
    public String processEditAgent(HttpServletRequest request, @Valid @ModelAttribute("editAgent") AgentDTO editAgent, BindingResult result, Map map, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "Agents");
        session.setAttribute("SubMenu", "Edit Agent");
        try {
            Users user = userManager.findUserByUserNameAndCompany((String) request.getSession().getAttribute("userName"), (String) request.getSession().getAttribute("company"));
            
            map.put("editAgent", editAgent);
            agentManager.updateAgent(editAgent,user);
            model.addAttribute(Constants.SUCCESSMESSAGE, "You have successfully updated the agent");
        } catch (Exception ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        }
        return EDIT_AGENT;
    }
    /* Displays Agent Dashboard */

    @RequestMapping(value = "/agent_dashboard", method = {RequestMethod.GET, RequestMethod.POST})
    public String showFormAgentDashboard(Map map, HttpServletRequest request, Model model) {
        System.out.println("=============AgentDashboard================");
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "Agents");
        session.setAttribute("SubMenu", "Agent Dashboard");
        
        try {
            
                Users companyAdmin = userManager.findUserByUserNameAndCompany((String) request.getSession().getAttribute("userName"), (String) request.getSession().getAttribute("company"));
                if(companyAdmin.getUserType().getTypeId()!=Constants.CA){
                    return Constants.UNAUTHORIZED_PAGE;
                }
                //System.out.println("Inside else "+(String)request.getSession().getAttribute("company"));
                List<Agents> agentList = agentManager.findAgentsByCompanyId(companyAdmin.getCompany().getCompanyId());
                List<Agents> newAgentList = new ArrayList<Agents>();
                for(Agents agent:agentList){
                    HibernateUtil.getSession().refresh(agent);
                    newAgentList.add(agent);
                }
                
                //HibernateUtil.getSession().refresh(agentList);
//                List agentDashboard = new ArrayList();
//                for (int i = 0; i < agentList.size(); i++) {
//                    HibernateUtil.getSession().refresh(agentList.get(i));
//                    AgentDTO aDash = new AgentDTO();
//                    int usedSec=agentList.get(i).getUsedSeconds();
//                    double agentUsedMin = (usedSec/60)+((usedSec%60)*0.01);
//                    aDash.setAgentName(agentList.get(i).getName());
//                    aDash.setAgentNumber(agentList.get(i).getNumber());
//                    aDash.setTotalMinutes(agentList.get(i).getTotalMins());
//                    aDash.setUsedMinutes(agentUsedMin);
//                    aDash.setAgentId(agentList.get(i).getAgentId());
//                    agentDashboard.add(aDash);
//                }
                map.put("agents", newAgentList);
                return AGENT_DASHBOARD;
            
        } catch (Click2CallException ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        } catch (Exception ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        }
    }

    @RequestMapping(value = "/deleteAgent", method = RequestMethod.GET)
    public @ResponseBody
    String deleteAgent(HttpServletRequest request, Map map, @RequestParam Integer agendId) {

        String msg = "";

        try {
            Agents agent = agentManager.findAgentByID(agendId);
            System.out.println("I found agent : " + agent.getName());
            if (agent != null) {
                agent.setStatus(Constants.DELETED);
                agentManager.updateAgent(agent);
                msg = "yes";
            }
        } catch (Click2CallException ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
            msg = "no";
        } catch (Exception ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
            msg = "no";
        }
        return msg;

    }

    /**
     * When creating an agent; validate the given agent number is already exists within the system
     * (ajax call) as agent number should be unique within the entire system. added by dewmini.
     *
     * @param agentNumber - given number for the agent
     * @return
     */
    @RequestMapping(value = "/checkAgentNumber", method = RequestMethod.GET)
    public @ResponseBody
    String checkAgentNumber(@RequestParam String type,String agentNumber,String agentId) {

        String msg = "false";

        try {
            Agents agent = null;
            
            if (type.equalsIgnoreCase("edit")) {
                Agents currentAgent = agentManager.findAgentByAgentId(Integer.valueOf(agentId));
                if (!currentAgent.getNumber().equalsIgnoreCase(agentNumber)) {
                    agent = agentManager.findAgentByNumber(agentNumber);
                }
            } else {
                agent = agentManager.findAgentByNumber(agentNumber);
            }

            if (agent != null) {

                msg = "true";
            }
        } catch (Click2CallException ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
            msg = "exception";
        } catch (Exception ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
            msg = "exception";
        }
        return msg;

    }

    /**
     *
     * @param agentName
     * @param companyId
     * @param agentId
     * @param type
     * @return
     */
    @RequestMapping(value = "/checkAgentName", method = RequestMethod.GET)
    public @ResponseBody
    String checkAgentName(@RequestParam String agentName, int companyId, int agentId, String type) {
        System.out.println("inside check agent name!!!!!!!!!!!!!");
        String msg = "false";
        Agents agent = null;
        try {

            if (type.equalsIgnoreCase("edit")) {
                Agents currentAgent = agentManager.findAgentByAgentId(agentId);
                if (!currentAgent.getName().equalsIgnoreCase(agentName)) {
                    agent = agentManager.findAgentByNameAndCompanyId(companyId, agentName);
                }
            } else {
                agent = agentManager.findAgentByNameAndCompanyId(companyId, agentName);
            }
            if (agent != null) {

                msg = "true";
            }
        } catch (Click2CallException ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
            msg = "exception";
        } catch (Exception ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
            msg = "exception";
        }
        return msg;

    }

    /**
     * When companyId is given - get the Company by id. x=ComoanyTotalMinutes(allocated at the time
     * of creation), y[i]=arrayofAllAgentsallocatedMinutes; i=number of agents.....................
     * z=x-sum of y[i]; z=total remaining minutes to be allocated (i.e. minutes available for new
     * agent)
     *
     * @param companyId - companyId of the current company
     * @return
     */
    @RequestMapping(value = "/getCompanyRemainMinToAllocate", method = RequestMethod.GET)
    public @ResponseBody
    Integer getCompanyRemainMinToAllocate(@RequestParam String type, int companyId, String agentId) {
        System.out.println("inside - companyId: " + companyId);
        int availableMins = 0;

        try {

            Company company = companyManager.findCompanyByID(companyId);
            if (company != null) {
int chraginTypeId = company.getChargingPlan().getChargingType().getTypeId();
                if (chraginTypeId == Constants.CALL_CHARGE_ID || chraginTypeId==Constants.REANTAL_AND_CALL_ID) {
                    availableMins = -1;
                } else {
                    System.out.println("getTotalMins: " + company.getAllocatedMinutes());
                    availableMins = company.getAllocatedMinutes();
                    Set<Agents> agents = company.getAgentses();
                    if (agents != null || !agents.isEmpty()) {
                        for (Agents agent : agents) {
                            System.out.println("agent name: " + agent.getName() + " ------totalmin: " + agent.getTotalMins());
                            availableMins = availableMins - agent.getTotalMins();
                        }
                    }
                    
                    if(type.equalsIgnoreCase("edit")){
                        Agents agent = agentManager.findAgentByID(Integer.valueOf(agentId));
                        availableMins = availableMins+agent.getTotalMins();
                    }
                }
            }


        } catch (Click2CallException ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);

        }
        System.out.println("availableMins: " + availableMins);
        return availableMins;

    }
}
