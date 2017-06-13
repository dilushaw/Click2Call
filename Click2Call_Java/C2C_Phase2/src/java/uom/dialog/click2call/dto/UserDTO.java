/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dto;

import org.springframework.stereotype.Component;
import uom.dialog.click2call.data.Company;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
@Component
public class UserDTO{
  
    private String userName;
    private String userType;
    private String fullName;
    private String email;
    private String phone;
    private String companyName;
    private Integer status;
    private String password;
    private String passwordReType;
    //private Company company;
    private Integer typeId;
    private String typeName;
    private Integer companyId;
    private String pwchange;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    
    public String getPwchange() {
        return pwchange;
    }

    public void setPwchange(String pwchange) {
        this.pwchange = pwchange;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserType(){
        return userType;
    }
    public void setUserType(String userType){
        this.userType = userType;
    }
    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public Integer getStatus(){
        return status;
    }
    public void setStatus(Integer status){
        this.status = status;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getPasswordReType() {
        return passwordReType;
    }

    public void setPasswordReType(String passwordReType) {
        this.passwordReType = passwordReType;
    }

    /*public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }*/

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    
}
