<%@ page
	import="wolkenag.frontcontroller.Terminbuchung1Controller, wolkenag.domain.Buchung, java.util.List"
	language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
Terminbuchung1Controller tb = new Terminbuchung1Controller();
tb.CheckInput(request, response);
%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css"
	href="calendar/evo-calendar/css/evo-calendar.css" />
<link rel="stylesheet" type="text/css"
	href="calendar/evo-calendar/css/evo-calendar.midnight-blue.css" />
<link rel="stylesheet" type="text/css" href="calendar/monstyle2.css" />

<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.6.0.js"></script>

<script src="calendar/evo-calendar/js/evo-calendar.js" defer></script>
<script type="text/javascript" src="frontpages/moncalendar.js" defer></script>

<script type="text/javascript">
	$(document).ready(function() {

		$("#title").on("click", function() {
			$(this).css("background", "lightblue");
		});

		$("#grund").on("click", function() {
			$(this).css("background", "lightblue");
		});
		
		$("#von").on("click", function() {
			$(this).css("background", "aqua");
		});
		
		$("#bis").on("click", function() {
			$(this).css("background", "red");
		});

	});
</script>


</head>

<body>

        <%@include  file="/Login/Startseite/Terminbuchungsschritt1/index.html" %>
        


	<%
	String calendarDate = request.getParameter("date");
	request.getSession().setAttribute("date", calendarDate);
	System.out.println(" calendarDate : " + calendarDate);
	%>

</body>

</html>
