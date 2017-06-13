<%-- 
    Document   : sessionExipired
    Created on : Jun 27, 2013, 9:56:56 AM
    Author     : Dewmini
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
    window.location="login";
}

//document.write("You will be redirected to main page in 10 sec.");
setTimeout('Redirect()', 10000);

</script>
    </head>
    <body>
        <div id="h_align">
            <div id="v_align">
                <div id="left_bar">
                    
                </div>
                <div id="right_bar-wrap">
                    Session Time Out!
                    <p>You will be redirected <a href="login.htm">login</a> page shortly...</p>
                </div>
            </div>
        </div>  
    </body>
</html>
