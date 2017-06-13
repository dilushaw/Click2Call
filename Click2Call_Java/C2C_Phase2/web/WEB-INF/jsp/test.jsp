<%-- 
    Document   : test
    Created on : Oct 24, 2012, 12:38:02 PM
    Author     : Hasala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link rel="stylesheet" type="text/css" href="resources/click2call.css"/>
         
          <script type="text/javascript">
            <jsp:include page="scripts/jQuery/jquery-1.6.2.min.js" />
        </script>
    </head>
    <body>
        <div id="Barcode" style="width:800" >
             <div id="HeaderBar"> </div>
        
        <form:form name="create" commandName="test" action="test.htm" method="POST">
        <table width="80%" border="0" align="center">
            <tr>
                    <td><B>Company Name</B></td>
                    <td><form:input path="companyName" name="textCompanyName" id="textCompanyName"/></td>
                    <td><form:errors path="companyName" /></td>
            </tr>
             <tr>
                    <td><B>Company Name</B></td>
                    <td><form:input path="contactPersonName" name="textContactPersonName" id="textContactPersonName"/></td>
                    <td><form:errors path="contactPersonName" /></td>
            </tr>
           
            <tr align="center">
                    <td colspan="3">
                        <input type="submit" name="buttonSave" id="buttonSave" value="Save" class="button"/>
                        <input type="reset" name="buttonReset" id="buttonReset" value="Reset" class="button"/>

                    </td>
            </tr>
        </table>
        </form:form>
        </div>
    </body>
</html>
