<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="Header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
This is my Home page.
${msg}
<br/>
<a href="./FeedbackServlet?action=addStudent">Add Student</a>
<br/>
<a href="./FeedbackServlet?action=listStudents">All Students</a>
<br/>
<a href="./FeedbackServlet?action=addFaculty">Add Faculty</a>
<br/>
<a href="./FeedbackServlet?action=listFaculties">All Faculties</a>
</body>
</html>