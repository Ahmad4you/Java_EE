<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blog Page</title>
<link rel="import" href="frontpages/loginform.html">


</head>
<body>
	<jsp:include page="../common/navbar.jsp" />
	<br>
	
	
	
	<%= request.getAttribute("blog_post") %>
	
	
</body>
</html>