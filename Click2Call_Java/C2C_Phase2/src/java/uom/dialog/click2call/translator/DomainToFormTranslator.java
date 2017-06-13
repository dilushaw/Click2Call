/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.translator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.data.*;
import uom.dialog.click2call.dto.*;
import uom.dialog.click2call.utils.Constants;

/**
 *
 * @author Hasala ©Dialog - University of Moratuwa Mobile Communications Research Laboratory Methods
 * in this class translate the database information into JSP pages
 */
@Component
public class DomainToFormTranslator {

    @Autowired
    AgentDTO agentDTO;
    @Autowired
    UserDTO userDTO;
    @Autowired
    CompanyDTO companyDTO;
    @Autowired
    MyProfileDTO myProfileDTO;
    @Autowired
    ChargingPlanDTO cplanDTO;
    //Method to translate data to Edit My Profile for Logged User

    public MyProfileDTO translateMyProfile(Users user) {
        myProfileDTO.setUserId(user.getUserId());
        myProfileDTO.setFullName(user.getFullName());
        myProfileDTO.setEmail(user.getEmail());
        myProfileDTO.setPassword(user.getPassword());
        myProfileDTO.setPhone(user.getPhone());
        return myProfileDTO;
    }
    //Currently Not in use

    public UserProfileDTO translateUserProfile(Company company, Users user) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(user.getUserName());
        userProfileDTO.setEmail(user.getEmail());
        return userProfileDTO;
    }
    //Method to translate data to Edit Agent for Company Admin

    public AgentDTO translateDataToEditAgent(Agents agent) {
        agentDTO.setCompanyName(agent.getCompany().getCompanyName());
        agentDTO.setAgentName(agent.getName());
        agentDTO.setAgentStatus(agent.getStatus());
        agentDTO.setAgentNumber(agent.getNumber());
        agentDTO.setTotalMinutes(agent.getTotalMins());
        agentDTO.setAgentId(agent.getAgentId());
        //System.out.println("I found agent ID========"+agent.getAgentId());
        agentDTO.setCompany(agent.getCompany());
        agentDTO.setAgentKey(agent.getAgentKey());
        agentDTO.setCompanyId(agent.getCompany().getCompanyId());
        return agentDTO;
    }
    //Method to translate data to Edit User for Dialog Admin

    public UserDTO translateDataToEditUser(Users user) {
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setUserType(user.getUserType().getTypeName());
        userDTO.setCompanyName(user.getCompany().getCompanyName());
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setStatus(user.getUserStatus());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
    //Method to translate data to Edit Company for Dialog Admin

    public CompanyDTO translateDataToEditCompany(Company company) {
        companyDTO.setCompanyId(company.getCompanyId());
        companyDTO.setCompanyName(company.getCompanyName());
        companyDTO.setContactPersonName(company.getContactName());
        companyDTO.setContactNumber(company.getContactNumber());
        companyDTO.setNumberOfAgents(company.getMaxAgents());
        companyDTO.setSecretKey(company.getSecretKey());
        companyDTO.setCompanyStatus(company.getCompanyStatus());
        companyDTO.setAuthModel(company.getAuthModel());
        companyDTO.setAuthId(company.getAuthModel().getAuthId());
        companyDTO.setPlanId(company.getChargingPlan().getPlanId());
        companyDTO.setChargingPlan(company.getChargingPlan());
        companyDTO.setEmail(company.getEmail());
        //Converting Business Times
        DateFormat formatter = new SimpleDateFormat("hh.mm aa");
        if (company.getStartTime() != null) {
            companyDTO.setBusinessStartTime(formatter.format(company.getStartTime()));
        }
        if (company.getEndTime() != null) {
            companyDTO.setBusinessEndTime(formatter.format(company.getEndTime()));
        }
        //
        return companyDTO;
    }

    public ChargingPlanDTO translateDataToEditCPlan(ChargingPlan cplan) {
        //int ctype = cplan.getChargingType().getTypeId();
        cplanDTO.setPlanName(cplan.getName());
        cplanDTO.setChargingTypeId(cplan.getChargingType().getTypeId());
        cplanDTO.setTypeName(cplan.getChargingType().getTypeName());
        
        cplanDTO.setMonthlyRental(cplan.getMonthlyRental());
            cplanDTO.setAllocatedMinutes(cplan.getAllocatedMinutes());
            cplanDTO.setPerMinCharge(cplan.getPerminuteCharge());
//        if (ctype == Constants.RENTAL_ID) {
//            cplanDTO.setMonthlyRental(cplan.getMonthlyRental());
//            cplanDTO.setAllocatedMinutes(cplan.getAllocatedMinutes());
//        }
//        if (ctype == Constants.CALL_CHARGE_ID) {
//            cplanDTO.setMonthlyRental(cplan.getMonthlyRental());
//            cplanDTO.setAllocatedMinutes(cplan.getAllocatedMinutes());
//            cplanDTO.setPerMinCharge(cplan.getPerminuteCharge());
//        }
//        if (ctype == Constants.REANTAL_AND_CALL_ID) {
//            cplanDTO.setPerMinCharge(cplan.getPerminuteCharge());
//        }
        return cplanDTO;
    }
}
