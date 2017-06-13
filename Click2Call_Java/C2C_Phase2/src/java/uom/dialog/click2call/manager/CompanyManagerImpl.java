/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.manager;

import controllers.CompanyController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NonUniqueResultException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.dao.BlackListDAO;
import uom.dialog.click2call.dao.CallDAO;
import uom.dialog.click2call.dao.CompanyDAO;
import uom.dialog.click2call.dao.SystemHistoryDAO;
import uom.dialog.click2call.data.Blacklist;
import uom.dialog.click2call.data.Company;
import uom.dialog.click2call.data.SystemHistory;
import uom.dialog.click2call.data.Users;
import uom.dialog.click2call.dto.CompanyDTO;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 *
 * @author Hasala ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
@Component
public class CompanyManagerImpl implements CompanyManager {

    @Autowired
    CompanyDAO companyDAO;
    @Autowired
    BlackListDAO blacklistDAO;
    @Autowired
    private ChargingPlanManager chargingPlanManager;
    @Autowired
    private AuthModelManager authModelManager;
    @Autowired
    CallDAO callDAO;
    @Autowired
    SystemHistoryManager systemHistoryManager;

    public void saveCompany(Company company) throws Click2CallException {
        try {
            HibernateUtil.beginTransaction();
            //CompanyDAO companyDAO = new CompanyDAOImpl();
            companyDAO.saveCompany(company);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            
            throw new Click2CallException("Exception occured", ex);
        }
    }

    public void simpleUpdate(Company company) throws Click2CallException {
        try {
            HibernateUtil.beginTransaction();
            //CompanyDAO companyDAO = new CompanyDAOImpl();
            companyDAO.update(company);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            
            throw new Click2CallException("Exception occured", ex);
        }
    }

    public void updateCompany(CompanyDTO editCompany, Users user) throws Click2CallException {
        try {
            
            Company company = this.findCompanyByID(editCompany.getCompanyId());
            String oldNumber = company.getContactNumber();
            String newNumber = editCompany.getContactNumber();
            String companyId = company.getCompanyId().toString();
            Integer oldPlanId = company.getChargingPlan().getPlanId();
            Integer newPlanId = editCompany.getPlanId();

            
            if (!newNumber.equalsIgnoreCase(oldNumber)) {
                systemHistoryManager.saveToSystemHistory("company", "contact_number", companyId, newNumber, oldNumber, user);
            }
            if (!newPlanId.equals(oldPlanId)) {
                systemHistoryManager.saveToSystemHistory("company", "plan_id", companyId, newPlanId.toString(), oldPlanId.toString(), user);
            }

            company.setCompanyStatus(editCompany.getCompanyStatus());
            company.setContactName(editCompany.getContactPersonName());
            company.setContactNumber(editCompany.getContactNumber());
            company.setEmail(editCompany.getEmail());
            company.setMaxAgents(editCompany.getNumberOfAgents());
            company.setChargingPlan(chargingPlanManager.findChargingPlanById(editCompany.getPlanId()));
            company.setAuthModel(authModelManager.findAuthModelById(editCompany.getAuthId()));
            //Time formatter
            DateFormat formatter = new SimpleDateFormat("hh.mm aa");
            if (editCompany.getBusinessStartTime() != null) {
                company.setStartTime((Date) formatter.parse(editCompany.getBusinessStartTime()));
            }
            if (editCompany.getBusinessEndTime() != null) {
                company.setEndTime((Date) formatter.parse(editCompany.getBusinessEndTime()));
            }
            //end time formatter
            
            HibernateUtil.beginTransaction();
            companyDAO.update(company);
            HibernateUtil.commitTransaction();
        } catch (ParseException ex) {
            ex.printStackTrace();
            
            throw new Click2CallException("ParseException occured", ex);
        } catch (HibernateException ex) {
            ex.printStackTrace();
            
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            
            throw new Click2CallException("Exception occured", ex);
        }
    }

    public void updateCompanyUsedmins(Integer companyId, int usedSec) throws Click2CallException {

        Company company = this.findCompanyByID(companyId);

        
        int oldUsedSec = company.getUsedSeconds();
        company.setUsedSeconds(usedSec + oldUsedSec);
        System.out.println(oldUsedSec + "used mints");
        HibernateUtil.beginTransaction();
        companyDAO.update(company);
        HibernateUtil.commitTransaction();
    }

    public List<Company> loadAllCompanies() throws Click2CallException {
        List<Company> companies = null;
        try {

            

            companies = companyDAO.loadAllCompanies();
            

        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {

            throw new Click2CallException("Hibernate exception occured", ex);
        }

        return companies;
    }

    public List<Company> findAllCompanies() throws Click2CallException {
        List<Company> companies = null;
        try {
            
            companies = companyDAO.findAllCompanies();
            

        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {

            throw new Click2CallException("Hibernate exception occured", ex);
        }

        return companies;
    }

    public Company findCompanyByName(String companyname) throws Click2CallException {
        Company company = null;
        try {
            
            company = companyDAO.findCompanyByName(companyname);

            
        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {

            throw new Click2CallException("Hibernate exception occured", ex);
        }
        return company;

    }

    public Company findCompanyByID(Integer id) throws Click2CallException {
        Company company = null;
        try {
            
            company = companyDAO.findByID(Company.class, id);

            
        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {

            throw new Click2CallException("Hibernate exception occured", ex);
        }
        return company;
    }

    public List<Blacklist> findBlackListByCompanyId(Integer companyId) throws Click2CallException {
        List<Blacklist> blacklist;
        try {
            
            blacklist = blacklistDAO.findBlackListByCompanyId(companyId);
            
        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            throw new Click2CallException("Hibernate exception occured", ex);
        }
        return blacklist;
    }

    public void addNewBlackListNumber(Blacklist number) {
        try {
            HibernateUtil.beginTransaction();
            blacklistDAO.save(number);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();

        }
    }

    public void deleteBlackListNumber(Blacklist blacklist) {
        try {
            HibernateUtil.beginTransaction();
            blacklistDAO.delete(blacklist);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    public Blacklist findBlacklistRecordByNumber(String number, Company company) throws Click2CallException {
        Blacklist blacklist;
        try {
            
            blacklist = blacklistDAO.findBlacklistRecordByNumber(number, company);
            
        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            throw new Click2CallException("Hibernate exception occured", ex);
        }
        return blacklist;
    }

    /**
     * When agent phone number(unique within the system) is given it will return the Company which
     * agent belongs to. added by Dewmini
     *
     * @param number - agent's phone number
     * @return
     */
//    public Company findCompanyByAgentNumber(String number) throws Click2CallException{
//        Company company = null;
//        try {
//            
//            company = companyDAO.findCompanyByAgentNumber(number);
//            
//        } catch (NonUniqueResultException ex) {
//            
//            throw new Click2CallException("More result found", ex);
//        } catch (HibernateException ex) {
//            
//            throw new Click2CallException("Hibernate exception occured", ex);
//        }
//        return company;
//    }
    public Company findCompanyByEmail(String email) throws Click2CallException {
        Company company = null;
        try {
            
            company = companyDAO.findCompanyByEmail(email);
            

        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            
            throw new Click2CallException("Hibernate exception occured", ex);
        }
        return company;
    }

    public Map<Company, Integer> getCompanyAllCalls() throws Click2CallException {
        Map<Company, Integer> companyCallsMap = new HashMap<Company, Integer>();
        List<Company> companies = companyDAO.loadAllCompanies();
        for (Company company : companies) {

            companyCallsMap.put(company, callDAO.findCallsByCompanyId(company.getCompanyId()).size());
        }
        return companyCallsMap;
    }

    public Map<Company, Integer> getCompanyCallsHistory(Date fromDate, Date toDate) throws Click2CallException {
        Map<Company, Integer> companyCallsMap = new HashMap<Company, Integer>();
        List<Company> companies = companyDAO.loadAllCompanies();
        for (Company company : companies) {

            companyCallsMap.put(company, callDAO.findCallsByCompanyIdAndDateRange(company.getCompanyId(), fromDate, toDate).size());
        }
        return companyCallsMap;
    }

    public Company findCompanyByPhoneNumber(String phoneNumber) throws Click2CallException {
        Company company = null;
        try {
            
            company = companyDAO.findCompanyByPhoneNumber(phoneNumber);
            

        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            
            throw new Click2CallException("Hibernate exception occured", ex);
        }
        return company;
    }
//    private void saveToSystemHistory(String tableName, String changeField,String companyId, String newNumber, String oldNumber, Users user) {
//        SystemHistory sysHistory = new SystemHistory();
//        sysHistory.setTableName(tableName);
//        sysHistory.setChangeField(changeField);
//        sysHistory.setTablePk(companyId);
//        sysHistory.setNewValue(newNumber);
//        sysHistory.setOldValue(oldNumber);
//        sysHistory.setUsers(user);
//        sysHistory.setChangeDate(Calendar.getInstance().getTime());
//        systemHistoryDAO.save(sysHistory);
//    }
}
