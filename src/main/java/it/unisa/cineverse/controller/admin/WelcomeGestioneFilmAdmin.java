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
import it.unisa.cineverse.model.bean.FormatoFilmBean;
import it.unisa.cineverse.model.bean.ProiezioneBean;
import it.unisa.cineverse.model.bean.SaleBean;
import it.unisa.cineverse.model.dao.FilmDao;
import it.unisa.cineverse.model.dao.FormatoFilmDao;
import it.unisa.cineverse.model.dao.ProiezioniDao;
import it.unisa.cineverse.model.dao.SaleDao;
import it.unisa.cineverse.model.dao.impl.FilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.FormatoFilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.PosterDaoImpl;
import it.unisa.cineverse.model.dao.impl.ProiezioniDaoImpl;
import it.unisa.cineverse.model.dao.impl.SaleDaoImpl;

/**
 * Servlet implementation class WelcomeGestioneFilmAdmin
 */
@WebServlet("/admin/WelcomeGestioneFilmAdmin")
public class WelcomeGestioneFilmAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private FilmDao film;
	private SaleDao sale;
	private FormatoFilmDao formato;
	private ProiezioniDao proiezione;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeGestioneFilmAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		if(ds == null) throw new ServletException("DataSource non disponibile nel contesto");
		
		film = new FilmDaoImpl(ds);
		sale = new SaleDaoImpl(ds);
		formato = new FormatoFilmDaoImpl(ds);
		proiezione = new ProiezioniDaoImpl(ds);
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
		
		List<SaleBean> s = null;
		try {
			s = sale.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<FormatoFilmBean> fo = null;
		try {
			fo = formato.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			for(FilmBean t : f)
			{
				List<ProiezioneBean> pro = proiezione.findAllByIdFilm(t.getId());
				t.setProiezione(pro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.setAttribute("film", f);
		request.setAttribute("sale", s);
		request.setAttribute("formato", fo);
		
		
		request.getRequestDispatcher("/WEB-INF/views/admin/gestionefilm.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
