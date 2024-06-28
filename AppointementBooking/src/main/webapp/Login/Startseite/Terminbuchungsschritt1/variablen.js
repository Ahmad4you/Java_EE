function eingabeSpeichern()
{
   var ausgabe
   var proof = false;

    if(document.getElementById("name").value == null || document.getElementById("name").value == 0)
    {
        ausgabe = "Name,"
        proof = true;
    }
     if(document.getElementById("grund").value == null || document.getElementById("grund").value == 0)
    {
        ausgabe += "Grund,"
        proof = true;
    }
     if(document.getElementById("von").value == null || document.getElementById("von").value == 0)
    {
        ausgabe += "Von,"
        proof = true;
    }
     if(document.getElementById("bis").value == null || document.getElementById("bis").value == 0)
    {
        ausgabe += "Bis"
        proof = true;
    }

    if(proof == true)
    {
        alert("Bitte alle Felder ausfüllen.(" + ausgabe + ")")
    } else
    {
        var Name = document.getElementById("name").value
        var Grund = document.getElementById("grund").value
        var Von = document.getElementById("von").value
        var Bis = document.getElementById("bis").value
        var activ_date = $('#calendar').evoCalendar('getActiveDate')
        
    }
}

