/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dao;

import java.util.List;
import uom.dialog.click2call.data.UserType;
import uom.dialog.click2call.exception.Click2CallException;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
public interface UserTypeDAO extends GenericDAO<UserType, String> {
    
    public UserType findUserTypeByID(String roleId) throws Click2CallException;
    public List<UserType> findAllUserTypes() throws Click2CallException;
}
