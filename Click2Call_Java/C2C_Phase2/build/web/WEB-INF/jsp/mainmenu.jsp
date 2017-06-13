<%-- 
    Document   : mainmenu
    Created on : Jul 18, 2012, 2:52:56 PM
    Author     : Dewmini
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
    <c:when test="${sessionScope.userType eq 'Dialog Admin'}">

        <table id="menuTable">
            <tr>
                <td class="${sessionScope.MenuTab eq 'Company'?'currentTab':'Tab'}">
                    <a href="company_dashboard.htm" >Company</a>
                </td>

                <c:if test="${sessionScope.MenuTab eq 'Agents'}">
                    <td class="${sessionScope.MenuTab eq 'Agents'?'currentTab':'Tab'}">
                        <a href="agent_dashboard.htm" >Agents</a>
                    </td>
                </c:if>
<td class="${sessionScope.MenuTab eq 'Charging'?'currentTab':'Tab'}">
                    <a href="allChargingPlans.htm" >Charging Plans</a>
                </td>
                <td class="${sessionScope.MenuTab eq 'Reports'?'currentTab':'Tab'}">
                    <a href="reports.htm" >Reports</a>
                </td>
                <td class="${sessionScope.MenuTab eq 'User Management'?'currentTab':'Tab'}">
                    <a href="user_dashboard.htm" >User Management</a>
                </td>
                <td class="${sessionScope.MenuTab eq 'My Profile'?'currentTab':'Tab'}">
                    <a href="my_profile.htm" >My Profile</a>
                </td>

            </tr>

        </table>


        <div id="submenu">
	<table><tr><td>&nbsp;<c:out value= "${sessionScope.SubMenu}" ></c:out></td></tr></table>

            </div>

    </c:when>



    <c:when test="${sessionScope.userType eq 'Company Admin'}">

        <table id="menuTable">
            <tr>

                <td class="${sessionScope.MenuTab eq 'Agents'?'currentTab':'Tab'}">
                    <a href="agent_dashboard.htm" >Agents</a>
                </td>

                <td class="${sessionScope.MenuTab eq 'BlackList'?'currentTab':'Tab'}">
                    <a href="blacklist.htm" >Blacklist </a>
                </td>

                <td class="${sessionScope.MenuTab eq 'Reports'?'currentTab':'Tab'}">
                    <a href="companyCalls.htm" >Reports</a>
                </td>
                <td class="${sessionScope.MenuTab eq 'My Profile'?'currentTab':'Tab'}">
                    <a href="my_profile.htm" >My Profile</a>
                </td>


            </tr>

        </table>


        <div id="submenu">
            <table><tr><td>&nbsp;<c:out value= "${sessionScope.SubMenu}" ></c:out></td></tr></table>

            </div>

    </c:when>
</c:choose>

