<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.cineverse.model.bean.FilmBean" %>
<%@ page import="it.unisa.cineverse.model.bean.ProiezioneBean" %>
<%@ page import="it.unisa.cineverse.model.bean.CategoriaPostiBean" %>
<%@ page import="it.unisa.cineverse.model.bean.PostiBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/prenotazioneposto.css">
    <link rel="stylesheet" href="style/posti.css">
<title>Posti</title>
</head>
<body>
	
	<%
		FilmBean f = (FilmBean) request.getAttribute("film");
		List<CategoriaPostiBean> c = (List<CategoriaPostiBean>) request.getAttribute("categoria");
		ProiezioneBean p = (ProiezioneBean) request.getAttribute("proiezioni");
		List<PostiBean> poss = (List<PostiBean>) request.getAttribute("posti");
		
	%>



    <div class="container-film">
        <div class="film">

            <div class="copertina">
                <img src="<%= f.getPoster().get(0).getCopertina()%>" alt = "<%= f.getPoster().get(0).getCopertina()%>">
            </div>

            <div class="descrizione-film">
                <div>
                <p><%=f.getTitolo() %></p>
                </div>
                <p>sala <%= p.getId_sale() %></p>
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
        LUX <span class="prezzo"><%=c.get(0).getModifica_prezzo() %></span>
      </div>

      <div class="legend-item mini-rosso">
        <span class="mini-posto mini-rosso"></span>
        Standard<span class="prezzo"><%=c.get(1).getModifica_prezzo() %></span>
      </div>

      <div class="legend-item mini-blu">
        <span class="mini-posto mini-blu"></span>
        Vip <span class="prezzo"><%=c.get(2).getModifica_prezzo()%></span>
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


  <div class="toast" id="toast"></div>
  
  <script>
    window.appData = {
    		
    		prezzoBase: <%= p.getPrezzo_base() %>,
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
    	        
    	        prezziCategorie: {
                    <% for (int i = 0; i < c.size(); i++) { 
                        CategoriaPostiBean cat = c.get(i);
                    %>
                        "<%= cat.getNome()%>": <%= cat.getModifica_prezzo() %><%= (i < c.size() - 1) ? "," : "" %>
                    <% } %>
                }
    	    };
    
</script>
  <script src="script/posti.js"></script>

</body>
</html>