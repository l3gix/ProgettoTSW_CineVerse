<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.cineverse.model.bean.FilmBean" %>
<%@ page import="it.unisa.cineverse.model.bean.ProiezioneBean" %>
<%@ page import="it.unisa.cineverse.model.bean.PostiBean" %>
<%@ page import="it.unisa.cineverse.model.bean.BigliettoBean" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ListaFilm</title>
  <link  rel="stylesheet" href="<%=request.getContextPath() %>/style/listabiglietti.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/style/sidebarprofilo.css">
</head>
<body>

<%@include file="sidebarprofilo.jsp" %>
<%
List<List<FilmBean>> listafilmtotale = (List<List<FilmBean>>) request.getAttribute("listafilm");

double totale = 0;

if (listafilmtotale != null && !listafilmtotale.isEmpty()) {
%>

<div class="carrello">
	<div class="carello-titolo">
	<div>
		<svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor" class="bi bi-ticket-perforated" viewBox="0 0 16 16">
					  <path d="M4 4.85v.9h1v-.9zm7 0v.9h1v-.9zm-7 1.8v.9h1v-.9zm7 0v.9h1v-.9zm-7 1.8v.9h1v-.9zm7 0v.9h1v-.9zm-7 1.8v.9h1v-.9zm7 0v.9h1v-.9z"/>
					  <path d="M1.5 3A1.5 1.5 0 0 0 0 4.5V6a.5.5 0 0 0 .5.5 1.5 1.5 0 1 1 0 3 .5.5 0 0 0-.5.5v1.5A1.5 1.5 0 0 0 1.5 13h13a1.5 1.5 0 0 0 1.5-1.5V10a.5.5 0 0 0-.5-.5 1.5 1.5 0 0 1 0-3A.5.5 0 0 0 16 6V4.5A1.5 1.5 0 0 0 14.5 3zM1 4.5a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 .5.5v1.05a2.5 2.5 0 0 0 0 4.9v1.05a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-1.05a2.5 2.5 0 0 0 0-4.9z"/>
					</svg>
	</div>
    <h1>I Mie Biglietti</h1>
	</div>
	<div class="info">
    <%
    for(List<FilmBean> listafilm : listafilmtotale){
    for (FilmBean film : listafilm) {

        if (film.getProiezione() != null && !film.getProiezione().isEmpty()) {

            for (ProiezioneBean proiezione : film.getProiezione()) {

                String posti = "";
                double totaleSpettacolo = 0;

                if (proiezione.getBiglietto() != null && !proiezione.getBiglietto().isEmpty()) {

                    for (BigliettoBean biglietto : proiezione.getBiglietto()) {

                        posti += biglietto.getId_posto() + ", ";
                        totaleSpettacolo += biglietto.getPrezzo();
                        totale += biglietto.getPrezzo();
                    }

                    if (posti.endsWith(", ")) {
                        posti = posti.substring(0, posti.length() - 2);
                    }
                }
    %>


    <div class="ticket-card">
        <div class="ticket-sopra">
            <h2><%= film.getTitolo() %></h2>

            <p>Sala <%= proiezione.getId_sale() %></p>
            <p><%= proiezione.getStarts() %></p>

            <div class="posti">
                <span>Posti <%= posti %></span>
            </div>
        </div>

        <div class="ticket-sotto">
            <p class="label">Prezzo</p>
            <h3><%= totaleSpettacolo %>€</h3>
	
	    <input type="hidden" name="id_proiezione" value="<%= proiezione.getId() %>">
		 <% 
	    if (proiezione.getBiglietto() != null) {
	        for (BigliettoBean biglietto : proiezione.getBiglietto()) {
	    %>
	        <input type="hidden" name="id_posto" value="<%= biglietto.getId_posto() %>">
	    <%
	        }
	    }
	    %>
		

        </div>
    </div>

    <%
            }
        }
    }
    }}
    %>

</div>
</div>

</body>
</html>

