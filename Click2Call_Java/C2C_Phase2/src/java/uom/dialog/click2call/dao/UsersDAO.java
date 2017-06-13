/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dao;

import java.util.List;
import uom.dialog.click2call.data.*;


/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
public interface UsersDAO extends GenericDAO<Users,String> {
     public void saveUser(Users user);
     public void deleteUser(Users user);
     public void updateUser(Users user);
     public void updateMyProfile(Users user);
     public Users findLoginUser(String userName, String password, String company);
     
     public Users findUserByUserNameAndCompany(String userName, String company);
     public Users findUserByCompanyandUsername(Company company,String userName);
     public List<Users> loadAllUsers();
     public Users findUserByUserId(Integer userId);
     public List<Users> findUsersByCompanyIdAndUserType(Integer companyId,Integer userType);

    public Users findUserByEmailAndCompanyId(String email, Integer companyId);
}
