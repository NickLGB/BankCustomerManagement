<%@ page language="java"
	import="java.util.*, java.sql.*, com.intern.model.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>welcome</title>

<script type="text/javascript">
    function abc(){
    	return window.confirm("Are you sure you want to delete?")
    }
    
    function getTransaction(userId){
     	var frm = document.getElementById("myForm");
    	var uId = document.getElementById("userId");
    	uId.value = userId
    	frm.method = "post";
    	frm.submit();
    }
</script>
</head>
<body  bgcolor="#CED3FE">
 <form id="myForm" action="transaction.jsp" method="post">

 <input type="hidden" id="userId" name="userId" value="" />
 <img src="image/c1.jpg" /><br/>
 <hr>
<%
  //  Prevent user's invalid login
    String userName = (String)session.getAttribute("userName");
    if(userName==null){
    	// user invalid login. return to home page
    	  response.sendRedirect("login.jsp?err=1");
    	return;
    }
%>
	<!--  <%=request.getParameter("userName")%>, your login is Successful! -->
	<!--  Welcome!<strong> <%= (String) session.getAttribute("userName") %></strong>!-->
	Welcome! <strong> <%=session.getAttribute("userName")%></strong>!
	<br>
	<input type="hidden" id="userName" name="userName"
		value="<%=request.getParameter("userName")%>" />
    <center>
	<h1>Customers Information List</h1>
	<%	 
		ArrayList al = (ArrayList) request.getAttribute("result");
	%>
	<table border="1">
		<tr bgColor=pink>
			<td>Id</td>
			<td>Name</td>
			<td>Email</td>
			<td>Phone Number</td>
			<td>Address</td>
			<td>Edit</td>
			<td>Delete</td>
			<td>Transaction</td>
		</tr>
		<%
			for (int i = 0; i < al.size(); i++) {
				UserBean ub = (UserBean) al.get(i);
		%>
		<tr bgColor=silver>
			<td><%=ub.getUserId()%></td>
			<td><%=ub.getUserName()%></td>
			<td><%=ub.getEmail()%></td>
			<td><%=ub.getPhoneNumber()%></td>
			<td><%=ub.getAddress()%></td>
			<td><a href="userprocess.jsp?flag=updateForm&userId=<%=ub.getUserId() %>&userName=<%=ub.getUserName()%>&passwd=<%=ub.getPasswd()%>&email=<%=ub.getEmail()%>&phoneNumber=<%=ub.getPhoneNumber()%>&address=<%=ub.getAddress()%>">Edit</a></td>
			<td><a onclick="return abc();" href="userprocess.jsp?flag=delUser&userId=<%=ub.getUserId()%>">Delete</a></td>
			<td><a href="JavaScript:getTransaction(<%=ub.getUserId() %>)">Transactions</a></td>
			
		</tr>
		<%
			}
		%>
	</table>
	
<br/>
	<%
	    //get pageNow from request	    
	    int pageNow = Integer.parseInt ((String) request.getAttribute("pageNow"));
		if (pageNow != 1) {
			out.println("<a href=userprocess.jsp?flag=paging&pageNow="
					+ (pageNow - 1)+">Previous</a>");
		}

		// get pageCount
		String s_pageCount = (String) request.getAttribute("pageCount");
		int pageCount = Integer.parseInt(s_pageCount);

		for (int i = 1; i <= pageCount; i++) {
			out.println("<a href=userprocess.jsp?flag=paging&pageNow=" + i+ ">[" + i + "]</a>");
		}
		if (pageNow != pageCount) {
			out.println("<a href=userprocess.jsp?flag=paging&pageNow="
					+ (pageNow + 1)+">Next</a><br/>");
		}
		

	%>
	

   
	<hr>
	<a href="home.jsp">Home</a> &nbsp; &nbsp; &nbsp;
	<a href="logoff.jsp">Log off</a>
	</center>
</form>
</body>
</html>
