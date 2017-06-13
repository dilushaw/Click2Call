/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications
 * Research Laboratory. All Rights Reserved. This software is the proprietary
 * information of Dialog-University of Moratuwa Mobile Communications Research
 * Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update
 * and/or enhance the software as it sees fit. and .
 */
package uom.dialog.click2call.jsondata;

/**
 * CallRequestResponseJSON.java (UTF-8) Class for represent JSON Object for Call
 * Request and Call Response
 * 
 * Feb 18, 2013
 *
 * @author Dewmini
 * @version 1.0
 */
public class CallRequestResponseJSON {

    private CallSessionInformation callSessionInformation;
//private String resourceURL;
//private String terminated;

    public CallSessionInformation getCallSessionInformation() {
        return callSessionInformation;
    }

    public void setCallSessionInformation(
            CallSessionInformation callSessionInformation) {
        this.callSessionInformation = callSessionInformation;
    }
//    public String getResourceURL() {
//        return resourceURL;
//    }
//
//    public void setResourceURL(String resourceURL) {
//        this.resourceURL = resourceURL;
//    }
//
//    public String getTerminated() {
//        return terminated;
//    }
//
//    public void setTerminated(String terminated) {
//        this.terminated = terminated;
//    }
}
