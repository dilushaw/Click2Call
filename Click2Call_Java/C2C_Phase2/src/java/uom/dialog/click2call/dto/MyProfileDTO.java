/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uom.dialog.click2call.dto;



import javax.validation.constraints.Size;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;
import org.springframework.stereotype.Component;
/**
 *
 * @author DialogLab1
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
@Component
public class MyProfileDTO {

private String fullName;
private String email;
private String phone;
private String password;
private String reTypePassword;
private String pwchange;
private String company;
private Integer userId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPwchange() {
        return pwchange;
    }

    public void setPwchange(String pwchange) {
        this.pwchange = pwchange;
    }

    public String getReTypePassword() {
        return reTypePassword;
    }

    public void setReTypePassword(String reTypePassword) {
        this.reTypePassword = reTypePassword;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
