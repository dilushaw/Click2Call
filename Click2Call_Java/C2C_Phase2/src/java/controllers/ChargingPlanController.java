/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory. All
 * Rights Reserved. This software is the proprietary information of Dialog-University of Moratuwa
 * Mobile Communications Research Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the
 * software as it sees fit.
 *
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import uom.dialog.click2call.data.ChargingPlan;
import uom.dialog.click2call.data.ChargingType;
import uom.dialog.click2call.data.Company;
import uom.dialog.click2call.data.Users;
import uom.dialog.click2call.dto.ChargingPlanDTO;
import uom.dialog.click2call.dto.CompanyDTO;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.manager.ChargingPlanManager;
import uom.dialog.click2call.manager.ChargingTypeManager;
import uom.dialog.click2call.manager.CompanyManager;
import uom.dialog.click2call.manager.SystemHistoryManager;
import uom.dialog.click2call.manager.UserManager;
import uom.dialog.click2call.translator.DomainToFormTranslator;
import uom.dialog.click2call.translator.FormToDomainTranslator;
import uom.dialog.click2call.utils.Constants;

/**
 * ChargingPlanController.java (UTF-8) Jun 13, 2013, 9:56:52 AM
 *
 * @author Dewmini
 */
@Controller
public class ChargingPlanController {

    @Autowired
    ChargingPlanManager chargingPlanManager;
    @Autowired
    ChargingTypeManager chargingTypeManager;
    @Autowired
    FormToDomainTranslator formToDomainTranslator;
    @Autowired
    DomainToFormTranslator domainToFormTranslator;
    @Autowired
    SystemHistoryManager systemHistoryManager;
    @Autowired
    UserManager userManager;
    @Autowired
    CompanyManager companyManager;

    @RequestMapping(value = "/allChargingPlans", method = {RequestMethod.GET, RequestMethod.POST})
    public String showFormCompanyDashboard(Map map, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "Charging");
        session.setAttribute("SubMenu", "All Charging Plans");
        try {
            Users currentUser = userManager.findUserByUserNameAndCompany((String) request.getSession().getAttribute("userName"), (String) request.getSession().getAttribute("company"));
                if(currentUser.getUserType().getTypeId()!=Constants.DA){
                    return Constants.UNAUTHORIZED_PAGE;
                }
            List<ChargingPlan> cplans = chargingPlanManager.findAllChargingPlans();
            map.put("cplans", cplans);
            return "allChargingPlans";
        } catch (Click2CallException ex) {
            Logger.getLogger(ChargingPlanController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        } catch (Exception ex) {
            Logger.getLogger(ChargingPlanController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        }

    }

    @RequestMapping(value = "/newChargingPlan", method = RequestMethod.GET)
    public String createNewChargingPlan(HttpServletRequest request, Map map, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "Charging");
        session.setAttribute("SubMenu", "Define New Charging Plan");
        try {
            //set form : Charging types drop down
            map.put("chargeTypes", chargingTypeManager.findAllChargingTypes());

            ChargingPlanDTO chargingPlanDTO = new ChargingPlanDTO();
            //set jsp form:newChargingPlanForm
            map.put("newChargingPlanForm", chargingPlanDTO);
            
            //set charge fields inputs disable
            chargeFieldsStatus( -1, map);//-1 > no charging paln selected
            
            return "newChargingPlan";
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

    @RequestMapping(value = "/newChargingPlan", method = RequestMethod.POST)
    public String submitNewChargingPlan(HttpServletRequest request, @Valid @ModelAttribute("newChargingPlanForm") ChargingPlanDTO chargingPlanDTO, BindingResult result,
            Map map, Model model) {



        try {
            //get the form selected ChargingType object
            ChargingType cType = chargingTypeManager.findChargingTypeById(chargingPlanDTO.getChargingTypeId());
            //pass the form values to the translator for creating New ChargingPlan object
            ChargingPlan cplan = formToDomainTranslator.translateNewChargingPlanForm(chargingPlanDTO, cType);
            //save created ChargingPlan
            chargingPlanManager.saveChargingPlan(cplan);
            
            //set charge fields inputs status to the status at the time new Charging plan is saved.
            chargeFieldsStatus( cplan.getChargingType().getTypeId(), map);
            //create again : Charging types drop down
            map.put("chargeTypes", chargingTypeManager.findAllChargingTypes());
            //set the selected Charging type when the form is saved
            chargingPlanDTO.setChargingTypeId(cplan.getChargingType().getTypeId());
            //set again jsp form:newChargingPlanForm with above changed values
            map.put("newChargingPlanForm", chargingPlanDTO);
            //set success message appears in the form.
            map.put("successMessage", "Successfully Added.");
            return "newChargingPlan";
//        } catch (Click2CallException ex) {
//            System.out.println("ex");
//            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
//            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
//            return Constants.ERROR_POPUP;
        } catch (Exception ex) {
            System.out.println("ex");
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR_POPUP;
        }
    }

    @RequestMapping(value = "/editChargingPlan", method = RequestMethod.GET)
    public String editChargingPlan(HttpServletRequest request, Map map, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "Charging");
        session.setAttribute("SubMenu", "Edit Charging Plan");
        try {
            
            int planId = Integer.valueOf(request.getParameter("planId"));
            System.out.println("-------------------------planId:" + planId + " ----------------");
            ChargingPlan cplan = chargingPlanManager.findChargingPlanById(planId);
            //map.put("chargeTypes", chargingTypeManager.findAllChargingTypes());

            ChargingPlanDTO chargingPlanDTO = new ChargingPlanDTO();
            chargingPlanDTO = domainToFormTranslator.translateDataToEditCPlan(cplan);

            int ctypeId = cplan.getChargingType().getTypeId();
            chargeFieldsStatus( ctypeId, map);
            map.put("chargeTypes", chargingTypeManager.findAllChargingTypes());
            map.put("planId", planId);
            map.put("editChargingPlanForm", chargingPlanDTO);
            return "editChargingPlan";
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

    @RequestMapping(value = "/editChargingPlan", method = RequestMethod.POST)
    public String submitEditChargingPlan(HttpServletRequest request, @Valid @ModelAttribute("editChargingPlanForm") ChargingPlanDTO chargingPlanDTO, BindingResult result, Map map, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "Charging");
        session.setAttribute("SubMenu", "Edit Charging Plan");
        try {
            
            //int planId = chargingPlanDTO.getChargingTypeId();
            int planId = Integer.valueOf(request.getParameter("planId"));
            ChargingPlan cplan = chargingPlanManager.findChargingPlanById(planId);
            //map.put("chargeTypes", chargingTypeManager.findAllChargingTypes());

            Users user = userManager.findUserByUserNameAndCompany((String) request.getSession().getAttribute("userName"), (String) request.getSession().getAttribute("company"));



            int ctypeId = cplan.getChargingType().getTypeId();
            

            Double monthlyRental = cplan.getMonthlyRental();
            Integer allocatedMins = cplan.getAllocatedMinutes();
            Double perMinCharge = cplan.getPerminuteCharge();
            
            //save changed values in system history
            if (monthlyRental != null && !monthlyRental.equals(chargingPlanDTO.getMonthlyRental())) {
                systemHistoryManager.saveToSystemHistory("charging_plan", "monthly_rental", cplan.getPlanId().toString(), chargingPlanDTO.getMonthlyRental().toString(), monthlyRental.toString(), user);
            }
            if (allocatedMins != null && !allocatedMins.equals(chargingPlanDTO.getAllocatedMinutes())) {
                systemHistoryManager.saveToSystemHistory("charging_plan", "allocated_minutes", cplan.getPlanId().toString(), chargingPlanDTO.getAllocatedMinutes().toString(), allocatedMins.toString(), user);
            }
            if (perMinCharge != null && !perMinCharge.equals(chargingPlanDTO.getPerMinCharge())) {
                systemHistoryManager.saveToSystemHistory("charging_plan", "perminute_charge", cplan.getPlanId().toString(), chargingPlanDTO.getPerMinCharge().toString(), perMinCharge.toString(), user);
            }


            //set charging pans to its new values
            cplan = formToDomainTranslator.translateEditChargingPlanForm(chargingPlanDTO, cplan);
            //update charging plan with new values
            chargingPlanManager.updateChargingPlan(cplan);
            //set all relavant comapnies(which belongs to particulat charging plan) allocated mins, new allocated mins of charging plan
            updateRelevantCompanyAllocatedMins(cplan, cplan.getAllocatedMinutes());
            //prepare jsp form again with new values
            chargingPlanDTO = domainToFormTranslator.translateDataToEditCPlan(cplan);

            chargeFieldsStatus( ctypeId, map);
            
            map.put("planId", planId);
            map.put("chargeTypes", chargingTypeManager.findAllChargingTypes());
            map.put("editChargingPlanForm", chargingPlanDTO);
            map.put("successMessage", "Successfully Edited.");
            return "editChargingPlan";
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

    private void updateRelevantCompanyAllocatedMins(ChargingPlan cpaln, Integer allocatedMins) throws Click2CallException {
        Set<Company> companies = cpaln.getCompanies();
        for (Company company : companies) {
            company.setAllocatedMinutes(allocatedMins);
            companyManager.simpleUpdate(company);
        }
    }

    @RequestMapping(value = "/checkPlanNameExist", method = RequestMethod.GET)
    public @ResponseBody
    String checkPlanNameExist(HttpServletRequest request) {
        System.out.println("inside checkPlanNameExist............");
        String html = "no";
        try {
            System.out.println("planName given : " + request.getParameter("planName"));
            ChargingPlan cplan = chargingPlanManager.findPlanByName(request.getParameter("planName"));
            if (cplan != null) {
                html = "yes";
            }
            System.out.println(" checkPlanNameExist............" + html);
        } catch (Click2CallException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            html = "exception";
        } catch (Exception ex) {

            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            html = "exception";
        }

        return html;
    }

    private void chargeFieldsStatus(int ctypeId, Map map) {
        String rentalStatus = "true";
        String allocMinStatus = "true";
        String perMinStatus = "true";

        if (ctypeId == Constants.RENTAL_ID) {
            rentalStatus = "false";
            allocMinStatus = "false";
            perMinStatus = "true";
        }
        if (ctypeId == Constants.CALL_CHARGE_ID) {
            rentalStatus = "true";
            allocMinStatus = "true";
            perMinStatus = "false";
        }
        if (ctypeId == Constants.REANTAL_AND_CALL_ID) {
            rentalStatus = "false";
            allocMinStatus = "false";
            perMinStatus = "false";
        }
        map.put("rentalStatus", rentalStatus);
        map.put("allocMinStatus", allocMinStatus);
        map.put("perMinStatus", perMinStatus);
    }
}
