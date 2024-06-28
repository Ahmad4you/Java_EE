<%@ page import = " java.util.*, com.javamsdt.servlettest.LoginServlet" language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h2>login Page</h2>

	<p>
		<a href="/servlettest/">Home Page</a>
	</p>

	<form action="/servlettest/login.jsp" method="post">
		<input type="text" name="username" placeholder="username"> <br>
		<input type="password" name="password" placeholder="password"><br>
		<input type="email" name="email" placeholder="email"><br>
		<button type="submit">Login</button>
		<button type="reset">Rest</button>
	</form>

</body>
</html>