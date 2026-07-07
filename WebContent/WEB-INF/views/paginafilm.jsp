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
<link rel="stylesheet" href="<%=request.getContextPath() %>/style/navbar.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/style/paginafilm.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/style/search.css">
</head>
<body>
	
	<%@include file="navbar.jsp" %>
	
	<%
		FilmBean filmpagina = (FilmBean) request.getAttribute("filmpagina");
		
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

	
	<section class="showing-list">
        <div class="container">
        <div class="showing-list-info">
            <div class="showing-list-film">
                <img src="<%= filmpagina.getPoster().get(0).getCopertina() %>" alt="<%= filmpagina.getPoster().get(0).getCopertina() %>">
            </div>
            <div class="showing-film-card">
                    <h1><%=filmpagina.getTitolo() %></h1>
                    <p><%=filmpagina.getSinossi() %></p>
                    <h3>Cast</h3>
                    <p><%=filmpagina.getCast_film() %></p>
                    <h3>Durata</h3>
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
        <div class="title">
        
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
             <a href="<%= request.getContextPath() %>/WelcomePrenotazionePosti?id=<%= p.getId() %>">
                <div class="session-range">
                    <p><span style="color:white"><%= p.getStarts().toLocalTime() %> </span>- <%= p.getEnds().toLocalTime() %></p>
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
        </div>
    </section>

    <script>
    //modo per passare il contextpath
	    const contextPath = "<%= request.getContextPath() %>";
	    const id = "<%= filmpagina.getId()%>"
	</script>
	
    <script src="<%=request.getContextPath() %>/script/paginafilm.js"></script>
</body>
</html>