/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.data.*;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
@Component
public class UsersDAOImpl extends GenericDAOImpl<Users, String> implements UsersDAO {
    public void saveUser(Users user){
    
        save(user);
        System.out.println("Saved User Successfully");
    }
    public void deleteUser(Users user){
        delete(user);
        System.out.println("User Deleted");
    }
    public void updateUser(Users user){
         update(user);
    }
    public void updateMyProfile(Users user){
    }
     public Users findLoginUser(String userName, String password, String company) {
        Users user = null;
        String sql = "FROM Users u WHERE u.userName = :userName AND u.password = :password AND u.company.companyName = :comp";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("userName", userName).setParameter("password", password).setParameter("comp", company);
        user = findOne(query);
        if (user != null) {
            Hibernate.initialize(user.getUserType());
        }
        return user;
    }
    //load user for given username, company name (eg. to find logged in user)
    public Users findUserByUserNameAndCompany(String userName, String company) {
        Users user = null;
        String hql = "From Users u where u.userName=:userName and u.company.companyName=:company";
        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("userName", userName).setParameter("company", company);
        user = findOne(query);
        return user;
    }
    public Users findUserByCompanyandUsername(Company company,String userName){
         Users user = null;
         String hql = "From Users u where u.company = :company and u.userName=:userName ";
       
         Query query = HibernateUtil.getSession().createQuery(hql).setParameter("company", company).setParameter("userName", userName);
        
         user = findOne(query);
        
         return user;
 }
    public List<Users> loadAllUsers(){
         //String sql = "FROM Users u WHERE u.companyStatus != :status and c.companyName not in (:account1,:account2)";
         //Query query = HibernateUtil.getSession().createQuery(sql).setParameter("status", 3).setParameter("account1", "Click2Call").setParameter("account2", "System");
         List<Users> allUsers = findAll(Users.class);
         //List<Users> allUsers = findMany(query);
         return allUsers;
    }
    public Users findUserByUserId(Integer userId){
    
        Users user = null;
        String hql = "From Users u where u.userId="+userId;
        Query query = HibernateUtil.getSession().createQuery(hql);
        user = findOne(query);
        return user;
    }
    
     public List<Users> findUsersByCompanyIdAndUserType(Integer companyId,Integer userType){
         List<Users> users = null;
         String hql = "From Users u where u.company.companyId=:companyId and u.userType.typeId=:typeId";
        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("companyId", companyId).setParameter("typeId", userType);
        users = findMany(query);
         return users;
     }

    public Users findUserByEmailAndCompanyId(String email, Integer companyId) {
        Users user = null;
        String hql = "From Users u where u.email=:email and u.company.companyId=:companyId";
        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("email", email).setParameter("companyId", companyId);
        user = findOne(query);
        return user;
    }
}
