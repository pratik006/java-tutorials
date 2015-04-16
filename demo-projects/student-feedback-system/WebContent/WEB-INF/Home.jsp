<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="Header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Home</title>
</head>
<body>

<div>
	<div class="error">${errorMsg}</div>
	<div>${msg}</div>
	<table style="width: 98%;">
		<tr>
			<td colspan="2" style="text-align: center;">Your Profile Details</td>
		</tr>
		<tr>
			<td>Name: ${user.firstName} ${user.lastName}</td><td>username: ${userName}</td>
		</tr>
		<tr>
			<td>email: ${user.email}</td><td>Date of Birth: ${user.dob} </td>
		</tr>
		<tr>
			<td>Gender: ${user.gender} </td><td>Caste: ${user.caste}</td>
		</tr>
		<tr>
			<td>Nationality: ${user.nationality}</td><td></td>
		</tr>
		<tr>
			<td colspan="2"><a href="./FeedbackServlet?action=changePasswordView">change your password</a></td>
		</tr>
	</table>
</div>

</body>
</html>