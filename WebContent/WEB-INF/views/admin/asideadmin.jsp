<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<nav>
<button class="menu-btn" onclick="apriSidebar()" style="color:black">
<svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5"/>
</svg>
</button>
</nav>

    <aside class="sidebar" id="sidebar">
        <button class="close-btn" onclick="chiudiSidebar()">X</button>

        <h2>Menu</h2>
        <a href="<%=request.getContextPath()%>/admin/WelcomeGestioneFilmAdmin">Gestione Film And Proiezioni</a>
        <a href="<%=request.getContextPath()%>/admin/WelcomeGestioneUtenteAdmin">Gestione utenti</a>
        <a href="<%=request.getContextPath()%>/admin/WelcomeGestioneSale">Gestione Sale</a>
        <div class="linea"></div>
        <a href="<%=request.getContextPath()%>/Logout">Logout</a>
        <a href="<%=request.getContextPath()%>/WelcomeIndex">HomePage</a>
        
    </aside>


<script src="<%= request.getContextPath() %>/script/asideadmin.js"></script>