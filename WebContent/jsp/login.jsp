<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign in</title>
<link href=”bootstrap/bootstrap.min.css” rel=”stylesheet” type=”text/css” />
<script type=”text/javascript” src=”bootstrap/js/bootstrap.min.js”></script>
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
</head>
<body bgcolor="#CED3FE">
    <img src="image/c1.jpg" />
   <hr>
<center>
<%
   String err= (String)request.getAttribute("err");

   if(err!=null){
	   if(err.equals("1")){
		   out.println("<font color=red size=3>This is an abnormal login.</font><br/>");
	   }
	   else if(err.equals("2")){
		   out.println("<font color=red size=3>Your Username and/or Password is incorrect.</font><br/>");
	   }
   }
%>

	<span><font color=blue size=4 style=bold>Please Sign in here</font></span><br/>
	<form action="user.jsp" method="post">
		<strong>Username &nbsp; </strong><input type="text" name="userName"><br /> <br/>
		<strong>Password &nbsp; </strong><input type="password" name="password"><br /> 
		<br/>
		<input type="submit" value="login">
		<input type="reset" value="clear"><br/>
	</form>
</center>
</body>
</html>
