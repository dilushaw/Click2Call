<%--
    Document   : login
    Created on : Oct 25, 2012, 9:15:36 AM
    Author     : Hasala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Dashboard</title>

       	<style type="text/css" title="currentStyle">
            @import "resources/DataTables/media/css/demo_page.css";
            @import "resources/DataTables/media/css/demo_table.css";
            div.table_Wrapper { border:10px solid blue; }
        </style>
        <script type="text/javascript" language="javascript" src="resources/scripts/user.js"></script>

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
        <!--	<link rel="stylesheet" href="resources/jquery-ui-1.9.1.custom/development-bundle/demos/demos.css">-->


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

        </script>



    </head>
    <body>


        <Div id="pop"  style="display:none;"   align="center">

        </Div>



        <table border="0" align="right" >
            <tr>
                <td align="left"></td>
                <td align="right"><input type="button" value="Create New User" onclick="PopupUserCreate()" class="button_bk" /></td>
            </tr>
        </table>
        <form:form commandName="user_dashboard" action="user_dashboard.htm" method="POST" id="user_dashboard" name="user_dashboard">

            <table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
                <thead>
                    <tr>
                        <th>User Name</th>
                        <th>Company</th>
                        <th>User Type</th>
                        <th>Telephone</th>
                        <th>Email</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <!--	<tfoot>
                                <tr>
                                        <th>User Name</th>
                                        <th>User Type</th>
                                        <th>Telephone</th>
                                        <th>Email</th>
                                        <th>Actions</th>
                                </tr>
                        </tfoot>-->
                <tbody>


                    <c:forEach var="item" items="${dashboard}">

                        <tr>

                            <td>${item.userName}</td>
                            <td>${item.companyName}</td>
                            <td>${item.userType}</td>
                            <td>${item.phone}</td>
                            <td>${item.email}</td>
                            <th>

                                <img src="resources/home_files/view.png" title="View User" class="iconImg" onclick="return PopupUser('${item.userId}')">
                                <img src="resources/home_files/edit.png" title="View User" class="iconImg" onclick="${item.userName eq sessionScope.userName && item.companyName eq sessionScope.company}?alert('Edit own info not allowed here; instead go to My Profile...'):PopupEditUser('${item.userId}')">
                                 <!--<a href="editUser.htm?userId=${item.userId}"><img src="resources/home_files/edit.png" title="View User" class="iconImg" ></a>-->
                                <%-- <a href="editUser.htm?userId=${item.userId}"onclick="checkDelete()"><img src="resources/home_files/remove.png" title="Delete User" alt="" class="iconImg"></a> --%>



                            </th>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>

        </form:form>

    </body>
</html>
