<%-- 
    Document   : createUser
    Created on : Nov 3, 2012, 12:15:31 PM
    Author     : Hasala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>User</title>
    <link rel="stylesheet" type="text/css" href="resources/click2call.css"/>
    <script type="text/javascript" src="resources/js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="resources/scripts/userValidation.js"></script>


    <script type="text/javascript" src="resources/scripts/user.js"></script>


    <script type="text/javascript" charset="utf-8">
        $(document).ready(function() {
            $("#pwchange").removeAttr('checked');
        });
    </script>


</head>
<body>

    <!-- This is the form for Create New User. -->
    <form:form name="editUser" commandName="editUser" action="editUser.htm" method="POST">

        <table border="0" align="center" cellpadding="5">
            <tr><th colspan="2"><font color="green">${successMessage}</font></th></tr>
            <td><form:hidden path="userId" name="userId" id="userId" /></td>
            <td><form:errors path="userId" /></td>
            <tr>
                <td>Username:</td>

                <td><form:input path="userName" name="userName" id="userName" class="disabledTextbox" /></td>
                <td><form:errors path="userName" /></td>
            </tr>
            <!--Begin New Code -->

            <tr>
                <td>User Type:</td>
                <td><form:input path="userType" name="userType" id="userType" class="disabledTextbox" /></td>
                <td><form:errors path="userType" /></td>    
            </tr>
            <tr>
                <td>Company:</td>
                <td><form:input path="companyName" name="companyName" id="companyName" class="disabledTextbox" /></td>
                <td><form:errors path="companyName" /></td>    

            </tr>

            <!--End New Code -->                                      

            <tr>
                <td>Full Name:*</td>

                <td><form:input path="fullName" name="fullName" id="fullName" size="50" maxlength="100"/></td>
                <td><form:errors path="fullName" /></td>
            </tr>
            <tr>
                <td>Email:*</td>

                <td><form:input path="email" name="email" id="email" size="30" maxlength="50" /></td>
                <td><form:errors path="email" /></td>
            </tr>
            <tr><td>Phone:*</td>

                <td><form:input path="phone" name="phone" id="phone" maxlength="10" /></td>
                <td><form:errors path="phone" /></td>
            </tr>
            <!-- Radio Buttons -->
            <tr><td>Status:</td>

                <td><form:radiobutton path="status" value="1" label="Enable" checked="checked"/>
                    <form:radiobutton path="status" value="0" label="Disable" />
                </td><td><form:errors path="status" cssClass="error" />
                </td>
            <tr>
                <td colspan="3"><form:checkbox path="pwchange" value="pw" id="pwchange" onclick="pwstate()" checked="false" ></form:checkbox><b> &nbsp;Change Password</b>
                    </td>
                </tr>

                <tr>
                    <td><div id="lblpw" style="display:none;position:relative;  margin: auto;"><label>New Password*:</label></div>
                    </td>
                    <td><div id="txtpw" style="display:none;position:relative;  margin: auto;"><form:password path="password" id="password"></form:password></div>
                    </td>
                </tr>

                <tr><td><div id="lblRepw" style="display:none;position:relative;  margin: auto;"><label> ReType Password*:</label></div>
                    </td>
                    <td><div id="txtRepw" style="display:none;position:relative;  margin: auto;"><form:password path="passwordReType" id="passwordReType"></form:password></div>
                    </td>
                </tr>





                <!-- Reset and Submit Buttons -->
                <tr align="center">
                    <td colspan="4">
                        <input type="submit" name="buttonSave" id="buttonSave" value="Save" class="button" onClick="return validateEditUserForm()"/>
                        <input type="reset" name="buttonReset" id="buttonReset" value="Reset" class="button" onclick="resetUserData()"/>
                <s:reset value="Reset" />                    </td>
        </tr>
        <!-- End Buttons -->
        <tr><td colspan="4"><div id="div1"></div></td></tr>
    </table>


</form:form>
<!-- End of New User Creation Form -->
</body>

</html>