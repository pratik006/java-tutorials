<%@page import="java.util.List"%>
<%@page import="com.tict.project.feedback.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="Header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All ${userType}</title>
</head>
<body>
<table>
	<% List<User> students = (List<User>)request.getAttribute("users"); %>
	<% for(User user : students){ %>
	<tr><td><a href="./FeedbackServlet?action=viewUser&uname=<%=user.getUsername()%>"><%=user.getFirstName()%> <%=user.getLastName() %></a></a></td></tr>
	<%} %>
</table>
</body>
</html>