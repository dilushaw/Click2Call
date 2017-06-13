




function validateUser(){
  var userName="";

  userName=$("#loginName").val();

  var password=$("#loginPassword").val();

  var company= $("#loginCompany").val();

  if((userName=="")||(password=="")||(company=="")){


      alert("emptyfield");
      return false;
  }

  return true;

/*    $.ajax({
        url: 'validateUser.htm',
        async: false,
        data: ({userName:userName,password:password,company:company}),
      
        success: function(data) {


       // alert("OK");
       // alert(data);
           if(data=='-2'){
                alert("UserName and Password cannot be empty!");
            }
            else if(data=='-1'){
                alert("Invalid user!");
            }

                
            else
            {
//                alert ("hello");

                document.location.href = 'company_dashboard.htm';
            }
        }
    });
*/
}


/*
function logoutUser(){

    // deleteCookie('sessionKey');
    //document.location.href = 'login.htm';
}
*/

  /*  </script>*/
