<%-- 
    Document   : loggeduserscreen
    Created on : Nov 3, 2012, 5:47:16 PM
    Author     : Hasala
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<c:choose>
    <c:when test="${sessionScope.userType eq 'Dialog Admin'}">
        <!-- Display home page for DSA-->
        <%@ include file="company_dashboard.jsp" %>

    </c:when>

    <c:when test="${sessionScope.userType eq 'Company Admin'}">
        <!-- Display home page for CA-->
        <%--<%@ include file="ca/createuser.jsp" %>--%>
        <%@ include file="agent_dashboard.jsp" %>
    </c:when>

</c:choose>

