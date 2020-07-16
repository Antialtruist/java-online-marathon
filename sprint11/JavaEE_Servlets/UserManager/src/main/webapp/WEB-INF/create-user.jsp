<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Create new user</title>
</head>
<body>
	<%@include file="header.html" %>
	<br><br>
	<form action="/users/create" method="post">
		<table>
			<tr>
				<td>
					<label for="username">Username: </label>
				</td>
				<td>
					<input type="text" id="username" name="username">
				</td>
			</tr>				
			<tr>
				<td>
					<label for="password">Password: </label>
				</td>
				<td>
					<input type="password" id="password" name="password">
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