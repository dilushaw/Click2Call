/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.manager;

import java.util.List;
import uom.dialog.click2call.data.AuthModel;
import uom.dialog.click2call.exception.Click2CallException;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
public interface AuthModelManager {
         List<AuthModel> findAllAuthModels() throws Click2CallException;
         AuthModel findAuthModelById(Integer authId)throws Click2CallException;
}
