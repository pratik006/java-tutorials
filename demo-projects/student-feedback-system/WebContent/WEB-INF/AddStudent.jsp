<%@page import="java.util.List"%>
<%@page import="com.tict.project.feedback.vo.Course"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="Header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Student</title>

</head>
<body>

	<form action="./FeedbackServlet" method="post">
		<input type="hidden" name="action" value="saveStudent" />
		<table>
			<tr>
				<td>First Name</td>
				<td><input type="text" name="fname" /></td>
				<td>Last Name</td>
				<td><input type="text" name="lname" /></td>
			</tr>
			<tr>
				<td>Roll Number</td>
				<td><input type="text" name="username" /></td>
				<td>Type</td>
				<td><input type="text" name="utype" value="STUDENT" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>Course</td>
				<td>
					<select name="courseId">
						<option value="Select">Select</option>
						<%List<Course> courses = (List<Course>)request.getAttribute("courses");%>
						<%for(Course course : courses) { %>
						<option value="<%= course.getId() %>"><%= course.getName() %></option>
						<%} %>
					</select>
				</td>
				<td>Semester</td>
				<td><select name="semester">
						<option value="semester1">Semester 1</option>
						<option value="semester2">Semester 2</option>
						<option value="semester3">Semester 3</option>
						<option value="semester4">Semester 4</option>
						<option value="semester5">Semester 5</option>
						<option value="semester6">Semester 6</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="3"></td>
				<td><input type="submit" value="Add Student" /></td>
			</tr>
		</table>
	</form>
</body>
</html>