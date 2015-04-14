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
	<b>Feedback Result</b>
	<div style="overflow: auto; max-height: 500px;">
		<table>
			<% String[][] results = (String[][])request.getAttribute("results");
			if(results != null && results.length>0) {
				for(String[] params : results) {
					%>
					<tr>
					<%for(String param : params) { %>
						<td><%= param %></td>
					<%} %>
					</tr>
				<%}	
			} %>
			
		</table>
	</div>

</body>
</html>