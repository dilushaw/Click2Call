<%-- 
    Document   : pop_layout
    Created on : Jun 14, 2013, 10:22:33 AM
    Author     : Dewmini
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <h2><tiles:insertAttribute name="heading" /></h2>
        <tiles:insertAttribute name="body" />
    </body>
</html>
