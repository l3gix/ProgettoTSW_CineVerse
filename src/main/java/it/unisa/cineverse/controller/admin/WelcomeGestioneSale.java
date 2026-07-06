package it.unisa.cineverse.controller.admin;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.FilmBean;
import it.unisa.cineverse.model.dao.FilmDao;
import it.unisa.cineverse.model.dao.PostiDao;
import it.unisa.cineverse.model.dao.impl.FilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.FormatoFilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.PostiDaoImpl;
import it.unisa.cineverse.model.dao.impl.ProiezioniDaoImpl;
import it.unisa.cineverse.model.dao.impl.SaleDaoImpl;

/**
 * Servlet implementation class WelcomeGestioneSale
 */
@WebServlet("/admin/WelcomeGestioneSale")
public class WelcomeGestioneSale extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private FilmDao film;
	private PostiDao posti;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeGestioneSale() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		if(ds == null) throw new ServletException("DataSource non disponibile nel contesto");
		
		film = new FilmDaoImpl(ds);
		posti = new PostiDaoImpl(ds);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<FilmBean> f = null;
		try {
			f = film.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("film", f);
		request.getRequestDispatcher("/WEB-INF/views/admin/gestionesale.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
