<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Employee</title>
</head>
<body>
	<h1>Remove Employee</h1>
	<form:form action="removeEmployee" modelAttribute="employeeToRemove">
		<form:select path="id" items="${allEmployees}" itemLabel="name" />
		<input type="submit" value="Remove" />
	</form:form>
	<br>
	<button onclick="window.location.href = '/employee';">Home</button>
</body>
</html>