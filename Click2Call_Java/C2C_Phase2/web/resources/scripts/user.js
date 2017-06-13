




function checkDelete(){


    alert("hello");
    var r = window.confirm("User will be delete?");
    if(r== true){
        alert("OK");
    }
    else{
        alert("notOk");
    }
    return false;
}

function showcompany(){
    
    
   if(( $("#userType").val())==3){

       $("#divcompanylbl").show();
       $("#divcompanytxt").show();
   }
   else{

       $("#divcompanytxt").hide();
       $("#divcompanylbl").hide();
    
   }
}


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


 function PopupUser(id)
{


     $( "#pop" ).html("<iframe  height='330' width='350' style='border: none' src='viewUser.htm?userId="+id+"'></iframe>").dialog({
 title:"View User Details",
 
 height: 390,
 width: 380,
 modal:true,
 show:"blind",
 hide:"explode",
 close: function(event,ui){
     $("pop").dialog('destroy');

        }
 });
return false;
}


 function PopupEditUser(id)
{


     $( "#pop" ).html("<iframe  height='390' width='500' style='border: none' src='editUser.htm?userId="+id+"'></iframe>").dialog({
title: "Edit User Details",
 height: 450,
 width: 530,
 modal:true,
 show:"blind",
 hide:"explode",
 close: function(event,ui){
     submit=true;
     $("#user_dashboard").submit();
     
     

        }
 });
return false;
}


function PopupUserCreate(){


     $( "#pop" ).html("<iframe  height='450' width='500' style='border: none' src='createUser.htm'></iframe>").dialog({
title: "Create New User",
 height: 550,
 width: 530,
 modal:true,
 show:"blind",
 hide:"explode",
 close: function(event,ui){
    submit=true;
     $("#user_dashboard").submit();
     

        }
 });
return false;

}

/*
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
//}


/*
function logoutUser(){

    // deleteCookie('sessionKey');
    //document.location.href = 'login.htm';
}
*/

  /*  </script>*/
