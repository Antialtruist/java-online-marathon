<%@page import="d.bozhevilnyi.Address"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Update existing Record in Address Book</title>
</head>
<body>
	<%@include file="header.html"%>
	<form action="/records/update" method="post">
    <%
    	Address address = (Address) request.getAttribute("address");
    %>

    <table>
        <tr>
            <td>
                <label for="firstName">First name: </label>
            </td>
            <td>
                <input type="text" id="firstName" name="firstName" value="<%=address.getFirstName()%>" disabled>
            </td>
        </tr>
        <tr>
            <td>
                <label for="lastName">Last name: </label>
            </td>
            <td>
                <input type="text" id="lastName" name="lastName" value="<%=address.getLastName()%>" disabled>
            </td>
        </tr>
        <tr>
            <td>
                <label for="address">Address: </label>
            </td>
            <td>
                <textarea  id="address" name="address" value="<%=address.getAddress()%>"></textarea>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Update">
            </td>
            <td>
                <input type="reset" value="Clear">
            </td>
        </tr>
    </table>
</form>
</body>
</html>