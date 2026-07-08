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
<link rel="stylesheet" href="<%=request.getContextPath() %>/style/navbar.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/style/index.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/style/search.css">
</head>
<body>
	<%@include file="navbar.jsp" %>
	
	<%
		List<FilmBean> films = (List<FilmBean>) request.getAttribute("films");
		List<FilmBean> comingsoon = (List<FilmBean>) request.getAttribute("comingsoon");
		
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
	
	
    <div class="slideshow-container">
  	
  	 <% for (FilmBean f : comingsoon) { %>
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
           
             

 	<% if (films != null && !films.isEmpty()) { %>

    <div class="button-day" id="giorni">

    <button 
        class="<%= dataSelezionata.equals(LocalDate.now()) ? "active-day" : "" %>"
        onclick="window.location.href='<%= request.getContextPath() %>/WelcomeIndex?date=<%= LocalDate.now().atStartOfDay() %>'">

    </button>

    <button 
        class="<%= dataSelezionata.equals(LocalDate.now().plusDays(1)) ? "active-day" : "" %>"
        onclick="window.location.href='<%= request.getContextPath() %>/WelcomeIndex?date=<%= LocalDate.now().plusDays(1).atStartOfDay() %>'">

    </button>

    <button 
        class="<%= dataSelezionata.equals(LocalDate.now().plusDays(2)) ? "active-day" : "" %>"
        onclick="window.location.href='<%= request.getContextPath() %>/WelcomeIndex?date=<%= LocalDate.now().plusDays(2).atStartOfDay() %>'">
        
    </button>

    <button 
        class="<%= dataSelezionata.equals(LocalDate.now().plusDays(3)) ? "active-day" : "" %>"
        onclick="window.location.href='<%= request.getContextPath() %>/WelcomeIndex?date=<%= LocalDate.now().plusDays(3).atStartOfDay() %>'">
        
    </button>

    <button 
        class="<%= dataSelezionata.equals(LocalDate.now().plusDays(4)) ? "active-day" : "" %>"
        onclick="window.location.href='<%= request.getContextPath() %>/WelcomeIndex?date=<%= LocalDate.now().plusDays(4).atStartOfDay() %>'">
     
    </button>

    <button 
        class="<%= dataSelezionata.equals(LocalDate.now().plusDays(5)) ? "active-day" : "" %>"
        onclick="window.location.href='<%= request.getContextPath() %>/WelcomeIndex?date=<%= LocalDate.now().plusDays(5).atStartOfDay() %>'">
       
    </button>

    <button 
        class="<%= dataSelezionata.equals(LocalDate.now().plusDays(6)) ? "active-day" : "" %>"
        onclick="window.location.href='<%= request.getContextPath() %>/WelcomeIndex?date=<%= LocalDate.now().plusDays(6).atStartOfDay() %>'">
        
    </button>

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
				
                <h3>Cast</h3>
                <p><%= f.getCast_film() %></p>

                <h3>Durata</h3>
                <p><%= f.getDurata_minuti() %> min</p>
            </div>
        </div>
		<div class="title">
			
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
                    <p><%= p.getStarts().toLocalTime() %>-<span style="color:#666666"><%= p.getEnds().toLocalTime() %></span></p>
                </div>

                <div class="hall">
                    <p>sala <%=p.getId_sale() %></p>
                    <p>Proiezione laser</p>
                    <p style="text-align: right;">Da <span style="font-size : 20px;color:white"><%=p.getPrezzo_base() %> € </span></p>
                </div>
               </a>
            </div>
            <%} %>
        </div>
    <% } 
    }
    %>
    
    <% } else { %>
	<div class="empty-proiezioni">
    <div class="empty-icon">
        <svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-calendar" viewBox="0 0 16 16">
            <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5M1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4z"/>
        </svg>
    </div>

    <h2>Nessuna proiezione disponibile</h2>
    <p>Non ci sono spettacoli programmati per questa data.</p>
</div>

<% } %>
    
    </div>
    </section>
	<script>
    const contextPath = "<%= request.getContextPath() %>";
	</script>
    <script src="<%=request.getContextPath() %>/script/index.js"></script>
</body>
</html>