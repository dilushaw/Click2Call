package controllers;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import logic.login.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
//import logic.barcodes.Barcode;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import uom.dialog.click2call.dao.AuthModelDAO;
import uom.dialog.click2call.dao.AuthModelDAOImpl;
import uom.dialog.click2call.utils.*;
import uom.dialog.click2call.data.Users;
import uom.dialog.click2call.dto.CompanyDTO;
import uom.dialog.click2call.dto.LoginDTO;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.manager.UserManager;
import uom.dialog.click2call.translator.FormToDomainTranslator;

/**
 *
 *
 *
 * @author nadeeka
 *
 */
@Controller
@SessionAttributes({"userName", "userID", "userType", "Company"})
public class MainController {

    @Autowired
    private UserManager userManager;
    @Autowired
    FormToDomainTranslator translator;
    @Autowired
    CompanyController companyController;
    @Autowired
    AgentController agentController;
    @Autowired
    UserController userController;
    //public static final String LOGIN_FORM = "loginForm";
    private static final String LOGIN = "login";
    private static final String LOGIN_DTO = "loginDTO";
    private static final String LOGGED_USER_SCREEN = "loggeduserscreen";
    /* 
     */
//
//    @RequestMapping(value = "/validateUser", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    String validateUser(@RequestParam String userName, String password,String company) {
//        String result = "ok";
//        Login login = new Login();
//
//        result = login.validateUser( userName, password,company);
//        System.out.println("Success 2");
//        return result;
//
//    }

    //begin test methods
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(HttpServletRequest request, Map map, Model model) {
        System.out.println("login get");
        LoginDTO loginDTO = new LoginDTO();
        map.put(LOGIN, loginDTO);

        return LOGIN;
    }

    @RequestMapping(value = "/home", method = {RequestMethod.POST, RequestMethod.GET})
    public String processLoginForm(HttpServletRequest request, @Valid @ModelAttribute("login") LoginDTO loginDTO, BindingResult result,
            Map map, Model model) {
        try {
            map.put(LOGIN, loginDTO);
            HttpSession session = request.getSession();
            System.out.println("LOGGED_USER= " + session.getAttribute(Constants.LOGGED_USER));

            if (session != null && session.getAttribute(Constants.LOGGED_USER) != null) {
                Users currentUser = userManager.findUserByUserNameAndCompany((String) request.getSession().getAttribute("userName"), (String) request.getSession().getAttribute("company"));
                return getHomePage(currentUser.getUserType().getTypeName(), map);
            }
            if (result.hasErrors()) {
                return LOGIN;
            }
            Users user = translator.translateLoginFormToUser(loginDTO);


            user = userManager.loadLoginUser(user);
            if (user == null) { // Not a valid user
                map.put(Constants.ERRORMESSAGE, "Invalid User name /Password /Company");
                return LOGIN;
            }

            if (user != null) {
                int status = user.getUserStatus();
                if (status == 0 || status == 3) {
                    map.put(Constants.ERRORMESSAGE, "This User is Inactive.");
                    return LOGIN;
                }
                int companyStatus = user.getCompany().getCompanyStatus();
                if (companyStatus != 1) {
                    map.put(Constants.ERRORMESSAGE, "This Company is Inactive.");
                    return LOGIN;
                }
                //Setting session values
                session.setAttribute(Constants.USER_NAME, loginDTO.getLoginName());
                session.setAttribute(Constants.COMPANY, loginDTO.getLoginCompany());
                session.setAttribute(Constants.USER_TYPE, user.getUserType().getTypeName());
                session.setAttribute(Constants.LOGGED_USER, true);
                String type = user.getUserType().getTypeName();
                System.out.println(type);
                return getHomePage(type, map);

            }
        } catch (Click2CallException ex) {
            return LOGIN;
        }
        return LOGGED_USER_SCREEN;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, Map map, Model model, SessionStatus sessionStatus) {

        map.remove(LOGIN_DTO);

        request.getSession().removeAttribute(Constants.USER_NAME);
        request.getSession().removeAttribute(Constants.COMPANY);
        request.getSession().removeAttribute(Constants.USER_TYPE);
        request.getSession().removeAttribute(Constants.LOGGED_USER);

        sessionStatus.setComplete();
        //request.getSession(false).invalidate();
        request.getSession(true);
        //request.getSession().
        LoginDTO loginDTO = new LoginDTO();
        model.addAttribute(Constants.SUCCESSMESSAGE, "You have logged out from the system");
        map.put(LOGIN, loginDTO);
        return LOGIN;
    }

    private String getHomePage(String type, Map map) {
        if (type.equals(Constants.DIALOG_ADMIN)) {
            //companyController.showFormCompanyDashboard(map, request, model);
            return "forward:/company_dashboard.htm";
        } else if (type.equals(Constants.CUSTOMRE_CARE_ADMIN)) {
            //userController.showFormUserDashboard(map, request, model);
            return "forward:/user_dashboard.htm";
        } else if (type.equals(Constants.COMPANY_ADMIN)) {
            //agentController.showFormAgentDashboard(map, request, model);
            return "forward:/agent_dashboard.htm";
        } else {
            map.put(Constants.ERRORMESSAGE, "Cannot find user type");
            System.out.println("case is here");
            return LOGIN;
        }
    }
}
