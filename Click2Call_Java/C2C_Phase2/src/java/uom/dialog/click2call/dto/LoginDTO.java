/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dto;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
public class LoginDTO {
 
    private String loginName;
    private String loginPassword;
    private String loginCompany;

    public String getLoginCompany() {
        return loginCompany;
    }

    public void setLoginCompany(String loginCompany) {
        this.loginCompany = loginCompany;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
 
}
