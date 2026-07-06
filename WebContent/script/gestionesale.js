
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

let contextPath = window.appData.path + "/admin";
console.log(contextPath);

/*Gestioni bottoni*/
const selectFilm = document.getElementById("film");
const selectOrario = document.getElementById("orario");
const selectSala = document.getElementById("sala");
const btnContinua = document.getElementById("continua");

function caricaOrari() {
    var idFilm = document.getElementById("film").value;

    var selectOrario = document.getElementById("orario");
    var selectSala = document.getElementById("sala");

    selectOrario.innerHTML = '<option value="">Caricamento...</option>';
    selectOrario.disabled = true;

    selectSala.innerHTML = '<option value="">Prima seleziona un orario</option>';
    selectSala.disabled = true;

    if (idFilm === "") {
        selectOrario.innerHTML = '<option value="">Prima seleziona un film</option>';
        return;
    }

    var url = contextPath + "/GestioneSale";

    var params = "action=orari" +
                 "&idFilm=" + encodeURIComponent(idFilm);

    loadAjaxDoc(url, "GET", params, mostraOrari);
}


function mostraOrari(request) {
	console.log("RISPOSTA ORARI:", request.responseText);
    var selectOrario = document.getElementById("orario");

    var orari = JSON.parse(request.responseText);

    selectOrario.innerHTML = '<option value="">Seleziona orario</option>';

    for (var i = 0; i < orari.length; i++) {
        var option = document.createElement("option");

        option.value = orari[i].value;
		console.log(orari[i]);
        option.textContent = orari[i].value.substring(11,16);

        selectOrario.appendChild(option);
    }

    selectOrario.disabled = false;
}

function caricaSale() {
    var idFilm = document.getElementById("film").value;
    var orario = document.getElementById("orario").value;

    var selectSala = document.getElementById("sala");

    selectSala.innerHTML = '<option value="">Caricamento...</option>';
    selectSala.disabled = true;

    if (orario === "") {
        selectSala.innerHTML = '<option value="">Prima seleziona un orario</option>';
        return;
    }

    var url = contextPath + "/GestioneSale";

    var params = "action=sale" +
                 "&idFilm=" + encodeURIComponent(idFilm) +
                 "&orario=" + encodeURIComponent(orario);

    loadAjaxDoc(url, "GET", params, mostraSale);
}


function mostraSale(request) {
	console.log("RISPOSTA SALE:", request.responseText);

	    var selectSala = document.getElementById("sala");

	    var sale = JSON.parse(request.responseText);

	    selectSala.innerHTML = '<option value="">Seleziona sala</option>';

	    for (var i = 0; i < sale.length; i++) {
	        var option = document.createElement("option");

	        // value = id_proiezione
	        option.value = sale[i].id_proiezione;

	        // salvo id_sala dentro data-id-sala
	        option.dataset.idSala = sale[i].id_sala;

	        option.textContent = "Sala " + sale[i].id_sala;

	        selectSala.appendChild(option);
	    }

	    selectSala.disabled = false;
}

function vaiAiPosti() {
	var selectSala = document.getElementById("sala");

	   var idProiezione = selectSala.value;

	   var optionSelezionata = selectSala.options[selectSala.selectedIndex];

	   var idSala = optionSelezionata.dataset.idSala;

	   console.log("ID PROIEZIONE:", idProiezione);
	   console.log("ID SALA:", idSala);

	   if (idProiezione === "") {
	       alert("Seleziona prima una sala");
	       return;
	   }

	   caricaPosti(idProiezione, idSala);
}

function caricaPosti(idProiezione,idSala) {
    var url = contextPath + "/GestioneSale";

    var params = "action=posti" +
                 "&idProiezione=" + encodeURIComponent(idProiezione)
				 +"&idsale=" + encodeURIComponent(idSala);

    console.log("CHIAMATA POSTI:", url + "?" + params);
	
	
    loadAjaxDoc(url, "GET", params, mostraPosti);
}

function mostraPosti(request)
{
	console.log("RISPOSTA POSTI:", request.responseText);
	
	var posti = JSON.parse(request.responseText);

	creaSalaConPosti(posti);
}

function creaSalaConPosti(posti) {
    var mappaPosti = document.getElementById("mappaPosti");
    mappaPosti.innerHTML = "";

    var postiPerRiga = {};

    for (var i = 0; i < posti.length; i++) {
        var posto = posti[i];

        var codicePosto = posto.row_label;
        var lettera = codicePosto.match(/[A-Za-z]+/)[0];

        if (!postiPerRiga[lettera]) {
            postiPerRiga[lettera] = [];
        }

        postiPerRiga[lettera].push(posto);
    }

    for (var lettera in postiPerRiga) {
        var rigaDiv = document.createElement("div");
        rigaDiv.className = "riga";

        var letteraDiv = document.createElement("div");
        letteraDiv.className = "lettera-riga";
        letteraDiv.textContent = lettera;

        rigaDiv.appendChild(letteraDiv);

        var postiRiga = postiPerRiga[lettera];

        postiRiga.sort(function(a, b) {
            var numA = parseInt(a.row_label.replace(/[A-Za-z]+/, ""));
            var numB = parseInt(b.row_label.replace(/[A-Za-z]+/, ""));
            return numA - numB;
        });

        for (var j = 0; j < postiRiga.length; j++) {
            var posto = postiRiga[j];

            var bottonePosto = document.createElement("button");

            bottonePosto.type = "button";
            bottonePosto.id = posto.row_label;
            bottonePosto.textContent = posto.row_label;

            bottonePosto.classList.add("posto");

            // Prima gestisco i vuoti
            if (posto.categoria === "Vuoto") {
                bottonePosto.classList.add("vuoto");
                bottonePosto.disabled = true;
                rigaDiv.appendChild(bottonePosto);
                continue;
            }

            // Poi gestisco gli occupati
            // Gli occupati devono avere priorità sul colore della categoria
            if (posto.occupato === true) {
                bottonePosto.classList.add("occupato");
                bottonePosto.disabled = true;
                rigaDiv.appendChild(bottonePosto);
                continue;
            }

            if (posto.categoria === "Standard") {
                bottonePosto.classList.add("rosso");
                bottonePosto.setAttribute("data-tipo", "rosso");
            } 
            else if (posto.categoria === "VIP") {
                bottonePosto.classList.add("blu");
                bottonePosto.setAttribute("data-tipo", "blu");
            } 
            else if (posto.categoria === "LUX") {
                bottonePosto.classList.add("ultra");
                bottonePosto.setAttribute("data-tipo", "ultra");
            }

            bottonePosto.onclick = function () {
                selezionaPosto(this);
            };

            rigaDiv.appendChild(bottonePosto);
        }

        mappaPosti.appendChild(rigaDiv);
    }
}
