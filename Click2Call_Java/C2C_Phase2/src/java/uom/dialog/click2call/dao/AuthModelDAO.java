/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dao;

import java.util.List;
import uom.dialog.click2call.data.AuthModel;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
public interface AuthModelDAO extends GenericDAO<AuthModel, String> {
    public AuthModel findAuthModelById (Integer id);
    public List<AuthModel> loadAllAuthModels();
}
