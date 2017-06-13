/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory. All Rights Reserved. This
 * software is the proprietary information of Dialog-University of Moratuwa Mobile Communications Research
 * Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the software as it sees fit.
 * 
 */

$(document).ready(function() {


    $("#allocatedMinutes").keypress(function(e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            //display error message att specific loaction
            //$("#errmsg").html("Digits Only").show().fadeOut("slow");
            //alert the error message
            alert("Digits Only");
            return false;
        }
    });
});

function displayChargingDetails(typeId) {

    var monthlyRental = $("#monthlyRental");
    var allocatedMinutes = $("#allocatedMinutes");
    var perMinCharge = $("#perMinCharge");
    if (typeId == "") {
        monthlyRental.prop("disabled", true);
        allocatedMinutes.prop("disabled", true);
        perMinCharge.prop("disabled", true);
        monthlyRental.val('');
        allocatedMinutes.val('');
        perMinCharge.val('');
    }
    if (typeId == 1) {
        monthlyRental.prop("disabled", false);
        allocatedMinutes.prop("disabled", false);
        perMinCharge.prop("disabled", true);
        monthlyRental.val('');
        allocatedMinutes.val('');
        perMinCharge.val('N/A');
    }
    if (typeId == 2) {
        monthlyRental.prop("disabled", true);
        allocatedMinutes.prop("disabled", true);
        perMinCharge.prop("disabled", false);
        monthlyRental.val('N/A');
        allocatedMinutes.val('N/A');
        perMinCharge.val('');
    }
    if (typeId == 3) {
        monthlyRental.prop("disabled", false);
        allocatedMinutes.prop("disabled", false);
        perMinCharge.prop("disabled", false);
        monthlyRental.val('');
        allocatedMinutes.val('');
        perMinCharge.val('');
    }
}

function validateNewChargingPlan() {
    var planName = $("#planName").val();
    var chargingTypeId = $("#chargingTypeId").val();
    var monthlyRental = $("#monthlyRental").val();
    var allocatedMinutes = $("#allocatedMinutes").val();
    var perMinCharge = $("#perMinCharge").val();
    if (planName == null || planName == "") {
        alert("Please give Name!");
        return false;
    }
    if (planName.length > 20) {
        alert("Plan Name cannot have more than 20 characters!");
        return false;
    }
    var planNameExists = checkPlanNameExist(planName);
    if (planNameExists == "yes") {
        alert("Name already taken!");
        return false;
    }

    if (chargingTypeId == "" || chargingTypeId == null) {
        alert("Please select charging type!");
        return false;
    }
    if (chargingTypeId == 1) {
        if (monthlyRentalValidation(monthlyRental) == false) {
            return false;
        }
        if (allocatedMinutesValidation(allocatedMinutes) == false) {
            return false;
        }
    }
    if (chargingTypeId == 2) {
        if (perMinChargeValidation(perMinCharge) == false) {
            return false;
        }
    }
    if (chargingTypeId == 3) {
        if (monthlyRentalValidation(monthlyRental) == false) {
            return false;
        }
        if (allocatedMinutesValidation(allocatedMinutes) == false) {
            return false;
        }
        if (perMinChargeValidation(perMinCharge) == false) {
            return false;
        }

    }
}

function monthlyRentalValidation(monthlyRental) {
    if (monthlyRental == "" || isNaN(monthlyRental) || monthlyRental <= 0) {
        alert("Invalid Monthly Rental!");
        return false;
    }
    //allow only 4 int values for monthlyRental
    if(checkDecimalIntOnly(monthlyRental, 4, "Monthly Rental")==false){
        return false;
    }
    if(checkDecimals(monthlyRental)==false){
        return false;
    }
    
//        var intRegex = /^\d+$/;
//if(!intRegex.test(monthlyRental)) {
//    alert("fjigioerj!");
//            return false;
//}
}

function allocatedMinutesValidation(allocatedMinutes) {
    if (allocatedMinutes == "" || isNaN(allocatedMinutes) || allocatedMinutes <= 0) {
        alert("Invalid Allocated Minutes!");
        return false;
    }
    if(allocatedMinutes.lenght > 5){
        alert("Allocated Minutes cannot have more than 5 characters!");
        return false;
    }
    if (allocatedMinutes % 1 != 0) {
        alert("Allocated Minutes cannot be a decimal value!");
        return false;
    }
}

function perMinChargeValidation(perMinCharge) {
    if (perMinCharge == "" || isNaN(perMinCharge) || perMinCharge <= 0) {
        alert("Invalid Per Minute Charge!");
        return false;
    }
    //allow only 2 int values for perMinCharge
    if (checkDecimalIntOnly(perMinCharge, 2, "Per Minute Charge") == false) {
        return false;
    }
    if (checkDecimals(perMinCharge) == false) {
        return false;
    }

}

//allow only for 2 decimal palces
function checkDecimals(fieldValue)
{
    decallowed = 2;
    //fieldValue.indexOf('.') == -1 means cannot be found
    if (fieldValue.indexOf('.') == -1)
        fieldValue += ".";
    dectext = fieldValue.substring(fieldValue.indexOf('.') + 1, fieldValue.length);

    if (dectext.length > decallowed)
    {
        alert("Enter a number with up to " + decallowed + "decimal places. try again.");
//        fieldName.select();
//        fieldName.focus();
        return false;
    }

}

//allow prdefined(ie.allowLength) length for int value
function checkDecimalIntOnly(fieldValue, allowLength, name) {

    var val = fieldValue.toString().split(".")[0];//get value befor decimal
    //var valAfter = fieldValue.toString().split(".")[1];//get value after decimal

    if (val.length > allowLength) {
        alert(name + " can have only " + allowLength + " digits before decimal");
        return false;
    }
}

function checkPlanNameExist(planName) {
    var status;

    $.ajax({
        url: 'checkPlanNameExist.htm',
        async: false,
        data: ({
            planName: planName

        }),
        success: function(result) {

            if (result == 'yes') {
                status = result;
            } else if (result == 'exception') {
                status = result;
            }
        }
    });
    return status;
}

function validateEditChargingPlan() {
    var chargingTypeId = $("#chargingTypeId").val();
    var monthlyRental = $("#monthlyRental").val();
    var allocatedMinutes = $("#allocatedMinutes").val();
    var perMinCharge = $("#perMinCharge").val();

    if (chargingTypeId == 1) {
        if (monthlyRentalValidation(monthlyRental) == false) {
            return false;
        }
        if (allocatedMinutesValidation(allocatedMinutes) == false) {
            return false;
        }
    }
    if (chargingTypeId == 2) {
        if (perMinChargeValidation(perMinCharge) == false) {
            return false;
        }
    }
    if (chargingTypeId == 3) {
        if (monthlyRentalValidation(monthlyRental) == false) {
            return false;
        }
        if (allocatedMinutesValidation(allocatedMinutes) == false) {
            return false;
        }
        if (perMinChargeValidation(perMinCharge) == false) {
            return false;
        }

    }
}