<%-- 
    Document   : allChargingPlans
    Created on : Jun 12, 2013, 2:42:00 PM
    Author     : Dewmini
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

            function PopupEditChargingPlan(id)
            {

                $("#pop").html("<iframe  height='400' width='500' style='border: none' src='editChargingPlan.htm?planId=" + id + "'></iframe>").dialog({
                    //$("#pop").load('viewCompany.htm').dialog({
                    title: 'Edit Charging Plan',
                    height: 470,
                    width: 550,
                    show: "blind",
                    hide: "explode",
                    modal: true,
                    close: function(event, ui) {
                        submit = true;
                        $("#allChargingPlans").submit();

                    }
                });
                return false;
            }


            function PopupNewChargingPlan()
            {

                $("#pop").html("<iframe  height='400' width='500' style='border: none' src='newChargingPlan.htm'></iframe>").dialog({
                    //$("#pop").load('viewCompany.htm').dialog({
                    title: 'New Charging Plan ',
                    height: 470,
                    width: 550,
                    show: "blind",
                    hide: "explode",
                    modal: true,
                    close: function(event, ui) {
                        submit = true;
                        $("#allChargingPlans").submit();

                    }
                });
                return false;
            }

        </script>

    </head>
    <body>

<form:form commandName="allChargingPlans" action="allChargingPlans.htm" id="allChargingPlans" method="POST">

        <table border="0" align="right">
            <tr>
                <td align="left"><!--<a href="createCompany.htm">-->
                    <!--                        <img src="resources/images/newcompany.png" onclick="PopupCreateCompany()" >-->
                    <input type="button" value="New Charging Plan" onclick="PopupNewChargingPlan()" class="button_bk" />
                </td>
            </tr>
        </table>

        <table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
            <thead>
                <tr>
                    <th>Package Name</th>
                    <th>Monthly Rental</th>
                    <th>Free Minutes</th>
                    <th>Per minute Charge</th>
                    <th>Type</th>
                    <th>&nbsp;</th>
                </tr>
            </thead>
            <tbody>


                <c:forEach  var="plan" items="${cplans}">
                    <tr>
                        <td>${plan.name}</td>
                        <td>${plan.monthlyRental}</td>
                        <td>${plan.allocatedMinutes}</td>
                        <td>${plan.perminuteCharge}</td>
                        <td>${plan.chargingType.typeName}</td>
                        <td>
                            <img src="resources/home_files/edit.png" title="Edit Plan" class="iconImg" alt="" onclick=" return PopupEditChargingPlan('${plan.planId}')">
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
</form:form>

        <Div id="pop"  style="display:none;" title="View Charging Plan Detail" align="center">

        </Div>


    </body>
</html>
