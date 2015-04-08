<%@page import="com.tict.project.feedback.vo.FeedbackConfigParam"%>
<%@page import="com.tict.project.feedback.vo.Subject"%>
<%@page import="com.tict.project.feedback.vo.Course"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="Header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Feedback</title>
</head>
<body>
<span style="float: right;"><a href="./FeedbackServlet?action=logout">logout</a></span>	
	<h1 style="text-align: center;">Feedback</h1>
	FACULTY NAME: ${user.firstName} ${user.lastName}<br>
	
	<form action="./FeedbackServlet?action=viewFeedback" method="post">
		COURSE: <select name="course">
			<%
				List<Course> courses = (List<Course>) request.getAttribute("courses");
				for (Course course : courses) {
			%>
			<option value="<%=course.getId()%>"><%=course.getName()%></option>
			<%
				}
			%>
		</select> <br> SUBJECT:<select name="subjectId">
			<%
				List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
				for (Subject subject : subjects) {
			%>
			<option value="<%=subject.getId()%>"><%=subject.getName()%></option>
			<%
				}
			%>
		</select> <br> 
		<input type="submit" value="Submit" /> 
	</form>

<br/>
<b>Feedback Result</b>
<br/>
<table>
	<% List<FeedbackConfigParam> results = (List<FeedbackConfigParam>)request.getAttribute("results");
	if(results != null && results.size()>0) {
		for(FeedbackConfigParam param : results) {
			%>
			<tr><td><%= param.getDesc() %></td><td><%= param.getScore() %></td></tr>
			<%}	
	} %>
	
</table>

</body>
</html>