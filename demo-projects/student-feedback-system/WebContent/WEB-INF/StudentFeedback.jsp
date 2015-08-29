<%@page import="com.tict.project.feedback.vo.Faculty"%>
<%@page import="java.util.Set"%>
<%@page import="com.tict.project.feedback.vo.FeedbackConfigParam"%>
<%@page import="com.tict.project.feedback.vo.Course"%>
<%@page import="com.tict.project.feedback.vo.Subject"%>
<%@page import="java.util.List"%>
<%@page import="com.tict.project.feedback.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="Header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Feedback</title>
</head>
<body>
	<h1 style="text-align: center;">Student Feedback</h1>
<div id="outerWrapper" style="width: 100%;">
<div id="feedbackFormDiv" style="width: 50%; margin: 0 auto; float: none;">

		<form action="./FeedbackServlet?action=addFeedback" method="post">
		
		<%
		Set<Course> courses = (Set<Course>)request.getAttribute("courses");
		Set<Subject> subjects = (Set<Subject>)request.getAttribute("subjects");
		Set<Faculty> faculties = (Set<Faculty>)request.getAttribute("faculties");
		%>
		
		<div>
		<table style="width: 90%;">
			<tr>
				<td>COURSE:</td>
				<td>
					<select name="courseId">
					<%
					 for(Course course : courses) {					  
					%>
						<option value="<%= course.getId() %>"><%= course.getName() %></option>
					<%} %>
					</select>
				</td>
			
				<td>SUBJECT:</td>
				<td>
					<select name="subjectId" onchange="this.form.submit();">
						<%
						if(subjects.size() > 1) {
						%>
						<option>Select</option>
						<% } %>
						<% 
						for(Subject subject : subjects) {
						%>
						<option value="<%= subject.getId() %>"><%= subject.getName() %></option>
						<%} %>
					</select>
				</td>
			</tr>
			<tr>
				<td>FACULTY NAME:</td>
				<td>
					<select name="facultyId" onchange="this.form.submit();">
						<%
						if(faculties.size() > 1) {
						%>
						<option>Select</option>
						<% } %>
						<% 
						for(Faculty faculty : faculties) {
						%>
						<option value="<%= faculty.getId() %>"><%= faculty.getFirstName() %> <%= faculty.getLastName() %></option>
						<%} %>
					</select>
				</td>
				<td></td>
				<td></td>
			</tr>
		</table>
		</div>
		</form>
		<form action="./FeedbackServlet?action=saveFeedback" method="post">
		<div>
			<table style="width: 90%; border-top: 1px solid black;">
			<tr>
				<th>EVALUATION</th>
				<th style="width: 100px;">REMARKS</th>

			</tr>
			<%	List<FeedbackConfigParam> params = (List<FeedbackConfigParam>)request.getAttribute("params");
				for(FeedbackConfigParam param : params) { 
			%>
			<tr>
				<td><%=param.getDesc()%></td>
				<td>
					<div class="styled-select">
						<select name="<%= param.getCode() %>">
						<%for(int i=1;i<=9;i++) {%>
							<option value="<%= i %>"><%= i %></option>
						<%} %>
						</select>
					</div>
				</td>
			</tr>
			<%}%>
			</table>
		</div>
		
		<input type="hidden" name="semSubFacId" value="<%= request.getAttribute("SEM_SUB_FAC_ID") %>" />
		<input type="submit" value="Submit">
	</form>
</div>
</div>
	


</body>
</html>