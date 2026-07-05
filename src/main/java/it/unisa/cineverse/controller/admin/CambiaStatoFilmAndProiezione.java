package it.unisa.cineverse.controller.admin;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import it.unisa.cineverse.model.dao.FilmDao;
import it.unisa.cineverse.model.dao.ProiezioniDao;
import it.unisa.cineverse.model.dao.impl.FilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.ProiezioniDaoImpl;

/**
 * Servlet implementation class CambiaStatoFilm
 */
@WebServlet("/admin/CambiaStatoFilmAndProiezione")
public class CambiaStatoFilmAndProiezione extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private FilmDao film;
	private ProiezioniDao proiezione;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiaStatoFilmAndProiezione() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		if(ds == null) throw new ServletException("DataSource non disponibile nel contesto");
		
		film = new FilmDaoImpl(ds);
		proiezione = new ProiezioniDaoImpl(ds);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String scelta = request.getParameter("proiezione");
		String status = request.getParameter("nuovoStatus");
		int id = Integer.parseInt(request.getParameter("id"));
		
		if("film".equals(scelta))
		{
			try {
				film.updateStatusById(id, status);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else 
		{
			try {
				proiezione.updateStatusById(id, status);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/admin/WelcomeGestioneFilmAdmin");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
