function registrieren(){
    var vorname=document.getElementById("vorname").value;
    var name=document.getElementById("name").value;
    var email=document.getElementById("email").value;
    var password=document.getElementById("password").value;
    var password2=document.getElementById("password2").value;

    
    if(password != password2){
        alert("Passwort muss übereinstimmen!");
    }
}