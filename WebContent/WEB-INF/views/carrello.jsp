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
<title>Carrello</title>
  <link  rel="stylesheet" href="style/carrello.css">
  <link rel="stylesheet" href="style/navbar.css">
  <link rel="stylesheet" href="style/search.css">
</head>
<body>

<%@include file="navbar.jsp" %>
<%
List<FilmBean> listafilm = (List<FilmBean>) request.getAttribute("filmdelcarello");

double totale = 0;

if (listafilm != null && !listafilm.isEmpty()) {
%>

<div class="carrello">

    <h1>Il tuo carrello</h1>

    <%
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
	<form action="<%= request.getContextPath() %>/CancellazioneBigliettoDaCarrello" method="post">
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
		
	    <button type="submit">Rimuovi</button>
	</form>
        </div>
    </div>

    <%
            }
        }
    }
    %>

    <div class="totale-box">
        <p>Totale</p>
        <h2><%= totale %>€</h2>
        <button
        	onclick="window.location.href='<%=request.getContextPath() %>/WelcomePagamento'"
        	>
        </button>
    </div>

</div>

<%
} else {
%>

<div class="carrello">
    <h1>Il tuo carrello</h1>
    <p>Il carrello è vuoto.</p>
</div>

<%
}
%>

</body>
</html>

