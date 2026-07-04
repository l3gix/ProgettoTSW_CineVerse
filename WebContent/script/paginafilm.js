let button = document.querySelectorAll("#giorni button");


const giorni = [
    "dom",
    "lun",
    "mar",
    "mer",
    "gio",
    "ven",
    "sab"
]

for(let i = 0 ; i < button.length - 1 ; i++)
{
	let data = new Date();
	    data.setDate(data.getDate() + i);
		
		// sto formattando i giorni per il backend 
	    let anno = data.getFullYear();
	    let mese = String(data.getMonth() + 1).padStart(2, "0"); // aggiungo gli zeri filler nel caso non ci sono
	    let giorno = String(data.getDate()).padStart(2, "0");// aggiungo gli zeri filler nel caso non ci sono

		let ore = String(data.getHours()).padStart(2, "0");
		let minuti = String(data.getMinutes()).padStart(2, "0");
		let secondi = String(data.getSeconds()).padStart(2, "0");

		let dataCompatibileLocalDateTime = `${anno}-${mese}-${giorno}T${ore}:${minuti}:${secondi}`;

		button[i].dataset.datetime = dataCompatibileLocalDateTime;
	    if (i === 0) {
	        button[i].innerHTML = "Oggi";
	    } else if (i === 1) {
	        button[i].innerHTML = "Domani";
	    } else {
	        button[i].innerHTML = button[i].innerHTML = giorni[data.getDay()];
	    }

	    button[i].addEventListener("click", function () {
	        window.location.href = contextPath + "/PaginaFilm?id=" + id + "&date=" + this.dataset.datetime; // chiamata in get al backend con il giorno attuale 
	    });
}


