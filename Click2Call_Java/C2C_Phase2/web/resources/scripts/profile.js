$(document).ready(function() {
            $("#pwchange").removeAttr('checked');

} );

function pwstate(){
 if($("#pwchange").attr('checked')){
    
     $("#lblpw").show();
     $("#lblRepw").show();
     $("#txtpw").show();
     $("#txtRepw").show();

 }
 else {
    
     $("#lblpw").hide();
      $("#lblRepw").hide();
      $("#txtpw").hide();
     $("#txtRepw").hide();

 }

}


 function validateProfileForm(){

     if((document.forms["profile"]["fullName"].value)==""||
        (document.forms["profile"]["email"].value)==""||(document.forms["profile"]["phone"].value)==""){

       alert("Please fill all the required fields.");  
       return false; 
        }
             else{
       var sizeValidate=validateProfileFormSizes();
         
       if(sizeValidate==false)
           {
               
            return false;    
           }

          else{
              var phoneValidate=validateProfilePhone();
         if(phoneValidate==false)
          {
       return false; 
          }
            else{
                var emailValidate = validateEmail();
                if(emailValidate==false){
                   return false;
                                      }
                }
              }
        }
        
         if($("#pwchange").attr('checked')){


         
               if((document.forms["profile"]["password"].value)==""||
                  (document.forms["profile"]["reTypePassword"].value)==""){

                  alert("Password cannot be empty.");  
                  return false; 
                    }
                    else{
               if(!((document.forms["profile"]["password"].value)==(document.forms["profile"]["reTypePassword"].value)))

                {
                    alert("Passwords do not match!");  
                    return false; 
 
                }
                else{
                        if((document.forms["profile"]["password"].value.length)>9)
                                 {
                                     alert("Password is too long!");
                                     return false;
                                 }

                }
                    
                }


         }


 }

//this function validates profile form sizes
function validateProfileFormSizes(){
    if((document.forms["profile"]["fullName"].value.length)>100)  
                 {
                    alert("Full Name is too long!");  
                    return false;    
                 }
          else{
                   if (document.forms["profile"]["email"].value.length>50)
                   {
                       alert("Email address is too long");
                       return false;
                   }
               }            
                            
}
//this function validates user profile phone
    function validateProfilePhone()
{ 
        var x = document.forms["profile"]["phone"].value;

       	 if(isNaN(x)|| x.indexOf(" ")!=-1)
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
//this function validates user profile email
function validateEmail()
    {
       
     var x=document.forms["profile"]["email"].value;
var atpos=x.indexOf("@");
var dotpos=x.lastIndexOf(".");
if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
  {
  alert("e-mail address is not valid");
  return false;
  }
   
    }
