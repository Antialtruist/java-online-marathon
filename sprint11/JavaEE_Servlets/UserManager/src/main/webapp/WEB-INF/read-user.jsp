<%@page import="com.softserve.itacademy.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Show info about user</title>
</head>
<body>
	<%@include file="header.html" %>
	<br><br>
	<% 
		User user = (User) request.getAttribute("user");
	%>	
	<p>Id: <%=user.getId()%></p>
	<p>Username: <%=user.getUsername()%></p>
	<p>Password: <%=user.getPassword()%></p>
</body>
</html>