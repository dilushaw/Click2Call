<%-- 
    Document   : createCompany
    Created on : Oct 24, 2012, 4:47:36 PM
    Author     : Hasala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Company</title>
        <!-- Include Resources -->
        <link rel="stylesheet" type="text/css" href="resources/click2call.css"/>
        <style type="text/css" media="all">@import "resources/timePicker/timePicker.css";</style>
        <script type="text/javascript" src="resources/js/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="resources/scripts/companyValidation.js"></script>

        <style type="text/css">

            input {
                margin:0;
                padding:0;
            }
            pre {
                background:#fff;
                border:1px solid #ddd;
                padding:4px;
            }
            .error {
                border:1px solid red;
            }
        </style>
        <!--        <script type="text/javascript" src="resources/timePicker/jquery.js"></script>-->
        <script type="text/javascript" src="resources/timePicker/jquery.timePicker.js"></script>
        <script type="text/javascript">
            jQuery(function() {
                // Default.

                $("#time2").timePicker({
                    startTime: "00.00", // Using string. Can take string or Date object.
                    endTime: new Date(0, 0, 0, 23, 30, 0), // Using Date object.
                    show24Hours: false,
                    separator: '.',
                    step: 15});


                $("#time3").timePicker({
                    startTime: "00.00", // Using string. Can take string or Date object.
                    endTime: new Date(0, 0, 0, 23, 30, 0), // Using Date object.
                    show24Hours: false,
                    separator: '.',
                    step: 15});


            });
        </script>
    </head>
    <body>
        <!-- <div id="Barcode"> -->
        <form:form name="create" commandName="createCompany" action="createCompany.htm" method="POST">
            <table  border="0" align="center" cellpadding="5">
                <tr><th colspan="4"><font color="green">${successMessage}</font></th></tr>
                <!--<tr>
                    <td colspan="4"><div style="color:red"></div></td>
                </tr> -->
                <tr>
                    <td><B>Company Name*</B></td>
                    <td><form:input  path="companyName" name="companyName" id="companyName" maxlength="50" />
                    </td>
                    <td><form:errors path="companyName" />
                    </td>
                </tr>
                <tr>
                    <td><B>Contact Person Name*</B></td>
                    <td><form:input  path="contactPersonName" name="contactPersonName" id="contactPersonName" maxlength="50" />
                    </td>
                    <td><form:errors path="contactPersonName" />
                    </td>
                </tr>
                <tr>
                    <td><B>Billing Number*</B></td>
                    <td><form:input  path="contactNumber" name="contactNumber" id="contactNumber" placeholder="077xxxxxxx" maxlength="10" />
                    </td>
                    <td>
                    </td>
                </tr>
                <tr>
                    <td><B>Email*</B></td>
                    <td><form:input  path="email" name="email" id="email" placeholder="example@example.com" maxlength="100" />
                    </td>
                    <td>
                    </td>
                </tr>
                <!-- Radio Buttons -->
                <tr>
                    <td><B>Status</B></td>
                    <td><form:radiobutton path="companyStatus" value="1" label="Enable" checked="checked"/>
                        <form:radiobutton path="companyStatus" value="0" label="Disable" />
                    </td><td><form:errors path="companyStatus" cssClass="error" />
                    </td>
                </tr> <!-- end radio buttons -->
                <tr>
                    <td><B>Max Number of Agents*</B></td>
                    <td><form:input  path="numberOfAgents" name="numberOfAgents" id="numberOfAgents" maxlength="2" />
                    </td>
                    <td><form:errors path="numberOfAgents" />
                    </td>
                </tr>
                <tr><td><B>Charging Plan*</B></td>
                    <td><c:choose>
                            <c:when test="${not empty plans}">

                                <form:select path='planId' id="planId" >
                                    <form:option value="" label="Please Select"/>
                                    <c:forEach items="${plans}" var="plan">
                                        <form:option value="${plan.planId}">${plan.name}</form:option>
                                    </c:forEach>
                                </form:select>
                            </c:when>
                            <c:otherwise>No Charging Plans found.</c:otherwise>
                        </c:choose></td>
                    <td></td>
                </tr>
                <tr><td><B>Authentication Model*</B></td>
                    <td><c:choose>
                            <c:when test="${not empty models}">

                                <form:select path='authId' id="authId" >
                                    <form:option value="" label="Please Select"/>
                                    <c:forEach items="${models}" var="model">
                                        <form:option value="${model.authId}">${model.name}</form:option>
                                    </c:forEach>
                                </form:select>
                            </c:when>
                            <c:otherwise>No Authentication Models found.</c:otherwise>
                        </c:choose></td>
                    <td></td>
                </tr>
                <tr>
                    <td><B>Business Hours*</B></td>
                    <td>From <form:input  path="startTime" id="time2" size="10" value="08.00 AM" readonly="true" />
                        To <form:input path="endTime" id="time3" size="10" value="05.00 PM" readonly="true" />
                    </td>

                    <td><form:errors path="startTime" />
                    </td>
                </tr>
                <!-- Reset and Submit Buttons -->
                <tr align="center">
                    <td colspan="4">
                        <input type="submit" name="buttonSave" id="buttonSave" value="Save" class="Button" onClick="return validateCompanyForm()">
                        <input type="reset" name="buttonReset" id="buttonReset" value="Reset" class="Button"/>
                <s:reset value="Reset" /> 
                                </td>
            </tr>
            <!-- End Buttons -->
            <tr><td colspan="4"><div id="div1"></div></td></tr>   

        </table>
    </form:form>
    <!--  </div> -->
</body>
</html>
