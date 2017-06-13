/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//this function validates user form
function validateUserForm()
{
    var typeId;
    typeId = $("#typeId").val();
    var companyId;
    companyId = $("#companyId").val();

    if (typeId == null || typeId == -1) {
        alert("Please select a user type.");
        return false;
    }
    if (companyId == 0) {
        alert("Please select a company to create a company admin.");
        return false;
    }
    if ((document.forms["create"]["userName"].value) == "" ||
            (document.forms["create"]["fullName"].value) == "" || (document.forms["create"]["phone"].value) == "" ||
            (document.forms["create"]["email"].value) == "" || (document.forms["create"]["typeId"].value) == "" ||
            (document.forms["create"]["password"].value) == "" || (document.forms["create"]["passwordReType"].value) == "")
    {
        alert("Please fill all the required fields.");
        return false;

    }
    else {
        var sizeValidate = validateCorporateUserSizes();

        if (sizeValidate == false)
        {

            return false;
        }
        else {

            var emailValidate = validateEmail();
            if (emailValidate == false)
            {
                return false;
            }
            else {
                var phoneValidate = validatePhone();
                if (phoneValidate == false)
                {
                    return false;
                }
                else {
                    var passwordValidate = validatePassword();
                    if (passwordValidate == false)
                    {
                        return false;
                    }
                }
            }
        }
    }
}
//this function validates user email
function validateEmail()
{

    var x = document.forms["create"]["email"].value;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= x.length)
    {
        alert("e-mail address is not valid");
        return false;
    }

}


//this function validates user phone    
function validatePhone()
{
    var x = document.forms["create"]["phone"].value;

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
//this function validates user form sizes
function validateCorporateUserSizes()

{
    if ((document.forms["create"]["userName"].value.length) > 15)
    {
        alert("User name is too long!");
        return false;
    }
    else
    {
        if ((document.forms["create"]["fullName"].value.length) > 20)
        {
            alert("Full name is too long!");
            return false;
        }
        else
        {
            if ((document.forms["create"]["password"].value.length) > 9)
            {
                alert("Password is too long!");
                return false;
            }
            else {
                if ((document.forms["create"]["passwordReType"].value.length) > 9)
                {
                    alert("Password is too long!");
                    return false;
                }
            }
        }

    }
}
//this function validates user passwords
function validatePassword()
{

    if (!((document.forms["create"]["password"].value) == (document.forms["create"]["passwordReType"].value)))

    {
        alert("Passwords do not match!");
        return false;

    }
}
//this function validates agent form
function validateAgentForm() {
    if ((document.forms["create"]["agentName"].value) == "" ||
            (document.forms["create"]["agentNumber"].value) == "" || (document.forms["create"]["totalMinutes"].value) == "") {

        alert("Please fill all the required fields.");
        return false;
    }
    else {
        var sizeValidate = validateAgentSizes();

        if (sizeValidate == false)
        {

            return false;
        }

        else {
            var phoneValidate = validateAgentPhone();
            if (phoneValidate == false)
            {
                return false;
            }
            else {
                var minValidate = validateAgentMins();
                if (minValidate == false) {
                    return false;
                }
            }
        }
    }
}
//this function validates agent form sizes
function validateAgentSizes() {
    if ((document.forms["create"]["agentName"].value.length) > 15)
    {
        alert("Agent name is too long!");
        return false;
    }

}
//this function validates agent phone
function validateAgentPhone()
{
    var x = document.forms["create"]["agentNumber"].value;

    if (isNaN(x) || x.indexOf(" ") != -1)
    {
        alert("Please enter a numeric value for the phone number. Ex: 0777123456");
        return false;
    }
    if (x.length != 10)
    {
        alert("Please enter 10 digits for the phone number. Ex: 0777123456");
        return false;
    }

}
//this function validates number of agent minutes
function validateAgentMins() {
    var x = document.forms["create"]["totalMinutes"].value;
    if (isNaN(x) || x.indexOf(" ") != -1)
    {
        alert("Please enter a numeric value for the total minutes.");
        return false;
    }
    if (x.length > 3)
    {
        alert("Number of minutes are too high!");
        return false;
    }
    if (x == 0) {
        alert("Number of minutes cannot be zero!");
        return false;
    }

}
// this function validates the company form
function validateCompanyForm() {

    if ((document.forms["create"]["companyName"].value) == "" ||
            (document.forms["create"]["contactPersonName"].value) == ""
            || (document.forms["create"]["numberOfAgents"].value) == ""
            || (document.forms["create"]["email"].value) == "") {

        alert("Please fill all the required fields.");
        return false;
    }
    
        var emailValid = validateEmail();
        var emailCheck = checkCompanyEmailExist();
        if(emailValid==false){
            return false;
        }
        if(emailCheck==false){
            return false;
        }
        var sizeValidate = validateCompanyFormSizes();

        if (sizeValidate == false)
        {

            return false;
        }

        else {
            var phoneValidate = validateCompanyPhone();
            var phoneCheck =checkCompanyPhoneExist();
            if (phoneValidate == false)
            {
                return false;
            }
            if (phoneCheck == false)
            {
                return false;
            }
            else {
                var minValidate = validateMaxAgents();
                if (minValidate == false) {
                    return false;
                }
            }
        }
    
//
    var planId;
    planId = $("#planId").val();
    var authId;
    authId = $("#authId").val();

    if (planId == null || planId == 0) {
        alert("Please select a charging plan.");
        return false;
    }
    if (authId == null || authId == 0) {
        alert("Please select an authentication model.");
        return false;
    }
    //

}
//this function validate comapny phone
function validateCompanyPhone()
{
    var x = document.forms["create"]["contactNumber"].value;

    if (isNaN(x) || x.indexOf(" ") != -1)
    {
        alert("Please enter a numeric value for the phone number. Ex: 0777123456");
        return false;
    }
    if (x.length != 10)
    {
        alert("Please enter 10 digits for the phone number. Ex: 0777123456");
        return false;
    }

}
//this function validates company form sizes
function validateCompanyFormSizes()

{
    if ((document.forms["create"]["companyName"].value.length) > 25)
    {
        alert("Company name is too long!");
        return false;
    }
    else
    {
        if ((document.forms["create"]["contactPersonName"].value.length) > 20)
        {
            alert("Contact person name is too long!");
            return false;
        }


    }
}
//this function validates max agents for the company
function validateMaxAgents() {
    var x = document.forms["create"]["numberOfAgents"].value;
    if (isNaN(x) || x.indexOf(" ") != -1)
    {
        alert("Please enter a numeric value for the number of agents.");
        return false;
    }
    if (x.length > 2)
    {
        alert("Number of agents are too high!");
        return false;
    }
    if (x == 0) {
        alert("Number of agents cannot be zero!");
        return false;
    }

}
//this function validates edituser page
function validateEditUserForm() {

    if ((document.forms["create"]["userName"].value) == "" ||
            (document.forms["create"]["fullName"].value) == "" || (document.forms["create"]["phone"].value) == "" ||
            (document.forms["create"]["email"].value) == "")
    {
        alert("Please fill all the required fields.");
        return false;

    }
    else {
        var sizeValidate = validateEditUserSizes();

        if (sizeValidate == false)
        {

            return false;
        }
        else {

            var emailValidate = validateEmail();
            if (emailValidate == false)
            {
                return false;
            }
            else {
                var phoneValidate = validatePhone();
                if (phoneValidate == false)
                {
                    return false;
                }
            }
        }
    }

    if ($("#pwchange").attr('checked')) {



        if ((document.forms["create"]["password"].value) == "" ||
                (document.forms["create"]["passwordReType"].value) == "") {

            alert("Password cannot be empty.");
            return false;
        }
        else {
            if (!((document.forms["create"]["password"].value) == (document.forms["create"]["passwordReType"].value)))

            {
                alert("Passwords do not match!");
                return false;

            }
            else {
                if ((document.forms["create"]["password"].value.length) > 9)
                {
                    alert("Password is too long!");
                    return false;
                }

            }

        }


    }




}
//this function validates edituser form sizes
function validateEditUserSizes()

{
    if ((document.forms["create"]["fullName"].value.length) > 20)
    {
        alert("Full name is too long!");
        return false;
    }
    else {
        if (document.forms["create"]["email"].value.length > 28)
        {
            alert("Email address is too long");
            return false;
        }
    }
}

/*
 * check company email already exists
 * below method added by dewmini
 */
function checkCompanyEmailExist(){
    var email=$("#email").val();
    $.ajax({
       
            url: 'checkCompanyEmail.htm',
            async:false,
            data: ({
                email:email
            
            }),

            success: function(result) {
                
                if(result == 'yes'){
                    alert("This email address already taken!");
                    return false;
                }else if(result == 'exception'){
                    alert("Error occured while checking given email!");
                    return false;
                }
            //  $("#msg").html(result);
            }
        
        });
}

/*
 * check company Contact number already exists
 * below method added by dewmini
 */
function checkCompanyPhoneExist(){
    var phone=$("#contactNumber").val();
    $.ajax({
       
            url: 'checkCompanyPhone.htm',
            async:false,
            data: ({
                phone:phone
            
            }),

            success: function(result) {
                
                if(result == 'yes'){
                    alert("This Contact Number already taken!");
                    return false;
                }else if(result == 'exception'){
                    alert("Error occured while checking given Contact Number!");
                    return false;
                }
            //  $("#msg").html(result);
            }
        
        });
}