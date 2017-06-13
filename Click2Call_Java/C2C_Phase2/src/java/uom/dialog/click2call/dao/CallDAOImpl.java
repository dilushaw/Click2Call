/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.data.Calls;
import uom.dialog.click2call.data.Company;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 *
 * @author Dilusha
 */
@Component
public class CallDAOImpl extends GenericDAOImpl<Calls, Integer> implements CallDAO {

    public List<Calls> findCallsbyStatus(int status) {
        List<Calls> calls = null;
        String hql = "From Calls c where c.status= :status ";

        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("status", status);

        calls = findMany(query);
        //Hibernate.initialize(calls);
        return calls;
    }

    public Calls getCallbyID(int callId) {
        Calls calls = null;
        String hql = "From Calls c where c.callId= :callId ";

        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("callId", callId);

        calls = findOne(query);

        return calls;
    }

    public void updateCall(Calls call) {

        update(call);
    }

    public List<Calls> findCallsByCompanyId(Integer companyId) {
        List<Calls> calls=null;
        String hql = "From Calls c where c.agents.company.companyId=:companyId ";
        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("companyId", companyId);
        calls = findMany(query);
        return calls;
    }
    
       public List<Calls> findCallsByCompanyIdAndDateRange(Integer companyId,Date fromDate, Date toDate){
            List<Calls> calls=null;
            Query query= null;
//            if(fromDate.equals(toDate)){
//                String hql = "From Calls c where c.agents.company.companyId=:companyId and c.startTime=:start";
//         query = HibernateUtil.getSession().createQuery(hql).setParameter("companyId", companyId).setParameter("start", fromDate);
//            }else{
        String hql = "From Calls c where c.agents.company.companyId=:companyId and c.startTime between :start and :end";
         query= HibernateUtil.getSession().createQuery(hql).setParameter("companyId", companyId).setParameter("start", fromDate).setParameter("end", toDate);
//            }
        calls = findMany(query);
        return calls; 
        }

}
