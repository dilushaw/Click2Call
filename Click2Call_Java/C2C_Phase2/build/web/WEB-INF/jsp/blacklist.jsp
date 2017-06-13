<%--
    Document   : login
    Created on : Oct 25, 2012, 9:15:36 AM
    Author     : Hasala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log In</title>
        


        <script type="text/javascript" language="javascript" src="resources/DataTables/media/js/jquery.js"></script>
        <script type="text/javascript" language="javascript" src="resources/scripts/blacklist.js"></script>





      <link rel="stylesheet" href="resources/jquery-ui-1.9.1.custom/development-bundle/themes/base/jquery.ui.all.css">
	   <!-- <script src="resources/jquery-ui-1.9.1.custom/development-bundle/jquery-1.8.2.js"></script>-->
	<script src="resources/jquery-ui-1.9.1.custom/development-bundle/external/jquery.bgiframe-2.1.2.js"></script>
	<script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.core.js"></script>
	<script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.widget.js"></script>
	<script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.mouse.js"></script>
	<script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.draggable.js"></script>
	<script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.position.js"></script>
	<script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.resizable.js"></script>
	<script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.dialog.js"></script>
	<script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.effect.js"></script>
	<script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.effect-blind.js"></script>
	<script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.effect-explode.js"></script>
<!--	<link rel="stylesheet" href="resources/jquery-ui-1.9.1.custom/development-bundle/demos/demos.css">-->


<style>

    .ui-widget-header {
        background:#990000;
  color: #FFFFFF;
  font-style: italic;
  font-size: 12px;
}

.ui-widget-content{
  /*  background: #E4E4E4;*/
   /* background-image:url(resources/images/background.png);
    background-repeat: no-repeat;
    background-position: right bottom ;
*/
font: 90%/1.48em "Lucida Grande", Verdana, Arial, Helvetica, sans-serif;
}



</style>
    <script type="text/javascript">
$(document).ready(function() {


    $("#newBlacklist").keypress(function(e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            //display error message att specific loaction
            $("#errmsg").html("Digits Only").show().fadeOut("slow");
            //alert the error message
            //alert("Digits Only");
            return false;
        }
    });
});
    </script>



    </head>

    <Div id="pop"   style="display:none; width: 50px;height: 50px;"   align="center">
        <p align="left"><label>Enter New Number</label></p>
        <input type="text" id="newBlacklist" name="newBlacklist" maxlength="10"><span id="errmsg" style="color: red"></span>
        
       </Div>


    <body>

        <form:form  name="blacklist" id="blacklist" commandName="blacklist" action="blacklist.htm" method="POST">
            
            
            <table width="100%" border="0" align="center" cellpadding="5">

             <td width="68%">
                <font color="red">${errorMessage}</font>
                </td>


                <tr><td>

                       <!-- <button name="new" style="border: none;background-color: transparent" onclick="return get_blacklist();" >-->
<!--                       <button name="new" style="border: none;background-color: transparent" onclick="newNumber()" >-->
<!--                        <button name="new" style="border: none;background-color: transparent" onclick=" return PopupEditUser();" >
                            <input type="image" src="resources/images/blacklist.png" alt="New Black list Number"  >
                        </button>-->
<input type="button" value="Black list Number" onclick="PopupEditUser()" class="button_bk" />
<input type="hidden" id="newNumber" name="newNumber" value="">
                        <input type="hidden" id="action" name ="action" value="">

                    </td></tr>
                <tr>
                    <td>

                <ul>


                    <c:forEach var="item" items="${blacklist}" >
                        <li>${item.number}
                          <%--  <button style="border: none;background-color: transparent" name="${item.number}" onclick="deletenumber()">--%>
                                <input type="image" src="resources/home_files/remove.png" alt="Submit button" onclick="return deletenumber('${item.number}')" >
                         <!--   </button></li>-->
                        </li>
                        
                    </c:forEach>



                </ul>

                        </td>
            <%--        <td align="right"><label>User name:</label>&nbsp;&nbsp;&nbsp;</td>

                <!-- Reset and Submit Buttons -->
                <tr align="center">
                    <td colspan="4">
                        <input type="submit" name="buttonSave" id="buttonSave" value="Save" class="Button" onClick="saveUserData('Add')"/>
                        <input type="reset" name="buttonReset" id="buttonReset" value="Reset" class="Button" onclick="resetUserData()"/>
                        <s:reset value="Reset" />                    </td>
                </tr>
                <!-- End Buttons -->
               <tr><td colspan="4"><div id="div1"></div></td></tr>


--%>
</tr>

</table>


    </form:form>

    </body>
</html>
