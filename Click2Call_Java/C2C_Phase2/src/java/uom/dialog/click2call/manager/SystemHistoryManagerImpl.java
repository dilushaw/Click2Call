/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory. All Rights Reserved. This
 * software is the proprietary information of Dialog-University of Moratuwa Mobile Communications Research
 * Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the software as it sees fit.
 * 
 */
package uom.dialog.click2call.manager;

import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.dao.SystemHistoryDAO;
import uom.dialog.click2call.data.SystemHistory;
import uom.dialog.click2call.data.Users;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 * SystemHistoryManagerImpl.java (UTF-8)
 * Jun 19, 2013, 11:31:11 AM
 *
 * @author Dewmini
 */
@Component
public class SystemHistoryManagerImpl implements SystemHistoryManager{
@Autowired
SystemHistoryDAO systemHistoryDAO;

    public void saveToSystemHistory(String tableName, String changeField, String tableId, String newValue, String oldValue, Users user) {
    SystemHistory sysHistory = new SystemHistory();
        sysHistory.setTableName(tableName);
        sysHistory.setChangeField(changeField);
        sysHistory.setTablePk(tableId);
        sysHistory.setNewValue(newValue);
        sysHistory.setOldValue(oldValue);
        sysHistory.setUsers(user);
        sysHistory.setChangeDate(Calendar.getInstance().getTime());
        HibernateUtil.beginTransaction();
        systemHistoryDAO.save(sysHistory);
        HibernateUtil.commitTransaction();
    }

}
