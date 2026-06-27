package it.unisa.cineverse.controller;

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
import it.unisa.cineverse.model.dao.impl.UtentiDaoImpl;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UtentiDao utenteDao;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		if(ds == null) throw new ServletException("DataSource non disponibile nel contesto");
		
		utenteDao = new UtentiDaoImpl(ds);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String telefono = request.getParameter("telefono");
		
		UtentiBean ut = new UtentiBean(email, password, nome, cognome, telefono);
		try {
			utenteDao.save(ut);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ut = null;
			e.printStackTrace();
		}
		
		if(ut != null)
		{
			HttpSession session = request.getSession();
			session.setAttribute("utente", ut);
			session.setAttribute("ruolo", ut.getRuolo());
			
			response.sendRedirect(request.getContextPath() + "/WelcomeIndex");
			
		}else 
		{
			request.setAttribute("errore","L'utente già esistente");
			request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
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
