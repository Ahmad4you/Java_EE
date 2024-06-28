<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="Login/style2.css" />
<title>login Page</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
	
	
	<form action="/AppointementBooking/" method="post">
  

  <div class="container">
    <label for="name"><b>Nachname</b></label>
    <input type="text" placeholder="Enter Nachname" name="name" required>
    
    <label for="vname"><b>Vorname</b></label>
    <input type="text" placeholder="Enter Vorname" name="vname" required>
    
     <label for="email"><b>Email</b></label>
    <input type="email" placeholder="Enter Email" name="email" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" required>
        
    <button type="submit">Login</button>
    <label>
      <input type="checkbox" checked="checked" name="remember"> Remember me
    </label>
  </div>

  <div class="container">
    <button type="button" class="cancelbtn">Cancel</button>
    <span class="psw">Forgot <a href="#">password?</a></span>
  </div>
</form>
	
</body>
</html>