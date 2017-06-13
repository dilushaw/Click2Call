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
import uom.dialog.click2call.dao.AuthModelDAO;
import uom.dialog.click2call.dao.AuthModelDAOImpl;
import uom.dialog.click2call.data.AuthModel;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 *
 * @author Hasala ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
@Component
public class AuthModelManagerImpl implements AuthModelManager {

    @Autowired
    AuthModelDAO authModelDAO;

    public List<AuthModel> findAllAuthModels() throws Click2CallException {
        List<AuthModel> models = null;
        try {
            models = authModelDAO.loadAllAuthModels();
        } catch (NonUniqueResultException ex) {
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            throw new Click2CallException("Hibernate exception occured", ex);
        }
        return models;
    }

    public AuthModel findAuthModelById(Integer authId) throws Click2CallException {
        AuthModel model = null;
        try {
            model = authModelDAO.findByID(AuthModel.class, authId);
        } catch (NonUniqueResultException ex) {
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            throw new Click2CallException("Hibernate exception occured", ex);
        }
        return model;
    }
}
