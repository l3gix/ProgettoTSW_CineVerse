<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<nav>
<button class="menu-btn" onclick="apriSidebar()" style="color:black">☰</button>
</nav>

    <aside class="sidebar" id="sidebar">
        <button class="close-btn" onclick="chiudiSidebar()">X</button>

        <h2>Menu</h2>
        <a href="<%=request.getContextPath()%>/admin/WelcomeGestioneFilmAdmin">Gestione Film And Proiezioni</a>
        <a href="<%=request.getContextPath()%>/admin/WelcomeGestioneUtenteAdmin">Gestione utenti</a>
        <div class="linea"></div>
        <a href="<%=request.getContextPath()%>/Logout">Logout</a>
        <a href="<%=request.getContextPath()%>/WelcomeIndex">HomePage</a>
        
    </aside>


<script src="<%= request.getContextPath() %>/script/asideadmin.js"></script>