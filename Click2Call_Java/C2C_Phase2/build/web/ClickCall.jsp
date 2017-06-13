<%@page import="Common.MsgLog"%>
<%@page import="user.PrimeThread"%>
<%@page import="user.CallGenerate"%>

<html>
<head><title>Click2Call</title>
    <link href="css/formcss.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript">
//Generates the captcha function    
	var a = Math.ceil(Math.random() * 9)+ '';
	var b = Math.ceil(Math.random() * 9)+ '';       
	var c = Math.ceil(Math.random() * 9)+ '';  
	var d = Math.ceil(Math.random() * 9)+ '';  
	var e = Math.ceil(Math.random() * 9)+ '';  
	  
	var code = a + b + c + d + e;
	document.getElementById("txtCaptcha").value = code;
	document.getElementById("txtCaptchaDiv").innerHTML = code;
       
</script>

<script type="text/javascript">
function checkform(theform){
	var why = "";
	 
	if(theform.txtInput.value == ""){
		why += "- Security code should not be empty.\n";
	}
	if(theform.txtInput.value != ""){
		if(ValidCaptcha(theform.txtInput.value) == false){
			why += "- Security code did not match.\n";
		}
	}
	if(why != ""){
		alert(why);
		return false;
	}
}
	
// Validate the Entered input aganist the generated security code function   
function ValidCaptcha(){
	var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
	var str2 = removeSpaces(document.getElementById('txtInput').value);
	if (str1 == str2){
		return true;	
	}else{
		return false;
	}
}

// Remove the spaces from the entered and generated code
function removeSpaces(string){
	return string.split(' ').join('');
}
	
</script>


</head>
<body>
    
     
            <form name="form2" action="ClickCall.jsp" method="post" onsubmit="return checkform(this);">
                <input type="hidden" name="agentId" id="textAgentId" readonly="true" value="<%= request.getParameter("ac") %>" />
                 <fieldset>
                     <legend style="width:100px">Contact form</legend>
               <label for="mobile">Your Mobile No. :   <input type="text" name="num1" value=""/>
             <p>

      <label for="code"> <span id="txtCaptchaDiv" style="background-image:url(resources/images/scare.jpg); height:15px; width:50px;font-size: 20px;position: relative; left: 6em;" border="1" width="30" height="15"></span><!-- this is where the script will place the generated code -->

      <input type="hidden" id="txtCaptcha" /></label><!-- this is where the script will place a copy of the code for validation: this is a hidden field -->
             </p><p>CAPTCHA Code * : 
      <input type="text" name="txtInput" id="txtInput" size="15" />

             </p>

            
             <input type="submit" value="Click2Call" style="position: relative; left: 8em;"/>
            
                 </fieldset>
        </form>
    <script type="text/javascript">
	function checkform(theform){
		var why = "";
		 
		if(theform.txtInput.value == ""){
			why += "- Security code should not be empty.\n";
		}
		if(theform.txtInput.value != ""){
			if(ValidCaptcha(theform.txtInput.value) == false){
				why += "- Security code did not match.\n";
			}
		}
		if(why != ""){
			alert(why);
			return false;
		}
	}
 

//Generates the captcha function    
	var a = Math.ceil(Math.random() * 9)+ '';
	var b = Math.ceil(Math.random() * 9)+ '';       
	var c = Math.ceil(Math.random() * 9)+ '';  
	var d = Math.ceil(Math.random() * 9)+ '';  
	var e = Math.ceil(Math.random() * 9)+ '';  
	  
	var code = a + b + c + d + e;
	document.getElementById("txtCaptcha").value = code;
	document.getElementById("txtCaptchaDiv").innerHTML = code;	

// Validate the Entered input aganist the generated security code function   
function ValidCaptcha(){
	var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
	var str2 = removeSpaces(document.getElementById('txtInput').value);
	if (str1 == str2){
		return true;	
	}else{
		return false;
	}
}

// Remove the spaces from the entered and generated code
function removeSpaces(string){
	return string.split(' ').join('');
}
</script>
<!--<form method=POST>Please enter num1 <input name=num1><br>Please enter num2 <input name=num2></form>-->
<%
int value1 = 0;
int value2 = 0;
int target = 21;
String result="result set";
boolean havevalue = false;
try {
    value1 = Integer.parseInt(request.getParameter("num1"));
     value2 = Integer.parseInt(request.getParameter("agentId"));// 777445234; //findAgentNumberByAgentKey("");
     String input=String.valueOf(value1)+":"+String.valueOf(value2);
     // result= new CallGenerate().generateCall(value2,value1);
       String writeVal=input+":Call generated";
     MsgLog.write(writeVal);
     PrimeThread primeThread=new PrimeThread(input);
     primeThread.start();
    havevalue = true;
    out.println ("Please wait for a call");
} catch (Exception e) {}


%>


<hr>

</body>
</html>
