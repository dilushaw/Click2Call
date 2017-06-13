/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dao;

import java.util.List;
import uom.dialog.click2call.data.Company;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
public interface CompanyDAO extends GenericDAO<Company,Integer>{
 
     public void saveCompany(Company company);
     public void updateCompany(Company company);
     public List<Company> loadAllCompanies();
     public List<Company> loadAllCompaniesByStatus(Integer companyStatus);
     public List<Company> findAllCompanies();
     public Company findCompanyByName(String companyName);
     //Company findCompanyByAgentNumber(String number);//added by dewmini
     public List<Company> findCompanies80PercentUsage();//added by dewmini

    public Company findCompanyByEmail(String email);

    public Company findCompanyByPhoneNumber(String phoneNumber);
}
