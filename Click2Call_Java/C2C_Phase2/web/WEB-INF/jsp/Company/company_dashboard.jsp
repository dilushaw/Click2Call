<%-- 
    Document   : company_dashboard
    Created on : Oct 9, 2012, 5:14:53 PM
    Author     : DialogLab1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
-->
<html xmlns="http://www.w3.org/1999/xhtml"><head>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>Click2Call - Home</title>

 <link rel="stylesheet" type="text/css" href="resources/click2call.css"/>

  <%
                    String userName = "";

                    userName = session.getAttribute("userName").toString();



        %>

    </head>
    <body onload="LoadUserTable()">
		<div id="Barcode" >

	<div id= "header">
        <div id="HeaderTopLinks" align=right> User: <%=userName%>&nbsp;|&nbsp;<a href="#">Logout</a>&nbsp; <br>
            </div>

	<div id="Logos">


	       <h1 id="banner"><input name="" type="image" src="resources/images/header.png" align="right"></h1>
                <h2 id="BarcodeLogo"><input name="" type="image" src="resources/images/dialog.png" align="left"></h2>


        </div><!--#Logos-->

	</div>



<!--       <div id="HeaderBar"> </div>-->




<div id= "Menu" style="width: 800px" >

	    <table>
	   <tr><td style="background-color:#ae0915;color:#000000"><a href="company_dashboard.htm">Company</a></td>
	   <td style="background-color:#996633" ><a href="reports.htm">Reports</a></td>
	   <td style="background-color:#996633"><a href="user_dashboard.htm">User Management</a></td></tr>


	   </table>

	   </div>
	<div id="submenu">
	<table><tr><td>&nbsp;Company Dashboard</td></tr></table>

	</div>
	<div id="content">

	          <title>Company Dashboard</title>
          <link href="resources/home_files/dsa_style.css" rel="stylesheet" type="text/css" media="screen">
          <link href="resources/home_files/extremecomponents.css" rel="stylesheet" type="text/css" media="screen">
           <table border="0" width="100%">
            <tbody>
              <tr>

                <td align="left"><div id="div-button"><a href="createcompany.html"><img src="images/newcompany.png" ></a></div></td>
              </tr>
            </tbody>
          </table>


          <font color="blue"></font>
          <form id="ec" action="dsaDashboard.htm" method="post">
            <div>
              <input name="ec_i" value="ec" type="hidden">
              <input name="ec_crd" value="10" type="hidden">
              <input name="ec_f_a" type="hidden">
              <input name="ec_p" value="1" type="hidden">
              <input name="ec_s_corporateName" type="hidden">
              <input name="ec_s_corporateStatus" type="hidden">
              <input name="ec_s_licenseStartDate" type="hidden">
              <input name="ec_s_licenseEndDate" type="hidden">
              <input name="ec_s_telephone" type="hidden">
              <input name="ec_s_email" type="hidden">
              <input name="userName" value="dialog" type="hidden">
              <input name="account" value="dialog" type="hidden">
              <input name="password" value="dialog" type="hidden">
            </div>
        </div>
            <div class="eXtremeTable">
              <table border="0" cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                  <tr>
                    <td></td>
                    <td align="right"><table class="toolbar" border="0" cellpadding="0" cellspacing="1">
                        <tbody>
                          <tr>
                            <td><img src="home_files/firstPageDisabled.gif" style="border:0" alt="First"></td>
                            <td><img src="home_files/prevPageDisabled.gif" style="border:0" alt="Prev"></td>
                            <td><img src="home_files/nextPageDisabled.gif" style="border:0" alt="Next"></td>
                            <td><img src="home_files/lastPageDisabled.gif" style="border:0" alt="Last"></td>
                            <td><img src="home_files/separator.gif" style="border:0" alt="Separator"></td>
                            <td style="width:20px"><select name="ec_rd" onChange="javascript:document.forms.ec.ec_crd.value=this.options[this.selectedIndex].value;document.forms.ec.ec_p.value='1';document.forms.ec.setAttribute('action','dsaDashboard.htm');document.forms.ec.setAttribute('method','post');document.forms.ec.submit()">
                                <option value="10" selected="selected">10</option>
                              <option value="50">50</option>
                              <option value="100">100</option>
                              </select>
                                <img src="home_files/rowsDisplayed.gif" style="border:0" alt="Rows Displayed"></td>
                          </tr>
                        </tbody>
                    </table></td>
                  </tr>
                </tbody>
              </table>
              <table id="ec_table" class="tableRegion" border="0" cellpadding="0" cellspacing="0" width="100%">
                <thead>
                  <tr style="padding: 0px;">
                    <td height="51" colspan="6"><table border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tbody>
                          <tr>
                            <td class="statusBar">5 results found, displaying 1 to 5 </td>
                            <td class="filterButtons"><img src="home_files/filterArrow.gif" style="border:0" alt="Arrow">&nbsp;<a href="javascript:document.forms.ec.ec_f_a.value='fa';document.forms.ec.ec_p.value='1';document.forms.ec.setAttribute('action','dsaDashboard.htm');document.forms.ec.setAttribute('method','post');document.forms.ec.submit()"><img src="home_files/filter.gif" style="border:0" title="Filter" alt="Filter"></a>&nbsp;<a href="javascript:document.forms.ec.ec_f_a.value='ca';document.forms.ec.ec_p.value='1';document.forms.ec.setAttribute('action','dsaDashboard.htm');document.forms.ec.setAttribute('method','post');document.forms.ec.submit()"><img src="home_files/clear.gif" style="border:0" title="Clear" alt="Clear"></a></td>
                          </tr>
                        </tbody>
                    </table></td>
                  </tr>
                  <tr class="filter">
                    <td width="22%"><input name="ec_f_corporateName" onKeyPress="if (event.keyCode == 13) {javascript:document.forms.ec.ec_f_a.value='fa';document.forms.ec.ec_p.value='1';document.forms.ec.setAttribute('action','dsaDashboard.htm');document.forms.ec.setAttribute('method','post');document.forms.ec.submit()}" type="text"></td>
                  <td width="16%"><select name="ec_f_corporateStatus" onChange="javascript:document.forms.ec.ec_f_a.value='fa';document.forms.ec.ec_p.value='1';document.forms.ec.setAttribute('action','dsaDashboard.htm');document.forms.ec.setAttribute('method','post');document.forms.ec.submit()">
                        <option selected="selected" value=""></option>
                        <option value="1">Active</option>
                        <option value="0">Inactive</option>
                    </select></td>
                    <td width="23%"><input name="ec_f_telephone" onKeyPress="if (event.keyCode == 13) {javascript:document.forms.ec.ec_f_a.value='fa';document.forms.ec.ec_p.value='1';document.forms.ec.setAttribute('action','dsaDashboard.htm');document.forms.ec.setAttribute('method','post');document.forms.ec.submit()}" type="text"></td>
                    <td width="23%"><input name="ec_f_email" onKeyPress="if (event.keyCode == 13) {javascript:document.forms.ec.ec_f_a.value='fa';document.forms.ec.ec_p.value='1';document.forms.ec.setAttribute('action','dsaDashboard.htm');document.forms.ec.setAttribute('method','post');document.forms.ec.submit()}" type="text"></td>
                    <td width="16%"></td>

                  </tr>
                  <tr>
                    <td class="tableHeader" onMouseOver="this.className='tableHeaderSort';this.style.cursor='pointer'" onMouseOut="this.className='tableHeader';this.style.cursor='default'" onClick="javascript:document.forms.ec.ec_s_corporateName.value='asc';document.forms.ec.ec_p.value='1';document.forms.ec.setAttribute('action','dsaDashboard.htm');document.forms.ec.setAttribute('method','post');document.forms.ec.submit()" title="Sort By Corporate Name">Company Name</td>

	            <td class="tableHeader" onMouseOver="this.className='tableHeaderSort';this.style.cursor='pointer'" onMouseOut="this.className='tableHeader';this.style.cursor='default'" onClick="javascript:document.forms.ec.ec_s_corporateStatus.value='asc';document.forms.ec.ec_p.value='1';document.forms.ec.setAttribute('action','dsaDashboard.htm');document.forms.ec.setAttribute('method','post');document.forms.ec.submit()" title="Sort By Corporate Status">Status</td>

	            <td class="tableHeader" onMouseOver="this.className='tableHeaderSort';this.style.cursor='pointer'" onMouseOut="this.className='tableHeader';this.style.cursor='default'" onClick="javascript:document.forms.ec.ec_s_telephone.value='asc';document.forms.ec.ec_p.value='1';document.forms.ec.setAttribute('action','dsaDashboard.htm');document.forms.ec.setAttribute('method','post');document.forms.ec.submit()" title="Sort By Telephone">Telephone</td>
                    <td class="tableHeader" onMouseOver="this.className='tableHeaderSort';this.style.cursor='pointer'" onMouseOut="this.className='tableHeader';this.style.cursor='default'" onClick="javascript:document.forms.ec.ec_s_email.value='asc';document.forms.ec.ec_p.value='1';document.forms.ec.setAttribute('action','dsaDashboard.htm');document.forms.ec.setAttribute('method','post');document.forms.ec.submit()" title="Sort By Email">Email</td>
                    <td class="tableHeader">Actions</td>
                  </tr>
                </thead>
                <tbody class="tableBody">
                  <tr class="odd">
                    <td>Company1</td>
                    <td> Active</td>
                    <td>+94777678678</td>
                    <td>agent@company1.com</td>

                   <td><a href="viewcompany.html"><img src="home_files/view.png" title="View Company" alt="" border="0" height="16" width="16"></a>&nbsp;&nbsp;&nbsp; <a href="editcompany.html"><img src="home_files/edit.png" title="Edit Company" alt="" border="0" height="16" width="16"></a>
&nbsp;&nbsp;&nbsp; <a href="iframe.html"><img src="home_files/iframe.png" title="iframe" alt="" border="0" height="16" width="16"></a>
&nbsp;&nbsp;&nbsp; <a href="blacklist.html"><img src="home_files/lock.png" title="Modify Black List" alt="" border="0" height="16" width="16"></a>&nbsp;&nbsp;&nbsp;</td>
                  </tr>
                  <tr class="even">
                    <td>Company2</td>
                    <td> Active</td>
                    <td>012345678912</td>
                    <td>admin@company2.com</td>

                    <td><a href="viewcompany.html"><img src="home_files/view.png" title="View Company" alt="" border="0" height="16" width="16"></a>&nbsp;&nbsp;&nbsp; <a href="editcompany.html"><img src="home_files/edit.png" title="Edit Company" alt="" border="0" height="16" width="16"></a>
&nbsp;&nbsp;&nbsp; <a href="iframe.html"><img src="home_files/iframe.png" title="iframe" alt="" border="0" height="16" width="16"></a>
&nbsp;&nbsp;&nbsp; <a href="blacklist.html"><img src="home_files/lock.png" title="Modify Black List" alt="" border="0" height="16" width="16"></a>&nbsp;&nbsp;&nbsp;</td>
                  </tr>
                  <tr class="odd">
                    <td>Company3</td>
                    <td> Inactive</td>
                    <td>+94777678678</td>
                    <td>user@company3.com</td>

                   <td><a href="viewcompany.html"><img src="home_files/view.png" title="View Company" alt="" border="0" height="16" width="16"></a>&nbsp;&nbsp;&nbsp; <a href="editcompany.html"><img src="home_files/edit.png" title="Edit Company" alt="" border="0" height="16" width="16"></a>
&nbsp;&nbsp;&nbsp; <a href="iframe.html"><img src="home_files/iframe.png" title="iframe" alt="" border="0" height="16" width="16"></a>
&nbsp;&nbsp;&nbsp; <a href="blacklist.html"><img src="home_files/lock.png" title="Modify Black List" alt="" border="0" height="16" width="16"></a>&nbsp;&nbsp;&nbsp;</td>
                  </tr>
                  <tr class="even">
                    <td>Company4</td>
                    <td> Inactive</td>
                    <td>+94777678678</td>
                    <td>admin@company4.com</td>

                    <td><a href="viewcompany.html"><img src="home_files/view.png" title="View Company" alt="" border="0" height="16" width="16"></a>&nbsp;&nbsp;&nbsp; <a href="editcompany.html"><img src="home_files/edit.png" title="Edit Company" alt="" border="0" height="16" width="16"></a>
&nbsp;&nbsp;&nbsp; <a href="iframe.html"><img src="home_files/iframe.png" title="iframe" alt="" border="0" height="16" width="16"></a>
&nbsp;&nbsp;&nbsp; <a href="blacklist.html"><img src="home_files/lock.png" title="Modify Black List" alt="" border="0" height="16" width="16"></a>&nbsp;&nbsp;&nbsp;</td>
                  </tr>
                  </tbody>
              </table>

          </form>


	</div>

	<div id="footer" >
            <p class="Copyright" align="center" style="color:#FFFFFF" >&copy; Dialog-University of Moratuwa Mobile Communications Research Laboratory. All Rights Reserved.</p>
	</div>








</div>

</body></html>
