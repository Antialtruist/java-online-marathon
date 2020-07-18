<%@page import="d.bozhevilnyi.Address"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>List of Records from Address Book</title>
</head>
<body>
	<%@include file="header.html" %>
	<br>
	<p>Sort by: <a href="/records/sort?sortType=ASC">ascending</a> | <a href="/records/sort?sortType=DESC">descending</a></p>
	<table border="1">
		<tr>
			<th>No.</th>
			<th>First name</th>
			<th>Last name</th>
			<th>Address</th>
			<th colspan="3">Operation</th>
		</tr>
		
		<%
	        List<Address> addresses = (List<Address>) request.getAttribute("addresses");
	        for (int i = 0; i < addresses.size(); i++) {
	    %>
	    <tr>
	        <td><%=i+1%></td>
	        <td><%=addresses.get(i).getFirstName()%></td>
	        <td><%=addresses.get(i).getLastName()%></td>
	        <td><%=addresses.get(i).getAddress()%></td>
	        <td>
	            <a href="/records/read?firstName=<%=addresses.get(i).getFirstName()%>&lastName=<%=addresses.get(i).getLastName()%>">Read</a>
	        </td>
	        <td>
	            <a href="/records/update?firstName=<%=addresses.get(i).getFirstName()%>&lastName=<%=addresses.get(i).getLastName()%>">Update</a>
	        </td>
	        <td>
	            <a href="/records/delete?firstName=<%=addresses.get(i).getFirstName()%>&lastName=<%=addresses.get(i).getLastName()%>">Delete</a>
	        </td>
		</tr>
		<%
			}
		%>
	</table>	
</body>
</html>