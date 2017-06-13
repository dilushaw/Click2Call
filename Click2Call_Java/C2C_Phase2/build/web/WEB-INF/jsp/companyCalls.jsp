<%-- 
    Document   : companyCalls
    Created on : Jun 19, 2013, 3:00:51 PM
    Author     : Dewmini
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">

        <title>Click2Call - Reports</title>


        <script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="resources/js/jquery.validate.js"></script>
        <script type="text/javascript" src="resources/scripts/reportValidation.js"></script>

        <style type="text/css" title="currentStyle">
            @import "resources/DataTables/media/css/demo_page.css";
            @import "resources/DataTables/media/css/demo_table.css";
            div.table_Wrapper { border:10px solid blue; }
        </style>
        <script type="text/javascript" language="javascript" src="resources/DataTables/media/js/jquery.dataTables.js"></script>
        <script type="text/javascript" charset="utf-8">
            $(document).ready(function() {
                $('#reportTable').dataTable({
                });
            });
        </script>

        <!-- js and styles for date picker -->
        <link rel="stylesheet" type="text/css" media="all" href="resources/css/jsDatePick_ltr.min.css" />
        <!--    <script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script>-->
        <script type="text/javascript" src="resources/js/jsDatePick.min.1.3.js"></script>
        <script type="text/javascript">
            $().ready(function() {
                new JsDatePick({
                    useMode: 2,
                    target: "fromDate",
                    dateFormat: "%d-%m-%Y"
                            /*selectedDate:{				This is an example of what the full configuration offers.
                             day:5,						For full documentation about these settings please see the full version of the code.
                             month:9,
                             year:2006
                             },
                             yearsRange:[1978,2020],
                             limitToToday:false,
                             cellColorScheme:"beige",
                             dateFormat:"%m-%d-%Y",
                             imgPath:"img/",
                             weekStartDay:1*/
                });
                new JsDatePick({
                    useMode: 2,
                    target: "toDate",
                    dateFormat: "%d-%m-%Y"
                });
            });
        </script>

    </head>

    <body>
        <!--        <table border="0" align="right">
                    <tr>
                        <td><input type="submit" value="Call History" class="button_bk" onclick="javascript:window.location.href = 'reports.htm';
                        return false;" /></td>
                        <td><input type="submit" value="Company Charges" class="button_bk" onclick="javascript:window.location.href = 'viewComapnyCharges.htm';
                        return false;" /></td>
                    </tr>
                </table>-->
        <B>Company Charging Plan Name : </B><c:out value="${charginPlan.name}" /> &nbsp;&nbsp;
        <c:choose>
            <c:when test="${charginPlan.chargingType.typeId eq 1}">
                <strong>|</strong>&nbsp;&nbsp;<B>Monthly Rental: </B>${charginPlan.monthlyRental}&nbsp;&nbsp;
                <strong>|</strong>&nbsp;&nbsp;<B>Free Minutes: </B>${charginPlan.allocatedMinutes}&nbsp;&nbsp;
            </c:when>
            <c:when test="${charginPlan.chargingType.typeId eq 2}">
                <strong>|</strong>&nbsp;&nbsp;<B>Per-Minute Charge: </B>${charginPlan.perminuteCharge}&nbsp;&nbsp;
            </c:when>
            <c:when test="${charginPlan.chargingType.typeId eq 3}">
                <strong>|</strong>&nbsp;&nbsp;<B>Monthly Rental: </B>${charginPlan.monthlyRental}&nbsp;&nbsp;
                <strong>|</strong>&nbsp;&nbsp;<B>Free Minutes: </B>${charginPlan.allocatedMinutes}&nbsp;&nbsp;
                <strong>|</strong>&nbsp;&nbsp;<B>Per-Minute Charge: </B>${charginPlan.perminuteCharge}&nbsp;&nbsp;
            </c:when>
        </c:choose>
        <form:form name="companyCallsForm" id="companyCallsForm" commandName="companyCallsForm" action="companyCalls.htm" method="POST">
            <table align="center">
                <tr>
                <tr>
                    <td colspan="2"><div id="reportMsg" style="color: red;"></div></td>
                </tr>
                <td><label for="From">From</label>
                    <form:input name="fromDate" id="fromDate" path="fromDate" readonly="true"/>
                    <label for="To">To</label>
                    <form:input name="toDate" id="toDate" path="toDate" readonly="true"/>
                </td>
                <td><input type="submit" name="viewReport" id="viewReport" value="View" class="button">
                <input type="button" name="clearReport" id="clearReport" value="Clear" class="button" onclick="javascript:window.location.href = 'companyCalls.htm';
                return false;"></td>
            </tr>
        </table>
        <hr>

        <table cellpadding="0" cellspacing="0" border="0" class="display" id="reportTable">
            <thead>
                <tr>
                    <th></th>
                    <th>Agent Name</th>
                    <th>Agent Number</th>
                    <th>Customer Number</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="call" items="${calls}"  varStatus="rowCount">
                    <tr>
                        <td>${rowCount.index+1}</td>
                        <td>${call.agents.name}</td>
                        <td>${call.agents.number}</td>
                        <td>${call.customerNumber}</td>
                        <td>${call.startTime}</td>
                        <td>${call.endTime}</td>
                        
                    </tr>
                </c:forEach>

            </tbody>
        </table>




    </form:form>


</body>


</html>