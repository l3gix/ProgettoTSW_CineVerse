<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="it.unisa.cineverse.model.bean.UtentiBean" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="style/modificaprofilo.css">
<link rel="stylesheet" href="style/sidebarprofilo.css">
</head>
<body>

<% 
	UtentiBean u =(UtentiBean) session.getAttribute("utente");

%>
<%@include file="sidebarprofilo.jsp" %>
	<div class="page">

        <section class="profile-overview">

            <div class="profile-header">
                <div class="profile-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor" class="bi bi-person-vcard" viewBox="0 0 16 16">
					  <path d="M5 8a2 2 0 1 0 0-4 2 2 0 0 0 0 4m4-2.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5M9 8a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4A.5.5 0 0 1 9 8m1 2.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5"/>
					  <path d="M2 2a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2zM1 4a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H8.96q.04-.245.04-.5C9 10.567 7.21 9 5 9c-2.086 0-3.8 1.398-3.984 3.181A1 1 0 0 1 1 12z"/>
					</svg>
                </div>
                <h1>Dettagli Personali</h1>
            </div>

            <div class="profile-wrapper">

        <form class="profile-form" method="post" action="<%=request.getContextPath()%>/ModificaProfilo">
			
			<input type="hidden" id="scelta" name="scelta" value="nonpassoword">
            <div class="form-row">
                <label for="nome">Nome</label>
                <input type="text" id="nome" name="nome" value="<%=utente.getNome()%>">
            </div>

            <div class="form-row">
                <label for="cognome">Cognome</label>
                <input type="text" id="cognome" name="cognome" value="<%=u.getCognome()%>">
            </div>

            
            <div class="form-row">
                <label for="numerotelefo">Numero Telefono</label>
                <input type="tel" id="numerotelefo" name="numerotelefo" value="<%=u.getPhone()%>">
            </div>

            <div class="button-row">
                <button type="submit" >Salva</button>
            </div>

        </form>
         <div class="profile-section-title">
                <h2>Cambio Password</h2>
            </div>
        <form class="profile-form" method="post" action="<%=request.getContextPath()%>/ModificaProfilo">
			
			<input type="hidden" id="scelta" name="scelta" value="modificapassword">
            <div class="form-row" style="margin-bottom:2px">
                <label for="nuovapassaword1">Nuova Password</label>
                <input type="text" id="nuovapassaword1" name="nuovapassaword1" placeholder="Nuova Password">
            </div>
            
             <div class="form-row" >
             	<label></label>
                <p>La vecchia password deve includere un minimo di 8 caratteri ,<br>una lettera maiuscola</p>
                
            </div>

            <div class="form-row">
                <label for="nuovapassaword2">Nuova Password</label>
                <input type="password" id="nuovapassaword2" name="nuovapassaword2" placeholder="Nuova Password">
                
            </div>
			
			<% String errorepassword =(String) request.getAttribute("errorroepassword");
               if (errorepassword != null) { %>
               <div class="form-row" >
             	<label></label>
                <p style="color:red"><%= errorepassword %></p>
                
            </div>
                	    
			<%}%>

            <div class="button-row">
                <button type="submit" >Salva</button>
            </div>

        </form>

    </div>
        </section>

    </div>
</body>
</html>