<%@page import="java.util.List"%>
<%@page import="com.softserve.itacademy.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Show all users</title>
</head>
<body>
	<%@include file="header.html" %>
	<br><br>
	<table border="1">
		<tr>
			<th>Id</th>
			<th>Username</th>
			<th>Password</th>
			<th colspan="3">Operations</th>
		</tr>
		
		<% 
			for (User user : (List<User>) request.getAttribute("users")) {
		%>
			<tr>
				<td><%=user.getId()%></td>
				<td><%=user.getUsername()%></td>
				<td><%=user.getPassword()%></td>
				<td>
					<a href="/users/read?id=<%=user.getId()%>">Read</a>
				</td>
				<td>
					<a href="/users/update?id=<%=user.getId()%>">Update</a>
				</td>
				<td>
					<a href="/users/delete?id=<%=user.getId()%>">Delete</a>
				</td>
			</tr>
		<%
			}
		%>
	</table>	
</body>
</html>