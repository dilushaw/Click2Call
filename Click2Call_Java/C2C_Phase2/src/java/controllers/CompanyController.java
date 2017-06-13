/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import uom.dialog.click2call.dao.CallDAO;
import uom.dialog.click2call.dao.CallDAOImpl;
import uom.dialog.click2call.data.AuthModel;
import uom.dialog.click2call.data.Calls;
import uom.dialog.click2call.data.ChargingPlan;
import uom.dialog.click2call.data.Company;
import uom.dialog.click2call.data.Users;
import uom.dialog.click2call.dto.CompanyDTO;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.manager.AuthModelManager;
import uom.dialog.click2call.manager.ChargingPlanManager;
import uom.dialog.click2call.manager.CompanyManager;
import uom.dialog.click2call.manager.UserManager;
import uom.dialog.click2call.translator.DomainToFormTranslator;
import uom.dialog.click2call.translator.FormToDomainTranslator;
import uom.dialog.click2call.utils.Constants;

/**
 *
 * @author Hasala ©Dialog - University of Moratuwa Mobile Communications Research Laboratory This is
 * the Controller Class for Company related activities
 */
@Controller
public class CompanyController {

    private static final String CREATECOMPANY_FORM = "createCompany";
    private static final String VIEW_COMPANY = "viewCompany";
    private static final String COMPANY_DASHBOARD = "company_dashboard";
    private static final String EDIT_COMPANY = "editCompany";
    @Autowired
    private CompanyManager companyManager;
    @Autowired
    private ChargingPlanManager chargingPlanManager;
    @Autowired
    private AuthModelManager authModelManager;
    @Autowired
    private DomainToFormTranslator domainToFormTranslator;
    @Autowired
    private FormToDomainTranslator formToDomainTranslator;
    @Autowired
    UserManager userManager;

    /* Displays Create Company */
    @RequestMapping(value = "/createCompany", method = RequestMethod.GET)
    public String showCreateCompanyForm(HttpServletRequest request, Map map, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "Company");
        session.setAttribute("SubMenu", new String[]{"Create Company"});
        try {
            map.put("plans", chargingPlanManager.findAllChargingPlans());
            map.put("models", authModelManager.findAllAuthModels());
            CompanyDTO createCompanyDTO = new CompanyDTO();
            map.put(CREATECOMPANY_FORM, createCompanyDTO);
            return CREATECOMPANY_FORM;
        } catch (Click2CallException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        } catch (Exception ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        }

    }
    /* Processes Create Company */

    @RequestMapping(value = "/createCompany", method = RequestMethod.POST)
    public String submitCompany(HttpServletRequest request, @Valid @ModelAttribute("createCompany") CompanyDTO createCompanyDTO, BindingResult result,
            Map map, Model model) {

        map.put("createCompany", createCompanyDTO);
        FormToDomainTranslator translator = new FormToDomainTranslator();
        try {
            Company com = companyManager.findCompanyByName(createCompanyDTO.getCompanyName());
            if (com == null) {
                System.out.println("company not exists");
                Company company = formToDomainTranslator.translateCreateCompanyForm(createCompanyDTO);
                ChargingPlan chargingPlan = chargingPlanManager.findChargingPlanById(createCompanyDTO.getPlanId());
                company.setAllocatedMinutes(chargingPlan.getAllocatedMinutes());
                company.setAuthModel(authModelManager.findAuthModelById(createCompanyDTO.getAuthId()));
                company.setChargingPlan(chargingPlan);
                companyManager.saveCompany(company);
                model.addAttribute(Constants.SUCCESSMESSAGE, "You have successfully created a new company");
                map.put("plans", chargingPlanManager.findAllChargingPlans());
                map.put("models", authModelManager.findAllAuthModels());
                return CREATECOMPANY_FORM;


            } else {
                System.out.println("company not exists");
                model.addAttribute(Constants.ERRORMESSAGE, "The Company name already exists");
                map.put("plans", chargingPlanManager.findAllChargingPlans());
                map.put("models", authModelManager.findAllAuthModels());
                return CREATECOMPANY_FORM;
            }

        } catch (Click2CallException ex) {
            System.out.println("ex");
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        } catch (Exception ex) {
            System.out.println("ex");
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        }


    }
    /*    //begin test methods
     @RequestMapping(value = "/test", method = RequestMethod.GET)
     public String showTestForm(HttpServletRequest request, Map map, Model model){
     System.out.println("hellooo");
     CompanyDTO createCompanyDTO = new CompanyDTO();
     map.put("test", createCompanyDTO);
    
     return "test";
     }
     
     @RequestMapping(value = "/test", method = RequestMethod.POST)
     public String submitTestForm(HttpServletRequest request,@Valid @ModelAttribute("test")  CompanyDTO createCompanyDTO, BindingResult result,
     Map map, Model model){
     System.out.println("Success 1 ---------");
     map.put("test", createCompanyDTO);
     FormToDomainTranslator translator = new FormToDomainTranslator();
     Company company = translator.translateCreateCompanyForm(createCompanyDTO);
     System.out.println("Success 2 ---------");
     companyManager.saveCompany(company);
     System.out.println("Success 3 ---------");
     return "test";
     }// end test methods */

    /* Displays List of Companies */
    @RequestMapping(value = "/loadCompaniesDropDown", method = RequestMethod.GET)
    public @ResponseBody
    String loadCompaniesDropDown(HttpServletRequest request, Map model) {
        String html = null;
        try {
            System.out.println("Company ID : " + request.getParameter("id"));
            List<Company> companies = companyManager.loadAllCompanies();

            html = prepareCompaniesDropDown(companies);

        } catch (Click2CallException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            html = "Couldn't load the companies!";
        } catch (Exception ex) {

            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            html = "Couldn't load the companies!";
        }

        return html;
    }
    //Method to generate the list of available companies in the Create User Form 

    private String prepareCompaniesDropDown(List<Company> companies) {
        StringBuilder dropDown = new StringBuilder();

        if (companies != null && !companies.isEmpty()) {
            int i = 1;
            dropDown.append("<select name=\"companyId\" id=\"companyId\">"
                    + "<option value=\"\">--Please Select</option>");

            for (Company com : companies) {

                dropDown.append("<option value=\"" + com.getCompanyId() + "\" >" + com.getCompanyName() + "</option>");
                //dropDown.append("<option value=\"" + cam.getCampaignId() + "\" onclick=\"setSelectedCamId('" + cam.getCampaignId() + "')\"  >" + cam.getCampaignName() + "</option>");
                i++;
            }
            dropDown.append("</select>");
        } else {
            dropDown.append("No active companies found.");
        }
        return dropDown.toString();
    }
    /* Displays View Company */

    @RequestMapping(value = "/viewCompany", method = RequestMethod.GET)
    public String showViewAgent(HttpServletRequest request, Map map, Model model) throws Click2CallException {
        try {
            Company company = companyManager.findCompanyByID(Integer.parseInt(request.getParameter("companyId")));
            map.put("company", company);
        } catch (Exception ex) {
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        }
        return VIEW_COMPANY;
    }
    /* Displays Company Dashboard */

    @RequestMapping(value = "/company_dashboard", method = {RequestMethod.GET, RequestMethod.POST})
    public String showFormCompanyDashboard(Map map, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "Company");
        session.setAttribute("SubMenu", "Company Dashboard");
        try {
            Users currentUser = userManager.findUserByUserNameAndCompany((String) request.getSession().getAttribute("userName"), (String) request.getSession().getAttribute("company"));
                if(currentUser.getUserType().getTypeId()!=Constants.DA){
                    return Constants.UNAUTHORIZED_PAGE;
                }
            List<Company> companyList = companyManager.loadAllCompanies();
            List cDashboard = new ArrayList();
            for (int i = 0; i < companyList.size(); i++) {
                CompanyDTO cDash = new CompanyDTO();
                cDash.setCompanyName(companyList.get(i).getCompanyName());
                cDash.setNumberOfAgents(companyList.get(i).getMaxAgents());
                cDash.setContactPersonName(companyList.get(i).getContactName());
                cDash.setContactNumber(companyList.get(i).getContactNumber());
                cDash.setCompanyId(companyList.get(i).getCompanyId());

                cDashboard.add(cDash);
            }
            map.put("cDashboard", cDashboard);
//            CallDAO callDAO = new CallDAOImpl();
//            Integer size = (callDAO.findAll(Calls.class)).size();
//            map.put("size", size);
            return COMPANY_DASHBOARD;
        } catch (Click2CallException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        } catch (Exception ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        }

    }

    /* Displays Edit Company */
    @RequestMapping(value = "/editCompany", method = RequestMethod.GET)
    public String showFormEditCompany(HttpServletRequest request, Map map, Model model) {

        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "Company");
        session.setAttribute("SubMenu", "Edit Company");

        try {
            CompanyDTO editCompany;
            Company company = companyManager.findCompanyByID(Integer.parseInt(request.getParameter("companyId")));
            map.put("plans", chargingPlanManager.findAllChargingPlans());
            map.put("models", authModelManager.findAllAuthModels());
            editCompany = domainToFormTranslator.translateDataToEditCompany(company);
            map.put(EDIT_COMPANY, editCompany);
            return EDIT_COMPANY;
        } catch (Click2CallException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        } catch (Exception ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        }


    }
    /* Processes Edit Company */

    @RequestMapping(value = "/editCompany", method = RequestMethod.POST)
    public String submitFormEditCompany(HttpServletRequest request, @Valid @ModelAttribute("editCompany") CompanyDTO editCompany, BindingResult result,
            Map map, Model model) {

        try {
            Users user = userManager.findUserByUserNameAndCompany((String) request.getSession().getAttribute("userName"), (String) request.getSession().getAttribute("company"));
            companyManager.updateCompany(editCompany,user);
            model.addAttribute(Constants.SUCCESSMESSAGE, "You have successfully updated the company");
            map.put("plans", chargingPlanManager.findAllChargingPlans());
            map.put("models", authModelManager.findAllAuthModels());
            return EDIT_COMPANY;
        } catch (Click2CallException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        } catch (Exception ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        }

    }

    /**
     * Check the given email(which contain in the request) is already exists in the system.(company
     * email is unique within the system)added by dewmini
     *
     * @param request - current http request
     * @return - yes=>if email already exists; no=>if email not exists; exception=>if exception
     * occurred
     */
    @RequestMapping(value = "/checkCompanyEmail", method = RequestMethod.GET)
    public @ResponseBody
    String checkCompanyEmail(HttpServletRequest request) {
        String html = "no";
        Company company = null;
        try {
            System.out.println("Email given : " + request.getParameter("email"));
            System.out.println("type given : " + request.getParameter("type"));
            System.out.println("companyId : " + request.getParameter("companyId"));
            if (request.getParameter("type").equalsIgnoreCase("edit")) {
                Company currentCompany = companyManager.findCompanyByID(Integer.valueOf(request.getParameter("companyId")));
                if (!currentCompany.getEmail().equalsIgnoreCase(request.getParameter("email"))) {
                    company = companyManager.findCompanyByEmail(request.getParameter("email"));
                }
            } else {
                company = companyManager.findCompanyByEmail(request.getParameter("email"));
            }
            if (company != null) {
                html = "yes";
            }


        } catch (Click2CallException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            html = "exception";
        } catch (Exception ex) {

            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            html = "exception";
        }

        return html;
    }

    /**
     * Check the given phone number(which contain in the request) is already exists in the
     * system.(company phone number is unique within the system)added by dewmini
     *
     * @param request - current http request
     * @return - yes=>if phone number already exists; no=>if phone number not exists; exception=>if
     * exception occurred
     */
    @RequestMapping(value = "/checkCompanyPhone", method = RequestMethod.GET)
    public @ResponseBody
    String checkCompanyPhone(HttpServletRequest request) {
        String html = "no";
        try {
            System.out.println("checkCompanyPhone given : " + request.getParameter("phone"));
            //Company company = companyManager.findCompanyByPhoneNumber(request.getParameter("phone"));
            Company company = null;
            
            if (request.getParameter("type").equalsIgnoreCase("edit")) {
                Company currentCompany = companyManager.findCompanyByID(Integer.valueOf(request.getParameter("companyId")));
                if (!currentCompany.getContactNumber().equalsIgnoreCase(request.getParameter("phone"))) {
                    company = companyManager.findCompanyByPhoneNumber(request.getParameter("phone"));
                }
            } else {
                System.out.println(" check new company creation");
                company = companyManager.findCompanyByPhoneNumber(request.getParameter("phone"));
            }
            
            if (company != null) {
                html = "yes";
            }

        } catch (Click2CallException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            html = "exception";
        } catch (Exception ex) {

            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            html = "exception";
        }

        return html;
    }

    /**
     * Check the given Company Name(which contain in the request) is already exists in the
     * system.(Company Name is unique within the system)added by dewmini
     *
     * @param request - current http request
     * @return - yes=>if Company Name already exists; no=>if Company Name not exists; exception=>if
     * exception occurred
     */
    @RequestMapping(value = "/checkCompanyName", method = RequestMethod.GET)
    public @ResponseBody
    String checkCompanyName(HttpServletRequest request) {
        String html = "no";
        try {
            System.out.println("companyName given : " + request.getParameter("companyName"));
            Company company = companyManager.findCompanyByName(request.getParameter("companyName"));
            if (company != null) {
                html = "yes";
            }

        } catch (Click2CallException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            html = "exception";
        } catch (Exception ex) {

            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            html = "exception";
        }

        return html;
    }
    
    
    
     @RequestMapping(value = "/viewChargingPlans", method = RequestMethod.GET) 
     public String viewChargingPlans(HttpServletRequest request,Map map) {
        try {
            List<ChargingPlan> charginPlans = chargingPlanManager.findAllChargingPlans();
            map.put("charginPlans", charginPlans);
        } catch (Click2CallException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            map.put(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        }catch (Exception ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            map.put(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        }
        return "viewChargingPlans";
     }
     
     
}
