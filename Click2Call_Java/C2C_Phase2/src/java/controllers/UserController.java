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
import uom.dialog.click2call.translator.DomainToFormTranslator;

/**
 *
 * @author Hasala ©Dialog - University of Moratuwa Mobile Communications Research Laboratory This is
 * the Controller Class for User related activities
 */
@Controller
public class UserController {

    private static final String CREATEUSER_FORM = "createUser";
    private static final String VIEW_USER = "viewUser";
    private static final String USER_DASHBOARD = "user_dashboard";
    private static final String EDIT_USER = "editUser";
    private static final String MY_PROFILE = "my_profile";
    @Autowired
    UserManager userManager;
    @Autowired
    UserTypeManager userTypeManager;
    @Autowired
    CompanyManager companyManager;
    @Autowired
    DomainToFormTranslator domainToFormTranslator;

    /* Displays Create User Page */
    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public String showCreateUserForm(HttpServletRequest request, Map map, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "User Management");
        session.setAttribute("SubMenu", "Create User");
        try {
            UserDTO createUserDTO = new UserDTO();
            map.put("types", userTypeManager.findAllUserTypes());
            map.put(CREATEUSER_FORM, createUserDTO);
            return CREATEUSER_FORM;
        } catch (Click2CallException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        }

    }
    /* Processes Create User Page */

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String submitUser(HttpServletRequest request, @Valid @ModelAttribute("createUser") UserDTO createUserDTO, BindingResult result,
            Map map, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "User Management");
        session.setAttribute("SubMenu", "Create User");

        try {
            map.put(CREATEUSER_FORM, createUserDTO);

//            Company currentCompany = companyManager.findCompanyByID(createUserDTO.getCompanyId());
//            //check username already exists for the relevant company
//            Users userCheck = userManager.findUserByUserNameAndCompany(createUserDTO.getUserName(), currentCompany.getCompanyName());
//            if (userCheck != null) {
//                model.addAttribute(Constants.ERRORMESSAGE, "User Name already exists!");
//                return CREATEUSER_FORM;
//            }

            FormToDomainTranslator translator = new FormToDomainTranslator();
            Users user = translator.translateCreateUserForm(createUserDTO);


            user.setUserType(userTypeManager.findUserTypeById(createUserDTO.getTypeId()));
            System.out.println("I found company Id " + createUserDTO.getCompanyId());
            System.out.println("user type id: " + createUserDTO.getTypeId());
            if (createUserDTO.getTypeId() == Constants.CA) {//Check if the user type is not company admin
                user.setCompany(companyManager.findCompanyByID(createUserDTO.getCompanyId()));
            } else {
                user.setCompany(companyManager.findCompanyByID(Constants.DIALOG_COMPANY_ID));
            }
            userManager.saveUser(user);
            model.addAttribute(Constants.SUCCESSMESSAGE, "You have successfully created a new user");
            return CREATEUSER_FORM;
        } catch (Click2CallException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        }

    }
    /* Displays View User Page */

    @RequestMapping(value = "/viewUser", method = RequestMethod.GET)
    public String showViewAgent(HttpServletRequest request, Map map, Model model) throws Click2CallException {
        Users user = userManager.findUserByID(Integer.parseInt(request.getParameter("userId")));
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "User Management");
        session.setAttribute("SubMenu", "View User");
        System.out.println("inside view user");
        System.out.println(user.getUserName());
        map.put("user", user);
        return VIEW_USER;
    }
    /* Displays Edit User Page */

    @RequestMapping(value = "/editUser", method = RequestMethod.GET)
    public String showEditUser(HttpServletRequest request, Map map, Model model) throws Click2CallException {
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "User Management");
        session.setAttribute("SubMenu", "Edit User");

        UserDTO editUser;
        Users user = userManager.findUserByID(Integer.parseInt(request.getParameter("userId")));
        editUser = domainToFormTranslator.translateDataToEditUser(user);

        map.put(EDIT_USER, editUser);
        return EDIT_USER;
    }
    /* Processes Edit User */

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public String processEditUser(HttpServletRequest request, @Valid @ModelAttribute("editUser") UserDTO editUser, BindingResult result, Map map, Model model) throws Click2CallException {
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "User Management");
        session.setAttribute("SubMenu", "Edit User");
        System.out.println("I found user name " + editUser.getUserName());
        System.out.println("I found user name " + editUser.getUserId());
        //map.put(EDIT_USER, editUser);
        //System.out.println(editUser.getPassword());
        userManager.updateUser(editUser);
        model.addAttribute(Constants.SUCCESSMESSAGE, "You have successfully updated the user");
        return EDIT_USER;
    }

    /* Displays User Dashboard */
    @RequestMapping(value = "/user_dashboard", method = {RequestMethod.GET, RequestMethod.POST})
    public String showFormUserDashboard(Map map, HttpServletRequest request, Model model) {
        System.out.println("=============UserDashboard================");
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "User Management");
        session.setAttribute("SubMenu", "User Dashboard");

        try {
            Users currentUser = userManager.findUserByUserNameAndCompany((String) request.getSession().getAttribute("userName"), (String) request.getSession().getAttribute("company"));
            if (currentUser.getUserType().getTypeId() != Constants.DA) {
                return Constants.UNAUTHORIZED_PAGE;
            }
            List<Users> userList = userManager.loadAllUsers();
            List dashboard = new ArrayList();

            for (int i = 0; i < userList.size(); i++) {
                UserDTO udash = new UserDTO();
                udash.setUserName(userList.get(i).getUserName());
                udash.setUserType(userList.get(i).getUserType().getTypeName());
                udash.setPhone(userList.get(i).getPhone());
                udash.setEmail(userList.get(i).getEmail());
                udash.setUserId(userList.get(i).getUserId());
                udash.setCompanyName(userList.get(i).getCompany().getCompanyName());
                dashboard.add(udash);

            }
            map.put("dashboard", dashboard);
            return USER_DASHBOARD;

        } catch (Click2CallException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        }



    }
    /* Displays My Profile for Logged User */

    @RequestMapping(value = "/my_profile", method = RequestMethod.GET)
    public String showEditMyProfile(Map map, HttpServletRequest request, Model model) {
        System.out.println("=============my_profile================");
        MyProfileDTO myProfileDTO = new MyProfileDTO();

        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "My Profile");
        session.setAttribute("SubMenu", "My Profile");
        try {

            Users loggedUser = userManager.findUserByUserNameAndCompany((String) request.getSession().getAttribute("userName"), (String) request.getSession().getAttribute("company"));
            myProfileDTO = domainToFormTranslator.translateMyProfile(loggedUser);
            map.put(MY_PROFILE, myProfileDTO);

            return MY_PROFILE;
        } catch (Click2CallException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        }




    }   /* Processes My Profile for Logged User */


    @RequestMapping(value = "/my_profile", method = RequestMethod.POST)
    public String processEditMyProfile(HttpServletRequest request, @Valid @ModelAttribute("my_profile") MyProfileDTO myProfileDTO, BindingResult result, Map map, Model model) throws Click2CallException {
        HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "My Profile");
        session.setAttribute("SubMenu", "My Profile");
        //System.out.println("I found user name "+myProfileDTO.getFullName());
        //System.out.println("I found user name "+myProfileDTO.getUserId());
        System.out.println("PASSWORD IS : " + myProfileDTO.getPassword());
        System.out.println("CHANGE PASS ID IS : " + myProfileDTO.getPwchange());
        userManager.updateMyProfile(myProfileDTO);
        model.addAttribute(Constants.SUCCESSMESSAGE, "You have successfully updated your profile");
        return MY_PROFILE;
    }

    /**
     * Check the given User Name(which contain in the request) is already exists in the within the
     * company.(User Name is unique within the company)added by dewmini
     *
     * @param request - current http request
     * @return - yes=>if User Name already exists; no=>if User Name not exists; exception=>if
     * exception occurred
     */
    @RequestMapping(value = "/checkUserName", method = RequestMethod.GET)
    public @ResponseBody
    String checkUserName(HttpServletRequest request) {
        String html = "no";
        Users user = null;
        //int companyId=Constants.DIALOG_COMPANY_ID;
        try {
//            System.out.println("company given 1: " + request.getParameter("companyId"));
//            System.out.println("company given 2: " + Integer.valueOf(request.getParameter("companyId")));
//            System.out.println("company given 3: " + Integer.parseInt(request.getParameter("companyId")));
            int userTypeId = Integer.valueOf(request.getParameter("typeId"));
//            if (userTypeId == Constants.CA) {
//               companyId = Integer.valueOf(request.getParameter("companyId"));
//            } 
            int companyId = (userTypeId == Constants.CA ? Integer.valueOf(request.getParameter("companyId")) : Constants.DIALOG_COMPANY_ID);
            Company userCompany = companyManager.findCompanyByID(companyId);
            user = userManager.findUserByUserNameAndCompany(request.getParameter("userName"), userCompany.getCompanyName());
            if (user != null) {
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

    @RequestMapping(value = "/checkUserEmail", method = RequestMethod.GET)
    public @ResponseBody
    String checkUserEmailExist(HttpServletRequest request) {
        System.out.println("+++++++++++++++++++++==================================%%%%%%%inside");
        String html = "no";
        Users user = null;
        Company currentCompany = null;
        try {

            System.out.println("Email given : " + request.getParameter("email"));
            System.out.println("type given : " + request.getParameter("actionType"));
            System.out.println("companyId : " + request.getParameter("companyId"));
            if (request.getParameter("actionType").equalsIgnoreCase("edit")) {

                Users currentUser = userManager.findUserByID(Integer.valueOf(request.getParameter("companyId")));
                currentCompany = currentUser.getCompany();
                if (!currentUser.getEmail().equalsIgnoreCase(request.getParameter("email"))) {
                    user = userManager.findUserByEmailAndCompanyId(request.getParameter("email"), currentCompany.getCompanyId());
                }
            } else {
                currentCompany = companyManager.findCompanyByID(Integer.valueOf(request.getParameter("companyId")));
                user = userManager.findUserByEmailAndCompanyId(request.getParameter("email"), currentCompany.getCompanyId());
            }
            if (user != null) {
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
}
