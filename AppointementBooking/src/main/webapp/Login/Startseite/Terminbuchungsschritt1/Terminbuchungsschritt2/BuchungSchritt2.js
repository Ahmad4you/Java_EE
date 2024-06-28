//Ausstattung wird ausgegeben mit der Variable 'ausstattung'
//die einzelnen variablen können mit 'billardtisch','tischkicker' und 'dartscheibe' ausgelesen werden
//mit 'billiardtischCheck','tischkickerCheck' und 'dartscheibeCheck' kann man die boolean werte der Checkboxen rauskriegen

function informationSpeichern(){
    var billiardtischCheck = document.getElementById("billardtischID").checked;
    var tischkickerCheck = document.getElementById("tischkickerID").checked;
    var dartscheibeCheck = document.getElementById("dartscheibeID").checked;
    
    if (billiardtischCheck == true){
        var billardtisch = document.getElementById('billardtischID').value;
    }
    else {
        var billardtisch = "";
    }
    if (tischkickerCheck == true){
        var tischkicker = document.getElementById('tischkickerID').value;
    }
    else{
        var tischkicker = "";
    }
    if (dartscheibeCheck == true){
        var dartscheibe = document.getElementById('dartscheibeID').value;
    }
    else{
        var dartscheibe= "";
    }

    var ausstattung = billardtisch + " " + tischkicker + " " + dartscheibe;


//Cateringwünsche werden ausgelesen mit der Variable 'catering'

    var catering = document.getElementById('cateringEingabeID').value;
    

//Extrawünsche werden ausgelesen mit der Variable 'extraWuensche'

    var extraWuensche = document.getElementById('extraWuenscheID').value;
}
