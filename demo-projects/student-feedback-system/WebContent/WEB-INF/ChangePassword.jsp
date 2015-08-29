<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="Header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change Password</title>
</head>
<body>
	<div>
		<div class="error">${errorMsg}</div>
		<form action="./FeedbackServlet?action=changePassword" method="post">
			<table style="width: 90%;">
				<tr>
					<td>Current Password: </td><td><input type="password" name="currentPassword" /></td>
				</tr>
				<tr>
					<td>New Password: </td><td><input type="password" name="newPassword" /></td>
				</tr>
				<tr>
					<td>Confirm New Password: </td><td><input type="password" name="confirmNewPassword" /></td>
				</tr>
				<tr>
					<td></td><td><input type="button" value="Reset"/> <input type="submit" value="Change" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>