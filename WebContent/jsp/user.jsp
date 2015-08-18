<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
import="java.util.*, java.sql.*, com.intern.model.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#CED3FE">
    <img src="image/c1.jpg" />
   <hr>
   <center>
 
    <%	 
		UserBean ub = (UserBean) request.getAttribute("result");
	%>
   <h2>Welcome! <%=ub.getUserName()%> </h2>
	<table border="1">
		<tr bgColor=pink>
			<td>Id</td>
			<td>Name</td>
			<td>Password</td>	
			<td>Email</td>
			<td>Phone Number</td>
			<td>Address</td>
			<td>Edit</td>
			<td>Transaction</td>
		</tr>

		<tr bgColor=silver>
			<td><%=ub.getUserId()%></td>
			<td><%=ub.getUserName()%></td>
			<td><%=ub.getPasswd()%></td>
			<td><%=ub.getEmail()%></td>
			<td><%=ub.getPhoneNumber()%></td>
			<td><%=ub.getAddress()%></td>
			<td><a href="userprocess.jsp?flag=updateForm&userId=<%=ub.getUserId() %>&userName=<%=ub.getUserName()%>&passwd=<%=ub.getPasswd()%>&email=<%=ub.getEmail()%>&phoneNumber=<%=ub.getPhoneNumber()%>&address=<%=ub.getAddress()%>">Edit</a></td>
			<td><a href="transaction.jsp?userId=<%=ub.getUserId() %>">Transactions</a></td>
		</tr>
		</table>
   <center>	
    <hr> 
     <a href="logoff.jsp">Log off</a>
</body>
</html>