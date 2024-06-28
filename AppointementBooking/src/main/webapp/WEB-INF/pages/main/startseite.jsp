<%@ page import="wolkenag.frontcontroller.StartseiteController"
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
StartseiteController st = new StartseiteController();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>StartSeite</title>

<link rel="stylesheet" href="Login/Startseite/stylestartseite.css">
<script src="Login/Startseite/startseitejs.js"></script>


</head>
<body>

	<div class="heading">
		<h1>Startseite</h1>
	</div>

	<div class="menu">
		<a href="/AppointementBooking/car?controller=about"><button type="button">MEINE
				TERMINE</button></a> <br> <a href="/AppointementBooking/car?controller=kalender"><button
				type="button">KALENDER</button></a> <br> <a
			href="/AppointementBooking/car?controller=terminbuchung1"><button type="button">TERMIN
				BUCHEN</button></a> <br> <a href=""><button type="button">APP
				DOWNLOAD</button></a> <br> <a href="./FAQ/FAQ.html"><button
				type="button">FAQ</button></a>
	</div>

	<div class="wwbild">
		<a><img src="Login/Startseite/logotrans.jpg" alt="LogoTrans"
			height="280px" width="280px"></a>
	</div>


</body>
</html>