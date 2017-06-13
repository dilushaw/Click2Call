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

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.data.MasterData;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 * MasterDAO.java (UTF-8) Apr 9, 2013, 3:33:56 PM
 *
 * @author Dewmini
 */
@Component
public class MasterDAOImpl extends GenericDAOImpl<MasterData, Integer> implements MasterDAO {

    /**
     * get next transaction_id value. For passing the transaction_id value for the Dialog Charging
     * Gateway(CG), transaction id should be unique.So master_data table will maintain the next
     * transaction _id to be used. when using this id this should be append with some other String
     * as it can be conflict with other transaction id whic is used for performCreditCheck in Dialog CG.
     *
     * @return - row of the master_data table matching type=chargebill
     */
    public MasterData findTransIdByType(String type) {
        MasterData masterData = null;
        String hql = "From MasterData m where m.type=:type";
        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("type", type);
        masterData = findOne(query);
        return masterData;
    }
}
