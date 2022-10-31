<%@ page language="java" import="com.aimee.model.Employee" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<h2 style="text-align: center; color: DarkSlateBlue">Welcome to
		HCL</h2>
	<h4 style="text-align: center; color: DarkSlateBlue">Login</h4>

	<form action="login" method="get" style="text-align: center">

		User Name: <input type="text" name="empName"></br/> Password: <input
			type="password" name="empPwd"></br/> <input type="submit"
			value="Login">
	</form>
</body>
</html>
