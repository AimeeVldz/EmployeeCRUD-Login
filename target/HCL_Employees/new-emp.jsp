<%@ page language="java" import="com.aimee.model.Employee" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>HCL Employees</title>
</head>
<body style="text-align: center">
	<h2 style="text-align: center; color: DarkSlateBlue">HCL Tech</h2>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${employee != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${employee == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${employee != null}">
  Employee Form
 </c:if>
						<c:if test="${employee == null}">
  Employee Form
  </c:if>
					</h2>
				</caption>

				<c:if test="${employee != null}">
					<input type="hidden" name="empID"
						value="<c:out value='${employee.empID}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Employee Name</label> <input type="text"
						value="<c:out value='${employee.empName}' />" class="form-control"
						name="empName" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Employee Email</label> <input type="text"
						value="<c:out value='${employee.empEmail}' />"
						class="form-control" name="empEmail">
				</fieldset>

				<fieldset class="form-group">
					<label>Employee Password </label> <input type="text"
						value="<c:out value='${employee.empPwd}' />" class="form-control"
						name="empPwd">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>

</html>

