<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="Header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Students</title>
</head>
<body>
	<div>
	<form action="./FeedbackServlet?action=uploadStudents" enctype="multipart/form-data" method="post">
	
		<table>
			<tr><td colspan="2" style="text-align: center;">Add Students Batch</td></tr>
			<tr>
				<td>Upload CSV file: <input type="file" name="uploadedFile" /><input type="hidden" name="action" value="uploadStudents" /></td>
				<td><input type="submit" value="Submit"/></td>
			</tr>
		</table>
	
	</form>
	</div>
</body>
</html>