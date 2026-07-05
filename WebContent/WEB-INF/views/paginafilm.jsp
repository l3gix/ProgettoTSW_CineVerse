<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Film</title>
<link rel="stylesheet" href="style/navbar.css">
<link rel="stylesheet" href="style/paginafilm.css">
</head>
<body>
	
	<%@include file="navbar.jsp" %>
	
	<%
		FilmBean filmpagina = (FilmBean) request.getAttribute("filmpagina");
	%>
	
	<section class="showing-list">
        <div class="container">
        <div class="showing-list-info">
            <div class="showing-list-film">
                <img src="<%= filmpagina.getPoster().get(0).getCopertina() %>" alt="<%= filmpagina.getPoster().get(0).getCopertina() %>">
            </div>
            <div class="showing-film-card">
                    <h1><%=filmpagina.getTitolo() %></h1>
                    <p><%=filmpagina.getSinossi() %></p>
                    <h3>cast</h3>
                    <p><%=filmpagina.getCast_film() %></p>
                    <h3>durata</h3>
                    <p><%=filmpagina.getDurata_minuti() %>min</p>
                    <h3>Data uscita</h3>
                    <p>
                    	<%= filmpagina.getData_rilascio().getDayOfMonth() %> 
					    <%= filmpagina.getData_rilascio().getMonth().getDisplayName(TextStyle.FULL, Locale.ITALIAN) %> 
					    <%= filmpagina.getData_rilascio().getYear() %>
					</p>
            </div>
        </div>
        <div class="button-day" id ="giorni">
            <button></button>
            <button></button>
            <button></button>
            <button></button>
            <button></button>
            <button></button>
            <button></button>
            <button>Tutti</button>

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
         <% for(ProiezioneBean p : filmpagina.getProiezione()) { %>
         
            <div class="session">
                <div class="session-range">
                    <p><%= p.getStarts().toLocalTime() %> - <%= p.getEnds().toLocalTime() %></p>
                </div>
                <div class="hall">
                    <p>sala <%=p.getId_sale() %></p>
                    <p>Proiezione laser</p>
                    <p style="text-align: right;">Da <%=p.getPrezzo_base() %> €</p>
                </div>
            </div>
            
			<%} %>
        	</div>
        </div>
    </section>

    <script>
    //modo per passare il contextpath
	    const contextPath = "<%= request.getContextPath() %>";
	    const id = "<%= filmpagina.getId()%>"
	</script>
	
    <script src="script/paginafilm.js"></script>
</body>
</html>