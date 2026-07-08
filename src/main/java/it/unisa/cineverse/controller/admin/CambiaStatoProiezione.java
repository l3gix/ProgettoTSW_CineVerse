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
@WebServlet("/admin/CambiaStatoProiezione")
public class CambiaStatoProiezione extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private FilmDao film;
	private ProiezioniDao proiezione;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiaStatoProiezione() {
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
		
		String cancella = request.getParameter("cancella");
		
		if("cancella".equals(cancella))
		{
			boolean fatto = false;
			try {
				fatto = proiezione.deleteIfNoBiglietti(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(fatto == false )
			{
				request.setAttribute("errore", "Non e stato possibile cancellare la proiezione perchè ci sono dei biglietti associati");
			}
		}
		else 
		{
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
		}
		
		request.getRequestDispatcher("/admin/WelcomeGestioneFilmAdmin").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
