/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scheduler;

import Scheduler.EmailSender;
import controllers.CompanyController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.dialog.cg.ws.messages.jaws.CreditCheckResponse;
import org.hibernate.Hibernate;
import org.hibernate.NonUniqueResultException;
import org.hibernate.collection.PersistentSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uom.dialog.click2call.dao.AgentUsageDAO;
import uom.dialog.click2call.dao.AgentsDAO;
import uom.dialog.click2call.dao.CallDAO;
import uom.dialog.click2call.dao.CompanyDAO;
import uom.dialog.click2call.dao.CompanyUsageDAO;
import uom.dialog.click2call.data.AgentUsage;
import uom.dialog.click2call.data.Agents;
import uom.dialog.click2call.data.Calls;
import uom.dialog.click2call.data.Company;
import uom.dialog.click2call.data.CompanyUsage;
import uom.dialog.click2call.jsondata.CallRequestResponseJSON;
import uom.dialog.click2call.jsondata.CallSessionInformation;
import uom.dialog.click2call.jsondata.Participant;
import uom.dialog.click2call.manager.AgentManager;
import uom.dialog.click2call.manager.CallManager;
import uom.dialog.click2call.manager.CallManagerImpl;
import uom.dialog.click2call.manager.CompanyManager;
import uom.dialog.click2call.utils.CommonUtil;
import uom.dialog.click2call.utils.HibernateUtil;
import uom.dialog.click2call.utils.Constants;

/**
 * This class Schedules the expire dates of corporate license and campaigns,currently campaign
 * license is not scheduled it can be done similar to corporate license
 *
 * @author Dilusha Weeraddana
 * @version 2.0
 */
@Service
public class Scheduler {

//    @Autowired
//    private CallManager callManager;
//    @Autowired
//    private AgentManager agentManager;
//    @Autowired
//    private CompanyManager companyManager;
    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private CallDAO callDAO;
    @Autowired
    private AgentsDAO agentDAO;
    @Autowired
    private AgentUsageDAO agentUsageDAO;
    @Autowired
    private CompanyUsageDAO companyUsageDAO;
    @Autowired
    EmailSender emailSender;

//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    /**
     * This method schedules the calls end time per every 20 seconds. update calls table : end_time,
     * start_time, call_charge ; update company used minutes ; update agent used minutes.
     * ........Currently particular call is charged for whole minute, i.e. not per second(if call
     * duration is 1.20 minutes and per minute charge is Rs.2.00 , call will be charged as Rs.4.00)
     *
     * @return Nothing
     */
    /**
     * check for every 20 seconds. get all calls with status 0(call_initial) which are not recorded
     * the end time and call charge. Then update the call end time and call charge. At the same time
     * for all such calls relevant agents' and companies' used minutes are updated.
     */
    @Scheduled(fixedDelay = 20000)
    public void checkCalls() {

        System.out.println("this is a schedular");
        List<Calls> calls = null;


        System.out.println("*******START-Check calls table-get call end time; etc. **********");
        try {

            HibernateUtil.beginTransaction();

            /**
             * Added by JDBCTEMPLATE String sql = "Select * from calls c where c.status=?";
             *
             *
             * List<Calls> calls = jdbcTemplate.query(sql,new Object[] {0},new RowMapper<Calls>() {
             * public Calls mapRow(ResultSet rs, int rowNum) throws SQLException { Calls c = new
             * Calls(); c.setCallId(rs.getInt("call_id")); //c.setAgents(null); return c; } });
             *
             * System.out.println("Size of calls "+calls.size());
             *
             */
            calls = callDAO.findCallsbyStatus(0);//get all calls where status is 0
            //System.out.println("Size of calls "+calls.size());
            if (calls != null) {
                for (int i = 0; i < calls.size(); i++) {
                    System.out.println("Call to charge: No " + i);
                    Calls call = calls.get(i);
                    Hibernate.initialize(call);
                    Agents agent = call.getAgents();
                    Company company = agent.getCompany();

                    String callCorrelator = calls.get(i).getCallCorrelator();
                    String clientCorrelator = "S001_21" + callCorrelator;
                    String startTime = null;
                    String endTime = null;
                    String participantStatus = null;
                    String terminated = this.getTimeline(clientCorrelator).getBody().getCallSessionInformation().getTerminated();
                    List<Participant> allParticipants = this.getTimeline(clientCorrelator).getBody().getCallSessionInformation().getParticipant();
                    for (Participant p : allParticipants) {
                        if (p.getParticipantName().equals("agent")) {
                            startTime = p.getStartTime();
                            endTime = p.getEndTime();
                            participantStatus = p.getParticipantStatus();
                        }
                        break;
                    }
                    System.out.println("participantStatus: " + participantStatus);
                    System.out.println("terminated: " + terminated);

                    //there may be an occurance for end time is been null, if response from the webservice is null
                    //another posibility : end time null ---- when agent cut the call

                    if (terminated != null && terminated.equalsIgnoreCase("true")) {
                        if (endTime != null && !endTime.isEmpty()) {

                            Date callendTime = CommonUtil.getFormattedDate(endTime);
                            Date callStartTime = CommonUtil.getFormattedDate(startTime);
                            //get call duration
                            //long durationmilSecs = callendTime.getTime() - call.getStartTime().getTime();

                            long durationmilSecs = callendTime.getTime() - callStartTime.getTime();

//                        int milliseconds = 3000;
//                        double x = ((milliseconds / 1000) % 60) * 0.01;
//                        double minutes = milliseconds / (1000 * 60) + x;



                            System.out.print("call duration in miliseconds: " + durationmilSecs);

                            //double callDuration = (double) durationmilSecs / (1000 * 60);//duration in minitues as a double value(decimal format)
                            //double callDuration = (double) durationmilSecs / 1000 ;//duration in seconds as a double value
                            int callDuration = (int)durationmilSecs / 1000 ;//duration in seconds as a int value
                            System.out.print("call duration in minutes(with in decimal format) " + callDuration);

                            /*
                             double remainSeconds = ((durationmilSecs / 1000) % 60) * 0.01;//get only remain seconds
                             double minSec = durationmilSecs / (1000 * 60) + remainSeconds;//concatanate minutes and seconds. eg. 1.27 = 1 minute, 27 seconds
                             */

                            //update agent info : used mins
                            int agentUsedSec = agent.getUsedSeconds()+ callDuration;
                            agent.setUsedSeconds(agentUsedSec);
                            agentDAO.update(agent);


                            //update company info : used mins

                            int companyUsedSec = company.getUsedSeconds()+ callDuration;
                            company.setUsedSeconds(companyUsedSec);
                            companyDAO.update(company);

                            //update call entity info : endtime and status
                            updateCallInfo(call, company, callDuration, callendTime, callStartTime);

                        }
                    }

                }
            }
            HibernateUtil.getSession().flush();
            HibernateUtil.commitTransaction();

        } catch (Exception ex) {
            ex.printStackTrace();
            //Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ResponseEntity<CallRequestResponseJSON> getTimeline(String clientCorrelator) {
        List<String> startEndTime = new ArrayList<String>();
        RestTemplate restTemplate = new RestTemplate();
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        headers.add("Authorization", "Bearer vke2qATop6pYU8G0fanYO9QQXhUa");
        CallSessionInformation callSessionInfo = new CallSessionInformation();
        CallRequestResponseJSON callJSON = new CallRequestResponseJSON();
        callJSON.setCallSessionInformation(callSessionInfo);
        //String response =restTemplate.getForObject("https://apistore.dialog.lk/apicall/clicktocall/1.0/callDurations?clientCorrelator=S001_21_10", String.class, new Object[]{});
        ResponseEntity<CallRequestResponseJSON> response = restTemplate.exchange("https://apistore.dialog.lk/apicall/clicktocall/1.0/callDurations?clientCorrelator=" + clientCorrelator,
                HttpMethod.GET,
                new HttpEntity<byte[]>(headers),
                CallRequestResponseJSON.class);
//        String endTime = response.getBody().getCallSessionInformation().getParticipant().get(0).getEndTime();
//        String startTime = response.getBody().getCallSessionInformation().getParticipant().get(0).getStartTime();
//        System.out.println("Response is=" + response.getStatusCode() + "  " + endTime);
//
//        startEndTime.add(startTime);
//        startEndTime.add(endTime);
//
//        return startEndTime;

        return response;
    }

    private void updateCallInfo(Calls call, Company company, double callDuration, Date callendTime, Date callStartTime) {
        double callCharge = 0.0;
        int callStatus = call.getStatus();
        if (callendTime != null) {
            int chargingTypeId = company.getChargingPlan().getChargingType().getTypeId();
            System.out.println("chargingTypeId: " + chargingTypeId);
            System.out.println("callDuration: " + callDuration);

            if (chargingTypeId == Constants.RENTAL_ID) {
                callCharge = 0.0;
                callStatus = Constants.CALL_BILLED;//as call charge is 0, will be considered as a billed call
            }
            if (chargingTypeId == Constants.CALL_CHARGE_ID) {
                System.out.println("call charge");
                DecimalFormat df = new DecimalFormat("##.## ");
                callCharge = callDuration * (company.getChargingPlan().getPerminuteCharge()/60);
                callCharge = Double.valueOf(df.format(callCharge));
                callStatus = Constants.CALL_TERMINATED;//change call status to 1 -> will be send for billing
            }
            if (chargingTypeId == Constants.REANTAL_AND_CALL_ID) {
                if (company.getUsedSeconds()> company.getChargingPlan().getAllocatedMinutes()*60) {
                    callCharge = callDuration * (company.getChargingPlan().getPerminuteCharge()/60);
                    callStatus = Constants.CALL_TERMINATED;//change call status to 1 -> will be send for billing
                } else {
                    callCharge = 0.0;
                    callStatus = Constants.CALL_BILLED;//as call charge is 0, will be considered as a billed call
                }
            }
        } else {
            callCharge = 0.0;
            callStatus = Constants.CALL_BILLING_EXCLUDED;
            /*
             * as agent cut the call when it initiates, there is no endtime for the call. 
             * Which means no one answer the call. Only riniging the phone happened. 
             * So such calls should be excluded from the billing.
             */
        }
        call.setStartTime(callStartTime);
        call.setEndTime(callendTime);
        call.setStatus(callStatus);
        call.setCallCharge(callCharge);
        callDAO.update(call);

    }

    //@Scheduled(fixedDelay = 10000)
    public void sendMailOn80PersentUsage() {



        System.out.println("***************START**********");
        try {
            HibernateUtil.beginTransaction();
            List<Company> companies = companyDAO.findCompanies80PercentUsage();
            System.out.println(".............");
            System.out.println(companies != null || !companies.isEmpty() ? "not null" : "null");
            List<String> emails = new ArrayList<String>();
            if (companies != null && !companies.isEmpty()) {


                int i = 0;
                for (Company company : companies) {
                    System.out.println("====================");
                    emails.add(company.getEmail());
                    i++;
                    //System.out.println("company: " + company.getCompanyName());
                    //System.out.println("emails: " + emails[i]);
                    System.out.println("--------------------");
                }
                emailSender.sendMailWithAuth("smtp.gmail.com,", "dilumc7@gmail.com", "punnandiblu777", "25", emails, "Click2Call:Warning 80% used minutes exceeded");

            }
//            String[] toEmails = new String[emails == null ? null : emails.size()];
//            emails.toArray(toEmails); // fill the array
            HibernateUtil.commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    public void sendMail(){
//        String host = "smtp.gmail.com";
//    String from = "username";
//    String pass = "password";
//    Properties props = System.getProperties();
//    props.put("mail.smtp.starttls.enable", "true"); // added this line
//    props.put("mail.smtp.host", host);
//    props.put("mail.smtp.user", from);
//    props.put("mail.smtp.password", pass);
//    props.put("mail.smtp.port", "587");
//    props.put("mail.smtp.auth", "true");
//
//    String[] to = {"to@gmail.com"}; // added this line
//
//    Session session = Session.getDefaultInstance(props, null);
//    MimeMessage message = new MimeMessage(session);
//    message.setFrom(new InternetAddress(from));
//
//    InternetAddress[] toAddress = new InternetAddress[to.length];
//
//    // To get the array of addresses
//    for( int i=0; i < to.length; i++ ) { // changed from a while loop
//        toAddress[i] = new InternetAddress(to[i]);
//    }
//    System.out.println(Message.RecipientType.TO);
//
//    for( int i=0; i < toAddress.length; i++) { // changed from a while loop
//        message.addRecipient(Message.RecipientType.TO, toAddress[i]);
//    }
//    message.setSubject("sending in a group");
//    message.setText("Welcome to JavaMail");
//    Transport transport = session.getTransport("smtp");
//    transport.connect(host, from, pass);
//    transport.sendMessage(message, message.getAllRecipients());
//    transport.close();
//    }
    /**
     * Scheduler to run on specific date. Set all companies used minutes to 0 as well as their
     * particular agents' used minutes to zero. (as new billing cycle starts)cron = "0 0 22 14 * ?"
     * -> runs at each month 14th day at 10.00 pm.
     */
    @Scheduled(cron = "0 0 22 14 * ?")
    //@Scheduled(fixedDelay = 10000)
    public void resetAllCompanyUsedTime() {
        System.out.println("runs at specific time : resetAllCompanyUsedMins");

        try {
            HibernateUtil.beginTransaction();

            List<Company> allCompanies = companyDAO.findAll(Company.class);
            for (Company company : allCompanies) {
                CompanyUsage companyUsage = new CompanyUsage();
                //add entry to company_usage table about company used seconds at the reset date
                System.out.println(company.getCompanyName() + " Used Mins:" + company.getUsedSeconds());
                companyUsage.setUsedSeconds(company.getUsedSeconds());
                companyUsage.setCompany(company);
                companyUsage.setResetDate(Calendar.getInstance().getTime());
                companyUsageDAO.save(companyUsage);

                //reset company's used seconds to zero
                company.setUsedSeconds(0);
                companyDAO.update(company);

                Set<Agents> agents = company.getAgentses();
                //List<Agents> agents = agentDAO.findAgentsByCompanyId(company.getCompanyId());
                for (Agents agent : agents) {
                    AgentUsage agentUsage = new AgentUsage();
                    //add entry to agent_usage table about agent used seconds at the reset date
                    agentUsage.setAgents(agent);
                    agentUsage.setUsedSeconds(agent.getUsedSeconds());
                    agentUsage.setResetDate(Calendar.getInstance().getTime());
                    agentUsageDAO.save(agentUsage);

                    //reset agent's used minutes to zero
                    agent.setUsedSeconds(0);
                    agentDAO.update(agent);
                }
            }
            HibernateUtil.commitTransaction();
        } catch (NonUniqueResultException ex) {
            HibernateUtil.rollbackTransaction();
            ex.printStackTrace();
        } catch (Exception ex) {
            HibernateUtil.rollbackTransaction();
            ex.printStackTrace();
        }
    }
}
