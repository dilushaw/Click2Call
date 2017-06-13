<%-- 
    Document   : page404
    Created on : Jun 27, 2013, 1:29:56 PM
    Author     : Dewmini
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>404 Not Found!</title>
        <link rel="stylesheet" type="text/css" href="resources/css/error_style.css"/>
        <style type="text/css">
            #h_align {
                display:table;
                height:300px;
                text-align:center;
            }
            #v_align{
                display:table-cell;vertical-align:middle; 
            }
            #left_bar {
                float: right;
                background-image:url(resources/images/header.png);
                width: 50%;
            }

            #right_bar {
                float: left;
                width: 50%;
                font: bold 40px "Times New Roman", Times, sans-serif;
                color: #C0C0C0;
            }  
            #right_bar p {
                font: 16px "Times New Roman", Times, serif;
                color: #666666;
            }
        </style>
        <script type="text/javascript">

function Redirect()
{
    //window.location="login.htm";
    history.go(-1);
}

//document.write("You will be redirected to main page in 10 sec.");
setTimeout('Redirect()', 10000);

</script>
    </head>
    <body>
<!--        <div id="h_align">
            <div id="v_align">
                <div id="left_bar">
                    
                </div>
                <div id="right_bar-wrap">
                    404! Not Found.
                    <p>You will be redirected <a href="login.htm">login</a> page shortly...</p>
                </div>
            </div>
        </div>  -->

<body id="e404">
<div id="root">
  <div id="content">
    <div class="outer">
      <div id="error">
        <h1 class="a sIFR-replaced">
          404! Not found! </h1>
        <p> The page you were looking for appears to have been moved, deleted or does not exist. You could go back to <a href="javascript:Redirect()"> <span class="go-back">where you were</span> </a> or head straight to the <a href="home.htm">home page</a> </p>
        <p style="font-style: italic;">Redirecting to previous page <img src="resources/images/loader.gif" style="display: inline;" /></p>
      </div>
    </div>
  </div>
</div>
<div id="ui-datepicker-div" style="display: none;"></div>
    </body>
</html>
