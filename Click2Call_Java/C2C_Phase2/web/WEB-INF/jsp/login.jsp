<%-- 
    Document   : login
    Created on : Oct 25, 2012, 9:15:36 AM
    Author     : Hasala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Click2Call - Log In</title>
        <link rel="stylesheet" type="text/css" href="resources/css/click2call.css"/>
    </head>
    <body>

        <div id="wrapper">  
            <div id="header">
                <div id="Logos">
                    <h1 id="Dialog"></h1>
                    <h2 id="C2CLogo"></h2>
                </div>
            </div> 


            <div id="HeaderBar"> </div>
            <div id="Content">

                <br><br><br><br>
                <form:form commandName="login" action="home.htm" method="POST">
                    <table align="center" width="400" >
                        <tr>
                            <td colspan="2" class="loginacc">Login your account:</td>
                        </tr>
                        <tr><td colspan="2"><font color="green">${successMessage}</font></td></tr>
                        <tr><td colspan="2"><font color="red">${errorMessage}</font></td></tr>
                        <tr><td><B>User Name</B></td>
                            <td>
                                <form:input path="loginName" name="textLoginName" id="textLoginName" maxlength="20" style="width:150px;" />
                            </td>
                        </tr>
                        <tr><td><B>Password</B></td>
                            <td>
                                <form:password path="loginPassword" name="textLoginPassword" id="textLoginPassword" maxlength="20" style="width:150px;" />
                            </td>
                        </tr>
                        <tr><td><B>Company</B></td>
                            <td>
                                <form:input path="loginCompany" name="textLoginCompany" id="textLoginCompany" maxlength="50" style="width:150px;" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr><td></td>
                            <td>
                                <input type="submit" name="buttonSave" id="buttonSave" value="Login" class="button">
                            </td>
                        </tr>
                        
                    </table>
                </form:form>
                <br><br><br><br>
            </div>


            <div id="footer">
                &copy; Dialog-University of Moratuwa Mobile Communications Research Laboratory. All Rights Reserved.
            </div>

        </div>

    </body>
</html>

