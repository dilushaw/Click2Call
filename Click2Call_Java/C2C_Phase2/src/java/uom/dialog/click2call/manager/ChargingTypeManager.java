/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory. All Rights Reserved. This
 * software is the proprietary information of Dialog-University of Moratuwa Mobile Communications Research
 * Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the software as it sees fit.
 * 
 */
package uom.dialog.click2call.manager;

import java.util.List;
import uom.dialog.click2call.data.ChargingType;
import uom.dialog.click2call.exception.Click2CallException;

/**
 * ChargingTypeManager.java (UTF-8)
 * Jun 13, 2013, 11:09:41 AM
 *
 * @author Dewmini
 */
public interface ChargingTypeManager {
List<ChargingType> findAllChargingTypes() throws Click2CallException;
ChargingType findChargingTypeById(Integer typeId) throws Click2CallException;
}
