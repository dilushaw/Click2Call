/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory. All
 * Rights Reserved. This software is the proprietary information of Dialog-University of Moratuwa
 * Mobile Communications Research Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the
 * software as it sees fit.
 *
 */
package uom.dialog.click2call.charging;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.dialog.cg.ws.messages.jaws.ChargedToBillRequest;
import lk.dialog.cg.ws.messages.jaws.ChargedToBillResponse;
import lk.dialog.cg.ws.messages.jaws.CreditCheckRequest;
import lk.dialog.cg.ws.messages.jaws.CreditCheckResponse;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.dao.CallDAO;
import uom.dialog.click2call.dao.CallDAOImpl;
import uom.dialog.click2call.dao.MasterDAO;
import uom.dialog.click2call.dao.MasterDAOImpl;
import uom.dialog.click2call.data.Calls;
import uom.dialog.click2call.data.MasterData;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.manager.CallManager;
import uom.dialog.click2call.manager.CallManagerImpl;
import uom.dialog.click2call.utils.Constants;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 * ChargingService.java (UTF-8) Apr 9, 2013, 10:30:03 AM
 *
 * @author Dewmini
 */
@Component
public class ChargingService {

    private static final byte[] PASSWORD = "cL1K2Ka11".getBytes();
    private static final String APP_ID = "CLICKCALL";
    private static final String REASON_CODE = "657";
    private static final String DOMAIN_ID = "GSM";
    @Autowired
    CallManager callManager;
    @Autowired
    MasterDAO masterDAO;
    @Autowired
    CallDAO callDAO;

    /**
     *
     * @return @throws Click2CallException
     */
    public ChargedToBillResponse chargeForNumbers() {
        ChargedToBillResponse chargeBillResponse = null;
        try {
            HibernateUtil.beginTransaction();
            String transId;
            String sessionKey = getSessionKey(APP_ID, PASSWORD);
            MasterData mdata = masterDAO.findTransIdByType("chargebill");
            HibernateUtil.getSession().refresh(mdata);
            Integer nextTrnasId = Integer.parseInt(mdata.getValue());
            //get all unbilled calls(identified as status = 1, after billed status will be changed to 2----->Constants.CALL_BILLED)
            List<Calls> unbilledCalls = callDAO.findCallsbyStatus(Constants.CALL_TERMINATED);
            //pass call charges for Dialog CG
            if (unbilledCalls != null && !unbilledCalls.isEmpty()) {
                for (Calls call : unbilledCalls) {
                    HibernateUtil.getSession().refresh(call);
                    Hibernate.initialize(call);
                    Hibernate.initialize(call.getAgents().getCompany().getContactNumber());
                    String chargingNumber = call.getAgents().getCompany().getContactNumber();
                    System.out.println("chargingNumber : " + chargingNumber);
                    transId = "chargebill2" + nextTrnasId.toString();//chargebill -> CB
                    System.out.println("transId : " + transId);
                    ChargedToBillRequest cBillRequest = prepareChargingForNumber(sessionKey, transId, chargingNumber, call.getCallCharge());
                    chargeBillResponse = chargeToBill(cBillRequest);
                    System.out.println("chargeBillResponse - Trans Result : " + chargeBillResponse.getTransResult());
                    System.out.println("account status : " + chargeBillResponse.getAccountStatus());
                    if (Integer.valueOf(chargeBillResponse.getTransResult()) == 0) {
                        call.setStatus(Constants.CALL_BILLED);
                        callDAO.update(call);
                        System.out.println("Call was billed!");
                    }
                    nextTrnasId++;
                }
                //incremnet next trans_id in the DB
                mdata.setValue(nextTrnasId.toString());
                masterDAO.update(mdata);//update transaction to its next value to be called by scheduler runs at next time
                HibernateUtil.commitTransaction();
            }
            //return chargeBillResponse;

        } catch (Exception ex) {
            HibernateUtil.rollbackTransaction();
            ex.printStackTrace();

        }
        return chargeBillResponse;
    }

    public CreditCheckResponse checkCreditForNumber(String number) {
        CreditCheckRequest creditCheckRequest = null;

        try {
            String sessionKey = getSessionKey(APP_ID, PASSWORD);
            String transId;
            HibernateUtil.beginTransaction();
            MasterData mdata = masterDAO.findTransIdByType("creditcheck");
            HibernateUtil.getSession().refresh(mdata);
            Integer nextTrnasId = Integer.parseInt(mdata.getValue());
            transId = "cr2" + nextTrnasId.toString();//cCheck-> CrC
            System.out.println("currnet transId: "+transId);
            creditCheckRequest = prepareCustomerCreditCheck(sessionKey, transId, number);
            nextTrnasId++;
            //incremnet next trans_id in the DB
            mdata.setValue(nextTrnasId.toString());
            masterDAO.update(mdata);//update transaction to its next value
            HibernateUtil.commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return performCreditCheck(creditCheckRequest);
    }

    public ChargedToBillRequest prepareChargingForNumber(String sessionKey, String transId, String number, Double amount) {
        ChargedToBillRequest cBillReq = new ChargedToBillRequest();
        cBillReq.setAuthKey(sessionKey);
        cBillReq.setTransactionID(transId);
        cBillReq.setRefAccount(number);
        cBillReq.setAmount(amount);
        cBillReq.setAppID(APP_ID);
        cBillReq.setDomainID(DOMAIN_ID);
        cBillReq.setReasonCode(REASON_CODE);
        cBillReq.setTaxable(false);
        return cBillReq;
    }

    public CreditCheckRequest prepareCustomerCreditCheck(String sessionKey, String transId, String number) {
        CreditCheckRequest crChkReq = new CreditCheckRequest();
        crChkReq.setAuthKey(sessionKey);
        crChkReq.setTransactionID(transId);
        crChkReq.setRefAccount(number);
        crChkReq.setAppID(APP_ID);
        crChkReq.setDomainID(DOMAIN_ID);
        return crChkReq;
    }

    /**
     * ******* Web Service call methods ************
     */
    /**
     * Method generated from web service
     *
     * @param string1 - APP_ID - should be given by dialog
     * @param arrayOfbyte2 - Password - should be given by dialog
     * @return
     */
    private static String getSessionKey(java.lang.String string1, byte[] arrayOfbyte2) {
        ws.cg.dialog.lk.CGWebService service = new ws.cg.dialog.lk.CGWebService();
        ws.cg.dialog.lk.CGWebServiceInterface port = service.getCGWebServiceInterfacePort();
        return port.getSessionKey(string1, arrayOfbyte2);
    }

    private static ChargedToBillResponse chargeToBill(ChargedToBillRequest chargedToBillRequest1) {
        ws.cg.dialog.lk.CGWebService service = new ws.cg.dialog.lk.CGWebService();
        ws.cg.dialog.lk.CGWebServiceInterface port = service.getCGWebServiceInterfacePort();
        return port.chargeToBill(chargedToBillRequest1);
    }

    private static CreditCheckResponse performCreditCheck(CreditCheckRequest creditCheckRequest1) {
        ws.cg.dialog.lk.CGWebService service = new ws.cg.dialog.lk.CGWebService();
        ws.cg.dialog.lk.CGWebServiceInterface port = service.getCGWebServiceInterfacePort();
        return port.performCreditCheck(creditCheckRequest1);
    }
}
