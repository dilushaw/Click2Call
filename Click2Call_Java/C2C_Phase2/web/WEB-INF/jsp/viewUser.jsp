<!-- 
    Document   : createuser
    Created on : Jul 23, 2012, 10:08:26 AM
    Author     : Hasala


-->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>View User</title>
	<link rel="stylesheet" type="text/css" href="resources/click2call.css"/>

    

    </head>
    <body>
	
	

		

        <form:form name="create" commandName="viewUser" action="viewUser.htm" method="POST">

            <table width="80%" border="0" align="center" cellpadding="5">
                    <tr>
                        <td><label>Username:</label></td>
                        <td><label>${user.userName}</label></td>

                    </tr>

                                    <tr>
                        <td><label>User Type:</label></td>
                      <td><label>${user.userType.typeName}</label></td>

                    </tr>
                      <tr>
                        <td ><label>Full Name:</label></td>
                      <td><label>${user.fullName}</label></td>

                    </tr>

                    <tr>
                    <td ><label>Email:</label></td>
                    
                  <td><label>${user.email}</label></td>
                    
                </tr>
                <tr><td ><label>Phone:</label></td>
       
                  <td><label>${user.phone}</label></td>
                   
                </tr>
                 <tr><td ><label>Status:</label></td>
                  
                  <td>
                  <c:choose>
                  <c:when test="${user.userStatus eq '1'}">Enabled</c:when>
                  <c:when test="${user.userStatus eq '0'}">Disabled</c:when>
                  <c:otherwise>Deleted</c:otherwise>
                  </c:choose>
                  </td>
                   
                </tr>
                 <tr><td ><label>Company:</label></td>
                  
                  <td><label>${user.company.companyName}</label></td>
                   
                </tr> 

       
            </table>
    </form:form>	

    </body>

</html>
