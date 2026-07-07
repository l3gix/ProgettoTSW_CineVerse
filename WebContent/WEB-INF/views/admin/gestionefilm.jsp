<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="it.unisa.cineverse.model.bean.FilmBean" %>
<%@ page import="it.unisa.cineverse.model.bean.SaleBean" %>
<%@ page import="it.unisa.cineverse.model.bean.FormatoFilmBean" %>
<%@ page import="it.unisa.cineverse.model.bean.ProiezioneBean" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Film And Proiezioni Admin</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style/gestionefilm.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style/asideadmin.css">
    
</head>
<body>
	
	<%@include file="asideadmin.jsp" %>
<%
	List<FilmBean> film = (List<FilmBean>) request.getAttribute("film");
	List<SaleBean> sale = (List<SaleBean>) request.getAttribute("sale");
	List<FormatoFilmBean> formato = (List<FormatoFilmBean>) request.getAttribute("formato");
	
%>
          <% if (request.getAttribute("errore") != null) { %>

		    <p style="color:red;">
		        <%= request.getAttribute("errore") %>
		    </p>
		
		<% } %>

<div class="admin-menu">
        <button onclick="showSection('inser-film')">InserisciFilm</button>
        <button onclick="showSection('insert-proiezioni')">Nuova Proiezione</button>
        <button onclick="showSection('modifica-status-proiezione')">Modifica Status Proiezione</button>
    </div>
    <section class="insert-film section" id="inser-film">
        <h1>Inserisci un nuovo film</h1>
		            
        <form 
      action="<%= request.getContextPath() %>/admin/InserimentoFilm"
      method="post"
      enctype="multipart/form-data">

         <div class="form-group">
        <label for="titolo">Titolo</label>
        <input type="text" name="titolo" id="titolo" placeholder="Inserisci il titolo" required>
        </div>

    <div class="form-group">
        <label for="sinossi">Sinossi</label>
        <textarea name="sinossi" id="sinossi" placeholder="Inserisci una descrizione"></textarea>
    </div>
    
     <div class="form-group">
        <label for="sinossi">Cast</label>
        <textarea name="cast" id="cast" placeholder="Inserisci il cast"></textarea>
    </div>

    <div class="form-group">
    <label for="ageRating">Classificazione età</label>
    <select name="ageRating" id="ageRating" required>
        <option value="">Seleziona classificazione</option>
        <option value="T">Per tutti</option>
        <option value="VM6">VM6</option>
        <option value="VM10">VM10</option>
        <option value="VM14">VM14</option>
        <option value="VM18">VM18</option>
    </select>
</div>

     <div class="form-group">
    <label for="durataMinuti">Durata in minuti</label>
    <input type="number"
           name="durataMinuti"
           id="durataMinuti"
           placeholder="Es. 120"
           required>
    </div>

<!-- devi mettere i generi pero dal jsp -->


    <div class="form-group">
    <label for="dataRilascio">Data rilascio</label>
    <input type="date" 
           name="dataRilascio" 
           id="dataRilascio" 
           required>
</div>

    <div class="form-group">
        <label for="trailer">Link trailer</label>
        <input type="text" name="trailer" id="trailer" placeholder="Inserisci il url del trailer" required>
        </div>

    <input type="hidden" name="status" id="status" value="now_showing">
        

    <div class="form-group">
        <label for="img1">Immagine Poster</label>
        <input type="file" name="img1" id="img1" accept="image/*" required>
    </div>

    <div class="form-group">
        <label for="img2">Immagine Banner</label>
        <input type="file" name="img2" id="img2" accept="image/*" required>
    </div>

    <button type="submit">Inserisci film</button>
    </form>
    </section>


 <section class="modifica-status section" id="modifica-status-proiezione">
        <h1>Gestione Proiezioni</h1>
    
<table class="film-table">
    <thead>
        <tr>
            <th>Titolo</th>
            <th>Sala</th>
            <th>Data inizio</th>
            <th>Data fine</th>
            <th>Status</th>
            <th>Azione</th>
            <th>Cancella</ht>
        </tr>
    </thead>

<%  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'alle' HH:mm"); %>
    <tbody>
           <% for(FilmBean f : film.reversed()) {
        	   for(ProiezioneBean p : f.getProiezione()){
          %>
            <tr>
                <td><%=f.getTitolo() %></td>
                <td><%=p.getId_sale()%></td>
                <td><%=p.getStarts().format(formatter)%></td>
                <td><%=p.getEnds().format(formatter)%></td>
                <td><%=p.getStatus()%></td>
                <td>
                    <form action="<%= request.getContextPath() %>/admin/CambiaStatoProiezione" method="post">
                        <input type="hidden" name="tipo" value="proiezione">

                       <%if(("archived").equals(p.getStatus())) {%>
                            <input type="hidden" name="nuovoStatus" value="scheduled">
                            <input type="hidden" name="id" value="<%=p.getId() %>" >
                            <button type="submit" class="btn-active">scheduled</button>
                       <%} else { %>
                            <input type="hidden" name="nuovoStatus" value="archived">
                            <input type="hidden" name="id" value="<%=p.getId() %>" >
                            <button type="submit" class="btn-archive">archived</button>
                        <%} %>
                    </form>
                </td>
                <td>
                    <form action="<%= request.getContextPath() %>/admin/CambiaStatoProiezione" method="post">
                        <input type="hidden" name="cancella" value="cancella">

                            <input type="hidden" name="id" value="<%=p.getId() %>" >
                            <button type="submit" class="btn-archive">Cancella</button>
                       
                    </form>
                </td>
            </tr>
             <% }}%>
    </tbody>
</table>
    </section>


    <section class="insert-proiezioni section" id="insert-proiezioni">
          <h1>Inserisci nuovo spettacolo</h1>
          <br>

    <form  action="<%= request.getContextPath() %>/admin/InserimentoProiezione" method="post">

        <div class="form-group">
            <label for="id_film">Film</label>
            <select name="id_film" id="id_film" required>
            <% for(FilmBean f : film){ %>
                <option value="<%=f.getId()%>"><%=f.getTitolo() %></option>
                <%} %>
            </select>
        </div>

        <div class="form-group">
            <label for="id_sale">Sala</label>
            <select name="id_sale" id="id_sale" required>
               <% for(SaleBean s : sale){ %>
                <option value="<%=s.getId()%>"> Sala <%=s.getId() %></option>
                <%} %>
            </select>
        </div>

        

        <div class="form-group">
            <label for="starts">Inizio spettacolo</label>
            <input type="datetime-local" name="starts" id="starts" required>
        </div>

        <div class="form-group">
            <label for="ends">Fine spettacolo</label>
            <input type="datetime-local" name="ends" id="ends" required>
        </div>

        <div class="form-group">
            <label for="prezzo_base">Prezzo base</label>
            <input type="number" 
                   name="prezzo_base" 
                   id="prezzo_base" 
                   step="0.01" 
                   min="0" 
                   placeholder="Es. 9.00" 
                   required>
        </div>

     
          
            <input type="hidden" name="status" id="status" value="scheduled" required>
   
        <button type="submit">Inserisci spettacolo</button>

    </form>  

    </section>

       <script src="<%= request.getContextPath() %>/script/gestionefilm.js"></script>
</body>
</html>