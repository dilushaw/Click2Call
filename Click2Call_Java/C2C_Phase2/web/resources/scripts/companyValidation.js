/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory. All Rights Reserved. This
 * software is the proprietary information of Dialog-University of Moratuwa Mobile Communications Research
 * Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the software as it sees fit.
 * 
 */

// this function validates the company form
function validateCompanyForm() {
    var companyName = $("#companyName").val();
    var contactPersonName = $("#contactPersonName").val();
    var contactNumber = $("#contactNumber").val();
    var email = $("#email").val();
    var numberOfAgents = $("#numberOfAgents").val();
    var planId = $("#planId").val();
    var authId = $("#authId").val();

    if (validateCompanyName(companyName) == false) {
        return false;
    }

    if (vlaidateContactPerson(contactPersonName) == false) {
        return false;
    }

    if (validateContactNumber(contactNumber) == false) {
        return false;
    }
    
    var phoneExist = checkCompanyPhoneExist("create","");
    if (phoneExist == "yes") {
        alert("Contact Number already exists!");
        return false;
    }

    if (validateEmail(email) == false) {
        return false;
    }
    var y = checkCompanyEmailExist("create", "");
    if (y == "yes") {
        alert("Email already exists!");
        return false;
    }

    if (validateNumberofAgents(numberOfAgents) == false) {
        return false;
    }

    if (planId == null || planId == "") {
        alert("Please select a charging plan.");
        return false;
    }

    if (authId == null || authId == "") {
        alert("Please select an authentication model.");
        return false;
    }
}

function validateCompanyName(companyName) {
    ////////////Check companyName///////////////
    if (companyName == "" || companyName == null) {
        alert("Company Name cannot be empty!");
        return false;
    }
    if (companyName.length > 50) {
        alert("Company Name should be less than 50 characters!");
        return false;
    }
    var compnayExists = checkCompanyName();
    if (compnayExists == "yes") {
        alert("Company Name already taken!");
        return false;
    }
}


function vlaidateContactPerson(contactPersonName) {
    /////////////check contactPersonName////////////////
    if (contactPersonName == "" || contactPersonName == null) {
        alert("Contact Person Name cannot be empty!");
        return false;
    }
    if (contactPersonName.length > 50) {
        alert("Contact Person Name should be less than 50 characters!");
        return false;
    }
}

function validateContactNumber(contactNumber) {
    ////////////Check contactNumber///////////////
    if (contactNumber == "" || contactNumber == null) {
        alert("Contact Number cannot be empty!");
        return false;
    }
    if (isNaN(contactNumber) || contactNumber.indexOf(" ") != -1)
    {
        alert("Please enter a numeric value for the phone number. Ex: 0777123456");
        return false;
    }
    if (contactNumber.length != 10 || contactNumber.substring(0, 3) != "077") {
        alert("Contact Number should be 077xxxxxxx format!");
        return false;
    }
    
}


function validateEmail(email) {
    ////////////Check email///////////////
    if (email == "" || email == null) {
        alert("Email cannot be empty!");
        return false;
    }
    if (email.length > 100) {
        alert("Email should be less than 100 characters!");
        return false;
    }
    var isValidEmail = validateEmailFormat();
    if (isValidEmail == "invalid") {
        alert("Invalid Email address!");
        return false;
    }

}

function validateNumberofAgents(numberOfAgents) {
    ////////////Check numberOfAgents///////////////
    if (numberOfAgents == "" || numberOfAgents == null) {
        alert("Number of Agents cannot be empty!");
        return false;
    }
    if (numberOfAgents <= 0) {
        alert("Invalid Number of Agents!");
        return false;
    }
    if (isNaN(numberOfAgents)) {
        alert("Please enter numeric value!");
        return false;
    }
    if (numberOfAgents > 20) {
        alert("Maximum number of agents should be less than 20!");
        return false;
    }
    if (numberOfAgents % 1 != 0 ) {
        alert("Number of agents cannot be a decimal value!");
        return false;
    }
}

/*
 * check company Name already exists
 * below method added by dewmini
 */
function checkCompanyName() {
    var status;
    var companyName = $("#companyName").val();
    $.ajax({
        url: 'checkCompanyName.htm',
        async: false,
        data: ({
            companyName: companyName

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

/*
 * check company email already exists
 * type = create / edit . if it going to be check at the time of edit corporate; the currently given email should exclude from the check
 * below method added by dewmini
 */
function checkCompanyEmailExist(type, companyId) {
    var status;
    var email = $("#email").val();
    $.ajax({
        url: 'checkCompanyEmail.htm',
        async: false,
        data: ({
            email: email,
            type: type,
            companyId: companyId

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

/*
 * check company Contact number already exists
 * below method added by dewmini
 * checkType -> "edit","create" which is used in edit form or new company creation form
 */
function checkCompanyPhoneExist(checkType,companyId) {
    var status;
    var phone = $("#contactNumber").val();
    //var companyId = $("#companyId").val();
    $.ajax({
        url: 'checkCompanyPhone.htm',
        async: false,
        data: ({
            phone: phone,
            type: checkType,
            companyId: companyId
        }),
        success: function(result) {

            if (result == 'yes') {
                status = result;
            } else if (result == 'exception') {
                status = result;
            }
            //  $("#msg").html(result);
        }

    });
    
    return status;
}



//this function validates user email
function validateEmailFormat()
{
    var status;
    var x = $("#email").val();
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= x.length)
    {
        status = "invalid";
    }
    return status;
}


/*************** Company Edit - Validation ***********************/

// this function validates the company edit form
function validateCompanyEditForm() {
    var companyId = $("#companyId").val();
    var contactPersonName = $("#contactPersonName").val();
    var contactNumber = $("#contactNumber").val();
    var email = $("#email").val();
    var numberOfAgents = $("#numberOfAgents").val();
    var planId = $("#planId").val();
    var authId = $("#authId").val();

    if (vlaidateContactPerson(contactPersonName) == false) {
        return false;
    }

if (validateContactNumber(contactNumber) == false) {
        return false;
    }
    
    var phoneExist = checkCompanyPhoneExist("edit",companyId);
    if (phoneExist == "yes") {
        alert("Contact Number already exists!");
        return false;
    }
    
    if (validateEmail(email) == false) {
        return false;
    }
    var y = checkCompanyEmailExist("edit", companyId);
    if (y == "yes") {
        alert("Email already exists!");
        return false;
    }

    if (validateNumberofAgents(numberOfAgents) == false) {
        return false;
    }
    
    if (planId == null || planId == "") {
        alert("Please select a charging plan.");
        return false;
    }

    if (authId == null || authId == "") {
        alert("Please select an authentication model.");
        return false;
    }
}