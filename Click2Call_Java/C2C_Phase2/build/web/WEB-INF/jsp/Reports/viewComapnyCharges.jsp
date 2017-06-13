<%-- 
    Document   : viewComapnyCharges
    Created on : May 28, 2013, 10:28:41 AM
    Author     : Dewmini
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
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
            div.table_Wrapper {
                border:10px solid blue;
            }
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
        <table border="0" align="right">
            <tr>
                <td><input type="submit" value="Call History" class="button_bk" onclick="javascript:window.location.href = 'reports.htm';
                return false;" /></td>
                <td><input type="submit" value="Company Charges" class="button_bk" onclick="javascript:window.location.href = 'viewComapnyCharges.htm';
                return false;" /></td>
            </tr>
        </table>
        
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="reportTable">
                <thead>
                    <tr>
                        <th>Company Name</th>
                        <th>Phone Number</th>
                        <th>Charging Package</th>
                        <th>Used Minutes</th>
                        <th>Free Minutes</th>
                        <th>Rental Amount</th>
                        <th>Outstanding</th>
                        <th>Credit Limit</th>
                    </tr>
                </thead>
                <!--            <tfoot>
                                        <tr>
                                            <th>User Name</th>
                                            <th>User Type</th>
                                            <th>Telephone</th>
                                            <th>Email</th>
                                            <th>Actions</th>
                                        </tr>
                                    </tfoot>-->
                <tbody>
                    <c:forEach var="keyAsCompany" items="${companyData}">
                        <c:set value="${keyAsCompany.key.usedSeconds}" var="usedSec" />
                        
                        
                        <c:set value="${usedSec%60}" var="sec" />
                        <fmt:formatNumber value="${(usedSec-sec)/60}" var="min" maxFractionDigits="0" />
                        
                        <tr>
                            <td>${keyAsCompany.key.companyName}</td>
                            <td>${keyAsCompany.key.contactNumber}</td>
                            <td>${keyAsCompany.key.chargingPlan.name}</td>
                            <td>${min}:${sec}</td>
                            <td>${keyAsCompany.key.chargingPlan.allocatedMinutes}</td>
                            <td>${keyAsCompany.key.chargingPlan.monthlyRental}</td>
                            <td>${keyAsCompany.value[0]}</td>
                            <td>${keyAsCompany.value[1]}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        
    </body>
</html>
