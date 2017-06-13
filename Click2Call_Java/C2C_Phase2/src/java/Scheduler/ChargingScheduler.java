/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory. All
 * Rights Reserved. This software is the proprietary information of Dialog-University of Moratuwa
 * Mobile Communications Research Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the
 * software as it sees fit.
 *
 */
package Scheduler;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uom.dialog.click2call.charging.ChargingService;
import uom.dialog.click2call.dao.MasterDAOImpl;
import uom.dialog.click2call.data.Calls;
import uom.dialog.click2call.data.MasterData;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.manager.CallManager;
import uom.dialog.click2call.utils.Constants;

import lk.dialog.cg.ws.messages.jaws.ChargedToBillResponse;
import lk.dialog.cg.ws.messages.jaws.ChargedToBillRequest;
import lk.dialog.cg.ws.messages.jaws.CreditCheckResponse;
import org.hibernate.Hibernate;
import uom.dialog.click2call.dao.CallDAO;
import uom.dialog.click2call.dao.CompanyDAO;
import uom.dialog.click2call.dao.MasterDAO;
import uom.dialog.click2call.data.Company;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 * ChargingScheduler.java (UTF-8) Apr 9, 2013, 10:33:36 AM
 *
 * @author Dewmini
 */
@Service
public class ChargingScheduler {

    private static final byte[] password = "cL1K2Ka11".getBytes();
    private static final String APP_ID = "CLICKCALL";
    private static final String REASON_CODE = "657";
    private static final String DOMAIN_ID = "GSM";
    @Autowired
    ChargingService chargingService;
    @Autowired
    private CallManager callManager;
    @Autowired
    CallDAO callDAO;
    @Autowired
    MasterDAO masterDAO;
    @Autowired
    CompanyDAO companyDAO;

    //To test in local machine. not update charging server. only check for method calls
    //@Scheduled(fixedDelay = 30000)
    public void testChargeMethod() {
        try {
            System.out.println("===========================================");
            HibernateUtil.beginTransaction();
            MasterData mdata = masterDAO.findTransIdByType("chargebill");
            //HibernateUtil.commitTransaction();
            Integer nextTrnasId = Integer.parseInt(mdata.getValue());
            // HibernateUtil.beginTransaction();
            List<Calls> unbilledCalls = callDAO.findCallsbyStatus(Constants.CALL_TERMINATED);

            //System.out.println(unbilledCalls!=null?"going to chrge for calls":"no calls to charge");
            System.out.println("unbilledCalls is empty? :" + unbilledCalls.isEmpty());
            if (unbilledCalls != null && !unbilledCalls.isEmpty()) {
                System.out.println("Going to charge for calls.");
                for (Calls call : unbilledCalls) {
                    //Hibernate.initialize(call);

                    Hibernate.initialize(call.getAgents().getCompany().getContactNumber());
                    //HibernateUtil.commitTransaction();
                    System.out.println("inside unbilled list");
                    System.out.println("Charge number: " + call.getAgents().getCompany().getContactNumber());
                    System.out.println("call charge: " + call.getCallCharge());

                    call.setStatus(Constants.CALL_BILLED);
                    //HibernateUtil.beginTransaction();
                    callDAO.update(call);
                    //HibernateUtil.commitTransaction();
                    System.out.println("after update call status");
                    nextTrnasId++;
                    System.out.println("....................................");
                }
                //incremnet next trans_id in the DB
                mdata.setValue(nextTrnasId.toString());
                //HibernateUtil.beginTransaction();
                masterDAO.update(mdata);//update transaction to its next value to be called by scheduler runs at next time
                System.out.println("after update masterDAO nxt tran value");
                HibernateUtil.commitTransaction();
                System.out.println("just after commitTransaction()");
            } else {
                System.out.println("no calls to charge");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    /**
     * check every 30 second. Get all calls with status 1(unbill) and pass those to CG for
     * billing. change all calls status to 2(billed)
     */
    //@Scheduled(fixedDelay = 30000)
    public void chargeAllUnbilledNumbers() {
        System.out.println("..........CG - Pass all calls with status=1 for Charging.........");
        try {
            System.out.println("bafore call sevice method");
            ChargedToBillResponse chargeResponse = chargingService.chargeForNumbers();
            if (chargeResponse == null) {
                System.out.println("No numbers to charge");
            }
            System.out.println(".......after call sevice method..........");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
