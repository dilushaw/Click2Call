<%-- 
    Document   : editAgent
    Created on : Oct 10, 2012, 3:10:39 AM
    Author     : DialogLab1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        
        <link rel="stylesheet" type="text/css" href="click2call.css"/>
    
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

        <script type="text/javascript" language="javascript" src="resources/DataTables/media/js/jquery.js"></script>
        <script type="text/javascript" src="resources/scripts/profile.js"></script>
        

   </head>
    <body>


        <div id="Barcode" style="width:800" >
        <!-- This is the form to edit user profile. -->
        <form:form name="profile" commandName="my_profile" action="my_profile.htm" method="POST">
    
        <table width="80%" border="0" align="center" cellpadding="5">
             
                <tr><td></td><td><font color="green">${successMessage}</font></td></tr>
                <td><form:input type="hidden" path="userId" id="userId" size="45" maxlength="40"></form:input></td>
                <tr>
                    <td align="right"><label>Username:&nbsp;&nbsp;</label></td>
                    
                    <td>
                      <c:out value="${sessionScope.userName}"></c:out>
                    </td>
                </tr>

                <tr>
                    <td align="right"><label>User Type:&nbsp;&nbsp;</label></td>
                    
                    <td>
                      <c:out value="${sessionScope.userType}"></c:out>
                    </td>
                </tr>

                <tr>
                    <td align="right"><label>Company:&nbsp;&nbsp;</label></td>
                    
                    <td><c:out value="${sessionScope.company}"></c:out>
                    </td>   
                </tr>


                <tr>
                    <td align="right"><label>Full Name*:</label></td>
                    
                    <td><form:input path="fullName" id="fullName" size="45" maxlength="100"></form:input></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right"><label>Email:*</label></td>
                    
                    <td><form:input path="email" id="email" size="45" maxlength="50"></form:input></td>
                    <td></td>
                </tr>

                <tr>
                    <td align="right"><label>Phone:*</label></td>
                    
                    <td><form:input path="phone" id="phone"  maxlength="10"></form:input></td>
                    
                </tr>

                <tr>
                    <td colspan="3"><form:checkbox path="pwchange" value="pw" id="pwchange" onclick="pwstate()" checked="false" ></form:checkbox><b> &nbsp;Change Password</b>
                    </td>
                </tr>

                <tr>
                    <td align="right"><div id="lblpw" style="display:none;position:relative;  margin: auto;"><label>New Password*:</label></div>
                    </td>
                    <td><div id="txtpw" style="display:none;position:relative;  margin: auto;"><form:password path="password" id="password"></form:password></div>
                    </td>               
                </tr>

                <tr><td align="right"><div id="lblRepw" style="display:none;position:relative;  margin: auto;"><label> ReType Password*:</label></div>
                    </td>
                    <td><div id="txtRepw" style="display:none;position:relative;  margin: auto;"><form:password path="reTypePassword" id="reTypPassword"></form:password></div>
                    </td> 
                </tr>




                <!-- Reset and Submit Buttons -->
               <tr align="center">
                    <td colspan="4">
                        <input type="submit" name="buttonSave" id="buttonSave" value="Save" class="button" onClick="return validateProfileForm()"/>
                    </td>
               </tr>
               <!-- End Buttons -->
               <tr><td colspan="4"><div id="div1"></div></td></tr>


        </table>

    </form:form>


	</div>
    </body>

</html>
