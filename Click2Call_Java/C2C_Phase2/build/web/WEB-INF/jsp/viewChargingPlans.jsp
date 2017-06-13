<%-- 
    Document   : viewChargingPlans
    Created on : May 28, 2013, 11:30:28 AM
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
        <title>Charging Plans</title>
        <link rel="stylesheet" type="text/css" href="resources/css/click2call.css"/>
    </head>
    <body>
        <h1>Details....</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Package Name</th>
                    <th>Monthly Rental</th>
                    <th>Free Minutes</th>
                    <th>Per minute Charge</th>
                    <th>Type</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach  var="plan" items="${charginPlans}">
                <tr>
                    <td>${plan.name}</td>
                    <td>${plan.monthlyRental}</td>
                    <td>${plan.allocatedMinutes}</td>
                    <td>${plan.perminuteCharge}</td>
                    <td>${plan.chargingType.typeName}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
