<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login/Register Form</title>
</head>
<body>
	<h1>Register</h1>
	<form:form action="/register" method="POST" modelAttribute="newAcc">
		<p>
			<form:label path="user_name">User Name:</form:label>
			<form:input path="user_name"></form:input>
			<form:errors path="user_name"></form:errors>
		</p>
		<p>
			<form:label path="email">email:</form:label>
			<form:input path="email"></form:input>
			<form:errors path="email"></form:errors>
		</p>
		<p>
			<form:label path="password">password:</form:label>
			<form:input path="password" type="password"></form:input>
			<form:errors path="password"></form:errors>
		</p>
		<p>
			<form:label path="confirmation">confirm password:</form:label>
			<form:input path="confirmation" type="password"></form:input>
			<form:errors path="confirmation"></form:errors>
		</p>
		<input type="submit" value="Register"/>
	</form:form>
	<h1>Login</h1>
		<form:form action="/login" method="POST" modelAttribute="newLogin">
		<p>
			<form:label path="email">email:</form:label>
			<form:input path="email"></form:input>
			<form:errors path="email"></form:errors>
		</p>
		<p>
			<form:label path="password">password:</form:label>
			<form:input path="password" type="password"></form:input>
			<form:errors path="password"></form:errors>
		</p>
		<input type="submit" value="Login"/>
	</form:form>
</body>
</html>