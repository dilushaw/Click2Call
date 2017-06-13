/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dao;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.data.UserType;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
@Component
public class UserTypeDAOImpl extends GenericDAOImpl<UserType, String> implements UserTypeDAO {
    
     public UserType findUserTypeByID(String typeId){
        UserType userType = null;
        String hql = "From UserType ut where ut.typeId=:id";
        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("id", typeId);
        userType = findOne(query);
        return userType;
    }
      public List<UserType> findAllUserTypes() throws Click2CallException{
        List<UserType> type;
        //String hql = "From ChargingPlan c where c.planId != :id";
        //Query query = HibernateUtil.getSession().createQuery(hql).setParameter("id", 0);
        //plan = findMany(query);
        type = findAll(UserType.class);
        return type;
    }
}
