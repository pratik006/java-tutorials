<%@page import="com.tict.project.feedback.vo.Subject"%>
<%@page import="java.util.List"%>
<%@page import="com.tict.project.feedback.vo.Course"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="Header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Faculty</title>

</head>
<body>
	<div>
	<form action="./FeedbackServlet" method="post">
		<input type="hidden" name="action" value="saveFaculty" />
		<table>
			<tr>
				<td>First Name</td>
				<td><input type="text" name="fname" /></td>
				<td>Last Name</td>
				<td><input type="text" name="lname" /></td>
			</tr>
			<tr>
				<td>Username</td>
				<td><input type="text" name="username" /></td>
				<td>Type</td>
				<td><input type="text" name="utype" value="FACULTY" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td>
					<select name="gender">
						<option value="M">Male</option>
						<option value="F">Female</option>
					</select>
				</td>
				<td>Email</td>
				<td><input type="text" name="email" /></td>
			</tr>
		</table>
		
		<table>
			<%for(int i=0;i<10;i++) { %>
			<tr>
				<td>Course</td>
				<td>
					<select name="courseId">
						<option value="">Select</option>
						<%List<Course> courses = (List<Course>)request.getAttribute("courses");%>
						<%for(Course course : courses) { %>
						<option value="<%= course.getId() %>"><%= course.getName() %></option>
						<%} %>
					</select>
				</td>
				<td>Semester</td>
				<td>
					<select name="semester">
						<option value="1">Mca Semester 1</option>
						<option value="2">Mca Semester 2</option>
						<option value="3">Mca Semester 3</option>
						<option value="4">Mca Semester 4</option>
						<option value="5">Mca Semester 5</option>
						<option value="6">Mca Semester 6</option>
						<option value="7">Bca Semester 1</option>
						<option value="8">Bca Semester 2</option>
						<option value="9">Bca Semester 3</option>
						<option value="10">Bca Semester 4</option>
						<option value="11">Bca Semester 5</option>
						<option value="12">Bca Semester 6</option>
					</select>
				</td>
				<td>Subject</td>
				<td>
					<select name="subject">
						<option value="">Select</option>
						<%List<Subject> subjects = (List<Subject>)request.getAttribute("subjects");%>
						<%for(Subject subject : subjects) { %>
						<option value="<%= subject.getId() %>"><%= subject.getName() %></option>
						<%} %>
					</select>
				</td>
			</tr>
			<%} %>
		</table>
		
		<input type="submit" value="Add Faculty" />
	</form>
	</div>
</body>
</html>