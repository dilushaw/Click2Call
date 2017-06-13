<%-- 
    Document   : header
    Created on : Jul 18, 2012, 2:50:20s PM
    Author     : Dewmini
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">



<!--

Name       : Common Layout  
Description: Common Layout for the logged in users for the System. Header, Menu bar, Content, Footer are separated and handled through tiles framework.
Version    : 2.1
Created   : 18-07-2012

-->
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title><tiles:insertAttribute name="title" ignore="true" /></title>

        <link href="resources/css/click2call.css" rel="stylesheet" type="text/css" />

    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <tiles:insertAttribute name="header" />
            </div> <!--End header-->
            <div style="clear: both;"
            <!-- Displaying the tabs menu-->
            <div class="fillDiv" id="mainmenu" >
                <tiles:insertAttribute name="menu" />
            </div>
            <!-- end fillDiv (tabs menu) --> 


            <div id="Content" class="Content"> 
                <tiles:insertAttribute name="body" />
            </div>
            <!-- End Content -->





            <div id="footer">
                <tiles:insertAttribute name="footer" />

            </div>

        </div><!-- End wrapper -->
    </body>
</html>

