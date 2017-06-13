/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory.  All Rights Reserved.
 * This software is the proprietary information of Dialog-University of Moratuwa Mobile Communications Research Laboratory(Dialog-UOM Lab).
 * 
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the software as it sees fit. and .
 */
package uom.dialog.click2call.jsondata;

import org.springframework.stereotype.Component;

/**
* Participant.java (UTF-8)
*
* Feb 19, 2013
* @author Dewmini
*/
@Component
public class Participant {
private String participantAddress;
	private String participantName;
        private String resourceURL;
        private String startTime;
        private String endTime;
        
        private String participantStatus;

	public String getParticipantAddress() {
		return participantAddress;
	}

	public void setParticipantAddress(String participantAddress) {
		this.participantAddress = participantAddress;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getParticipantStatus() {
        return participantStatus;
    }

    public void setParticipantStatus(String participantStatus) {
        this.participantStatus = participantStatus;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
}
