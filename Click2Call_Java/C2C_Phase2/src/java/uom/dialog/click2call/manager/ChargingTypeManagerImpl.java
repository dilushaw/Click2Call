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
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.dao.ChargingTypeDAO;
import uom.dialog.click2call.data.ChargingType;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 * ChargingTypeManagerImpl.java (UTF-8) Jun 13, 2013, 11:10:02 AM
 *
 * @author Dewmini
 */
@Component
public class ChargingTypeManagerImpl implements ChargingTypeManager {

    @Autowired
    ChargingTypeDAO chargingTypeDAO;

    public List<ChargingType> findAllChargingTypes() throws Click2CallException {
        List<ChargingType> chargingTypes = null;
        try {

            chargingTypes = chargingTypeDAO.findAll(ChargingType.class);

        } catch (HibernateException ex) {

            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {

            throw new Click2CallException("Exception occured", ex);
        }
        return chargingTypes;
    }

    public ChargingType findChargingTypeById(Integer typeId) throws Click2CallException {
        ChargingType chargingType = null;
        try {

            chargingType = chargingTypeDAO.findByID(ChargingType.class, typeId);

        } catch (HibernateException ex) {

            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {

            throw new Click2CallException("Exception occured", ex);
        }
        return chargingType;
    }
}
