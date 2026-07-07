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
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Locale" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/style/prenotazioneposto.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/style/posti.css">
<title>Posti</title>
</head>
<body>
	
	<%
		DateTimeFormatter formatterOrario = DateTimeFormatter.ofPattern("HH:mm", Locale.ITALIAN);
		DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("EEEE d MMMM", Locale.ITALIAN);
		
		FilmBean f = (FilmBean) request.getAttribute("film");
		List<CategoriaPostiBean> c = (List<CategoriaPostiBean>) request.getAttribute("categoria");
		ProiezioneBean p = (ProiezioneBean) request.getAttribute("proiezioni");
		List<PostiBean> poss = (List<PostiBean>) request.getAttribute("posti");
		List<PostiBean> postisala = (List<PostiBean>) request.getAttribute("postisala");
		
		//prendo i posti nella sessione 
		 HttpSession sessione = request.getSession(false);

		    List<BigliettoBean> cart = null;

		    if (sessione != null) {
		        cart = (List<BigliettoBean>) sessione.getAttribute("cart");
		    }

		    List<PostiBean> postiCarrello = new ArrayList<>();
		    

		    if (cart != null) {
		        for (BigliettoBean b : cart) {
		            for (PostiBean pPosto : postisala) {
		                if (pPosto.getId() == b.getId_posto()) {
		                    postiCarrello.add(pPosto);
		                    break;
		                }
		            }
		        }
		    }
		
	%>

<header>
        <nav>
            <div class="logo">
                <img src="img/logo_CineVerse.png" alt="logo_CineVerse" width="60px" height="60px">
            </div>
            <div class="desc">
            	<p id="tempo"></p>
            </div>
            <!--profilo-->
             <div class="icon">
                <%
                	UtentiBean utente =(UtentiBean) session.getAttribute("utente"); // prendiamo l'unte dalla sessione
                %>
                <% if(utente == null) { %>
                <button 
                	onclick="window.location.href='<%= request.getContextPath() %>/WelcomeLogin'"
                	>
	                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16" style="color:white;">
	                    <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10s-3.516.68-4.168 1.332c-.678.678-.83 1.418-.832 1.664z"/>
	                </svg>
                </button>
                
                <% } else { %>
                <button 
                	onclick="window.location.href='<%= request.getContextPath() %>/common/WelcomeProfilo'"
                	>
	                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16" style="color: #007BFA">
	                    <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10s-3.516.68-4.168 1.332c-.678.678-.83 1.418-.832 1.664z"/>
	                </svg>
                </button>
                <%} %>
                </div>
        </nav>
 </header>
 
 
    <div class="container-film">
        <div class="film">

            <div class="copertina">
                <img src="<%= f.getPoster().get(0).getCopertina()%>" alt = "<%= f.getPoster().get(0).getCopertina()%>">
            </div>

            <div class="descrizione-film">
                <div>
                <h3>Acquista Ora</h3>
                <p><%=f.getTitolo() %></p>
                </div>
                <p>sala <%= p.getId_sale() %> </p>
                <p><%= p.getStarts().format(formatterData)%>, <%= p.getStarts().format(formatterOrario)%> - <%=p.getEnds().format(formatterOrario) %> </p>
            </div>
        </div>
    </div>

    <main class="pagina">
    <div class="top-bar">
      <button class="back-btn" onclick="window.location.href='<%=request.getContextPath()%>/WelcomeIndex'"> Vedi tutti i film</button>
    </div>

    <div class="layout-prenotazione">
    <section class="area-prenotazione">
      <div class="schermo"></div>
      <h1 class="titolo-sala">SALA <%= p.getId_sale()%></h1>

      <div class="mappa-wrapper" id="mappaPosti">
      </div>
    </section>

    <section class="riepilogo">
      <div class="legend-item mini-ultra">
        <span class="mini-posto mini-ultra"></span>
        LUX <span class="prezzo"><%=c.get(0).getModifica_prezzo() + p.getPrezzo_base()%></span>
      </div>

      <div class="legend-item mini-rosso">
        <span class="mini-posto mini-rosso"></span>
        Standard<span class="prezzo"><%=c.get(1).getModifica_prezzo() + p.getPrezzo_base()%></span>
      </div>

      <div class="legend-item mini-blu">
        <span class="mini-posto mini-blu"></span>
        Vip <span class="prezzo"><%=c.get(2).getModifica_prezzo() + p.getPrezzo_base()%></span>
      </div>

      <div class="legend-item ">
        <span class="mini-posto mini-occupato"></span>
        Occupato
      </div>

      <div class="box-totale">
        <p>Posti selezionati: <strong id="postiScelti">nessuno</strong></p>
        <p>Totale: <strong id="totale">0,00€</strong></p>

        <div class="azioni">
          <button class="btn" id="conferma">Prosegui</button>
        </div>
      </div>
    </section>
    </div>
  </main>


  
  <div id="modale" class="modale">
    <div class="box-modale">
        <h2 id="titolo-modale"></h2>
        <p id="contenuto-modale"></p>

        <button id="bottone-modale">Chiudi</button>
    </div>
</div>
  
  <script>
    window.appData = {
    		context: "<%= request.getContextPath() %>",
    		idproiezione : <%=p.getId() %>,
    		prezzoBase : <%= p.getPrezzo_base() %>,
    		isLogged: <%= session.getAttribute("utente") != null %>,
    		posti: [
    	            <% 
    	                for (int i = 0; i < poss.size(); i++) { 
    	                    PostiBean t = poss.get(i);
    	            %>
    	                {
    	                    id: <%= t.getId() %>,
    	                    label: "<%= t.getRow_label() %>",
    	                    categoria: "<%= t.getId_categoria_posti() %>"
    	                }<%= (i < poss.size() - 1) ? "," : "" %>
    	            <% 
    	                } 
    	            %>
    	        ],
    	        
    	        posticarello: [
    	            <% 
    	                if (postiCarrello != null && !postiCarrello.isEmpty()) {
    	                    for (int i = 0; i < postiCarrello.size(); i++) { 
    	                        PostiBean t = postiCarrello.get(i);
    	            %>
    	                {
    	                    id: <%= t.getId() %>,
    	                    label: "<%= t.getRow_label() %>",
    	                    categoria: "<%= t.getId_categoria_posti() %>"
    	                }<%= (i < postiCarrello.size() - 1) ? "," : "" %>
    	            <% 
    	                    }
    	                }
    	            %>
    	        ],
    	        
    	        postisala: [
    	            <% 
    	                for (int i = 0; i < postisala.size(); i++) { 
    	                    PostiBean t = postisala.get(i);
    	            %>
    	                {
    	                    id: <%= t.getId() %>,
    	                    label: "<%= t.getRow_label() %>",
    	                    categoria: "<%= t.getId_categoria_posti() %>"
    	                }<%= (i < postisala.size() - 1) ? "," : "" %>
    	            <% 
    	                } 
    	            %>
    	        ],
    	        
    	        prezziCategorie: {
                    <% for (int i = 0; i < c.size(); i++) { 
                        CategoriaPostiBean cat = c.get(i);
                    %>
                        "<%= cat.getNome()%>": <%= cat.getModifica_prezzo() %><%= (i < c.size() - 1) ? "," : "" %>
                    <% } %>
                }
    	    };
    
</script>
  <script src="<%=request.getContextPath() %>/script/posti.js"></script>

</body>
</html>