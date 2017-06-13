/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dao;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.data.Agents;
import uom.dialog.click2call.data.Blacklist;
import uom.dialog.click2call.data.Company;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 *
 * @author Hasala
 */
@Component
public class BlackListDAOImpl extends GenericDAOImpl<Blacklist, String> implements BlackListDAO {

    public boolean checkBlacklist(int number, Company company) {
        int a = number;
        Company c = company;
        boolean result = true;
        return result;
    }

    public List<Blacklist> findBlackListByCompanyId(Integer companyId) {
        List<Blacklist> blacklist = null;
        String sql = "From Blacklist b where b.company.companyId=:id";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("id", companyId);
        blacklist = findMany(query);
        return blacklist;
    }

    public void addNewBlackListNumber(Blacklist number) {
        save(number);
        System.out.println("Saved blacklist number");
    }

    public Blacklist findBlacklistRecordByNumber(String number, Company company) {
        Blacklist blacklist;
        String sql = "From Blacklist b where b.number=:number and b.company=:company";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("number", number).setParameter("company", company);
        blacklist = findOne(query);
        return blacklist;
    }

    public List<String> findBlackListNumbersByCompanyId(Integer companyId) {
        List<String> blackListNums = null;
        String sql = "b.number From Blacklist b where b.company.companyId=:id";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("id", companyId);
        blackListNums = (List<String>) query.list();
        return blackListNums;
    }

//    public Blacklist findByNumber(String Number) {
//        Blacklist blackList = null;
//        
//        return blackList;
//    }
}
