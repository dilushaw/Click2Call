/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory. All Rights Reserved. This
 * software is the proprietary information of Dialog-University of Moratuwa Mobile Communications Research
 * Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the software as it sees fit.
 * 
 */
package uom.dialog.click2call.manager;

import uom.dialog.click2call.data.Users;

/**
 * SystemHistoryManager.java (UTF-8)
 * Jun 19, 2013, 11:30:59 AM
 *
 * @author Dewmini
 */
public interface SystemHistoryManager {
void saveToSystemHistory(String tableName, String changeField,String tableId, String newValue, String oldValue, Users user);
}
