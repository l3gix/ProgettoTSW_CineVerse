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
<title>Pagamento</title>
  <link  rel="stylesheet" href="style/pagamento.css">
  <link rel="stylesheet" href="style/navbar.css">
  <link rel="stylesheet" href="style/search.css">
</head>
<body>

<%@include file="navbar.jsp" %>
<%
List<FilmBean> listafilm = (List<FilmBean>) request.getAttribute("filmdelpagamento");

double totale = 0;

if (listafilm != null && !listafilm.isEmpty()) {
%>

<div class="carrello">
	<div class="carello-titolo">
    <h1>IL Tuo pagamento</h1>
	</div>
	<div class="info">
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
    	<div class="totale-box-info">
        <p>Totale</p>
        <h2><%= totale %>€</h2>
        </div>
        <section class="payment-card">

            <div class="payment-header">
                <h1>Pagamento</h1>
                <p>Inserisci i dati della carta oppure scegli un provider</p>
            </div>

            <form action="<%=request.getContextPath()%>/Pagamento" method="post" onsubmit="paga(event)">
				<input type="hidden" name="costototale" id="costototale" value="<%= totale %>">
                <input type="hidden" name="provider" id="provider" value="Carta">

                <div class="provider-box">
                    <button type="button" class="provider-btn" onclick="setProvider(this, 'PayPal')">
                        PayPal
                    </button>

                    <button type="button" class="provider-btn" onclick="setProvider(this, 'Google Pay')">
                        Google Pay
                    </button>

                    <button type="button" class="provider-btn" onclick="setProvider(this, 'Apple Pay')">
                        Apple Pay
                    </button>

                    <button type="button" class="provider-btn" onclick="setProvider(this, 'Stripe')">
                        Stripe
                    </button>
                </div>

                <p class="selected-provider">
                    Metodo selezionato: <span id="providerText">Carta</span>
                </p>
                <div class="form-group">
                    <label for="nome">Nome sulla carta</label>
                    <input 
                        type="text" 
                        id="nome" 
                        name="nome_carta" 
                        placeholder="Mario Rossi"
                        required>
                </div>

                <div class="form-group">
                    <label for="numero">Numero carta</label>
                    <input 
                        type="text" 
                        id="numero" 
                        name="numero_carta" 
                        placeholder="1234 5678 9012 3456"
                        maxlength="19"
                        required>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label for="scadenza">Scadenza</label>
                        <input 
                            type="text" 
                            id="scadenza" 
                            name="scadenza" 
                            placeholder="MM/AA"
                            maxlength="5"
                            required>
                    </div>

                    <div class="form-group">
                        <label for="cvv">CVV</label>
                        <input 
                            type="password" 
                            id="cvv" 
                            name="cvv" 
                            placeholder="123"
                            maxlength="4"
                            required>
                    </div>
                </div>

                <button type="submit" class="pay-btn">
                    Paga ora
                </button>

            </form>

        </section>
        
        
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
</div>

<script type="text/javascript" src="script/pagamento.js"></script>
</body>
</html>

