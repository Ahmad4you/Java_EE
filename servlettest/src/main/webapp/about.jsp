<%@ page import = " java.util.*, com.javamsdt.servlettest.AboutServlet" language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>About</title>
</head>
<body>
	<h2>about Page</h2>

	<p>
		<a href="/servlettest/">Home Page</a>
	</p>

	<form action="/servlettest/about.jsp" method="get">
		<input type="text" name="query" placeholder="searsh query">
		<button type="submit">go to google</button>
	</form>
</body>
</html>