<%@page import="com.tict.project.feedback.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
html {
	background: #63bad8 url(resources/images/bg.jpg) 50% 0px repeat-x;
	text-align: center;
}

</style>
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/css/menu.css">
<script type="text/javascript" src="resources/js/feedback.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<div style="width: 1024px; margin-left: auto; margin-right: auto;">
	<div style="width: 20%; height: 200px; float: left;">
		<img src="resources/images/tict-logo.jpg" height="200px;" />
	</div>
	<div style="width: 80%; height: 200px; float: right;">
		<img src="resources/images/tict-home.jpg" style="width: 100%; height: 200px;">
	</div>
</div>
<% User user = (User) session.getAttribute("user"); %>

	<nav>
		<ul>
			<li><a href="./FeedbackServlet">Home</a></li>
			<% if("ADMIN".equals(user.getType())) { %>		
			<li><a href="#">Admin</a>
				<ul>
					<li><a href="./FeedbackServlet?action=addStudentsPage">Add Students</a></li>
					<li><a href="./FeedbackServlet?action=addFaculty">Add Faculty</a></li>
					<li><a href="./FeedbackServlet?action=viewFeedback">View Feedback</a></li>
				</ul>
			</li>
			<%} %>
			<% if("STUDENT".equals(user.getType())) { %>
			<li>
				<a href="#">Students</a>
				<ul>
					<li><a href="./FeedbackServlet?action=addFeedback">Give Feedback</a></li>
				</ul>
			</li>
			<%} %>
			<li><a href="./FeedbackServlet?action=logout">Logout</a></li>
			<li><a href="http://www.tict.edu.in/contact-tict.html">Contact Us</a></li>
		</ul>
	</nav>
<body>

	
</body>