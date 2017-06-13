<%-- 
    Document   : CreateAgent
    Created on : Oct 10, 2012, 3:32:24 AM
    Author     : DialogLab1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Company Agent</title>
        <!--Include Resources -->
        <link rel="stylesheet" type="text/css" href="resources/click2call.css"/>
        <script type="text/javascript" src="resources/js/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="resources/js/jquery.validate.js"></script>
        <script type="text/javascript" src="resources/scripts/agentValidation.js"></script>
    </head>
    <body>
        <form:form name="createAgent" id="createAgent" commandName="createAgent" action="createAgent.htm" method="POST">

        

            <table width="80%" border="0" align="center" cellpadding="5">
                <tr><th colspan="4"><font color="green">${successMessage}</font></th></tr>
             <!-- <tr>
                    <td colspan="4"><div style="color:red"></div></td>
              </tr> -->
              <tr>
                    <td><B>Company Name:</B></td>
                    <td>&nbsp;</td>
                    <td><form:input path="companyName" name="companyName" id="companyName" disabled="${'true'}"/>
                        <form:hidden  path="companyName" name="companyName" id="companyName" />
                    <form:hidden  path="companyId" name="companyId" id="companyId" /></td>
                    <td><form:errors path="companyName" /></td>
              </tr>

              <tr>
                    <td><B>Agent Name:*</B></td>
                    <td>&nbsp;</td>
                    <td><form:input path="agentName" name="agentName" id="agentName" maxlength="20"/></td>
                    <td></td>
              </tr>

              <tr><td><B>Agent Number:*</B></td>
                  <td>&nbsp;</td>
                  <td><form:input path="agentNumber" name="agentNumber" id="agentNumber" maxlength="10" /></td>
                    <td></td>
              </tr>
              <tr><td><B>Total Minutes:*</B></td>
                  <td>&nbsp;</td>
                  <td><form:input path="totalMinutes" name="totalMinutes" id="totalMinutes" maxlength="5" /></td>
                   <td></td>
              </tr>
                <!-- Radio Buttons -->
              <tr><td><B>Status:</B></td>
                     <td>&nbsp;</td>
                    <td><form:radiobutton path="agentStatus" value="1" label="Enable" checked="checked"/>
                        <form:radiobutton path="agentStatus" value="0" label="Disable" />
                    </td><td>
                    </td>
              </tr> <!-- end radio buttons -->
              





              <!-- Reset and Submit Buttons -->
              <tr align="center">
                    <td colspan="4">
                        <input type="submit" name="buttonSave" id="buttonSave" value="Save"  class="button" onclick="return validateAgentForm()"/>
                        <input type="reset" name="buttonReset" id="buttonReset" value="Reset" class="button"/>
                        <s:reset value="Reset" />
                    </td>
              </tr>
                <!-- End Buttons -->
                <tr><td colspan="4"><div id="validationError"></div></td></tr>
            </table>
        </form:form>
    </body>
</html>
