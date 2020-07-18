<%@page import="d.bozhevilnyi.Address"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	<title>Read Record from Address Book</title>
</head>
<body>
	<%@include file="header.html"%>
	<br><br>
	<%
	    Address address = (Address) request.getAttribute("address");
	%>
	
	    <p>First name: <%=address.getFirstName()%></p>
	    <p>Last name: <%=address.getLastName()%></p>
	    <p>Address: <%=address.getAddress()%></p>
</body>
</html>