<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<h1>Employees</h1>

	<table border="1">
		<tr style="font-weight: bold;">
			<td>Name</td>
			<td>Technology</td>
	</tr>
		<c:forEach var="employee" items="${allEmployees}">

			<tr>
				<td><c:out value="${employee.name}" /></td>
				<td><c:out value="${employee.technology.name}" /></td>
			</tr>
		</c:forEach>
</table>

	<br>
	<input type="submit" value="Add employee"
	onclick="window.location.href='/employee/addEmployee';" />
	<br>
	<br>
	<input type="submit" value="Remove employee"
	onclick="window.location='/employee/removeEmployee';" />
	<br>
	<br>
	<input type="submit" value="Logout" onclick="window.location='logout';" />
</body>
</html>