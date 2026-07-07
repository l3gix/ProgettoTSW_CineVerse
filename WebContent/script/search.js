const baseUrl = window.appData.contex;

const openSearch = document.getElementById("openSearch");
const closeSearch = document.getElementById("closeSearch");
const searchPanel = document.getElementById("searchPanel");
const searchOverlay = document.getElementById("searchOverlay");
const searchInput = document.getElementById("searchInput");
const searchResults = document.getElementById("searchResults");

let searchTimer = null;

openSearch.addEventListener("click", function () {
    searchPanel.classList.add("active");
    searchOverlay.classList.add("active");

    setTimeout(function () {
        searchInput.focus();
    }, 300);
});

closeSearch.addEventListener("click", chiudiSearch);
searchOverlay.addEventListener("click", chiudiSearch);

function chiudiSearch() {
    searchPanel.classList.remove("active");
    searchOverlay.classList.remove("active");
    searchInput.value = "";
    searchResults.innerHTML = "";
}

searchInput.addEventListener("input", function () {
    const query = searchInput.value.trim();

    clearTimeout(searchTimer);

    if (query.length < 2) {
        searchResults.innerHTML = "";
        return;
    }

    searchTimer = setTimeout(function () {
        cercaFilm(query);
    }, 300);
});

function cercaFilm(query) {
	console.log(baseUrl);
    const searchUrl = baseUrl + "/SearchFilm";
    const params = "q=" + encodeURIComponent(query);

    loadAjaxDoc(searchUrl, "GET", params, mostraRisultatiAjax);
}

function mostraRisultatiAjax(request) {
    searchResults.innerHTML = "";
	console.log(request.responseText);
    let films;

    try {
        films = JSON.parse(request.responseText);
    } catch (e) {
        searchResults.innerHTML = "<p class='search-empty'>Errore nei dati ricevuti</p>";
        console.log(request.responseText);
        return;
    }
	
	console.log(films);

    if (films.length === 0) {
        searchResults.innerHTML = "<p class='search-empty'>Nessun film trovato</p>";
        return;
    }

    films.forEach(function (film) {
        const item = document.createElement("div");
        item.classList.add("search-item");

        item.innerHTML = "<h3>" + film.titolo + "</h3>";

        item.addEventListener("click", function () {
            window.location.href = baseUrl + "/PaginaFilm?id=" + film.id;
        });

        searchResults.appendChild(item);
    });
	
}

document.addEventListener("keydown", function (e) {
    if (e.key === "Escape") {
        chiudiSearch();
    }
});

function createXMLHttpRequest() {
    var request;

    try {
        request = new XMLHttpRequest();
    } catch (e) {
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

    if (request) {

        request.onreadystatechange = function () {
            if (this.readyState == 4) {
                if (this.status == 200) {
                    cFuction(this);
                } else {
                    if (this.status == 0) {
                        alert("Problemi nell'esecuzione della richiesta: nessuna risposta ricevuta nel tempo limite");
                    } else {
                        alert("Problemi nell'esecuzione della richiesta:\n" + this.statusText);
                    }

                    return null;
                }
            }
        };

        setTimeout(function () {
            if (request.readyState < 4) {
                request.abort();
            }
        }, 15000);

        if (method.toLowerCase() == "get") {
            if (params) {
                request.open("GET", url + "?" + params, true);
            } else {
                request.open("GET", url, true);
            }

            request.send(null);

        } else {
            if (params) {
                request.open("POST", url, true);
                request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                request.send(params);
            } else {
                console.log("Usa GET se non ci sono parametri!");
                return null;
            }
        }
    }
}