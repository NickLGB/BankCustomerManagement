<%@ page language="java" 
    import="java.util.*, java.sql.*, com.intern.model.*"
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transaction</title>
</head>
<body bgcolor="#CED3FE">
    <img src="image/c1.jpg" />
   <hr>
   <center>
 <h1>Transaction History</h1>
	<%	 
	
	     ArrayList tranList = (ArrayList) request.getAttribute("result");

	%>
	<span><%=session.getAttribute("userName")%></span>
	<hr>
	
	<table border="1">
		<tr bgcolor="pink">
			<td>Transaction Date</td>
			<td>Transaction Type</td>
			<td>Amount</td>
			<td>Amount Balance</td>
		</tr>
		<%
			for (int i = 0; i < tranList.size(); i++) {
				TranData td = (TranData) tranList.get(i);
		%>
		<tr bgcolor="silver">
			<td><%=td.getDate()%></td>
			<td><%=td.getTranType()%></td>
			<td>$<%=td.getAmt()%></td>
            <td>$<%=td.getBalance()%></td>		
		</tr>
		<%
			}
		%>
	</table>
 
    <hr>
 	<a href="home.jsp">Home</a> &nbsp; &nbsp; &nbsp;
	<a href="logoff.jsp">Log off</a>
	</center>
</body>
</html>