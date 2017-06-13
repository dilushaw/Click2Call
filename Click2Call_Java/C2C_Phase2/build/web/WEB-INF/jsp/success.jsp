<%-- 
    Document   : success
    Created on : Nov 5, 2012, 10:30:16 AM
    Author     : Hasala
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>
        <link rel="stylesheet" type="text/css" href="resources/css/click2call.css"/>
    </head>
    <body>
      <table width="100%" border="0">
            <tr>Back to <
                <c:choose>
        <c:when test="${sessionScope.userType eq 'Dialog Admin'}">
            <li><a href="company_dashboard.htm" >Company Dashboard</a></li>
            <td width="68%">
                <font color="green">${successMessage}</font>
                </td>
        </c:when>
            
            <c:when test="${sessionScope.userType eq 'Company Admin'}">
            <li><a href="agent_dashboard.htm" >Agent Dashboard</a></li>
            <td width="68%">
                <font color="green">${successMessage}</font>
                </td>
        </c:when>
            </c:choose>
                 </tr>
                  
        </table>
    </body>
</html>
