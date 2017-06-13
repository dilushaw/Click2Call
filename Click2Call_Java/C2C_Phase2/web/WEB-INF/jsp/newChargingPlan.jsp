<%-- 
    Document   : newChargingPlan
    Created on : Jun 13, 2013, 10:56:48 AM
    Author     : Dewmini
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>New Charging Plan</title>
        <link rel="stylesheet" type="text/css" href="resources/click2call.css"/>
        <script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="resources/scripts/cplanValidation.js"></script>
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
        <script type="text/javascript">

//            $('#chargingTypeId').change(function() {
//                alert("test");
//                if ($(this).find('option:selected').text() == "Call Charge") {
//                    $("#monthlyRental").attr('disabled', 'disabled');
//                }
//            });
            
        </script>
    </head>
    <body>
        <form:form name="newChargingPlan" commandName="newChargingPlanForm" action="newChargingPlan.htm" method="POST">

            <table  border="0" align="center" cellpadding="5">
                <tr><th colspan="3"><font color="green">${successMessage}</font></th></tr>
                <!--<tr>
                    <td colspan="4"><div style="color:red"></div></td>
                </tr> -->
                <tr>
                    <td><label>Charging Plan Name</label>*</td>
                    <td><form:input  path="planName" name="planName" id="planName" /></td>
                </tr>
                <tr>
                    <td><label>Charging Type</label>*</td>
                    <td><c:choose>
                            <c:when test="${not empty chargeTypes}">

                                <form:select path='chargingTypeId' id="chargingTypeId" onchange="displayChargingDetails(this.value)">
                                    <form:option value="" label="Please Select"/>
                                    <c:forEach items="${chargeTypes}" var="cType">
                                        <form:option value="${cType.typeId}">${cType.typeName}</form:option>
                                    </c:forEach>
                                </form:select>
                            </c:when>
                            <c:otherwise>No predefined charging types found.</c:otherwise>
                        </c:choose></td>
                </tr>
                <!-- Radio Buttons --> 
                <!-- end radio buttons -->
                <tr>
                    <td><label>Monthly Rental</label></td>
                    <td><form:input id="monthlyRental" path="monthlyRental" disabled="${rentalStatus}" maxlength="10" /></td>
                </tr>
                <tr>
                    <td><label>Allocated Minutes</label></td>
                    <td><form:input id="allocatedMinutes" path="allocatedMinutes" disabled="${allocMinStatus}" maxlength="5" /></td>
                </tr>
                <tr>
                    <td><label>Per Minute Charge</label></td>
                    <td><form:input id="perMinCharge" path="perMinCharge" disabled="${perMinStatus}" maxlength="6" /></td>
                </tr>
                <tr><td></td>
                    <td></td>
                </tr>

                <!-- Reset and Submit Buttons -->
                <tr align="center">
                    <td colspan="3">
                        <input type="submit" name="buttonSave" id="buttonSave" value="Save" class="button" onClick="return validateNewChargingPlan()">
                        <input type="reset" name="buttonReset" id="buttonReset" value="Reset" class="button"/>
                <s:reset value="Reset" />                                </td>
        </tr>
        <!-- End Buttons -->
        <tr><td colspan="3"></td></tr>   
    </table>
</form:form>
</body>
</html>
