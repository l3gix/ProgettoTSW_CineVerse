package it.unisa.cineverse.controller.common;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.UtentiBean;
import it.unisa.cineverse.model.dao.UtentiDao;
import it.unisa.cineverse.model.dao.impl.FilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.PosterDaoImpl;
import it.unisa.cineverse.model.dao.impl.ProiezioniDaoImpl;
import it.unisa.cineverse.model.dao.impl.UtentiDaoImpl;
import it.unisa.cineverse.util.PasswordUtil;

/**
 * Servlet implementation class ModificaProfilo
 */
@WebServlet("/common/ModificaProfilo")
public class ModificaProfilo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UtentiDao utente;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaProfilo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		if(ds == null) throw new ServletException("DataSource non disponibile nel contesto");
		
		utente = new UtentiDaoImpl(ds);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scelta = request.getParameter("scelta");
		HttpSession session = request.getSession(false);
		
		if (session == null) {
		    response.sendRedirect(request.getContextPath() + "/WelcomeLogin");
		    return;
		}// cosi se nel caso non esiste non la crea
		UtentiBean utentesessione =(UtentiBean) session.getAttribute("utente");
		
		if("modificapassword".equals(scelta))
		{
			
			String nuovapassword1 = request.getParameter("nuovapassaword1");
			String nuovapassword2 = request.getParameter("nuovapassaword2");
			
			nuovapassword1 = PasswordUtil.toDigest(nuovapassword1);
			nuovapassword2 = PasswordUtil.toDigest(nuovapassword2);
			
			
			if(!nuovapassword1.equals(nuovapassword2)) 
			{
				request.setAttribute("errorroepassword", "Hai inserito due password diverse");
				request.getRequestDispatcher("/WEB-INF/views/common/modificaprofilo.jsp").forward(request, response);
				return;
			}
			else 
			{
					utentesessione.setPassword_hash(nuovapassword1);
					try {
						utente.update(utentesessione);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					session.setAttribute("utenti", utentesessione);	
					 request.getRequestDispatcher("/WEB-INF/views/common/profilo.jsp").forward(request, response);
				     return;
			} 
		}else 
		{
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String numerotelefono = request.getParameter("numerotelefo");
			
			utentesessione.setNome(nome);
			utentesessione.setCognome(cognome);
			utentesessione.setPhone(numerotelefono);
			
			try {
				utente.update(utentesessione);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("utenti", utentesessione);
			request.getRequestDispatcher("/WEB-INF/views/common/profilo.jsp").forward(request, response);
		    return;
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
