/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dao;

import java.util.Date;
import java.util.List;
import uom.dialog.click2call.data.Calls;

/**
 *
 * @author Dilusha
 */
public interface CallDAO extends GenericDAO<Calls,Integer>{
    public List<Calls> findCallsbyStatus(int status);
    public Calls getCallbyID(int callId);
    public void updateCall(Calls call);
    List<Calls> findCallsByCompanyId(Integer companyId);
    List<Calls> findCallsByCompanyIdAndDateRange(Integer companyId,Date fromDate, Date toDate);
} 
