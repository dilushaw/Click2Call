/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.manager;

import java.util.List;
import uom.dialog.click2call.data.Users;
import uom.dialog.click2call.dto.MyProfileDTO;
import uom.dialog.click2call.dto.UserDTO;
import uom.dialog.click2call.exception.Click2CallException;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
public interface UserManager {
    public void saveUser(Users user);
    public void deleteUser(Users user);
    void updateUser(UserDTO editUser) throws Click2CallException;
    void updateMyProfile(MyProfileDTO myProfileDTO) throws Click2CallException;
    Users loadLoginUser(Users user)throws Click2CallException;
    
    public Users findUserByID(Number id) throws Click2CallException;
    public Users findUserByUserNameAndCompany(String userName, String company) throws Click2CallException;
    List<Users> loadAllUsers() throws Click2CallException;
    public Users findUserByUserId(Integer userId) throws Click2CallException;

    public Users findUserByEmailAndCompanyId(String email, Integer companyId) throws Click2CallException;
}
