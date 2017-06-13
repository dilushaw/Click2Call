/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.manager;

import controllers.CompanyController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NonUniqueResultException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.dao.AgentsDAO;
import uom.dialog.click2call.dao.CallDAO;
import uom.dialog.click2call.dao.CompanyDAO;
import uom.dialog.click2call.data.Agents;
import uom.dialog.click2call.data.Calls;
import uom.dialog.click2call.data.Company;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.utils.CommonUtil;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 *
 * @author Dilusha
 */
@Component
public class CallManagerImpl implements CallManager {

    @Autowired
    CallDAO callsDAO;
    @Autowired
    AgentsDAO agentDAO;
    @Autowired
    Calls clickCall;

    public List<Calls> findCallsbyStatus(int status) throws Click2CallException {
        List<Calls> calls = null;
        try {
            calls = callsDAO.findCallsbyStatus(status);
        } catch (HibernateException ex) {
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            throw new Click2CallException("Exception occured", ex);
        }
        return calls;
    }

    /**
     * When customer generate the call via iframe, relevant call data will be stored to the
     * database. Information such as customer, agent, call times. added by Dewmini
     *
     * @param currentAgent
     * @param customerNumber
     * @param startTime
     * @param sequenceNumber
     * @throws Click2CallException
     */
    public void saveAgentCall(Agents currentAgent, String customerNumber, Date startTime, String sequenceNumber) throws Click2CallException {
        try {
            Calls click2Call = new Calls();
            
            click2Call.setAgents(currentAgent);
            click2Call.setCallCorrelator(sequenceNumber);
            click2Call.setCustomerNumber(customerNumber);
            click2Call.setStartTime(startTime);
            click2Call.setStatus(0);
            
            HibernateUtil.beginTransaction();
            callsDAO.save(click2Call);
            HibernateUtil.commitTransaction();
            
            System.out.println("all # of calls: " + callsDAO.findAll(Calls.class).size());
            // HibernateUtil.getSession().merge(click2Call);
//            Set callSet = currentAgent.getCallses();
//            c.allSet.add(click2Call);
//            currentAgent.setCallses(callSet);
//            agentDAO.update(currentAgent);
        } catch (HibernateException ex) {          
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            throw new Click2CallException("Exception occured", ex);
        }
    }

    public void updateCall(int callId, String endTime) throws Click2CallException {
        try {
            Date callendTime = CommonUtil.getFormattedDate(endTime);

            Calls call = this.findcallByID(callId);
            
            call.setEndTime(callendTime);
            call.setStatus(1);//updated
            
            HibernateUtil.beginTransaction();
            callsDAO.updateCall(call);
            HibernateUtil.commitTransaction();
            
        } catch (HibernateException ex) {

            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {

            throw new Click2CallException("Exception occured", ex);
        }
    }

    public Calls findcallByID(Integer callId) throws Click2CallException {
        Calls call = null;
        try {
            call = callsDAO.getCallbyID(callId);
        } catch (NonUniqueResultException ex) {
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            throw new Click2CallException("Exception occured", ex);
        }
        return call;
    }

    public List<Calls> findCallsByCompanyId(Integer companyId) throws Click2CallException {
        List<Calls> calls = null;
        try {
            calls = callsDAO.findCallsByCompanyId(companyId);
        } catch (NonUniqueResultException ex) {
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            throw new Click2CallException("Exception occured", ex);
        }
        return calls;
    }

    public List<Calls> findCallsByCompanyIdAndDateRange(Integer companyId, Date fromDate, Date toDate) throws Click2CallException {
        List<Calls> calls = null;
        try {
            calls = callsDAO.findCallsByCompanyIdAndDateRange(companyId, fromDate, toDate);
        } catch (NonUniqueResultException ex) {
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            throw new Click2CallException("Exception occured", ex);
        }
        return calls;
    }
}
