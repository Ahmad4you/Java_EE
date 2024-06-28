<%@ page import="wolkenag.frontcontroller.FrontController" language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
    <% FrontController fr= new FrontController();
    fr.CheckLogin(request,response);
    %>
<!DOCTYPE html>
<html lang="de">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Home Page</title>
  <link rel="stylesheet" href="Login/style2.css">
    <script src="Login/login2.js"></script>
    
     <style>
        a {
           color: #92AFAD;
           font-size: 1rem;
           text-decoration: underline;
           
        }
     </style>
</head>
<body>
<div >

	<form class="box" action="/AppointementBooking/index.jsp" method="POST">
<h1>login</h1>
	 <div class="user-box">

<input type="text" name="email" placeholder="E-Mail" id="email" >
<input type="password" name="password" placeholder="Passwort" id="password">
</div>
<input type="submit" name="Login" value="Login" >

<a target="_self">Passwort vergessen?</a>
<br>
<br>
<a href="/AppointementBooking/car?controller=registrierung" target="_self">Noch keinen Account? Hier kostenlos registrieren.</a>

</form>
</div>
<div class="wwbild">
   <a><img src="Login/logotrans.jpg" alt="LogoTrans" height="280px" width="280px"></a>
</div>
	

<%-- 	<% session.setAttribute("errorusername", "username or password can not be empty");
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	 
	%>
	
	<% if (email ==null || email =="" ||  password ==null || password =="" ){ %>
	
	<% }else { %>
		<%= "nein" %>
		<jsp:forward page="/car?controller=about"></jsp:forward>
	<% } %> --%>
	
	
	<div class="wwbild">
   <a><img src="Login/logotrans.jpg" alt="LogoTrans" height="280px" width="280px"></a>
</div>
</body>
</html>