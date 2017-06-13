/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dao;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.data.Company;
import uom.dialog.click2call.data.Users;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 *
 * @author Hasala ©Dialog - University of Moratuwa Mobile Communications
 * Research Laboratory
 */
@Component
public class CompanyDAOImpl extends GenericDAOImpl<Company, Integer> implements CompanyDAO {

    public void saveCompany(Company company) {

        save(company);
    }

    public void updateCompany(Company company) {
        //  Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        // session.beginTransaction();
        //session.save(corporate);
        //getTransaction().commit();
        update(company);
        //System.out.println(corporate.getCorporateStatus());
     

    }

    public List<Company> loadAllCompanies() {

        String sql = "FROM Company c WHERE c.companyStatus != :status and c.companyName not in (:account1,:account2,:account3)";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("status", 3).setParameter("account1", "Click2Call").setParameter("account2", "System").setParameter("account3", "dialogc2c");
        //List<Corporate> allcorporates = findAll(Corporate.class);
        List<Company> allCompanies = findMany(query);
        return allCompanies;
    }

    public List<Company> findAllCompanies() {
        List<Company> companies = findAll(Company.class);
        return companies;
    }

    public Company findCompanyByName(String companyName) {
        Company company = null;
        String hql = "From Company c where c.companyName = :companyName ";

        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("companyName", companyName);

        company = findOne(query);

        return company;
    }

    public List<Company> loadAllCompaniesByStatus(Integer companyStatus) {
        String hql = "FROM Company c WHERE c.companyName not in (:account1,:account2) and  c.companyStatus";
        if (companyStatus == -1) {
            hql += " in(0,1)";
        } else {
            hql += " in(" + companyStatus + ")";
        }
        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("account1", "Click2Call").setParameter("account2", "System");
        List<Company> allCompanies = findMany(query);
        return allCompanies;
    }

    /**
     * When agent phone number(unique within the system) is given it will return
     * the Company which agent belongs to. added by Dewmini
     *
     * @param number - agent's phone number
     * @return
     */
//    public Company findCompanyByAgentNumber(String number) {
//        Company company = null;
//        String hql = "a.company From Company a where a.number = :number ";
//        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("number", number);
//        company = findOne(query);
//        return company;
//    }
    
    
    public List<Company> findCompanies80PercentUsage(){
        List<Company> comps = null;
        String sql = "FROM Company c WHERE (c.usedSeconds/(c.allocatedMinutes*60))>= :value";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("value", 0.8);
//        String sql = "FROM Company c WHERE c.allocatedMinutes>= :value";
//        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("value", 3000);
        comps = findMany(query);
        return comps;
    }

    public Company findCompanyByEmail(String email) {
        Company company = null;
        String hql = "From Company c where c.email = :email ";
        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("email", email);
        company = findOne(query);
        return company;
    }

    public Company findCompanyByPhoneNumber(String phoneNumber) {
        Company company = null;
        String hql = "From Company c where c.contactNumber = :givenContactNumber ";
        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("givenContactNumber", phoneNumber);
        company = findOne(query);
        return company;
    }
}
