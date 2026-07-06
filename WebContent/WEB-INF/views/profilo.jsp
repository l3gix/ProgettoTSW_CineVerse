<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="style/profilo.css">
<link rel="stylesheet" href="style/sidebarprofilo.css">
</head>
<body>
<%@include file="sidebarprofilo.jsp" %>
	<div class="page">

        <section class="profile-overview">

            <div class="profile-header">
                <div class="profile-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                    <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10s-3.516.68-4.168 1.332c-.678.678-.83 1.418-.832 1.664z"/>
                </svg></div>
                <h1>Panoramica dell'account</h1>
            </div>

            <div class="profile-section-title">
                <h2>DETTAGLI PERSONALI</h2>

                <button class="edit-btn" type="button">
                    ✎ Modifica
                </button>
            </div>

            <div class="profile-details">

                <div class="profile-field">
                    <h3>Nome</h3>
                    <p><%=utente.getNome() %></p>
                </div>

                <div class="profile-field">
                    <h3>Cognome</h3>
                    <p><%=utente.getCognome()%></p>
                </div>

                <div class="profile-field empty">
                    <h3>Cellulare</h3>
                    <p><%=utente.getPhone() %></p>
                </div>

                <div class="profile-field">
                    <h3>Email</h3>
                    <p><%=utente.getEmail() %></p>
                </div>

            </div>

        </section>

    </div>
</body>
</html>