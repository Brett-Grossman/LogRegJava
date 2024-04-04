<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>

<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Ninja!</title>
</head>
<body>
	<h1>Thanks for logging in <c:out value="${acc.getUser_name()}"></c:out>!</h1>
	<p>
    	<a href="/logout">Logout</a>
	</p>
	
	
	
	
</body>
</html>