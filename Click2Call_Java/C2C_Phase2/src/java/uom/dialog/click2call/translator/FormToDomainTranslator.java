/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.translator;

import controllers.CompanyController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.data.Agents;
import uom.dialog.click2call.data.ChargingPlan;
import uom.dialog.click2call.data.ChargingType;
import uom.dialog.click2call.data.Company;
import uom.dialog.click2call.data.Users;
import uom.dialog.click2call.dto.AgentDTO;
import uom.dialog.click2call.dto.ChargingPlanDTO;
import uom.dialog.click2call.dto.CompanyDTO;
import uom.dialog.click2call.dto.LoginDTO;
import uom.dialog.click2call.dto.UserDTO;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
@Component
public class FormToDomainTranslator {
     
    public Users translateLoginFormToUser(LoginDTO loginForm) {

        Users user = new Users();

        user.setUserName(loginForm.getLoginName());
        user.setPassword(loginForm.getLoginPassword());

        user.setCompany(new Company());
        user.getCompany().setCompanyName(loginForm.getLoginCompany());
        //String account = loginForm.getAccount();

        return user;

    }
    //Companies will be created by Dialog Admin
    public Company translateCreateCompanyForm(CompanyDTO createCompanyDTO){
        
        Company newCompany = new Company();
        newCompany.setCompanyName(createCompanyDTO.getCompanyName());
        newCompany.setContactName(createCompanyDTO.getContactPersonName());
        newCompany.setEmail(createCompanyDTO.getEmail());
        newCompany.setCompanyStatus(createCompanyDTO.getCompanyStatus());
        newCompany.setContactNumber(createCompanyDTO.getContactNumber());
        newCompany.setMaxAgents(createCompanyDTO.getNumberOfAgents());
        newCompany.setSecretKey(this.generateCompanyKey());
        newCompany.setUsedSeconds(0);
        //Time formatter
        DateFormat formatter = new SimpleDateFormat("hh.mm aa");
        if (createCompanyDTO.getBusinessStartTime()!=null){
        try{
            newCompany.setStartTime((Date)formatter.parse(createCompanyDTO.getBusinessStartTime()));
        } catch (ParseException ex){
        
             Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        if(createCompanyDTO.getBusinessEndTime()!=null){
        try{
             newCompany.setEndTime((Date)formatter.parse(createCompanyDTO.getBusinessEndTime()));
        }
        catch(ParseException ex){
             Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        //end time formatter     
        return newCompany;
    }
    //Users will be created by Dialog Admin
    public Users translateCreateUserForm(UserDTO createUserDTO){
        
        Users newUser = new Users();
        newUser.setUserName(createUserDTO.getUserName());
        newUser.setPassword(createUserDTO.getPassword());
        newUser.setEmail(createUserDTO.getEmail());
        newUser.setUserStatus(createUserDTO.getStatus());
        newUser.setPhone(createUserDTO.getPhone());
        newUser.setFullName(createUserDTO.getFullName());
        return newUser;
    }
    //Agent will be created by Company Admin
    public Agents translateCreateAgentForm(AgentDTO createAgentDTO){
        
        Agents newAgent = new Agents();
        newAgent.setName(createAgentDTO.getAgentName());
        newAgent.setNumber(createAgentDTO.getAgentNumber());
        newAgent.setStatus(createAgentDTO.getAgentStatus());
        newAgent.setTotalMins(createAgentDTO.getTotalMinutes());
        newAgent.setUsedSeconds(0);
        newAgent.setNextCorrelate(1);
        newAgent.setAgentKey(this.generateAgentKey()+createAgentDTO.getAgentNumber().substring(6)+this.generateAgentKey());
        newAgent.setIframe("http://www.dialog.lk/click2call/"+newAgent.getAgentKey());
        newAgent.setDateCreated(new java.util.Date());
        return newAgent;
    }
    /*Method to generate random character part of Agent Key
     Agent key = Random 3 Characters + Last 4 Digits of Agent Number + Random 3 Characters*/
    public String generateAgentKey(){
        String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String key="";
        Random generator = new Random();
        for (int i=0; i<3; i++) {
        key+=validChars.charAt(generator.nextInt(validChars.length()));
        }
        System.out.println(key);
        return key;
    }
    /*Method to generate company secret key */
    public String generateCompanyKey(){
        String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String key="";
        Random generator = new Random();
        for (int i=0; i<10; i++) {
        key+=validChars.charAt(generator.nextInt(validChars.length()));
        }
        System.out.println(key);
        return key;
    }
    
    public ChargingPlan translateNewChargingPlanForm(ChargingPlanDTO chargingPlanDTO,ChargingType chType){
        ChargingPlan chargingPlan = new ChargingPlan();
        chargingPlan.setName(chargingPlanDTO.getPlanName());
        chargingPlan.setChargingType(chType);
        chargingPlan.setMonthlyRental(chargingPlanDTO.getMonthlyRental());
        chargingPlan.setAllocatedMinutes(chargingPlanDTO.getAllocatedMinutes());
        chargingPlan.setPerminuteCharge(chargingPlanDTO.getPerMinCharge());
        return chargingPlan;
    }
    
    public ChargingPlan translateEditChargingPlanForm(ChargingPlanDTO chargingPlanDTO,ChargingPlan cplan){
        cplan.setMonthlyRental(chargingPlanDTO.getMonthlyRental());
        cplan.setAllocatedMinutes(chargingPlanDTO.getAllocatedMinutes());
        cplan.setPerminuteCharge(chargingPlanDTO.getPerMinCharge());
        return cplan;
    }
}
