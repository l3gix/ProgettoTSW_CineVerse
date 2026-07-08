document.addEventListener("DOMContentLoaded", function () {
    const activeSection = document.body.dataset.activeSection || "vis-pagamenti";
    showSection(activeSection);
});

function showSection(id) {
    const sections = document.querySelectorAll(".section");

    sections.forEach(function (section) {
        section.classList.remove("active");
    });

    const sectionToShow = document.getElementById(id);

    if (sectionToShow != null) {
        sectionToShow.classList.add("active");
    }
}

function filtraPagamenti() {
    const inputUtente = document.getElementById("searchUtente");
    const inputDatainizio = document.getElementById("searchDatainizio");
    const inputDatafine = document.getElementById("searchDatafine");
    const tabella = document.getElementById("tabellaPagamenti");

    if (inputUtente == null || inputDatainizio == null || inputDatafine == null || tabella == null) {
        console.log("Elementi ricerca pagamenti non trovati");
        return;
    }

    const valoreUtente = inputUtente.value.toLowerCase().trim();
    const valoreDatainizio = inputDatainizio.value;
    const valoreDatafine = inputDatafine.value;

    const righe = tabella.querySelectorAll("tbody tr");

    righe.forEach(function (riga) {
        const utente = riga.getAttribute("data-utente");
        const data = riga.getAttribute("data-data");

        const matchUtente = valoreUtente === "" || utente.includes(valoreUtente);

        let matchData = true;

        if (valoreDatainizio !== "" && valoreDatafine !== "") {
            matchData = data >= valoreDatainizio && data <= valoreDatafine;
        } else if (valoreDatainizio !== "") {
            matchData = data >= valoreDatainizio;
        } else if (valoreDatafine !== "") {
            matchData = data <= valoreDatafine;
        }

        if (matchUtente && matchData) {
            riga.style.display = "";
        } else {
            riga.style.display = "none";
        }
    });
}

function resetFiltri() {
    const inputUtente = document.getElementById("searchUtente");
    const inputDatainizio = document.getElementById("searchDatainizio");
    const inputDatafine = document.getElementById("searchDatafine");
    const tabella = document.getElementById("tabellaPagamenti");

    if (inputUtente == null || inputDatainizio == null || inputDatafine == null || tabella == null) {
        console.log("Elementi reset pagamenti non trovati");
        return;
    }

    inputUtente.value = "";
    inputDatainizio.value = "";
    inputDatafine.value = "";

    const righe = tabella.querySelectorAll("tbody tr");

    righe.forEach(function (riga) {
        riga.style.display = "";
    });
}