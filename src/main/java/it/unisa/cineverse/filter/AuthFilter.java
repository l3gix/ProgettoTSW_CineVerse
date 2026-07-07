package it.unisa.cineverse.filter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.FilterChain;
import java.io.IOException;

/**
 * Servlet implementation class AuthFilter
 */
@WebFilter("/*")
public class AuthFilter extends HttpFilter 
{
	 @Override
	    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	            throws IOException, ServletException {

	        String path = request.getServletPath();

	        // Se l'URL non è protetto, lascia passare
	        if (!path.startsWith("/admin/") && !path.startsWith("/common/")) {
	            chain.doFilter(request, response);
	            return; // Evita che il codice successivo venga eseguito
	        }

	        // Controllo che il ruolo sia in sessione
	        HttpSession session = request.getSession(false);

	        String role = (session != null) ? (String) session.getAttribute("ruolo") : null;

	        // Controllo autenticazione e autorizzazione
	        boolean autorizzato = false;

	        if (role != null) {

	            if (path.startsWith("/admin/")) {
	                autorizzato = role.equals("admin");

	            } else if (path.startsWith("/common/")) {
	                autorizzato = role.equals("admin") || role.equals("cliente");
	            }
	        }

	        if (autorizzato) {
	            chain.doFilter(request, response);
	        } else {
	            response.sendRedirect(request.getContextPath() + "/WelcomeIndex");
	        }
	    }
}

