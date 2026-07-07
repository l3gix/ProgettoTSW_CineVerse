<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.cineverse.model.bean.FilmBean" %>
<%@ page import="it.unisa.cineverse.model.bean.ProiezioneBean" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.TextStyle" %>
<%@ page import="java.util.Locale" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>CineVerse</title>
<link rel="stylesheet" href="style/navbar.css">
<link rel="stylesheet" href="style/index.css">
<link rel="stylesheet" href="style/search.css">
</head>
<body>
	<%@include file="navbar.jsp" %>
	
	<%
		List<FilmBean> films = (List<FilmBean>) request.getAttribute("films");
	%>
	
	
    <div class="slideshow-container">
  	
  	 <% for (FilmBean f : films) { %>
    <div class="mySlides fade">
        <div>
        <img src="<%= f.getPoster().get(0).getBanner() %>" alt="<%= f.getPoster().get(0).getBanner()%>" style="width:100%">
        </div>
        <div class="slide-info">
            <div class="slide-info-desc">
                <h2><%=f.getTitolo()%></h2>
                <p><%=f.getSinossi() %></p>
            </div>
           <div class="slide-info-button">
                <button onclick="window.location.href='<%= request.getContextPath()%>/PaginaFilm?id=<%=f.getId()%>'">Acquista Ora</button>
            </div>
        </div>
    </div>


    <!-- Next and previous buttons -->
    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
    <a class="next" onclick="plusSlides(1)">&#10095;</a>
   <%} %>
    </div>

   

    <section class="showing-list">
        <div class="container">
             <div class="button-day" id ="giorni">
            <button></button>
            <button></button>
            <button></button>
            <button></button>
            <button></button>
            <button></button>
            <button></button>

        </div>
        
        <% if (films != null && !films.isEmpty()) { %>

    <% for (FilmBean f : films) { %>

        <div class="showing-list-info">
            <div class="showing-list-film">
                <a href="<%= request.getContextPath()%>/PaginaFilm?id=<%=f.getId()%>">
                <img src="<%= f.getPoster().get(0).getCopertina()%>" alt="<%= f.getPoster().get(0).getCopertina()%>">
            	</a>
            </div>

            <div class="showing-film-card">
                <h1><%= f.getTitolo() %></h1>
				
				<p class="sinossi"><%=f.getSinossi() %></p>
				
                <h3>cast</h3>
                <p><%= f.getCast_film() %></p>

                <h3>durata</h3>
                <p><%= f.getDurata_minuti() %> min</p>
            </div>
        </div>
		<div class="title">
			<% 
			 String dateParam = request.getParameter("date");
			 LocalDate dataSelezionata;
			 
		    if (dateParam == null || dateParam.isBlank()) {
		        dataSelezionata = LocalDate.now();
		    } else {
		        dataSelezionata = LocalDateTime.parse(dateParam).toLocalDate();
		    }

				
			 String giornoSettimana = dataSelezionata
			            .getDayOfWeek()
			            .getDisplayName(TextStyle.FULL, Locale.ITALIAN)
			            .toUpperCase();

			    String mese = dataSelezionata
			            .getMonth()
			            .getDisplayName(TextStyle.SHORT, Locale.ITALIAN)
			            .toUpperCase()
			            .replace(".", "");
			
			%>
            <h3> 
            PROSSIMI SPETTACOLI PER
            <span style="color : #007BFA">
        		<%= giornoSettimana %>, <%= dataSelezionata.getDayOfMonth() %> <%= mese %>
		    </span>
		    </h3>
        </div>
        <div class="list-hall">
        <% for(ProiezioneBean p : f.getProiezione()) { %>
            <div class="session">
             <a href="<%= request.getContextPath() %>/WelcomePrenotazionePosti?id=<%= p.getId() %>">
                <div class="session-range">
                    <p><%= p.getStarts().toLocalTime() %> - <%= p.getEnds().toLocalTime() %></p>
                </div>

                <div class="hall">
                    <p>sala <%=p.getId_sale() %></p>
                    <p>Proiezione laser</p>
                    <p style="text-align: right;">Da <%=p.getPrezzo_base() %> €</p>
                </div>
               </a>
            </div>
            <%} %>
        </div>
    <% } 
    }
    %>
    </div>
    </section>
	<script>
    const contextPath = "<%= request.getContextPath() %>";
	</script>
    <script src="script/index.js"></script>
</body>
</html>