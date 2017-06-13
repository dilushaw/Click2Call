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

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * CallSessionInformation.java (UTF-8)
 * 
* Feb 19, 2013
 *
 * @author Dewmini
 */
@Component
public class CallSessionInformation {

    private String clientCorrelator;
    private List<Participant> participant = new ArrayList<Participant>();
    private String resourceURL;
    private String terminated;

    public String getClientCorrelator() {
        return clientCorrelator;
    }

    public void setClientCorrelator(String clientCorrelator) {
        this.clientCorrelator = clientCorrelator;
    }

    public List<Participant> getParticipant() {
        return participant;
    }

    public void setParticipant(List<Participant> participant) {
        this.participant = participant;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public String getTerminated() {
        return terminated;
    }

    public void setTerminated(String terminated) {
        this.terminated = terminated;
    }
    
    
}
