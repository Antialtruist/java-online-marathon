<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Create new record in Address Book</title>
</head>
<body>
	<%@include file="header.html" %>
	<br><br>
	<%
        String message = (String) request.getAttribute("message");
    %>
    <div><%=message%></div>
    <br>
	<form action="/records/create" method="post">
		<table>
			<tr>
				<td>
					<label for="firstName">First name: </label>
				</td>
				<td>
					<input type="text" id="firstName" name="firstName">
				</td>
			</tr>				
			<tr>
				<td>
					<label for="lastName">Last name: </label>
				</td>
				<td>
					<input type="text" id="lastName" name="lastName">
				</td>
			</tr>	
			<tr>
				<td>
					<label for="address">Address: </label>
				</td>
				<td>
					<textarea type="text" id="address" name="address"></textarea>
				</td>
			</tr>				
			<tr>
				<td>
					<input type="submit" value="Create">
				</td>
				<td>
					<input type="reset" value="Clear">
				</td>
			</tr>				
		</table>
	</form>
</body>
</html>