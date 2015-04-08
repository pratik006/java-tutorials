<%@page import="com.tict.project.feedback.vo.Course"%>
<%@page import="com.tict.project.feedback.vo.Subject"%>
<%@page import="java.util.List"%>
<%@page import="com.tict.project.feedback.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Feedback</title>
</head>
<body>
	<h1 style="text-align: center;">Student Feedback</h1>


	<form action="./FeedbackServlet?action=saveFeedback" method="post">
		COURSE:<br> <select name="course">
			<% 
			List<Course> courses = (List<Course>)request.getAttribute("courses");
			for(Course course : courses) { 
			%>
			<option value="<%=course.getId()%>"><%= course.getName() %></option>
			<%} %>
		</select> <br> SUBJECT:<br> 
		
		<select name="subjectId">
			<% 
			List<Subject> subjects = (List<Subject>)request.getAttribute("subjects");
			for(Subject subject : subjects) { 
			%>
			<option value="<%=subject.getId()%>"><%= subject.getName() %></option>
			<%} %>
		</select> <br> FACULTY NAME:<br> 
		
		<select name="facultyId">
			<% 
			List<User> faculties = (List<User>)request.getAttribute("faculties");
			for(User faculty : faculties) { 
			%>
			<option value="<%=faculty.getId()%>"><%= faculty.getFirstName()+" "+faculty.getLastName() %></option>
			<%} %>
		</select> <br>
		
		
		<table border="1" style="width: 100%">
			<tr>
				<th>EVALUATION</th>
				<th>REMARKS</th>

			</tr>
			<tr>
				<td>DEPTH IN THE SUBJECT</td>
				<td><select name="DEPTH">
						<option value="3">GOOD</option>
						<option value="2">STANDARD</option>
						<option value="1">BAD</option>
				</select></td>
			</tr>

			<tr>
				<td>COMMUNICATION SKILL</td>
				<td><select name="COMM_SKILL">
						<option value="3">GOOD</option>
						<option value="2">STANDARD</option>
						<option value="1">BAD</option>
				</select></td>
			</tr>

			<tr>
				<td>CONFIDENCE IN TEACHING</td>
				<td><select name="CONFIDENCE">
						<option value="3">GOOD</option>
						<option value="2">STANDARD</option>
						<option value="1">BAD</option>
				</select></td>
			</tr>

			<tr>
				<td>RELATIONSHIP WITH STUDENT</td>
				<td><select name="RELATION">
						<option value="3">GOOD</option>
						<option value="2">STANDARD</option>
						<option value="1">BAD</option>
				</select></td>
			</tr>

			<tr>
				<td>PATIENCE</td>
				<td><select name="PATIENCE">
						<option value="3">GOOD</option>
						<option value="2">STANDARD</option>
						<option value="1">BAD</option>
				</select></td>
			</tr>

			<tr>
				<td>HOW FREE YOU FEEL TO ASK A QUESTION</td>
				<td><select name="COMFORT">
						<option value="3">GOOD</option>
						<option value="2">STANDARD</option>
						<option value="1">BAD</option>
				</select></td>
			</tr>

			<tr>
				<td>PUNCTUALITY</td>
				<td><select name="PUNCTUALITY">
						<option value="3">GOOD</option>
						<option value="2">STANDARD</option>
						<option value="1">BAD</option>
				</select></td>
			</tr>

			<tr>
				<td>HOW INTERSTING THE TEACHER CLASS IS?</td>
				<td><select name="INTEREST">
						<option value="3">GOOD</option>
						<option value="2">STANDARD</option>
						<option value="1">BAD</option>
				</select></td>
			</tr>

			<tr>
				<td>HOW RELEVANT IS THAT NOTES TO STUDY</td>
				<td><select name="NOTES">
						<option value="3">GOOD</option>
						<option value="2">STANDARD</option>
						<option value="1">BAD</option>
				</select></td>
			</tr>

		</table>

		<br> <input type="submit" value="Submit">
	</form>


</body>
</html>