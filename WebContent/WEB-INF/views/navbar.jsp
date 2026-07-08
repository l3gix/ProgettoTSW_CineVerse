<%@page import="java.nio.file.Path"%>
<%@page import="it.unisa.cineverse.model.bean.UtentiBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<header>
        <nav>
            <div class="logo">
	                <img src="<%=request.getContextPath()%>/img/logo_CineVerse.png" alt="Logo_Cineverse" width="60px" height="60px">
            </div>
			

             <div class="burger">
                <ul>
                    <li><a href="<%= request.getContextPath()%>/WelcomeIndex" >
                    Al cinema</a></li>
                    <li><a href="<%= request.getContextPath()%>/WelcomeComingSoon" >
                    Coming soon</a></li>
                    <li><a href="<%= request.getContextPath()%>/WelcomeAboutUs">About us</a></li>
                </ul>
            </div>


            <div class="icon">
                <!--Search-->
                <button id="openSearch" class="search-icon-btn" type="button">
				    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
 				 <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
					</svg>
				</button>
            
                <!--Cart-->
                 <button 
                	onclick="window.location.href='<%= request.getContextPath() %>/WelcomeCarrello'"
                	>
                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                    <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
                </svg>
 				</button>
                <!--profilo-->
                <%
                	UtentiBean utente =(UtentiBean) session.getAttribute("utente"); // prendiamo l'unte dalla sessione
                %>
                <% if(utente == null) { %>
                <button 
                	onclick="window.location.href='<%= request.getContextPath() %>/WelcomeLogin'"
                	>
	                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16" ">
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
                
            </div>
                <%} %>
                

                <!--Burger-->
                <button class="menu-button" type="button">
                    <!-- Icona burger -->
                    <svg class="menu-icon open-icon" xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5"/>
                    </svg>

                    <!-- Icona X -->
                    <svg class="menu-icon close-icon" xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M2.146 2.146a.5.5 0 0 1 .708 0L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854a.5.5 0 0 1 0-.708"/>
                    </svg>
                </button>
            </div>

           

        
        </nav>
    </header>
    
    <div id="searchOverlay" class="search-overlay"></div>

<div id="searchPanel" class="search-panel">
    <button id="closeSearch" class="close-search" type="button">×</button>

    <h2>CERCA</h2>

    <div class="search-form">
        <input 
            type="text" 
            id="searchInput"
            placeholder="Cerca un film..." 
            autocomplete="off"
        >

        <button type="button" class="search-submit">
            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
 				 <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
					</svg>
        </button>
    </div>

    <div id="searchResults" class="search-results"></div>
</div>
<script>
window.appData = 
{
		contex : "<%=request.getContextPath()%>"
};
</script>
<script src="<%=request.getContextPath() %>/script/navbar.js"></script>
<script src="<%=request.getContextPath() %>/script/search.js"></script>

