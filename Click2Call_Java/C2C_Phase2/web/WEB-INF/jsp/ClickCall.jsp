<%-- 
    Document   : ClickCall
    Created on : Mar 21, 2013, 12:05:20 PM
    Author     : Dewmini
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head><title>Click2Call</title>

        <link href="resources/css/formcss.css" rel="stylesheet" type="text/css" media="screen" />
        <script type="text/javascript" src="resources/js/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="resources/scripts/purl.js"></script>
        <script type="text/javascript">

            function reloadCaptcha() {
                var a = Math.ceil(Math.random() * 9) + '';
                var b = Math.ceil(Math.random() * 9) + '';
                var c = Math.ceil(Math.random() * 9) + '';
                var d = Math.ceil(Math.random() * 9) + '';
                var e = Math.ceil(Math.random() * 9) + '';

                var code = a + b + c + d + e;
                document.getElementById("txtCaptcha").value = code;
                document.getElementById("txtCaptchaDiv").innerHTML = code;
            }
            function checkform() {
                $("#statusMsg").html("");
                $("#mobileNumMsg").html("");
                $("#captchaMsg").html("");
                var mobileNum = $("#num1").val();
                //if (mobileNum == "" || mobileNum.length != 10 || mobileNum.substring(0, 3) != "077") {
                if (mobileNum == "" || mobileNum.length != 10) {
                    $("#mobileNumMsg").html("Incorrect Number!");
                    return false;
                }
                var why = "";
                var txtInput = $("#txtInput").val();
                if (txtInput == "") {
                    $("#captchaMsg").html("Incorrect!");
                    return false;
                }
                if (txtInput != "") {
                    if (ValidCaptcha() == false) {
                        $("#captchaMsg").html("Incorrect!");
                        return false;
                    }
                }
                reloadCaptcha();
                $("#txtInput").val('');
                initiateCall();
            }

            // Validate the Entered input aganist the generated security code function   
            function ValidCaptcha() {
                var txtCaptcha = $("#txtCaptcha").val();
                var txtInput = $("#txtInput").val();
                var str1 = removeSpaces(txtCaptcha);
                var str2 = removeSpaces(txtInput);
                if (str1 == str2) {
                    return true;
                } else {
                    return false;
                }
            }

            // Remove the spaces from the entered and generated code
            function removeSpaces(string) {
                return string.split(' ').join('');
            }

            function initiateCall() {
                $("#statusMsg").html("Please wait...");
                var num1 = $("#num1").val();
                //alert("111111111111111");
                var agentKey = $.url().param('agent'); 
                //alert("222222");
                $.ajax({
                    url: 'Click2CallGen.htm',
                    type: "POST",
                    async: false,
                    data: ({
                        customerNumber: num1,
                        agentKey:agentKey
                    }),
                    success: function(result) {
                        //alert("Sucess: "+result);
                        $("#statusMsg").html(result);
                    }
                });
                return result;
            }

        </script>
    </head>
    <body>
        <form name="ClickCallForm" action="ClickCall.htm" method="post">
            <input type="hidden" name="agentId" id="textAgentId" readonly="true" value="<%= request.getParameter("ac")%>" />
            <fieldset>
                <legend style="width:100px">Contact Form</legend>
                <table width="410" border="0" align="left">
                    <tr>
                        <td colspan="4"><span id="statusMsg"></span></td>
                    </tr>
                    <tr>
                        <td width="140" align="right"><label>Your Mobile No.*</label></td>
                        <td width="9">:</td>
                        <td colspan="2"><input type="text" name="num1" id="num1" value="" maxlength="10"  placeholder="Give 10 digit number" />&nbsp;<span id="mobileNumMsg" class="error"></span></td>
                    </tr>
                    <tr>
                        <td align="right">&nbsp;</td>
                        <td>&nbsp;</td>
                        <td width="111" align="left" style="padding-bottom: 5px">
                            <!-- this is where the script will place the generated code -->
                            <span id="txtCaptchaDiv" style="background-image:url(resources/images/scare.jpg);font-size: 20px; border: 1px solid activeborder;padding:0px 8px;"></span>
                            <input type="hidden" id="txtCaptcha" /></td>
                        <td width="135" align="left" style="padding-bottom: 5px"><img src="resources/images/refresh.png" alt="" align="bottom" style="width: 26px; height: 26px;" onClick="reloadCaptcha()" /></td>
                    </tr>
                    <tr>
                        <td align="right"><label>CAPTCHA Code*</label></td>
                        <td>:</td>
                        <td colspan="2"><input type="text" name="txtInput" id="txtInput" />&nbsp;<span id="captchaMsg" class="error"></span></td>
                    </tr>
                    <tr>
                        <td align="right">&nbsp;</td>
                        <td>&nbsp;</td>
                        <td colspan="2"><img src="resources/images/C2C.png" style="margin-top: 5px;" onClick="checkform()"/></td>
                    </tr>
                </table>

            </fieldset>
        </form>
        <script type="text/javascript">

            //Generates the captcha function    
            var a = Math.ceil(Math.random() * 9) + '';
            var b = Math.ceil(Math.random() * 9) + '';
            var c = Math.ceil(Math.random() * 9) + '';
            var d = Math.ceil(Math.random() * 9) + '';
            var e = Math.ceil(Math.random() * 9) + '';

            var code = a + b + c + d + e;
            document.getElementById("txtCaptcha").value = code;
            document.getElementById("txtCaptchaDiv").innerHTML = code;


        </script>       


    </body>
</html>
