<%@ page language="java" import="com.aimee.model.Employee"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
</head>
<body>
	<p style="text-align: center; color: DarkSlateBlue">
		Invalid Credentials. Please Try Again.
		<%@ include file="login.jsp"%>
	</p>
</body>
</html>