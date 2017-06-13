<%--
    Document   : login
    Created on : Oct 25, 2012, 9:15:36 AM
    Author     : Hasala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agent Dashboard</title>


        <script type="text/javascript" src="resources/scripts/delete.js"></script>


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
                /* background: #E4E4E4;*/
                background-image:url(resources/images/background.png);
                background-repeat: no-repeat;
                background-position: right bottom ;

            }

        </style>


        <script type="text/javascript">
                  function Popup(id)
                  {

                      $("#pop").html("<iframe  height='400' width='400' style='border: none' src='viewAgent.htm?agentId=" + id + "'></iframe>").dialog({
                          //$("#pop").load('viewCompany.htm').dialog({

                          height: 470,
                          width: 450,
                          modal: true,
                          show: "blind",
                          hide: "explode",
                          close: function(event, ui) {
                              $("pop").dialog('destroy');

                          }
                      });
                      return false;
                  }


                  function PopupEditAgents(id)
                  {

                      $("#pop").html("<iframe  height='400' width='400' style='border: none' src='editAgent.htm?agentId=" + id + "'></iframe>").dialog({
                          //$("#pop").load('viewCompany.htm').dialog({
                          title: 'Edit Agent Details',
                          height: 470,
                          width: 450,
                          modal: true,
                          show: "blind",
                          hide: "explode",
                          close: function(event, ui) {
                              submit = true;
                              $("#agent_dashboard").submit();


                          }
                      });
                      return false;
                  }

                  function PopupCreateAgents()
                  {

                      $("#pop").html("<iframe  height='400' width='400' style='border: none' src='createAgent.htm'></iframe>").dialog({
                          //$("#pop").load('viewCompany.htm').dialog({
                          title: 'Create Agent',
                          height: 470,
                          width: 450,
                          modal: true,
                          show: "blind",
                          hide: "explode",
                          close: function(event, ui) {
                              submit = true;
                              $("#agent_dashboard").submit();


                          }
                      });
                      return false;
                  }

        </script>

    <Div id="pop"  style="display:none;" title="View Agent Details" align="center">

    </Div>


</head>
<body>

    <table border="0" align="right" >
        <tr>
            <td align="left"></td>
            <td align="right"><input type="button" value="Create New Agent" onclick="PopupCreateAgents()" class="button_bk" /></td>
        </tr>
    </table>

    <form:form commandName="agent_dashboard" id="agent_dashboard" action="agent_dashboard.htm" method="POST">
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="example" width="90%">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Number</th>
                    <th>Total Minutes</th>
                    <th>Used Minutes</th>

                    <th>Actions</th>
                </tr>
            </thead>
            <!--	<tfoot>
                            <tr>
                                    <th>Name</th>
                                    <th>Number</th>
                                    <th>Total Minutes</th>
                                    <th>Used Minutes</th>
                                    <th>Actions</th>
                            </tr>
                    </tfoot>-->

            <tbody>


                <c:forEach var="item" items="${agents}">
                    <c:set value="${item.usedSeconds}" var="usedSec" />
                        <c:set value="${usedSec%60}" var="sec" />
                        
                        <fmt:formatNumber value="${(usedSec-sec)/60}" var="min" maxFractionDigits="0" />
                    <tr>

                        <td>${item.name}</td>
                        <td align="center">${item.number}</td>
                        <td align="center">${item.totalMins}</td>
                        <td align="center">${min}:${sec}</td>
                        <td align="center">
                            <img src="resources/home_files/view.png" title="View Agent" class="iconImg" alt="" onclick=" return Popup('${item.agentId}')">
                            <img src="resources/home_files/edit.png" title="Edit Agent" class="iconImg" alt="" onclick=" return PopupEditAgents('${item.agentId}')">
                            <%--<a href="viewAgent.htm?agentId=${item.agentId}"><img src="resources/home_files/view.png" title="View Agent" alt="" class="iconImg"></a> --%>
                            <%-- <a href="editAgent.htm?agentId=${item.agentId}"><img src="resources/home_files/edit.png" title="Edit Agent" alt="" class="iconImg"></a>--%>
                            <%--<a href="javascript:deleteAgent('${item.agentId}')"><img src="resources/home_files/remove.png" title="Delete Agent" alt="" class="iconImg"></a>--%>
                        </td>

                    </tr>
                </c:forEach>


            </tbody>
        </table>
    </form:form>
</body>
</html>
