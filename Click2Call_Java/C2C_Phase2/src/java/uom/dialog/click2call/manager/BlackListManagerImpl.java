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
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.dao.BlackListDAO;
import uom.dialog.click2call.data.Blacklist;
import uom.dialog.click2call.data.Company;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 * BlackListManagerImpl.java (UTF-8) Apr 4, 2013, 3:54:15 PM
 *
 * @author Dewmini
 */
@Component
public class BlackListManagerImpl implements BlackListManager {

    @Autowired
    BlackListDAO blackListDAO;

    public List<Blacklist> findBlackListByCompanyId(Integer companyId) throws Click2CallException {
        List<Blacklist> blackList = null;
        try {
            blackList = blackListDAO.findBlackListByCompanyId(companyId);
        } catch (NonUniqueResultException ex) {
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (HibernateException ex) {
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            throw new Click2CallException("Exception occured", ex);
        }
        return blackList;
    }

    public Boolean checkNumberIsBlackListed(String Number, Company company) throws Click2CallException {
        Boolean chkNumber = false;
        try {
            Blacklist blackList = blackListDAO.findBlacklistRecordByNumber(Number, company);
            if (blackList != null) {
                chkNumber = true;
            }
        } catch (NonUniqueResultException ex) {
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (HibernateException ex) {
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            throw new Click2CallException("Exception occured", ex);
        }
        return chkNumber;
    }
}
