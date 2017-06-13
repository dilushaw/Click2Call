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
    <title>Corporate User</title>
    <link rel="stylesheet" type="text/css" href="resources/click2call.css"/>
    <script type="text/javascript" src="resources/js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="resources/scripts/userValidation.js"></script>
    <script type="text/javascript">
        function showCompanies(id) {
            // alert(id);
            // document.getElementById("testSpan"+id).innerHTML="<table border='1'><tr><td>Id</td><td>"+id+"</td></tr><tr><td>LOB ID</td><td>"+name+"</td></tr></table>";
            // alert(id);
            if (id == -1 || id == 1 || id == 2 || id == 3) {
                document.getElementById("companySpan").innerHTML = "Companies are applicable only for Company Admin user type.";
            } else {



                document.getElementById("companySpan").innerHTML = "Loading...";
                $.ajax({
                    url: 'loadCompaniesDropDown.htm',
                    async: false,
                    data: ({id: id}),
                    success: function(data) {
                        //  alert(data);
                        document.getElementById("companySpan").innerHTML = data;

                    }

                });
            }
        }
    </script>
</head>
<body>

    <!-- This is the form for Create New User. -->
    <form:form name="createUser" id="createUser" commandName="createUser" action="createUser.htm" method="POST">

        <table width="80%" border="0" align="center" cellpadding="5">
            <tr><th colspan="4"><font color="green">${successMessage}</font><font color="red">${errorMessage}</font></th></tr>
            <!--                <tr>
                                <td colspan="4"><div style="color:red"></div></td>
                            </tr>-->
            <tr>
                <td><B>Username:*</B></td>

                <td><form:input path="userName" name="userName" id="userName" maxlength="20" /></td>
                <td><form:errors path="userName" /></td>
            </tr>
            <!--Begin New Code -->

            <tr>
                <td><B>User Type:</B></td>

                <td width="628">
                    <c:choose>
                        <c:when test="${not empty types}">

                            <form:select path='typeId' name="typeId" id="typeId" onchange="showCompanies(this.value)" >
                                <form:option value="-1" label="Please Select"/>
                                <c:forEach items="${types}" var="type">
                                    <form:option value="${type.typeId}" path="typeId">${type.typeName}</form:option>
                                </c:forEach>
                            </form:select>
                        </c:when>
                        <c:otherwise>No User Types Found</c:otherwise>
                    </c:choose>
                </td>
            <tr>
                <td><B>Company:</B></td>

                <td width="628">
                    <span id="companySpan"><form:hidden path="companyId" id="companyId" />Companies are applicable only for Company Admin user type.</span>
                </td>
            </tr>

            <!--End New Code -->                                      

            <tr>
                <td><B>Full Name:*</B></td>

                <td><form:input path="fullName" name="fullName" id="fullName" size="50" maxlength="100"/></td>
                <td><form:errors path="fullName" /></td>
            </tr>
            <tr>
                <td><B>Email:*</B></td>

                <td><form:input path="email" name="email" id="email" size="30" maxlength="50" placeholder="example@example.com" /></td>
                <td><form:errors path="email" /></td>
            </tr>
            <tr><td><B>Phone:*</B></td>

                <td><form:input path="phone" name="phone" id="phone"  maxlength="10" placeholder="077xxxxxxx" /></td>
                <td><form:errors path="phone" /></td>
            </tr>
            <!-- Radio Buttons -->
            <tr><td><B>Status:</B></td>

                <td><form:radiobutton path="status" value="1" label="Enable" checked="checked"/>
                    <form:radiobutton path="status" value="0" label="Disable" />
                </td><td><form:errors path="status" cssClass="error" />
                </td>
            </tr> <!-- end radio buttons -->

            <tr>
                <td align="right" colspan="4"><hr></td>
            </tr>

            <tr><td><B>Password:*</B></td>

                <td><form:password path="password" name="password" id="password" /></td>
                <td><form:errors path="password" /></td>
            </tr>

            <tr><td><B>ReType Password:*</B></td>

                <td><form:password path="passwordReType" name="passwordReType" id="passwordReType" /></td>
                <td><form:errors path="passwordReType" /></td>
            </tr>



            <!-- Reset and Submit Buttons -->
            <tr align="center">
                <td colspan="4">
                    <input type="submit" name="buttonSave" id="buttonSave" value="Save" class="button" onClick="return validateUserForm()"/>
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