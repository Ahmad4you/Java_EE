<%@ page import="wolkenag.frontcontroller.KalenderController"
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
KalenderController kl = new KalenderController();
%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Kalender</title>

<link rel="stylesheet" type="text/css"
	href="calendar/evo-calendar/css/evo-calendar.css" />
<link rel="stylesheet" type="text/css"
	href="calendar/evo-calendar/css/evo-calendar.midnight-blue.css" />
	<link rel="stylesheet" type="text/css"
	href="calendar/mystyle.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;700&display=swap"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="calendar/evo-calendar/js/evo-calendar.js" defer></script>
<script type="text/javascript" src="frontpages/moncalendar.js" defer></script>





</head>

<body>
	<div class="wwbild">
		<a><img src="calendar/logotrans.jpg" alt="LogoTrans"
			height="280px" width="280px"></a>

	</div>

	<a href="/AppointementBooking/car?controller=startseite"><button type="button"
			class="backbutton">Zur�ck</button></a>




	 <div class="hero">
        <div id="calendar"></div>
        </div>





</body>

</html>