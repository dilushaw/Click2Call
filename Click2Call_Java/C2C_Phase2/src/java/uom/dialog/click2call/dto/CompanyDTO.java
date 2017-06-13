/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dto;

import org.springframework.stereotype.Component;
import uom.dialog.click2call.data.AuthModel;
import uom.dialog.click2call.data.ChargingPlan;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
@Component
public class CompanyDTO{
    
    private String companyName; // Name of the Company
    private String companyNumber; // Contact Number of the Company
    private String contactPersonName; // Name of the Contact Person
    private String contactNumber; // Contact Number of the Contact Person
    private Integer companyStatus; // Company Status
    private String numberOfAgents; // Max number of Agents
    private String startTime; // Business Start Time
    private String endTime; // Business End Time
    private ChargingPlan chargingPlan; //Call Charging Plan
    private AuthModel authModel; // Public User Capcha Type
    private Integer planId; //Charging plan
    private String planName;
    private Integer authId;
    private String authName;
    private Integer companyId;
    private String secretKey;
    private String email;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    
    public String getCompanyName(){
        return companyName;
    }
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
      public String getCompanyNumber(){
        return companyNumber;
    }
    public void setCompanyNumber(String companyNumber){
        this.companyNumber = companyNumber;
    }
    public String getContactPersonName(){
        return contactPersonName;
    }
    public void setContactPersonName(String contactPersonName){
        this.contactPersonName = contactPersonName;
    }
      public String getContactNumber(){
        return contactNumber;
    }
    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
    }
    public Integer getCompanyStatus(){
        return companyStatus;
    }
    public void setCompanyStatus(Integer companyStatus){
        this.companyStatus = companyStatus;
    }
    public String getNumberOfAgents(){
        return numberOfAgents;
    }
    public void setNumberOfAgents(String numberOfAgents){
        this.numberOfAgents = numberOfAgents;
    }
     public String getBusinessStartTime(){
        return startTime;
    }
    public void setBusinessStartTime(String startTime){
        this.startTime = startTime;
    }
     public String getBusinessEndTime(){
        return endTime;
    }
    public void setBusinessEndTime(String endTime){
        this.endTime = endTime;
    }

    public AuthModel getAuthModel() {
        return authModel;
    }

    public void setAuthModel(AuthModel authModel) {
        this.authModel = authModel;
    }

    public ChargingPlan getChargingPlan() {
        return chargingPlan;
    }

    public void setChargingPlan(ChargingPlan chargingPlan) {
        this.chargingPlan = chargingPlan;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
