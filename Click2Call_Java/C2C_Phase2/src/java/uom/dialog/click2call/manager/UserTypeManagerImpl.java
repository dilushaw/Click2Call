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
import uom.dialog.click2call.dao.UserTypeDAO;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.utils.HibernateUtil;
import uom.dialog.click2call.data.UserType;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
@Component
public class UserTypeManagerImpl implements UserTypeManager {
    @Autowired
    UserTypeDAO userTypeDAO;
     public List<UserType> findAllUserTypes() throws Click2CallException{
      List<UserType> types = null;
        
        try {
        
        types = userTypeDAO.findAllUserTypes();
        
        
        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {

        throw new Click2CallException("Hibernate exception occured", ex);
        }
        
        return types;
     }
     public UserType findUserTypeById(Integer typeId)throws Click2CallException{
         UserType type = null;
     
     try {
            
            type = userTypeDAO.findByID(UserType.class, typeId);
        
        
        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {

            throw new Click2CallException("Hibernate exception occured", ex);
        }
        return type;
     }
}
