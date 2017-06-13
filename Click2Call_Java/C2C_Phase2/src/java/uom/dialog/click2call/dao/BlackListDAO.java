/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dao;

import java.util.List;
import uom.dialog.click2call.data.Blacklist;
import uom.dialog.click2call.data.Company;

/**
 *
 * @author Hasala
 */
public interface BlackListDAO extends GenericDAO<Blacklist,String> {
    public boolean checkBlacklist(int number, Company company); 
    public List<Blacklist> findBlackListByCompanyId(Integer companyId);
    public void addNewBlackListNumber(Blacklist number);
    public Blacklist findBlacklistRecordByNumber(String number, Company company);
    public List<String> findBlackListNumbersByCompanyId(Integer companyId);

    //public Blacklist findByNumber(String Number);
}
