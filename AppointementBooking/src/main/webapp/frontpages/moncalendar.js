
// initialize your calendar, once the page's DOM is ready
$('#calendar').evoCalendar({
	theme: 'Midnight Blue',
	todayHighlight: true,
	eventListToggler: true,
	'sidebarDisplayDefault': false,
	'language': 'de',
	calendarEvents: [
		{
			id: 'event1', // Event's ID (required)
			name: "Event", // Event name (required)
			date: "February/12/2022", // Event date (required)
			type: "holiday", // Event type (required)
			everyYear: true // Same event every year (optional)
		},
		{
			id: '345',
			name: "Vacation Leave",
			badge: "02/13 - 02/15", // Event badge (optional)
			date: ["February/14/2022", "February/17/2022"], // Date range
			description: "Vacation leave for 3 days.", // Event description (optional)
			type: "event",
			color: "#63d867" // Event custom color (optional)
		}
	]

});


/*function goback() {
	history.go(-1);
}
*/

// initialize your calendar, once the page's DOM is ready
$('#smallcalendar').evoCalendar({
	theme: 'Midnight Blue',
	todayHighlight: true,
	eventListToggler: true,
	'sidebarDisplayDefault': false,
	'sidebarToggler': true,
	'format': 'yyyy-mm-dd ',
	'language': 'de',
	

});

$(document).ready(function() {


	/*document.getElementById("von").addEventListener("click", function() {*/
	document.getElementById("smallcalendar").addEventListener("click", function() {
		let dateTosend = $('#smallcalendar').evoCalendar('getActiveDate');

		$.ajax({
			method: "POST",
			url: "http://localhost:8080/Wlax/car?controller=terminbuchung1",
			data: { date: dateTosend },
			success: function(data, textStatus, xhr) {

			},
			error: function(xhr, textStatus, error) {

				console.log(error);
			}

		});
	 });


});


function getDate() {
	var date = $('#smallcalendar').evoCalendar('getActiveDate');
	dateTosend = date;
}

function addCalendarEvent(event) {
	$('#smallcalendar').evoCalendar('addCalendarEvent', { event });
}

function removeCalendarEvent(id) {

	$('#smallcalendar').evoCalendar('removeCalendarEvent', id);
}

$(document).ready(function() {

	//document.getElementById("title").addEventListener("click", function(){

	$.ajax({
		method: "GET",
		url: "http://localhost:8080/Wlax/Events",
		success: function(data, textStatus, xhr) {
			fillCalender(data);
		},
		error: function(xhr, textStatus, error) {

			console.log(error);
		}

	});
	
});


function formatDate(start, end) {
	/*
	start: Jun 22, 2016, 7:10:25 PM
	end:   Jun 22, 2016, 8:10:25 PM
	formatedDate: 9:15:00 AM  - 8:15:00 PM
	*/
	let formatedDate = "";

	start = start.replace(/^.+,\s/g, "")
	end = end.replace(/^.+,\s/g, "")

	formatedDate = start + ' - ' + end;

	return formatedDate;
}

function fillCalender(events) {
	for (let i = 0; i < events.length; i++) {
		$('#calendar').evoCalendar('addCalendarEvent', {
			id: events[i].id_buchung,
			name: events[i].titel,
			badge: formatDate(events[i].beginn, events[i].ende),
			description: events[i].beschreibung,
			date: events[i].beginn,
			type: "event",
			color: "#63d867"
		});

		$('#smallcalendar').evoCalendar('addCalendarEvent', {
			id: events[i].id_buchung,
			name: events[i].titel,
			badge: formatDate(events[i].beginn, events[i].ende),
			description: events[i].beschreibung,
			date: events[i].beginn,
			type: "event",
			color: "#63d867"
		});
	}
}
