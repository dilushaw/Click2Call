/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory. All
 * Rights Reserved. This software is the proprietary information of Dialog-University of Moratuwa
 * Mobile Communications Research Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the
 * software as it sees fit. and .
 */
package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lk.dialog.cg.ws.messages.jaws.CreditCheckResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import uom.dialog.click2call.charging.ChargingService;
import uom.dialog.click2call.dao.MasterDAO;
import uom.dialog.click2call.data.Agents;
import uom.dialog.click2call.data.MasterData;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.jsondata.CallRequestResponseJSON;
import uom.dialog.click2call.jsondata.CallSessionInformation;
import uom.dialog.click2call.jsondata.Participant;
import uom.dialog.click2call.manager.AgentManager;
import uom.dialog.click2call.manager.BlackListManager;
import uom.dialog.click2call.manager.CallManager;
import uom.dialog.click2call.manager.CompanyManager;
import uom.dialog.click2call.utils.C2CEncryptor;
import uom.dialog.click2call.utils.CommonUtil;
import uom.dialog.click2call.utils.Constants;

/**
 * CallGenController.java (UTF-8) - Class for generating calls via dialog click2call api
 *
 * Feb 18, 2013, 11:53:11 AM
 *
 * @author Dewmini
 */
@Controller
public class CallGenController {
//@Inject

    @Autowired
    AgentManager agentManager;
    @Autowired
    CallManager callsManager;
    @Autowired
    BlackListManager blackListManager;
    @Autowired
    CompanyManager companyManager;
    @Autowired
    MasterDAO masterDAO;
    @Autowired
    ChargingService chargingService;
    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/Click2Call", method = RequestMethod.GET)
    public String showCallGenPage() {
        System.out.println("enter debug log here : enter show call gen page");
        return "ClickCall";
    }

    @RequestMapping(value = "/Click2CallGen", method = RequestMethod.POST)
    public @ResponseBody
    String Click2CallGenerate(HttpServletRequest request, @RequestParam String customerNumber, String agentKey) {
        System.out.println("enter debug log here : enter call generation method");
        String statusMsg = "Process didnt Starts yet!";
        try {
            //String agentKey = request.getParameter("agent");
            System.out.println("agentKey: " + agentKey);
            if(agentKey==null || agentKey.isEmpty()){
                return statusMsg = "Couldn't found valid agent key!";
            }
            String agentPhoneNumber = C2CEncryptor.decrypt(agentKey);
            System.out.println("agentPhoneNumber: " + agentPhoneNumber);

            Agents currentAgent = agentManager.findAgentByNumber(agentPhoneNumber);
            Boolean checkCustomerNum = blackListManager.checkNumberIsBlackListed(customerNumber, currentAgent.getCompany());

            if (checkCustomerNum) {
                return statusMsg = "Your Number is blacklisted!";
            }

            if (currentAgent == null) {
                return statusMsg = "Cannot connect with an agent!";
            }
            
            if(currentAgent.getStatus()==Constants.INACTIVE){
                return statusMsg = "Connecting agent is not active!";
            }

            /**
             * Check the agent's company credit limit. If outstanding value is greater than the
             * company credit limit, not allow to generate the calls.To run this code snippet it
             * should be able to access dialog charging gateway.
             */
//            String companyBillingNumber = currentAgent.getCompany().getContactNumber();
//            System.out.println("Company billing number : "+companyBillingNumber);
//            CreditCheckResponse crRes = chargingService.checkCreditForNumber(companyBillingNumber);
//            System.out.println("CallGen: Company Outsatding : "+Math.abs(crRes.getOutStanding()));
//            System.out.println("CallGen: Company Credit Limit : "+crRes.getCreditlimit());
//            System.out.println("CallGen: Account Status : "+crRes.getAccountStatus());
//            System.out.println("CallGen: Transaction result : "+crRes.getTransResult());
//            if(crRes.getOutStanding()>=crRes.getCreditlimit()){
//                 return statusMsg = "Comapny Credit limit has exceeded!";
//            }
            /**
             * Check the agent's allocated minutes. If agent's used minutes are greater than the
             * allocated minutes(toatal_mins) not allow to generate the call.
             */
            if (currentAgent.getUsedSeconds()>= (currentAgent.getTotalMins()*60)) {
                return statusMsg = "Current agent's calls are temporarily limited!";
            }

            /**
             * if all the above conditions are satisfied; create the participants for the call.
             */
            List<Participant> participantList = createParticipants(agentPhoneNumber, customerNumber);

            //if (currentAgent != null) {
            /**
             * sequenceNumber is to be used by “clientCorrelator":"S001_21_<sequence number>” ;
             * <sequence number> is created as a Combination of "agentId_nextCorrelate"
             */
            StringBuilder sequenceNumber = new StringBuilder();
            sequenceNumber.append("A");
            sequenceNumber.append(currentAgent.getAgentId());
            sequenceNumber.append("_" + currentAgent.getNextCorrelate());

            HttpEntity<CallRequestResponseJSON> entity = pepareJsonEntity(sequenceNumber, currentAgent, participantList);

            // Send the request as POST
            ResponseEntity<CallRequestResponseJSON> result = restTemplate.exchange("http://apistore.dialog.lk/apicall/clicktocall/1.0", HttpMethod.POST, entity, CallRequestResponseJSON.class);

            //update agent's next correlate number after generating the call
            currentAgent.setNextCorrelate(currentAgent.getNextCorrelate() + 1);
            agentManager.updateAgent(currentAgent);

            prepareAndSaveCall(result, currentAgent, customerNumber, sequenceNumber);
            statusMsg = (result.getStatusCode().toString().equalsIgnoreCase("201") ? "You will receive a call shortly..." : "Try Again!");
            //} else {
            //    statusMsg = "Cannot connect with an agent!";
            //}

        } catch (Exception e) {
            //logger.error(e);
            statusMsg = "Try Later!";
            System.out.println("enter error log here : exception occured" + e);
        }

        System.out.println("statusMsg:......." + statusMsg);
        return statusMsg;
    }

    /**
     *
     * @param agentPhoneNumber
     * @param customerNumber
     * @return
     */
    private List<Participant> createParticipants(String agentPhoneNumber, String customerNumber) {
        //ObjectMapper mapper = new ObjectMapper();
        //CallRequestDTO callDTO =  mapper.readValue(new File("d:\\callinfo.json"), CallRequestDTO.class);
        List<Participant> participantList = new ArrayList<Participant>();
        Participant participant = new Participant();
        participant.setParticipantAddress(agentPhoneNumber);
        participant.setParticipantName("agent");
        participantList.add(participant);
        participant = new Participant();
        participant.setParticipantAddress(customerNumber);
        participant.setParticipantName("customer");
        participantList.add(participant);
        return participantList;
    }

    /**
     *
     * @param result
     * @param currentAgent
     * @param customerNumber
     * @param sequenceNumber
     * @throws Click2CallException
     */
    private void prepareAndSaveCall(ResponseEntity<CallRequestResponseJSON> result, Agents currentAgent, String customerNumber, StringBuilder sequenceNumber) throws Click2CallException {
        //                Collection<Participant> agent = Collections2.filter(result.getBody().getCallSessionInformation().getParticipant(), new Predicate<Participant>() {
        //                    @Override
        //                    public boolean apply(Participant p) {
        //                        return p.getParticipantName().equals("agent");
        //                    }
        //                });
        String startTime = "";
        List<Participant> allParticipants = result.getBody().getCallSessionInformation().getParticipant();
        for (Participant p : allParticipants) {
            if (p.getParticipantName().equals("agent")) {
                startTime = p.getStartTime();
            }
            break;
        }

        Date stdate = CommonUtil.getFormattedDate(startTime);
        callsManager.saveAgentCall(currentAgent, customerNumber, stdate, sequenceNumber.toString());
    }

    private HttpEntity<CallRequestResponseJSON> pepareJsonEntity(StringBuilder sequenceNumber, Agents currentAgent, List<Participant> participantList) {
        CallSessionInformation callSessionInfo = new CallSessionInformation();
        //Dialog api store application subscription id is : S001_21.(given by dialog)
        //use this in the prefix section[S001_21] of the “clientCorrelator":"S001_21_<sequence number>” payload item when initiating the request.
        String correlator;
        correlator = "S001_21" + sequenceNumber;
        System.out.println("Client correlator: " + correlator);
        callSessionInfo.setClientCorrelator(correlator);
        callSessionInfo.setParticipant(participantList);

        CallRequestResponseJSON callJSON = new CallRequestResponseJSON();
        callJSON.setCallSessionInformation(callSessionInfo);

        // Prepare acceptable media type
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        headers.add("Authorization", "Bearer vke2qATop6pYU8G0fanYO9QQXhUa");//TODO should read from property file
        // Pass the new call request and header
        HttpEntity<CallRequestResponseJSON> entity = new HttpEntity<CallRequestResponseJSON>(callJSON, headers);
        return entity;
    }
}
