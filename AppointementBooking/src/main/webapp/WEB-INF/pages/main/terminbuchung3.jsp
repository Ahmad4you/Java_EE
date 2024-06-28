<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
    <title>Terminbuchung Schritt 3</title>

    <link rel="stylesheet" href="Login/Startseite/Terminbuchungsschritt1/Terminbuchungsschritt2/Terminbuchungsschritt3/styleterminbuchung3.css">
    <script src="Login/Startseite/Terminbuchungsschritt1/Terminbuchungsschritt2/Terminbuchungsschritt3/terminbuchung3js.js"></script>
</head>
    <button type="button" class="backbutton">Zur�ck</button>
<body>
<div class="heading">
    <h1>Terminbuchung Schritt 3</h1>
    <a>Bitte trage alle Teilnehmer ein, die zu erwarten sind.</a>
</div>

<div class="teilnehmer">
<div id="myDIV" class="header">
    <h2>Teilnehmerliste</h2>
    <input type="text" id="myInput" placeholder="Name, Vorname">
    <span onclick="newElement()" class="addBtn">+</span>
  </div>
  
  <ul id="myUL">
 
  </ul>
</div>
<div class="weiter">
    <input type="submit" name="" value="Eingabe best�tigen" onclick="eingabeBestaetigen()">
</div>


<div class="wwbild">
    <a><img src="Login/Startseite/Terminbuchungsschritt1/Terminbuchungsschritt2/Terminbuchungsschritt3/logotrans.jpg" alt="LogoTrans" height="280px" width="280px"></a>
</div>
</body>
</html>