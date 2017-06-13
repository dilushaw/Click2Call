/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.common.collect.HashBiMap;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lk.dialog.cg.ws.messages.jaws.CreditCheckResponse;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uom.dialog.click2call.charging.ChargingService;
import uom.dialog.click2call.data.Calls;
import uom.dialog.click2call.data.Company;
import uom.dialog.click2call.data.Users;
import uom.dialog.click2call.dto.CompanyDTO;
import uom.dialog.click2call.dto.UserDTO;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.manager.CompanyManager;
import uom.dialog.click2call.manager.UserManager;
import uom.dialog.click2call.manager.UserTypeManager;
import uom.dialog.click2call.translator.FormToDomainTranslator;
import uom.dialog.click2call.utils.Constants;
import uom.dialog.click2call.dto.MyProfileDTO;
import uom.dialog.click2call.dto.ReportDTO;
import uom.dialog.click2call.manager.CallManager;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 *
 * @author Hasala ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
@Controller
public class ReportController {

    private static final String REPORTS = "reports";
    @Autowired
    CompanyManager companyManager;
    @Autowired
    ChargingService chargingService;
    @Autowired
    CallManager callManager;
    @Autowired
    UserManager userManager;

    @RequestMapping(value = "/reports", method = RequestMethod.GET)
    public String showReportForm(HttpServletRequest request, Map model) {
        try {
            Users currentUser = userManager.findUserByUserNameAndCompany((String) request.getSession().getAttribute("userName"), (String) request.getSession().getAttribute("company"));
            if (currentUser.getUserType().getTypeId() != Constants.DA) {
                return Constants.UNAUTHORIZED_PAGE;
            }
            System.out.println("report get");
            HttpSession session = request.getSession();
            session.setAttribute("MenuTab", "Reports");
            session.setAttribute("SubMenu", "Call History");
            model.put("reportForm", new ReportDTO());
            model.put("callDashboard", companyManager.getCompanyAllCalls());

        } catch (Click2CallException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            model.put(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        } catch (Exception ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            model.put(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        }
        return REPORTS;
    }

    @RequestMapping(value = "/reports", method = RequestMethod.POST)
    public String processReportForm(HttpServletRequest request, @ModelAttribute("reportForm") ReportDTO reportDTO, BindingResult result, Map model) {
        try {
            System.out.println("report post");
            HttpSession session = request.getSession();
            session.setAttribute("MenuTab", "Reports");
            session.setAttribute("SubMenu", "Show Reports");

            model.put("reportForm", reportDTO);
            System.out.println("from : " + reportDTO.getFromDate());
            System.out.println("to : " + reportDTO.getToDate());
            DateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
            //Date currentdate = new Date();

            Date from = formatter.parse(reportDTO.getFromDate());
            Date to = formatter.parse(reportDTO.getToDate());
            to = increaseToDateBy1(to);
            model.put("callDashboard", companyManager.getCompanyCallsHistory(from, to));
        } catch (Click2CallException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            model.put(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        } catch (ParseException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            model.put(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        } catch (Exception ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            model.put(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        }
        return REPORTS;
    }

    //report for Dialog Admin
    @RequestMapping(value = "/viewComapnyCharges", method = RequestMethod.GET)
    public String showCompanyCharges(HttpServletRequest request, Map map) {
        try {
            Users currentUser = userManager.findUserByUserNameAndCompany((String) request.getSession().getAttribute("userName"), (String) request.getSession().getAttribute("company"));
            if (currentUser.getUserType().getTypeId() != Constants.DA) {
                return Constants.UNAUTHORIZED_PAGE;
            }
            HttpSession session = request.getSession();
            session.setAttribute("MenuTab", "Reports");
            session.setAttribute("SubMenu", "Company Charging Data");
            CreditCheckResponse crRes = null;

            List<Company> companies = companyManager.loadAllCompanies();

            Map<Company, Double[]> chargeMap = new HashMap<Company, Double[]>();

            for (Company company : companies) {
                HibernateUtil.getSession().refresh(company);
                crRes = chargingService.checkCreditForNumber(company.getContactNumber());
                Double[] crValues = new Double[2];
                crValues[0] = crRes.getOutStanding();
                crValues[1] = crRes.getCreditlimit();
                System.out.println("R-Outstanding: " + crValues[0] + "R-Credit Limit: " + crValues[1]);
                chargeMap.put(company, crValues);
            }
            map.put("companyData", chargeMap);
        } catch (Click2CallException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            map.put(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        } catch (Exception ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            map.put(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        }
        return "viewComapnyCharges";
    }

    //report for Company Admin
    @RequestMapping(value = "/companyCalls", method = RequestMethod.GET)
    public String showCompanyCalls(Map map, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "Reports");
        session.setAttribute("SubMenu", "Company Calls");
        try {
            Users currentUser = userManager.findUserByUserNameAndCompany((String) request.getSession().getAttribute("userName"), (String) request.getSession().getAttribute("company"));
            if (currentUser.getUserType().getTypeId() != Constants.CA) {
                return Constants.UNAUTHORIZED_PAGE;
            }
            List<Calls> callList = callManager.findCallsByCompanyId(currentUser.getCompany().getCompanyId());
            List<Calls> newCallList = new ArrayList<Calls>();
            for (Calls call : callList) {
                HibernateUtil.getSession().refresh(call);
                newCallList.add(call);
            }
            map.put("charginPlan", currentUser.getCompany().getChargingPlan());
            map.put("calls", newCallList);
            map.put("companyCallsForm", new ReportDTO());
            return "companyCalls";
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

    @RequestMapping(value = "/companyCalls", method = RequestMethod.POST)
    public String processCompanyCalls(HttpServletRequest request, @ModelAttribute("companyCallsForm") ReportDTO reportDTO, BindingResult result, Map model) {
        try {
            System.out.println("companyCallsForm post");
            HttpSession session = request.getSession();
            session.setAttribute("MenuTab", "Reports");
            session.setAttribute("SubMenu", "Company Calls - Filtered");


            model.put("companyCallsForm", reportDTO);
            Users currentUser = userManager.findUserByUserNameAndCompany((String) request.getSession().getAttribute("userName"), (String) request.getSession().getAttribute("company"));

            DateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
            //Date currentdate = new Date();

            Date from = formatter.parse(reportDTO.getFromDate());
            Date to = formatter.parse(reportDTO.getToDate());
            to = increaseToDateBy1(to);

            List<Calls> callList = callManager.findCallsByCompanyIdAndDateRange(currentUser.getCompany().getCompanyId(), from, to);
            List<Calls> newCallList = new ArrayList<Calls>();
            for (Calls call : callList) {
                HibernateUtil.getSession().refresh(call);
                newCallList.add(call);
            }
            model.put("charginPlan", currentUser.getCompany().getChargingPlan());
            model.put("calls", newCallList);
        } catch (Click2CallException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            model.put(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        } catch (ParseException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            model.put(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        } catch (Exception ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            model.put(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        }
        return "companyCalls";
    }

    private Date increaseToDateBy1(Date to) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(to);
        cal.add(Calendar.DATE, 1);
        to = cal.getTime();
        return to;
    }
}
