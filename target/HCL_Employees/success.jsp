<%@ page language="java" import="com.aimee.model.Employee"
	contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Employees</title>
</head>
<body>
	<div class="row">

		<p align="center">You were successfully signed in. Here is your
			list of employees</p>
		<div class="container">
			<h3 class="text-center" align="center">List of Employees</h3>
			<hr>
			<div class="container text-right" align="right">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Employee</a>
			</div>
			<br>
			<table class="table table-bordered" align="center">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Password</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="employee" items="${employees}">

						<tr>
							<td><c:out value="${employee.empID}" /></td>
							<td><c:out value="${employee.empName}" /></td>
							<td><c:out value="${employee.empEmail}" /></td>
							<td><c:out value="${employee.empPwd}" /></td>
							<td><a href="update?id=<c:out value='${employee.empID}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${employee.empID}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>

</html>



