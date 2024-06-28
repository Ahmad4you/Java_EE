<%@ page import="wolkenag.frontcontroller.Terminbuchung2Controller, wolkenag.domain.Buchung, java.util.List" language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <% Terminbuchung2Controller tb= new Terminbuchung2Controller();
    tb.CheckInput(request, response);
    %>
<!DOCTYPE html>
<html lang="de">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>W-Lax Verwaltung</title>
    <link rel="stylesheet" href="Login/Startseite/Terminbuchungsschritt1/Terminbuchungsschritt2/BuchungSchritt2.css">
    <script src="Login/Startseite/Terminbuchungsschritt1/Terminbuchungsschritt2/BuchungSchritt2.js"></script>

    <style>
        a {
           color: #92AFAD;
           font-size: 1rem;
           text-decoration: underline;
           
        }
     </style>

</head>
<body>
   <div class="wwbild">
      <a><img src="Login/Startseite/Terminbuchungsschritt1/Terminbuchungsschritt2/logotrans.jpg" alt="LogoTrans" height="280px" width="280px"></a>
   </div>
   
   <div class="standort">
   <h1>Standort</h1>
   </div>
   <a href="/AppointementBooking/car?controller=terminbuchung1"><button type="button" class="backbutton">Zurück</button></a>
   <div class="ausstattung">
      <h1>Ausstattung</h1>
   </div>
   <div class="heading">
      <h1>Terminbuchung Schritt 2</h1>
      <a>Bitte wähle an Ausstattung. Solltest du Extrawünsche haben, äußere diese.</a>
  </div>
   <div class="extraWuensche">
      <h3>Extrawünsche</h3>
   </div>

<!--  -->
  <form action="/AppointementBooking/car?controller=terminbuchung2" method="POST"> 
  <div>
   <div class="ulm">
      <input type="radio" id="ulm_radio" name="rad" value="ulm_checked" checked>
      <label for="ulm_radio">Stuttgart</label>
    </div>
    <br>
    <div class="greven">
      <input type="radio" id="greven_radio" name="rad" value="greven_checked">
      <label for="greven_radio">Muenchen</label>
    </div>
    <br>
   </div>
   
   <div class="checkBillard">
      <input type="checkbox" id="billardtischID" name="billardtischID" value="billardtisch">
      <label for="billardtischID">Billardtisch</label>
    </div>
    <br>
    <div class="checkTischkicker">
      <input type="checkbox" id="tischkickerID" name="tischkickerID" value="tischkicker">
      <label for="tischkickerID">Tischkicker</label>
    </div>
    <br>
    <div class="checkDartscheibe">
      <input type="checkbox" id="dartscheibeID" name="dartscheibeID" value="dartscheibe">
      <label for="dartscheibeID">Dartscheibe</label>
    </div>
     <div class="catering1">
      <input type="checkbox" id="cateringID" name="cateringID" value="catering">
      <label for="cateringID">Catering</label>
    </div>
   <div class="extrawuenscheEingabe">
      <textarea id="extraWuenscheID" name="extraWuenscheID" rows="4" cols="40" placeholder="Extrawünsche" style="resize: none;"></textarea>
   </div>
   <a ><button type="submit" name="btn_weiter" class="vorbutton">Weiter</button></a>
   
   </form>   
</body>
</html>
