prezziBasedef = window.appData.prezzoBase;
postidef = window.appData.posti;
prezzidef = window.appData.prezziCategorie;
id_proiezione = window.appData.idproiezione;
contex = window.appData.context;
postiinsala = window.appData.postisala;
console.log(prezzidef);
console.log(postiinsala);



const colore_tipo = {
	"rosso" : "Standard",
	"blu" : "VIP",
	"ultra" : "LUX"
};

const tipotocolor = 
{
	"Standard" : "rosso",
	"VIP" : "blu",
	"LUX" : "ultra",
	"Vuoto" : "vuoto",
}

var sala = [
  {
    lettera: "A",
    posti: [
      "rosso", "rosso", "rosso", "rosso", "rosso", "rosso", "rosso",
      "rosso", "rosso", "rosso", "rosso", "rosso", "rosso", "rosso",
      "rosso", "rosso", "rosso"
    ]
  },
  {
    lettera: "B",
    posti: [
      "rosso", "rosso", "rosso", "rosso", "rosso", "rosso", "rosso",
      "rosso", "rosso", "rosso", "rosso", "rosso", "rosso", "rosso",
      "rosso", "rosso", "rosso"
    ]
  },
  {
    lettera: "C",
    posti: [
      "blu", "blu", "blu", "blu", "blu", "blu", "blu",
      "blu", "blu", "blu", "blu", "blu", "blu", "blu",
      "blu", "blu", "blu"
    ]
  },
  {
    lettera: "D",
    posti: [
      "rosso", "rosso", "rosso", "rosso", "rosso", "rosso", "rosso",
      "rosso", "rosso", "rosso", "rosso", "rosso", "rosso", "rosso",
      "rosso", "rosso", "rosso"
    ]
  },
  {
    lettera: "E",
    posti: [
      "rosso", "rosso", "rosso", "rosso", "rosso", "rosso", "rosso",
      "rosso", "rosso", "rosso", "rosso", "rosso", "rosso", "rosso",
      "rosso", "rosso", "rosso"
    ]
  },
  {
    lettera: "F",
    posti: [
      "vuoto", "ultra", "ultra", "vuoto",
      "ultra", "ultra", "vuoto",
      "ultra", "ultra", "vuoto",
      "ultra", "ultra", "vuoto",
      "ultra", "ultra", "vuoto", "vuoto"
    ]
  },
  {
    lettera: "G",
    posti: [
      "rosso", "rosso", "rosso", "rosso", "rosso",
      "occupato", "accessibile", "vuoto", "vuoto",
      "accessibile", "occupato",
      "rosso", "rosso", "rosso", "rosso", "rosso", "vuoto"
    ]
  }
];

var mappaPosti = document.getElementById("mappaPosti");
var postiScelti = document.getElementById("postiScelti");
var totale = document.getElementById("totale");
var confermaBtn = document.getElementById("conferma");
var toast = document.getElementById("toast");

var selezionati = [];
/*
function creaSala() {
  for (var i = 0; i < sala.length; i++) {
    var riga = sala[i];

    var rigaDiv = document.createElement("div");
    rigaDiv.className = "riga";

    var letteraDiv = document.createElement("div");
    letteraDiv.className = "lettera-riga";
    letteraDiv.innerHTML = riga.lettera;

    rigaDiv.appendChild(letteraDiv);

    for (var j = 0; j < riga.posti.length; j++) {
      var tipoPosto = riga.posti[j];
      var numeroPosto = j + 1;

      var bottonePosto = document.createElement("button");

      bottonePosto.type = "button";
      bottonePosto.className = "posto " + tipoPosto;

	  bottonePosto.id = riga.lettera + numeroPosto
      bottonePosto.setAttribute("data-tipo", tipoPosto);

      if (
        tipoPosto == "occupato" ||
        tipoPosto == "accessibile" ||
        tipoPosto == "vuoto"
      ) {
        bottonePosto.disabled = true;
      }

      bottonePosto.onclick = function () {
        selezionaPosto(this);
      };

      rigaDiv.appendChild(bottonePosto);
    }

    mappaPosti.appendChild(rigaDiv);
  }
}
*/


function creaSala1() {
	const postiPerRiga = {};
	
	postiinsala.forEach(posto => {
		    // prende la lettera da A1, A2, A10, B1 ecc.
		    const lettera = posto.label.match(/[A-Za-z]+/)[0];

		    if (!postiPerRiga[lettera]) {
		        postiPerRiga[lettera] = [];
		    }

		    postiPerRiga[lettera].push(posto);
	});
		
	console.log(postiPerRiga.length);
  for (let lettera in postiPerRiga) {
	console.log(lettera);
    //var riga = sala[i];
	
	//Creazionde della riga 
    var rigaDiv = document.createElement("div");
    rigaDiv.className = "riga";

    var letteraDiv = document.createElement("div");
    letteraDiv.className = "lettera-riga";
    letteraDiv.innerHTML = lettera;

    rigaDiv.appendChild(letteraDiv);
	
	console.log(postiPerRiga);

	const postiRiga = postiPerRiga[lettera];
	
     postiRiga.forEach( posto => {

      var bottonePosto = document.createElement("button");

      bottonePosto.type = "button";
      bottonePosto.className = "posto " + tipotocolor[posto.categoria].toLowerCase();

	  bottonePosto.id = posto.label;
      bottonePosto.setAttribute("data-tipo", tipotocolor[posto.categoria]);

      if (
        posto.categoria == "Vuoto"
      ) {
        bottonePosto.disabled = true;
      }

      bottonePosto.onclick = function () {
        selezionaPosto(this);
      };

      rigaDiv.appendChild(bottonePosto);
    });

    mappaPosti.appendChild(rigaDiv);
  }
}

function selezionaPosto(posto) {

	//console.log(posto)
  var id = posto.id;
  var tipo = posto.getAttribute("data-tipo");

  var codicePosto = id;
  var posizione = cercaPostoSelezionato(codicePosto); // se ci sono posti selezionati 
  
  var categoria = colore_tipo[tipo];
  var prezzoPosto = Number(prezzidef[categoria]);
  
  let params = 
  	"id_posto=" + encodeURIComponent(trovaIdPostoDaCodice(id)) +
  	"&id_proiezione=" + encodeURIComponent(id_proiezione )+
  	"&prezzo=" + encodeURIComponent(prezziBasedef + prezzoPosto);
	
  if (posizione == -1) {
	
	console.log("codice",trovaIdPostoDaCodice(id))
	console.log("tipo:", tipo);
	console.log("categoria:", categoria);
	console.log("prezzo categoria:", prezzidef[categoria]);
	
	
    var nuovoPosto = {
		codice: codicePosto,
		tipo: tipo,
		categoria: categoria,
		prezzo: prezzoPosto
    };
	
	

    selezionati.push(nuovoPosto);
    let colore = posto.querySelector(".posto");
    posto.classList.add("selezionato");
	
	console.log(params)
	
	salvaPostoAjax("aggiungi",params) ;
	
  } else {
    selezionati.splice(posizione, 1);
    posto.classList.remove("selezionato");
	salvaPostoAjax("rimuovi",params) ;
  }

  aggiornaRiepilogo();
}



function trovaIdPostoDaCodice(codicePosto) {
    var postoTrovato = postiinsala.find(p => p.label == codicePosto);
    if (postoTrovato) {
        return postoTrovato.id;
    }
    return null;
}




//funzione cha chiama ajax 
function salvaPostoAjax( azione, par) {
	    var params = 
	        "azione=" + encodeURIComponent(azione) +
			"&" + par;

	    loadAjaxDoc(contex+"/PrenotazionePosti", "POST", params, handleSelezionaPosto);
	}

function handleSelezionaPosto(request)
{
	
}

function postiOccupati() // funzione per controllare se i posti sono occupati dal backend 
{
	const buttons = document.querySelectorAll("#mappaPosti button");
	
	buttons.forEach(b => {
		const isoccupato = postidef.find(
			posto => {
			        return String(posto.label) === String(b.id);
			    });
				if(isoccupato)
				{
					console.log("occupato" + b.id);		
					b.classList.add("occupato");
					b.disabled = true;
				}
				
	});
}

function cercaPostoSelezionato(codicePosto) {
  for (var i = 0; i < selezionati.length; i++) {
    if (selezionati[i].codice == codicePosto) {
      return i;
    }
  }

  return -1;
}

function aggiornaRiepilogo() {
  if (selezionati.length == 0) {
    postiScelti.innerHTML = "nessuno";
    totale.innerHTML = "0,00€";
    return;
  }

  var testoPosti = "";
  var somma = 0;

  for (var i = 0; i < selezionati.length; i++) {
    testoPosti += selezionati[i].codice;
    somma += selezionati[i].prezzo +  prezziBasedef;

    if (i < selezionati.length - 1) {
      testoPosti += ", ";
    }
  }

  postiScelti.innerHTML = testoPosti;
  totale.innerHTML = formattaPrezzo(somma);
}

function formattaPrezzo(prezzo) {
  return prezzo.toFixed(2).replace(".", ",") + "€";
}


function confermaPrenotazione() {
  if (selezionati.length == 0) {
    mostraMessaggio("Seleziona almeno un posto.");
    return;
  }

  var testoPosti = "";
  var somma = 0;

  for (var i = 0; i < selezionati.length; i++) {
    testoPosti += selezionati[i].codice;
    somma += selezionati[i].prezzo + prezziBasedef;

    if (i < selezionati.length - 1) {
      testoPosti += ", ";
    }
  }

  mostraMessaggio(
    "Prenotazione confermata: " + testoPosti + " - Totale " + formattaPrezzo(somma)
  );

  /*
    Qui puoi collegare la prenotazione al carrello o al database.

    Esempio:
    localStorage.setItem("postiCinema", JSON.stringify(selezionati));
  */
 
	console.log("totale da mandare :" + somma );
}

function mostraMessaggio(messaggio) {
  toast.innerHTML = messaggio;
  toast.classList.add("visibile");

  setTimeout(function () {
    toast.classList.remove("visibile");
  }, 2500);
}


confermaBtn.onclick = function () {
  confermaPrenotazione();
};

//creaSala();
creaSala1()
postiOccupati()
aggiornaRiepilogo();


/*Funzioni Ajax */

function createXMLHttpRequest() {
	var request;
	try {
		// Firefox 1+, Chrome 1+, Opera 8+, Safari 1.2+, Edge 12+, Internet Explorer 7+
		request = new XMLHttpRequest();
	} catch (e) {
		// past versions of Internet Explorer 
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP");  
		} catch (e) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("Il browser non supporta AJAX");
				return null;
			}
		}
	}
	return request;
}

function loadAjaxDoc(url, method, params, cFuction) {
	var request = createXMLHttpRequest();
	if(request){
		
		request.onreadystatechange = function() {
			if (this.readyState == 4) {
				if (this.status == 200) {
				    cFuction(this);
				} else {				
					if(this.status == 0){ // When aborting the request
						alert("Problemi nell'esecuzione della richiesta: nessuna risposta ricevuta nel tempo limite");
					} else { // Any other situation
						alert("Problemi nell'esecuzione della richiesta:\n" + this.statusText);
					}
					return null;
				}
		    }
		};
		
		setTimeout(function () {     // to abort after 15 sec
        	if (request.readyState < 4) {
            	request.abort();
        	}
    	}, 15000); 
		
		if(method.toLowerCase() == "get"){
			if(params){
				request.open("GET", url + "?" + params, true);
			} else {
				request.open("GET", url, true);
			}
			request.setRequestHeader("Connection", "close");
	        request.send(null);
	        
		} else {
			
			if(params){
				request.open("POST", url, true);
				request.setRequestHeader("Connection", "close");
				request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	        	request.send(params);
			} else {
				console.log("Usa GET se non ci sono parametri!");
				return null;
			}
			
		}
		
	}
}

