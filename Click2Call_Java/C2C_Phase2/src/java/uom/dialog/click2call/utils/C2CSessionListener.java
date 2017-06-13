/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory. All
 * Rights Reserved. This software is the proprietary information of Dialog-University of Moratuwa
 * Mobile Communications Research Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the
 * software as it sees fit.
 *
 */
package uom.dialog.click2call.utils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Date;

/**
 * C2CSessionListener.java (UTF-8) Jun 27, 2013, 9:49:25 AM
 *
 * @author Dewmini
 */
public class C2CSessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.print(getTime() + " (session) Created:");
        System.out.println("ID=" + session.getId() + " MaxInactiveInterval="
                + session.getMaxInactiveInterval());
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        // session has been invalidated and all session data 
//(except Id)is no longer available
        System.out.println(getTime() + " (session) Destroyed:ID="
                + session.getId());
        forwardPage();
    }
    
    public String forwardPage(){
        System.out.println("inside C2CSessionListener forward page");
        return "forward:/sessionExipired.htm";
    }

    private String getTime() {
        return new Date(System.currentTimeMillis()).toString();
    }
}
