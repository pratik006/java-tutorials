<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Student</title>

</head>
<body>

<form action="./FeedbackServlet" method="post">
<input type="hidden" name="action" value="saveUser" />
<table>
	<tr><td>First Name</td><td><input type="text" name="fname" value="${user.firstName}"/></td><td>Last Name</td><td><input type="text" name="lname" value="${user.lastName}"/></td></tr>
	<tr><td>Roll Number</td><td><input type="text" name="username" value="${user.username}" readonly="readonly"/></td><td>Type</td><td><input type="text" name="utype" value="${user.type}" readonly="readonly"/></td></tr>
	<tr><td colspan="3"></td><td><input type="submit" value="Save" /></td></tr>
</table>
</form>
</body>
</html>