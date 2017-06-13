
function get_blacklist(){

  var number =prompt("Please eneter the new number ","");
  if(number != null){
     // alert(number);
    $("#newNumber").val(number);
   // validate add
      return true;

  }


  return false;

}

function deletenumber(no){
    
    $("#action").val('D');
     $("#newNumber").val(no);

    return true;
    
}


function PopupEditUser()
{
      

$("#newBlacklist").val('');
    $( "#pop" ).dialog({
        title: "Black list New Number",
        height: 190,
        width:270,
        modal:true,
      buttons:{
            "Save":{
              text:'Save',
              
              click:function(){
                  $("#action").val('A');
                  var number = $("#newBlacklist").val();
                    if(number != null){
     // alert(number);
                    $("#newNumber").val(number);
                    }
                  $("#pop").dialog('close');
                 
                  submit=true;
                  $('#blacklist').submit();
                      

                  return true;
              }
            },
            "Cancel":{
                text:'Cancel',
                click:function(){
            $("#pop").dialog('close');

        }

            }
        },
        close: function(event,ui){
            $("#pop").dialog('destroy');
             

            return true;
        }
    });
   

return false;
}