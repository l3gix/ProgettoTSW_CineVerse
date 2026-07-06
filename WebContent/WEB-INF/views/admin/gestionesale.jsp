<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.cineverse.model.bean.FilmBean" %>
<%@ page import="it.unisa.cineverse.model.bean.UtentiBean" %>
<%@ page import="it.unisa.cineverse.model.bean.ProiezioneBean" %>
<%@ page import="it.unisa.cineverse.model.bean.CategoriaPostiBean" %>
<%@ page import="it.unisa.cineverse.model.bean.PostiBean" %>
<%@ page import="it.unisa.cineverse.model.bean.BigliettoBean" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/style/gestionesale.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style/asideadmin.css">
<title>Posti</title>
</head>
<body>
		<%@include file="asideadmin.jsp" %>
	<%
		List<FilmBean> listaFilm = (List<FilmBean>) request.getAttribute("film");
		
	%>
<div class= "selezione-box">
<select id="film" name="film" onchange="caricaOrari()">
    <option value="">Seleziona film</option>

    <% for(FilmBean film : listaFilm) { %>
        <option value="<%= film.getId() %>">
            <%= film.getTitolo() %>
        </option>
    <% } %>
</select>


<select id="orario" name="orario" onchange="caricaSale()" disabled>
    <option value="">Prima seleziona un film</option>
</select>

<select id="sala" name="sala" disabled>
    <option value="">Prima seleziona un orario</option>
</select>

<button id="continua" onclick="vaiAiPosti()">Continua</button>
</div>


    <div class="layout-prenotazione">
    <section class="area-prenotazione">
      <div class="schermo"></div>
      <h1 class="titolo-sala" >SALA </h1>

      <div class="mappa-wrapper" id="mappaPosti">
      </div>
    </section>

   

    </div>
 <script >
 
 window.appData = {
		 path : "<%=request.getContextPath()%>"
 };
 </script>
  
  
  <script src="<%=request.getContextPath() %>/script/gestionesale.js"></script>

</body>
</html>