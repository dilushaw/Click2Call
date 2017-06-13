/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory. All
 * Rights Reserved. This software is the proprietary information of Dialog-University of Moratuwa
 * Mobile Communications Research Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the
 * software as it sees fit.
 *
 */
package uom.dialog.click2call.manager;

import java.util.List;
import uom.dialog.click2call.data.Blacklist;
import uom.dialog.click2call.data.Company;
import uom.dialog.click2call.exception.Click2CallException;

/**
 * BlackListManager.java (UTF-8) Apr 4, 2013, 3:54:03 PM
 *
 * @author Dewmini
 */
public interface BlackListManager {

    List<Blacklist> findBlackListByCompanyId(Integer companyId) throws Click2CallException;

    Boolean checkNumberIsBlackListed(String Number, Company company) throws Click2CallException;
}
