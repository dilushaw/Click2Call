/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.utils;

/**
 *
 * @author Hasala ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
public class Constants {
    //db special data
    public static final int DIALOG_COMPANY_ID = 1;
    //user type id constants
    public static final int DA = 1;
    public static final int DU = 2;
    public static final int CCA = 3;
    public static final int CA = 4;
    //user type constants
    public static final String DIALOG_ADMIN = "Dialog Admin";
    public static final String DIALOG_USER = "Dialog User";
    public static final String CUSTOMRE_CARE_ADMIN = "Customer Care Admin";
    public static final String COMPANY_ADMIN = "Company Admin";
    //constants variables set for the session 
    public static final String USER_NAME = "userName";
    public static final String COMPANY = "company";
    public static final String LOGGED_USER = "isLoggedIn";
    public static final String USER_TYPE = "userType";
    //Error/Success Pages
    public static final String ERROR = "error";
    public static final String SUCCESS = "success";
    public static final String ERROR_POPUP = "errorPopup";
    /* Error/Success Messages */
    public static final String SUCCESSMESSAGE = "successMessage";
    public static final String ERRORMESSAGE = "errorMessage";
    public static final String PROCESS_FAIL = "Could not process your request at this moment :";
    //Entity status constants
    public static final Integer ACTIVE_AND_INACTIVE = -1;//for entity staus=active/inctive (Company, User, Agent)
    public static final Integer ACTIVE = 1;//for entity staus=active (Company, User, Agent)
    public static final Integer INACTIVE = 0;//for entity staus=inactive (Company, User, Agent)
    //public static final Integer EXPIRED = 2;//for entity staus=expired (Company, User, Agent)
    public static final Integer DELETED = 3;//for entity staus=deleted (Company, User, Agent)
    //Call Status
    public static final Integer CALL_INITIAL = 0;
    public static final Integer CALL_TERMINATED = 1;
    public static final Integer CALL_BILLED = 2;
    public static final Integer CALL_BILLING_EXCLUDED = 3;
    //Chargin types
    public static final int RENTAL_ID = 1;
    public static final int CALL_CHARGE_ID = 2;
    public static final int REANTAL_AND_CALL_ID = 3;
    public static final String RENTAL = "Rental";
    public static final String CALL_CHARGE = "Call Charge";
    public static final String REANTAL_AND_CALL = "Rental + Call Charge";
    //formats
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static String PARTICIPANT_CONNECTED="CallParticipantConnected";
    public static String PARTICIPANT_NOT_CONNECTED="CallParticipantInitial or CallParticipantNotReachable";
    public static String UNAUTHORIZED_PAGE = "unauthorized";
}
