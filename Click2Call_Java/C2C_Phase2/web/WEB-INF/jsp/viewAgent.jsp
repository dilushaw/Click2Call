
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Company Agent</title>

        <link rel="stylesheet" type="text/css" href="resources/click2call.css"/>



    </head>
    <body>



        <!-- This is the form for Create New Corporate User. -->
        <form:form name="create" commandName="viewAgent" action="viewAgent.htm" method="POST">

            <table  border="0" align="center" cellpadding="5">
                <tr>
                    <tr>
                        <td colspan="4"><div style="color:red"></div></td>
                    </tr>
                    <tr>
                        <td align="right"><label>Agent Name:</label></td>
                        <td>&nbsp;</td>
                        <td>${agent.name}</td>

                    </tr>

                    <tr>
                        <td align="right"><label>Agent Key:</label></td>
                        <td>&nbsp;</td>
                        <td>${agent.agentKey}</td>

                    </tr>
                    <tr><td align="right"><label>Agent Number:</label></td>
                        <td>&nbsp;</td>
                        <td>${agent.number}</td>

                    </tr>
                    <tr><td align="right"><label>Total Minutes:</label></td>
                        <td>&nbsp;</td>
                        <td>${agent.totalMins}</td>

                    </tr>
                    <tr><td align="right"><label>Used Minutes:</label></td>
                        <td>&nbsp;</td>
                        <td>${agent.usedSeconds}</td>

                    </tr>
                    <tr><td align="right"><label>iFrame:</label></td>
                        <td>&nbsp;</td>
                        <td>

                            <c:out value='<iframe src="http://localhost:8080/Click2call/Click2Call.htm?agent=${agentKey}"  height="220px" width="350px"><p>Your browser does not support iframes.</p> </iframe>' />
                        </td>

                    </tr> 






            </table>

        </form:form>
        <!-- End of New User Creation Form -->
    </body>

</html>
