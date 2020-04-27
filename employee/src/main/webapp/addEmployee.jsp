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
<h1>Add Employee</h1>
	<form:form action="addEmployee" modelAttribute="newEmployee">
		<table>
			<tr>
				<td>Name:</td>
				<td><form:input path="name" /></td>
		</tr>
			<tr><td>Technology:</td>
				<td><form:input path="technology.name" /></td>
		</tr>

		</table>
		<br>
		<button type="submit">Save</button>
	</form:form>
	<br>
	<button onclick="window.location.href = '/employee';">Home</button>

</body>
</html>