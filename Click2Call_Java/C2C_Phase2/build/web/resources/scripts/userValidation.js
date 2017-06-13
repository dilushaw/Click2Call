/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory. All Rights Reserved. This
 * software is the proprietary information of Dialog-University of Moratuwa Mobile Communications Research
 * Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the software as it sees fit.
 * 
 */

//this function validates user form
function validateUserForm()
{
    var typeId = $("#typeId").val();
    var companyId = (typeId == 4 ? $("#companyId").val() : 1);//1=Dialog company id
    var userName = $("#userName").val();
    var fullName = $("#fullName").val();
    var email = $("#email").val();
    var phone = $("#phone").val();
    var password = $("#password").val();
    var passwordReType = $("#passwordReType").val();

    if (userName == null || userName == "" || userName.length > 20) {
        alert("Please give user name!");
        return false;
    }
    if (userName == null || userName == "" || userName.length > 20) {
        alert("Username should be less than 20 characters!");
        return false;
    }


    if (typeId == null || typeId == -1) {
        alert("Please select a user type.");
        return false;
    }
    if (typeId == "4" && (companyId == "" || companyId == null)) {
        //if (companyId == 0) {
        alert("Please select a company to create a company admin.");
        return false;
    }

    var userExist = checkUserName(companyId, userName, typeId);
    if (userExist == "yes") {
        alert("User Name already taken!")
        return false;
    }

    if (fullName == null || fullName == "") {
        alert("Please give full name!");
        return false;
    }
    if (fullName == null || fullName == "" || fullName.length > 100) {
        alert("Full name should be less than 100 characters!");
        return false;
    }

    if (email == null || email == "") {
        alert("Please give email!");
        return false;
    }
    if (email.length > 50) {
        alert("Email should be less than 50 characters!");
        return false;
    }

    var emailValidate = validateEmail(email);
    if (emailValidate == false)
    {
        return false;
    }
    var userEmailExist = checkUserEmailExist(companyId, email, "", "create");
    if (userEmailExist == "yes") {
        alert("This Email already taken!")
        return false;
    }

    var phoneValidate = validatePhone(phone);
    if (phoneValidate == false)
    {
        return false;
    }

    var passwordValidate = validatePassword(password, passwordReType);
    if (passwordValidate == false)
    {
        return false;
    }
}

//this function validates user email
function validateEmail(email)
{

    var x = email;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= x.length)
    {
        alert("e-mail address is not valid");
        return false;
    }

}


//this function validates user phone    
function validatePhone(phone)
{
    var x = phone;
    if (x == null || x == "") {
        alert("Please give phone number");
        return false;
    }
    if (isNaN(x) || x.indexOf(" ") != -1)
    {
        alert("Please enter a numeric value for the phone number. Ex:0777123456");
        return false;
    }
    if (x.length != 10)
    {
        alert("Please enter 10 digits for the phone number. Ex:0777123456");
        return false;
    }

}

//this function validates user passwords
function validatePassword(password, passwordReType)
{
    if ($("#pwchange").attr('checked')) {
        if (password == null || password == "") {
            alert("Please give password!");
            return false;
        }
        if (password.length > 20) {
            alert("Password should be less than 20 characters!");
            return false;
        }
        if (!(password == passwordReType))

        {
            alert("Passwords do not match!");
            return false;

        }
    }
}

/*
 * check user Name already exists
 */
function checkUserName(companyId, userName, typeId) {
    var status;

    $.ajax({
        url: 'checkUserName.htm',
        async: false,
        data: ({
            companyId: companyId,
            userName: userName,
            typeId: typeId
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
 * check user email already exists
 */
function checkUserEmailExist(companyId, email, userId, actionType) {
    var status;
    $.ajax({
        url: 'checkUserEmail.htm',
        async: false,
        data: ({
            companyId: companyId,
            email: email,
            actionType: actionType,
            userId: userId
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

function validateEditUserForm() {

    var userId = $("#userId").val();
    var fullName = $("#fullName").val();
    var email = $("#email").val();
    var phone = $("#phone").val();
    var password = $("#password").val();
    var passwordReType = $("#passwordReType").val();

    if (fullName == null || fullName == "") {
        alert("Please give full name!");
        return false;
    }
    if (fullName == null || fullName == "" || fullName.length > 100) {
        alert("Full name should be less than 100 characters!");
        return false;
    }

    if (email == null || email == "") {
        alert("Please give email!");
        return false;
    }
    if (email.length > 50) {
        alert("Email should be less than 50 characters!");
        return false;
    }

    var emailValidate = validateEmail(email);
    if (emailValidate == false)
    {
        return false;
    }
    var userEmailExist = checkUserEmailExist("", email, userId, "edit");
    if (userEmailExist == "yes") {
        alert("This Email already taken!")
        return false;
    }

    var phoneValidate = validatePhone(phone);
    if (phoneValidate == false)
    {
        return false;
    }

    var passwordValidate = validatePassword(password, passwordReType);
    if (passwordValidate == false)
    {
        return false;
    }
}

