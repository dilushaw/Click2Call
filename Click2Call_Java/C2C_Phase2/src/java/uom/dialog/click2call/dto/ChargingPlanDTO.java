/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory. All Rights Reserved. This
 * software is the proprietary information of Dialog-University of Moratuwa Mobile Communications Research
 * Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the software as it sees fit.
 * 
 */
package uom.dialog.click2call.dto;

import org.springframework.stereotype.Component;

/**
 * ChargingPlanDTO.java (UTF-8)
 * Jun 13, 2013, 11:22:25 AM
 *
 * @author Dewmini
 */
@Component
public class ChargingPlanDTO {
    
    private String planName;
    private Integer chargingTypeId;
    private Double monthlyRental;
    private Integer allocatedMinutes;
    private Double perMinCharge;
    private String typeName;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Integer getChargingTypeId() {
        return chargingTypeId;
    }

    public void setChargingTypeId(Integer chargingTypeId) {
        this.chargingTypeId = chargingTypeId;
    }

    public Double getMonthlyRental() {
        return monthlyRental;
    }

    public void setMonthlyRental(Double monthlyRental) {
        this.monthlyRental = monthlyRental;
    }

    public Integer getAllocatedMinutes() {
        return allocatedMinutes;
    }

    public void setAllocatedMinutes(Integer allocatedMinutes) {
        this.allocatedMinutes = allocatedMinutes;
    }

    public Double getPerMinCharge() {
        return perMinCharge;
    }

    public void setPerMinCharge(Double perMinCharge) {
        this.perMinCharge = perMinCharge;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    

}
