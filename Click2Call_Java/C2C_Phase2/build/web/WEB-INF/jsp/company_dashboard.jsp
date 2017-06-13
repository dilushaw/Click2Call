<%--
    Document   : login
    Created on : Oct 25, 2012, 9:15:36 AM
    Author     : Hasala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Company Dashboard</title>

       	<style type="text/css" title="currentStyle">
            @import "resources/DataTables/media/css/demo_page.css";
            @import "resources/DataTables/media/css/demo_table.css";
            div.table_Wrapper { border:10px solid blue; }
        </style>

        <script type="text/javascript" language="javascript" src="resources/DataTables/media/js/jquery.js"></script>
        <script type="text/javascript" language="javascript" src="resources/DataTables/media/js/jquery.dataTables.js"></script>
        <script type="text/javascript" charset="utf-8">
            $(document).ready(function() {
                $('#example').dataTable({
                });
            });
        </script>








        <link rel="stylesheet" href="resources/jquery-ui-1.9.1.custom/development-bundle/themes/base/jquery.ui.all.css">
        <!-- <script src="resources/jquery-ui-1.9.1.custom/development-bundle/jquery-1.8.2.js"></script>-->
        <script src="resources/jquery-ui-1.9.1.custom/development-bundle/external/jquery.bgiframe-2.1.2.js"></script>
        <script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.core.js"></script>
        <script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.widget.js"></script>
        <script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.mouse.js"></script>
        <script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.draggable.js"></script>
        <script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.position.js"></script>
        <script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.resizable.js"></script>
        <script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.dialog.js"></script>
        <script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.effect.js"></script>
        <script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.effect-blind.js"></script>
        <script src="resources/jquery-ui-1.9.1.custom/development-bundle/ui/jquery.ui.effect-explode.js"></script>
        <link rel="stylesheet" href="resources/jquery-ui-1.9.1.custom/development-bundle/demos.css">

        <style>

            .ui-widget-header {
                background:#990000;
                color: #FFFFFF;
                font-style: italic;
                font-size: 12px;
            }

            .ui-widget-content{
                /*background: #E4E4E4;*/
                background-image:url(resources/images/background.png);
                background-repeat: no-repeat;
                background-position: right bottom ;

            }

        </style>

        <script type="text/javascript">
            function Popup(id)
            {

                $("#pop").html("<iframe  height='400' width='400' style='border: none' src='viewCompany.htm?companyId=" + id + "'></iframe>").dialog({
                    //$("#pop").load('viewCompany.htm').dialog({
                    height: 470,
                    width: 450,
                    show: "blind",
                    hide: "explode",
                    modal: true,
                    close: function(event, ui) {




                    }
                });
                return false;
            }


            function PopupEditCompany(id)
            {

                $("#pop").html("<iframe  height='400' width='550' style='border: none' src='editCompany.htm?companyId=" + id + "'></iframe>").dialog({
                    //$("#pop").load('viewCompany.htm').dialog({
                    title: 'Edit Company Details',
                    height: 470,
                    width: 570,
                    show: "blind",
                    hide: "explode",
                    modal: true,
                    close: function(event, ui) {
                        submit = true;
                        $("#company_dashboard").submit();

                    }
                });
                return false;
            }


            function PopupCreateCompany()
            {

                $("#pop").html("<iframe height='400' width='550' style='border: none' src='createCompany.htm'></iframe>").dialog({
                    //$("#pop").load('viewCompany.htm').dialog({
                    title: 'Create Company ',
                    height: 470,
                    width: 570,
                    show: "blind",
                    hide: "explode",
                    modal: true,
                    close: function(event, ui) {
                        submit = true;
                        $("#company_dashboard").submit();

                    }
                });
                return false;
            }


            function PopupCharginPlans()
            {

                $("#pop").html("<iframe height='400' width='500' style='border: none' src='viewChargingPlans.htm'></iframe>").dialog({
                    //$("#pop").load('viewCompany.htm').dialog({
                    title: 'View Chargin Plans',
                    height: 470,
                    width: 550,
                    show: "blind",
                    hide: "explode",
                    modal: true,
                    close: function(event, ui) {
                        submit = true;
                        $("#company_dashboard").submit();

                    }
                });
                return false;
            }



        </script>

    </head>
    <body>



        <form:form commandName="company_dashboard" action="company_dashboard.htm" id="company_dashboard" method="POST">
            <table border="0" align="right">
                <tr>
                    <td align="left"><!--<a href="createCompany.htm">-->
<!--                        <img src="resources/images/newcompany.png" onclick="PopupCreateCompany()" >-->
                    <input type="button" value="Create New Company" onclick="PopupCreateCompany()" class="button_bk" />
                    </td>
                    <td align="left">
<!--                        <input type="button" value="View Chargin Plans" onclick="PopupCharginPlans()" class="button_bk" />-->
                    </td>
                </tr>
            </table>

            <table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
                <thead>
                    <tr>
                        <th>Company Name</th>
                        <th>Max Agents</th>
                        <th align="left">Contact Person</th>
                        <th>Number</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <!--	<tfoot>
                                <tr>
                                        <th>Company Name</th>
                                        <th>Max Agents</th>
                                        <th>Contact Person</th>
                                        <th>Number</th>
                                        <th>Actions</th>
                                </tr>
                        </tfoot>-->

                <tbody>


                    <c:forEach var="item" items="${cDashboard}">
                        <tr>

                            <td>${item.companyName}</td>
                            <%--<td><c:choose>
                                    <c:when test="${company.companyStatus eq '1'}">Enabled</c:when>
                                    <c:when test="${company.companyStatus eq '0'}">Disabled</c:when>
                                    <c:otherwise>Deleted</c:otherwise>
                               </c:choose>
                           </td> --%>
                            <td>${item.numberOfAgents}</td>
                            <td>${item.contactPersonName}</td>
                            <td>${item.contactNumber}</td>
                            <td><img src="resources/home_files/view.png" title="View Company" class="iconImg" alt="" onclick=" return Popup('${item.companyId}')">

                                <img src="resources/home_files/edit.png" title="Edit Company" class="iconImg" alt="" onclick=" return PopupEditCompany('${item.companyId}')">



                                <%--<a href="deleteCompany.htm?companyId=${item.companyId}"><img src="resources/home_files/remove.png" title="Delete Company" alt="" class="iconImg"></a>--%>
                                <%-- <a href="agent_dashboard.htm?companyId=${item.companyId}"><img src="resources/home_files/agent.png" title="View Agents" alt="" class="iconImg"></a> --%>
                            </td>

                        </tr>
                    </c:forEach>


                </tbody>
            </table>

        </form:form>

        <div id="pop"  style="display:none;" align="center">

        </div>


    </body>
</html>
