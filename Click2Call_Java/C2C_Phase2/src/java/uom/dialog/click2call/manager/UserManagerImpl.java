/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.manager;

import java.util.List;
import javax.persistence.NonUniqueResultException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.dao.UsersDAO;
import uom.dialog.click2call.dao.UsersDAOImpl;
import uom.dialog.click2call.data.Company;
import uom.dialog.click2call.data.Users;
import uom.dialog.click2call.dto.MyProfileDTO;
import uom.dialog.click2call.dto.UserDTO;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 *
 * @author Hasala ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
@Component
public class UserManagerImpl implements UserManager {

    @Autowired
    private UsersDAO usersDAO;

    public void saveUser(Users user) {
        try {
            HibernateUtil.beginTransaction();
            UsersDAO usersDAO = new UsersDAOImpl();
            usersDAO.saveUser(user);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();

        }
    }

    public void updateUser(UserDTO editUser) throws Click2CallException {

        Users user = this.findUserByUserId(editUser.getUserId());
        
        user.setFullName(editUser.getFullName());
        user.setEmail(editUser.getEmail());
        user.setPhone(editUser.getPhone());
        user.setUserStatus(editUser.getStatus());
        //System.out.println("Inside User Manager : "+editUser.getPassword());
        if (!(editUser.getPwchange() == null)) {
            if (!(editUser.getPassword().equalsIgnoreCase(""))) {
                user.setPassword(editUser.getPassword());
            }
        }
        HibernateUtil.beginTransaction();
        usersDAO.updateUser(user);
        HibernateUtil.commitTransaction();
    }

    public void deleteUser(Users user) {
        try {
            HibernateUtil.beginTransaction();
            UsersDAO usersDAO = new UsersDAOImpl();
            usersDAO.delete(user);

            HibernateUtil.commitTransaction();
        } catch (NonUniqueResultException ex) {
            ex.printStackTrace();
            

        } catch (HibernateException ex) {
            ex.printStackTrace();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            

        }
    }

    public void updateMyProfile(MyProfileDTO myProfileDTO) throws Click2CallException {

        Users user = this.findUserByUserId(myProfileDTO.getUserId());
        
        user.setFullName(myProfileDTO.getFullName());
        user.setEmail(myProfileDTO.getEmail());
        user.setPhone(myProfileDTO.getPhone());
        System.out.println("Pass : " + myProfileDTO.getPassword());
        System.out.println("ReType Pass : " + myProfileDTO.getReTypePassword());
        if (!(myProfileDTO.getPwchange() == null)) {
            if (!(myProfileDTO.getPassword().equalsIgnoreCase(""))) {
                user.setPassword(myProfileDTO.getPassword());
            }
        }
        HibernateUtil.beginTransaction();
        usersDAO.update(user);
        HibernateUtil.commitTransaction();
    }

    public Users loadLoginUser(Users user) throws Click2CallException {

        Users loginUser = null;
        try {
            
            UsersDAO usersDAO = new UsersDAOImpl();
            loginUser = usersDAO.findLoginUser(user.getUserName(), user.getPassword(), user.getCompany().getCompanyName());
            

        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            throw new Click2CallException("Database exception occured", ex);
        }
        return loginUser;

    }

    public Users findUserByID(Number id) throws Click2CallException {
        Users user = null;
        try {
            
            UsersDAO usersDAO = new UsersDAOImpl();
            user = usersDAO.findByID(Users.class, id);
            

        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            throw new Click2CallException("Database exception occured", ex);
        }
        return user;
    }

    public Users findUserByUserNameAndCompany(String userName, String company) throws Click2CallException {
        Users user = null;
        try {
            
            user = usersDAO.findUserByUserNameAndCompany(userName, company);
            

        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            throw new Click2CallException("Database exception occured", ex);
        }
        return user;
    }

    public List<Users> loadAllUsers() throws Click2CallException {
        List<Users> users = null;
        try {
            
            users = usersDAO.loadAllUsers();
            
            
        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {

            throw new Click2CallException("Hibernate exception occured", ex);
        }
        
        return users;
    }

    public Users findUserByUserId(Integer userId) throws Click2CallException {

        Users user = null;
        try {
            
            user = usersDAO.findUserByUserId(userId);
            
        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            
            throw new Click2CallException("Hibernate exception occured", ex);
        }
        return user;
    }

    public Users findUserByEmailAndCompanyId(String email, Integer companyId) throws Click2CallException {
        Users user = null;
        try {
            
            user = usersDAO.findUserByEmailAndCompanyId(email,companyId);
            
        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            
            throw new Click2CallException("Hibernate exception occured", ex);
        }
        return user;
    }
}