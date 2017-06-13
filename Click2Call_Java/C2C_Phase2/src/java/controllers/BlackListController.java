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
import uom.dialog.click2call.data.Blacklist;
import uom.dialog.click2call.data.Users;
import uom.dialog.click2call.dto.BlackListDTO;
import uom.dialog.click2call.dto.UserDTO;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.manager.CompanyManager;
import uom.dialog.click2call.manager.UserManager;
import uom.dialog.click2call.manager.UserTypeManager;
import uom.dialog.click2call.translator.FormToDomainTranslator;
import uom.dialog.click2call.utils.Constants;
import uom.dialog.click2call.dto.MyProfileDTO;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
@Controller
public class BlackListController {
        private static final String BLACKLIST = "blacklist";
        //private static final String VIEW_USER = "viewUser";
        //private static final String USER_DASHBOARD ="user_dashboard";
        @Autowired
        UserManager userManager;
//        @Autowired
//        UserTypeManager userTypeManager;
        @Autowired
        CompanyManager companyManager;
        

    List blacklist = new ArrayList();

    @RequestMapping(value = "/blacklist", method = RequestMethod.GET)
    public String showBlacklist(HttpServletRequest request, Map map, Model model){
        try {
            HttpSession session = request.getSession();
            session.setAttribute("MenuTab", "BlackList");
            session.setAttribute("SubMenu", "Blacklisted numbers");
            blacklist=null;
            Users admin = userManager.findUserByUserNameAndCompany((String)request.getSession().getAttribute("userName"), (String)request.getSession().getAttribute("company"));            
            blacklist = companyManager.findBlackListByCompanyId(admin.getCompany().getCompanyId());

            if(!blacklist.isEmpty()){
            map.put(BLACKLIST, blacklist);
            System.out.println("inside if : ");
            return BLACKLIST;
            }
            else{
            model.addAttribute(Constants.ERRORMESSAGE,"Blacklist is empty");
            return BLACKLIST;
            }
        } catch (Click2CallException ex) {
            Logger.getLogger(BlackListController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE,Constants.PROCESS_FAIL+ex.getMessage());
            return Constants.ERROR;
        } catch (Exception ex) {
            Logger.getLogger(BlackListController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute(Constants.ERRORMESSAGE, Constants.PROCESS_FAIL + ex.getMessage());
            return Constants.ERROR;
        }
    }



        @RequestMapping(value = "/blacklist", method = RequestMethod.POST)
        public String submitblacklist(HttpServletRequest request,Map map, Model model)
           {
                  
               String action = request.getParameter("action");                  
            try {
                Users admin = userManager.findUserByUserNameAndCompany((String)request.getSession().getAttribute("userName"), (String)request.getSession().getAttribute("company"));
                String number =request.getParameter("newNumber");
      
                if(action.equalsIgnoreCase("A")){
                    Blacklist checkBL = companyManager.findBlacklistRecordByNumber(number, admin.getCompany());
                    if(checkBL==null){
                    Blacklist newNumber = new Blacklist();
                    newNumber.setCompany(admin.getCompany());
                    newNumber.setNumber(number);
                    companyManager.addNewBlackListNumber(newNumber);
                    blacklist = companyManager.findBlackListByCompanyId(admin.getCompany().getCompanyId());
                    }
                    else{
                    model.addAttribute(Constants.ERRORMESSAGE, "Number already exists in the Blacklist");
                    }

                }                
                else if(action.equalsIgnoreCase("D")){
                Blacklist deleteNumber;
                deleteNumber = companyManager.findBlacklistRecordByNumber(number, admin.getCompany());
                companyManager.deleteBlackListNumber(deleteNumber);
                blacklist = companyManager.findBlackListByCompanyId(admin.getCompany().getCompanyId());
                }
                if(!blacklist.isEmpty()){
                map.put(BLACKLIST, blacklist);
                }
                else{
                model.addAttribute(Constants.ERRORMESSAGE, "Blacklist is empty");
                }
                return BLACKLIST;
            } catch (Click2CallException ex) {
                Logger.getLogger(BlackListController.class.getName()).log(Level.SEVERE, null, ex);
                model.addAttribute(Constants.ERRORMESSAGE,Constants.PROCESS_FAIL+ex.getMessage());
                return Constants.ERROR;
            }
              
     }

    private List getBlacklist(){
        System.out.println("get array list");
        /*HttpSession session = request.getSession();
        session.setAttribute("MenuTab", "BlackList");
        session.setAttribute("SubMenu", "Show Blacklist numbers");
*/
        List blacklist = new ArrayList();

            //for(int i=0; i<userList.size(); i++){
            BlackListDTO  blist = new BlackListDTO();
            blist.setBid(1);
            blist.setNumber("Hello");
            blacklist.add(blist);

           // }

            blist.setBid(2);
            blist.setNumber("Hello1");
            blacklist.add(blist);

             //map.put("blacklist", blacklist);

        return blacklist;
    }




}


