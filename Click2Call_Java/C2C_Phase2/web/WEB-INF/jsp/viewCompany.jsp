<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>View Company</title>
		
	<link rel="stylesheet" type="text/css" href="resources/click2call.css"/>

    </head>
    <body>
	
	

        <!-- This is the form for Create New Company. -->
        <form:form name="create" commandName="viewCompany" action="viewCompany.htm" method="POST">

            <table border="0" align="center" cellpadding="5">

              <tr>
                    <td colspan="4"><div style="color:red"></div></td>
              </tr>
               <tr>
                    <td><label>Company Name:</label></td>
                    <td>&nbsp;</td>
                  <td><label>${company.companyName}</label></td>
               </tr>
						
                <tr>
                    <td><label>Contact Person Name:</label></td>
                    <td>&nbsp;</td>
                  <td><label>${company.contactName}</label></td>
                   
                </tr>
                <tr>
                <td><label>Contact Person Number:</label></td>
                    <td>&nbsp;</td>
                <td><label>${company.contactNumber}</label></td>
                    
                </tr>
                <tr>
                <td><label>Email:</label></td>
                    <td>&nbsp;</td>
                <td><label>${company.email}</label></td>
                    
                </tr>
                <tr><td><label>Max Agents:</label></td>
                  <td>&nbsp;</td>
                  <td><label>${company.maxAgents}</label></td>
                    
                </tr>
                <tr><td><label>Status:</label></td>
                  <td>&nbsp;</td>
                  <td>
                  <c:choose>
                  <c:when test="${company.companyStatus eq '1'}">Enabled</c:when>
                  <c:when test="${company.companyStatus eq '0'}">Disabled</c:when>
                  <c:otherwise>Deleted</c:otherwise>
                  </c:choose></font>
                  </td>
                   
                </tr>    
    <%--              <tr><td><label>Business Start Time:</label></td>
                     <td>&nbsp;</td>
                     <td><label>${company.startTime}</label></td>
                  </tr>
                  <tr><td><label>Business End Time:</label></td>
                     <td>&nbsp;</td>
                     <td><label>${company.endTime}</label></td>
                  </tr> --%>
                
                  
               
		<tr><td><label>Secret Key:</label></td>
                  <td>&nbsp;</td>
                  <td><label>${company.secretKey}</label></td>
               
                </tr>
                <tr>
                    <td><label>Charging Plan:</label></td>
                    <td>&nbsp;</td>
                  <td><label>${company.chargingPlan.name}</label></td> 
                </tr>
                <tr>
                    <td><label>Authentication Model:</label></td>
                    <td>&nbsp;</td>
                  <td><label>${company.authModel.name}</label></td> 
                </tr>
                <tr><td colspan="4"><div id="div1"></div></td></tr>                            
            </table>
    </form:form>		
    </body>

</html>
