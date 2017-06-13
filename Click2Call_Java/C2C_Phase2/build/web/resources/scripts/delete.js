/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function deleteAgent(delAgentId){
    //alert("on delete");
    $("#deleteSuccess").html("");
    $("#deleteError").html("");
    var msg = '';
    var agentId;
    agentId = delAgentId;
    var x=window.confirm("Are you sure you want to delete the agent?")
    if (x){

        $.ajax({
       
            url: 'deleteAgent.htm',
            async:false,
            data: ({
                agentId:agentId
            
            }),

            success: function(result) {
                //$("#msg").html(result);
                if(result == 'yes'){
                    //$("#deleteMsg").html("Sucessfully deleted ...........................");
                    window.location.href = "agent_dashboard.htm?delete="+result;
                //window.location.href = "corporateUsers.htm?delete=true";
                }else{
                    $("#deleteError").html("Delete unsuccessfull");  
                }
            //  $("#msg").html(result);
            }
        
        });
    }else{
        //        $("#deleteSuccess").html("");
        //        $("#deleteError").html("");
        //window.location.replace("corporateUsers.htm");
        return;
    }
    
}

function deleteAdminByCSA(delUserId){
    //alert("on delete");
    $("#deleteSuccess").html("");
    $("#deleteError").html("");
    var msg = '';
    var userId;
    userId = delUserId;
    var x=window.confirm("Are you sure you want to delete the admin user?")
    if (x){

        $.ajax({
       
            url: 'deleteCorporateAdmin.htm',
            async:false,
            data: ({
                userId:userId
            
            }),

            success: function(result) {
                if(result == 'yes'){
                    window.location.href = "corporateAdmins.htm?delete="+result;
                }else{
                    $("#deleteError").html("Delete unsuccessfull");  
                }
            }
        
        });
    }else{
        return;
    }
    
}

function deleteUserByCA(delUserId){
    $("#deleteSuccess").html("");
    $("#deleteError").html("");
    var msg = '';
    var userId;
    userId = delUserId;
    var x=window.confirm("Are you sure you want to delete the user?")
    if (x){

        $.ajax({
       
            url: 'deleteUser.htm',
            async:false,
            data: ({
                userId:userId
            
            }),

            success: function(result) {
                if(result == 'yes'){
                    window.location.href = "allCorporateUsers.htm?delete="+result;
                }else{
                    $("#deleteError").html("Delete unsuccessfull");  
                }
            }
        
        });
    }else{
        return;
    }
    
}

function deleteCampaign(delCampId,userId,row){
    $("#deleteSuccess").html("");
    $("#deleteError").html("");
    var msg = '';
    var campId;
    var userId;
    campId = delCampId;
    userId = userId;
    var x=window.confirm("Are you sure you want to delete the Campaign?")
    if (x){

        $.ajax({
       
            url: 'deleteCampaign.htm',
            async:false,
            data: ({
                userId:userId,
                campId : campId
            
            }),

            success: function(result) {
                if(result == 'yes'){
                    //                        var url = window.location.href;    
                    //                        if (url.indexOf('?') > -1){
                    //                            url += '&delete='+result;
                    //                        }else{
                    //                            url += '?delete='+result;
                    //                        }
                    deleteRow(row);
                    $("#deleteSuccess").html("Campaign Sucessfully Deleted...");
                }else{
                    $("#deleteError").html("Delete unsuccessfull");  
                }
            }
        
        });
    }else{
        return;
    }
}
    
function deleteCampaignCU(delCampId,userId){
    
    $("#deleteSuccess").html("");
    $("#deleteError").html("");
    var msg = '';
    var campId;
    var userId;
    campId = delCampId;
    userId = userId;
    var x=window.confirm("Are you sure you want to delete the Campaign?")
    if (x){

        $.ajax({
       
            url: 'deleteCampaign.htm',
            async:false,
            data: ({
                userId:userId,
                campId : campId
            
            }),

            success: function(result) {
                if(result == 'yes'){
                    window.location.href = "cuDashboard.htm?delete="+result;
                }else{
                    $("#deleteError").html("Delete unsuccessfull");  
                }
            }
        
        });
    }else{
        return;
    }
}

function deleteRow(rowid)  
{   
    var row = document.getElementById(rowid);
    row.parentNode.removeChild(row);
}

function deleteBarcode(delbarcodeId,campaignId){
    
    $("#deleteSuccess").html("");
    $("#deleteError").html("");
    var msg = '';
    var barcodeId;
    barcodeId = delbarcodeId;
    var x=window.confirm("Are you sure you want to delete the barcode?")
    if (x){

        $.ajax({
       
            url: 'deleteBarcode.htm',
            async:false,
            data: ({
                barcodeId:barcodeId
            
            }),

            success: function(result) {
                if(result == 'yes'){
                    window.location.href = "viewCampaign.htm?campaignId="+campaignId+"&delete="+result;
                }else{
                    $("#deleteError").html("Delete unsuccessfull");  
                }
            }
        
        });
       
    }else{
        return;
    }
    
}
