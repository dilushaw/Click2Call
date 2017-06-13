/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory. All
 * Rights Reserved. This software is the proprietary information of Dialog-University of Moratuwa
 * Mobile Communications Research Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the
 * software as it sees fit.
 *
 */
package uom.dialog.click2call.dao;

import uom.dialog.click2call.data.MasterData;

/**
 * MasterDAO.java (UTF-8) Apr 10, 2013, 11:54:22 AM
 *
 * @author Dewmini
 */
public interface MasterDAO extends GenericDAO<MasterData, Integer> {

    MasterData findTransIdByType(String type);
}
