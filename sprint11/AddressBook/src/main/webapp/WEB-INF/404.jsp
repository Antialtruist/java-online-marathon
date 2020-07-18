<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Record not found</title>
</head>
<body>
	<h2>Person with name '<%=request.getParameter("firstName")%> <%=request.getParameter("lastName")%>' not found in Address Book!</h2>
</body>
</html>