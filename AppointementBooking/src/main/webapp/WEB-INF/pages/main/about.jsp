<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>	
<html>
<head>
<title>Calendar</title>
<meta charset='utf-8' />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src='calendar/lib/main.js' defer></script>
<script type="text/javascript" src="frontpages/moncalendar.js" defer></script>
<link href='calendar/lib/main.css' rel='stylesheet' />

<script type="text/javascript">
$(document).ready(function(){
	$("input").hide();
	
	$("#btn1").click(function(){
    $("#frm").load("Hello", function(data, state){
    	$("#r").text(state);
    });
    
  });
	     
  $("#calendar").click(function(){
    
  });
  

});


	

</script>

<style type="text/css">
.fc .fc-col-header-cell-cushion { /* needs to be same precedence */
  padding-top: 5px; /* an override! */
  padding-bottom: 5px; /* an override! */
}

#calendar {
  
  width: 900px;
  margin: 0 20px 20px 0;
}

 html, body {
    overflow: hidden; /* don't do scrollbars */
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  
</style>

</head>
<body>
	<jsp:include page="../common/navbar.jsp" />

<input type="text" value="testjqueryHide"/>     

 <button type="button" id="btn1">test recive data</button>
 <button type="button" id="btn2" >test</button>
    <div id="frm"></div>
    <h1 id="r"></h1>
  
    <div id='calendar'></div> 
 

            <br>
    
   
          
       
       
      <%! String jspData = "test";%>     // contains your jsp data
// In JSP(HTML)
<input id="jsp-data" type="hidden" value="<%=jspData%>" />

<%! Object jsData;%>   
<input id="send" type="text" value="<%=jsData%>" class="send1" onblur="<%=jsData%>"/>
<%System.out.println("jsData: "+jsData); %>




</body>

</html>
