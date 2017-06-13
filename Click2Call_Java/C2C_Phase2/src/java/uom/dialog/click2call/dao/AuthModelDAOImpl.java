/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dao;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.data.AuthModel;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
@Component
public class AuthModelDAOImpl extends GenericDAOImpl <AuthModel, String> implements AuthModelDAO {
 public AuthModel findAuthModelById(Integer id){
 
     AuthModel model=null;
     
     return model;
 }
 public List<AuthModel> loadAllAuthModels(){
     
     List<AuthModel> allAuthModels=null;
     //int status = 0;
     //String sql = "FROM AuthModel a WHERE a.authId <> :status";
     //Query query = HibernateUtil.getSession().createQuery(sql).setParameter("status", status);
     //allAuthModels=findMany(query);
     allAuthModels = findAll(AuthModel.class);
     return allAuthModels;
 }
}
