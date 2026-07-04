prezziBasedef = window.appData.prezzoBase;
postidef = window.appData.posti;
prezzidef = window.appData.prezziCategorie;
console.log(prezzidef);



const colore_tipo = {
	"rosso" : "Standard",
	"blu" : "VIP",
	"ultra" : "LUX"
};

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
      "rosso", "rosso", "rosso", "rcolore_tipo[tipo]]osso", "rosso",
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

function selezionaPosto(posto) {
  var id = posto.id;
  var tipo = posto.getAttribute("data-tipo");

  var codicePosto = id;
  var posizione = cercaPostoSelezionato(codicePosto); // se ci sono posti selezionati 

  if (posizione == -1) {
	
	var categoria = colore_tipo[tipo];
	
	console.log("tipo:", tipo);
	console.log("categoria:", categoria);
	console.log("prezzo categoria:", prezzidef[categoria]);
	
	var prezzoPosto = Number(prezzidef[categoria]);
	
    var nuovoPosto = {
		codice: codicePosto,
		tipo: tipo,
		categoria: categoria,
		prezzo: prezzoPosto
    };
	
	

    selezionati.push(nuovoPosto);
    let colore = posto.querySelector(".posto");
    posto.classList.add("selezionato");
  } else {
    selezionati.splice(posizione, 1);
    posto.classList.remove("selezionato");
  }

  aggiornaRiepilogo();
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

creaSala();
postiOccupati()
aggiornaRiepilogo();


/*Funzioni Ajax */



