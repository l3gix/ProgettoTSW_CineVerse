<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="it.unisa.cineverse.model.bean.UtentiBean" %>
<%@ page import="it.unisa.cineverse.model.bean.PagamentoBean" %>
<%@ page import="it.unisa.cineverse.model.bean.PrenotazioniBean" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Gestione Utenti</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/style/gestioneutente.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/style/asideadmin.css">
</head>
<body>


	<%@include file="asideadmin.jsp" %>
<%
	List<UtentiBean> utenti = (List<UtentiBean>) request.getAttribute("utenti");
	List<PrenotazioniBean> prenotazione =(List<PrenotazioniBean>) request.getAttribute("prenotazioni");
	
%>

<div class="admin-menu">
<button onclick="showSection('vis-utenti')">Utenti</button>
<button onclick="showSection('vis-pagamenti')">Pagamenti utenti</button>

</div>
<section class="list-user section" id="vis-utenti">

<h1>Visualizzazione utenti</h1>

<table class="user-table">
    <thead>
        <tr>
        	<th>Email</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Telefono</th>
            
        </tr>
    </thead>

    <tbody>
           <%for (UtentiBean u : utenti){ %>
            <tr>
                <td><%=u.getEmail() %></td>
                <td><%=u.getNome() %></td>
                <td><%=u.getCognome() %></td>
				<td><%=u.getPhone() %></td>
            </tr>
            <%} %>
    </tbody>
    </table>
    </section>
    
    
    <section class="list-user section" id="vis-pagamenti">

<h1>Pagamento utenti</h1>

<div class="search-box">
    <input 
        type="text" 
        id="searchUtente" 
        placeholder="Cerca per utente"
        oninput="filtraPagamenti()">

    <input 
        type="date" 
        id="searchDatainizio" 
        onchange="filtraPagamenti()">

    <input 
        type="date" 
        id="searchDatafine" 
        onchange="filtraPagamenti()">

    <button type="button" onclick="resetFiltri()">Reset</button>
</div>

<table class="user-table" id="tabellaPagamenti">
    <thead>
        <tr>
        	<th>Utente</th>
            <th>Importo</th>
            <th>Provider</th>
            <th>ID transazione</th>
            <th>Data Pagamento</th>
        </tr>
    </thead>
<%  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'alle' HH:mm"); %>
    <tbody>
           <%for (PrenotazioniBean u : prenotazione){ 
           		for(PagamentoBean p : u.getPagamento()){
           %>
            <tr 
            
            data-utente="<%= u.getId_utenti().toLowerCase() %>"
            data-data="<%= p.getData_pagamento().toLocalDate() %>">
            
            
                <td><%=u.getId_utenti() %></td>
                <td><%=u.getImporto_totale() %></td>
                <td><%=p.getProvider()%></td>
				<td><%=p.getId_transazione_provider() %></td>
				<td><%=p.getData_pagamento().format(formatter) %></td>
            </tr>
            <%}} %>
    </tbody>
    </table>
    </section>
     <script src="<%= request.getContextPath() %>/script/gestioneutente.js"></script>
</body>
</html>