/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;
import uom.dialog.click2call.data.Blacklist;
import uom.dialog.click2call.data.Company;
import uom.dialog.click2call.data.Users;
import uom.dialog.click2call.dto.CompanyDTO;
import uom.dialog.click2call.exception.Click2CallException;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
public interface CompanyManager {
    
    void saveCompany(Company company) throws Click2CallException;
    void simpleUpdate(Company company) throws Click2CallException;//added by dewmini
    void updateCompany (CompanyDTO editCompany, Users user) throws Click2CallException;
    public void updateCompanyUsedmins (Integer companyId,int usedSec) throws Click2CallException;
    List<Company> loadAllCompanies() throws Click2CallException;
    Company findCompanyByID(Integer id)throws Click2CallException;
    Company findCompanyByName(String companyname)throws Click2CallException;
    List<Company> findAllCompanies() throws Click2CallException;
    List <Blacklist> findBlackListByCompanyId (Integer companyId)throws Click2CallException;
    void addNewBlackListNumber(Blacklist number);
    Blacklist findBlacklistRecordByNumber (String number, Company company)throws Click2CallException;
    void deleteBlackListNumber(Blacklist blacklist);
    //Company findCompanyByAgentNumber(String number) throws Click2CallException;//added by dewmini

    public Company findCompanyByEmail(String email) throws Click2CallException;//added by dewmini
    
    public Map<Company,Integer> getCompanyAllCalls() throws Click2CallException;//added by dewmini;
    public Map<Company, Integer> getCompanyCallsHistory(Date fromDate, Date toDate) throws Click2CallException;//added by dewmini;

    public Company findCompanyByPhoneNumber(String phoneNumber) throws Click2CallException;//added by dewmini;
}
