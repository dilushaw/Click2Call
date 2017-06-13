<%-- 
    Document   : errorPopup
    Created on : Apr 29, 2013, 12:08:22 PM
    Author     : Dewmini
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>ERROR!</title>
    <link rel="stylesheet" type="text/css" href="resources/css/click2call.css"/>
    
    
</head>
<body>

    <table width="100%" border="0">
  <tr>
    <td><div align="center"><font color="red">${errorMessage}</font></div></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>

</body>

</html>