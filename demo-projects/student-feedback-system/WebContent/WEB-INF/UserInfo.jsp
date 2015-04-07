<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css" >
	@import url('./resources/css/style.css');
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Information</title>
</head>
<body>
<a href="./FeedbackServlet?action=editUser&uname=${user.username}">Edit</a>
<table border="1">
	<tr><td>First Name</td><td>${user.firstName}</td>
	<td>Last Name</td><td>${user.lastName}</td></tr>
	<tr><td>Roll Number</td><td>${user.username}</td><td>Type</td><td>${user.type}</td></tr>
</table>
</body>
</html>