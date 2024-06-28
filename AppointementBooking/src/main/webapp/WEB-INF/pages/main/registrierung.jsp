<%@ page import="wolkenag.frontcontroller.RegistrierungController" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <% RegistrierungController fr= new RegistrierungController();
    fr.CheckLogin(request,response);
    %>
<!DOCTYPE html>
<html lang="de">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Registrierung</title>
<link rel="stylesheet" href="Login/signupstyle.css">
    <script src="Login/signupjs.js"></script>

 <style>
        a {
           color: #92AFAD;
           font-size: 1rem;
           text-decoration: underline;
           
        }
     </style>


</head>
<body>

<form class="box" action="/AppointementBooking/car?controller=registrierung" method="POST">
<h1>Registrieren</h1>
<input type="text" name="vorname" placeholder="Vorname" id="vorname">
<input type="text" name="name" placeholder="Name" id="name">
<input type="text" name="email" placeholder="E-Mail" id="email">
<input type="password" name="password" placeholder="Passwort" id="password">
<input type="password" name="bestaetigen" placeholder="bestaetigen" id="bestaetigen">
<input type="submit" name="registrieren" value="registrieren">
</form>

<div class="wwbild">
   <a><img src="Login/logotrans.jpg" alt="LogoTrans" height="280px" width="280px"></a>
</div>

</body>
</html>