<%-- 
    Document   : header
    Created on : Jul 18, 2012, 2:52:23 PM
    Author     : Dewmini
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div id="HeaderTopLinks">
    User: <c:out value="${sessionScope.userName}"/>&nbsp;|&nbsp;Company: <c:out value="${sessionScope.company}"/>&nbsp;|&nbsp;<a href="logout.htm">Logout</a>&nbsp;  
    <br><font style="color: #C60000;"> You are logged in as <c:choose>
        <c:when test="${sessionScope.userType eq 'Dialog Admin'}">Dialog Admin</c:when>
        <c:when test="${sessionScope.userType eq 'Company Admin'}">Company Admin</c:when>
        <c:when test="${sessionScope.userType eq 'Customer Care Admin'}">Customer Care Admin</c:when>
        <c:otherwise>Dialog User</c:otherwise>
    </c:choose></font>
</div>    
    
<div id="Logos">
    <h1 id="Dialog"></h1>
    <h2 id="C2CLogo"></h2>
</div>
<!--#End Logos--> 
