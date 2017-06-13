/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory. All Rights Reserved. This
 * software is the proprietary information of Dialog-University of Moratuwa Mobile Communications Research
 * Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the software as it sees fit.
 * 
 */

function validateAgentForm() {
   
var companyId = $("#companyId").val();
    var agentName = $("#agentName").val();
    var agentNumber = $("#agentNumber").val();
    var totalMinutes = $("#totalMinutes").val();

    //validate agent name
    if (agentName == null || agentName == "") {
        alert("Please give agent name!");
        return false;
    }
    if (agentName.length > 20) {
        alert("Agent name should be less than 20 characters!");
        return false;
    }
    var nameExist = checkAgentName("create", agentName, companyId, 0);
    if (nameExist == "true") {
        alert("This Name already taken!");
        return false;
    }
    
    //validate agent number
    if (agentNumber == null || agentNumber == "") {
        alert("Please give agent phone number!");
        return false;
    }
    if (isNaN(agentNumber) || agentNumber.indexOf(" ") != -1)
    {
        alert("Please enter a numeric value for the phone number.");
        return false;
    }
//    if (agentNumber.substring(0, 3) != "077" || agentNumber.length != 10) {
//        alert("Agent Number should be 077xxxxxxx format!");
//        return false;
//    }
    if (agentNumber.length != 10) {
        alert("Agent Number should be 10 digit!");
        return false;
    }
    
    if (agentNumber.indexOf(".") != -1) {
            alert("Agent number should be digits only!");
            return false;
        }
        
    var agentNumberExist = checkAgentNumber("create",agentNumber,"");
    if (agentNumberExist == "true") {
        alert("Agent Number already exists!");
        return false;
    }

    //validate toatal mins
    if (totalMinutes == null || totalMinutes == "") {
        alert("Please give total minutes!");
        return false;
    }
    if (isNaN(totalMinutes)) {
        alert("Please give numeric value!");
        return false;
    }
    if (!(totalMinutes > 0)) {
        alert("Please give total minutes greater than zero!");
        return false;
    }
    if (totalMinutes%1 != 0) {
        alert("Total minutes cannot be a decimal value!");
        return false;
    }
   
    var availableMin = getCompanyRemainMinToAllocate("create",companyId,"");
    //if parseInt(availableMin)=-1 means, company chargin type is Call_Charge or RentalAndCallCharge. This validation only applicable when comapny charging type is Rental.
    if(parseInt(parseInt(availableMin))!=-1 && parseInt(totalMinutes)>parseInt(availableMin)){
        if(parseInt(availableMin)==0){
            alert("Your company has no more free minutes!");
        }else{
        alert("Your company remain only "+availableMin+" minutes!");
        }
        return false;
    }
}

function checkAgentName(type,agentName,companyId,agentId) {
    
    $.ajax({
        type: "GET",
        url: 'checkAgentName.htm',
        async: false,
        data: ({
            type:type,
            agentName: agentName,
            companyId:companyId,
            agentId:agentId
        }),
        dataType: "html",
        success: function(result) {
            //resut = true if agent name already taken; else response will be set to 'false'
           
            response = result;//(result == 'true') ? false : true;
        }
    })
   
    return response;
}

function checkAgentNumber(type,agentNumber,agentId) {
    $.ajax({
        type: "GET",
        url: 'checkAgentNumber.htm',
        async: false,
        data: ({
            type:type,
            agentNumber: agentNumber,
            agentId:agentId
        }),
        dataType: "html",
        success: function(result) {
            //resut = true if agent number alrady taken; if sa response will be set to 'false'
            //If agent number is avilable response will be true
            response = result;//(result == 'true') ? false : true;
        }
    })
    
    return response;
}

/*
 * To assing minutes to call for this agent it shoul Check Comapny allocated minites and minutes 
 * assigned to other agents.
 * Comapny_Mins - TotalOf_OtherAgents_Mins = currentAgent_total_Available_Minutes 
 * (substract Company Total mins by sum of other agents allocated mins) 
 */
function getCompanyRemainMinToAllocate(type,companyId,agentId) {
    
    var response;
    $.ajax({
        type: "GET",
        url: 'getCompanyRemainMinToAllocate.htm',
        async: false,
        data: ({
            type:type,
            companyId: companyId,
            agentId:agentId
        }),
        dataType: "html",
        success: function(result) {
            
            response = result;//company available minutes 
        }
    });
    
    return response;
}

function validateAgentEditForm() {
    var agentId = $("#agentId").val();
    var companyId = $("#companyId").val();
    var agentName = $("#agentName").val();
    var agentNumber = $("#agentNumber").val();
    var totalMinutes = $("#totalMinutes").val();
    
//    //validate agent name
//    if (agentName == null || agentName == "") {
//        alert("Agent name cannot be blank!");
//        return false;
//    }
//    if (agentName.length > 20) {
//        alert("Agent name should be less than 20 characters!");
//        return false;
//    }
//    var nameExist = checkAgentName("create", agentName, companyId, agentId);
//    if (nameExist == "true") {
//        alert("This Name already taken!");
//        return false;
//    }
//    
    //validate agent number
    if (agentNumber == null || agentNumber == "") {
        alert("Please give agent phone number!");
        return false;
    }
    if (isNaN(agentNumber) || agentNumber.indexOf(" ") != -1)
    {
        alert("Please enter a numeric value for the phone number.");
        return false;
    }
//    if (agentNumber.substring(0, 3) != "077" || agentNumber.length != 10) {
//        alert("Agent Number should be 077xxxxxxx format!");
//        return false;
//    }
    if (agentNumber.length != 10) {
        alert("Agent Number should be 10 digit!");
        return false;
    }
    var agentNumberExist = checkAgentNumber("edit",agentNumber,agentId);
    if (agentNumberExist == "true") {
        alert("Agent Number already exists!");
        return false;
    }
    
    //validate toatal mins
    if (totalMinutes == null || totalMinutes == "") {
        alert("Please give total minutes!");
        return false;
    }
    if (isNaN(totalMinutes)) {
        alert("Please give numeric value!");
        return false;
    }
    if (!(totalMinutes > 0)) {
        alert("Please give total minutes greater than zero!");
        return false;
    }
    
    var availableMin = getCompanyRemainMinToAllocate("edit",companyId,agentId);
    
    //if parseInt(availableMin)=-1 means, company is not allocated predefined minutes as it Charging type is "Call Charge"
    if(parseInt(parseInt(availableMin))!=-1 && parseInt(totalMinutes)>parseInt(availableMin)){
        alert("Current company remain only "+availableMin+" minutes!");
        return false;
    }
}

/*
 * below code is for doing validation using jquery validation plugin
 * 
 */
/*
$().ready(function() {
    $.validator.addMethod("checkAgentNumber", function(value, element) {
        $.ajax({
            type: "GET",
            url: 'checkAgentNumber.htm',
            async: false,
            data: ({
                agentNumber: value
            }),
            dataType: "html",
            success: function(result) {
                //resut = true if agent number alrady taken; if sa response will be set to 'false'
                //If agent number is avilable response will be true
                response = (result == 'true') ? false : true;
            }
        })
        
        return response;
    }, "This Number already taken!");

    $.validator.addMethod("minutesValue", function(value, element) {
        //phone_number = phone_number.replace(/\s+/g, ""); 
        return this.optional(element) || value > 0;
    }, "Minutes should be greater than 0 ");

    $("#createAgent").validate({
        errorLabelContainer: "#validationError",
        rules: {
            agentName: "required",
            agentNumber: "required",
            totalMinutes: "required",
            agentName: {
                required: true,
            },
                    agentNumber: {
                required: true,
                maxlength: 10,
                minlength: 10,
                checkAgentNumber: true
            },
            totalMinutes: {
                required: true,
                numberValue: true
            }
        },
        messages: {
            agentName: {
                required: "Agent Name cannot be empty! "
            },
            agentNumber: {
                required: "Agent Number cannot be empty! ",
                minlength: "Agent Number should be 10 digits! ",
                maxlength: "Agent sNumber should be 10 digits! ",
                checkAgentNumber: "Agent Number already taken! "
            },
            totalMinutes: {
                required: "Agent Number cannot be empty! ",
                numberValue: "Total Minutes should be greater than 0 "
            }
        }
    });
});

*/