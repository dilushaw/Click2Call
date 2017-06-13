/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.manager;

import java.util.Date;
import java.util.List;
import uom.dialog.click2call.data.Agents;
import uom.dialog.click2call.data.Calls;
import uom.dialog.click2call.exception.Click2CallException;

/**
 *
 * @author Dilusha
 */
public interface CallManager {

    List<Calls> findCallsbyStatus(int status) throws Click2CallException;

    Calls findcallByID(Integer callId) throws Click2CallException;

    void updateCall(int callId, String endTime) throws Click2CallException;

    void saveAgentCall(Agents currentAgent, String customerNumber, Date startTime, String sequenceNumber) throws Click2CallException;//added by Dewmini

    List<Calls> findCallsByCompanyId(Integer companyId) throws Click2CallException;

    List<Calls> findCallsByCompanyIdAndDateRange(Integer companyId, Date fromDate, Date toDate) throws Click2CallException;
}
