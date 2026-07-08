package it.unisa.cineverse.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.BigliettoBean;
import it.unisa.cineverse.model.bean.CategoriaPostiBean;
import it.unisa.cineverse.model.bean.FilmBean;
import it.unisa.cineverse.model.bean.PostiBean;
import it.unisa.cineverse.model.bean.ProiezioneBean;
import it.unisa.cineverse.model.dao.BigliettoDao;
import it.unisa.cineverse.model.dao.CategoriaPostiDao;
import it.unisa.cineverse.model.dao.FilmDao;
import it.unisa.cineverse.model.dao.PosterDao;
import it.unisa.cineverse.model.dao.PostiDao;
import it.unisa.cineverse.model.dao.ProiezioniDao;
import it.unisa.cineverse.model.dao.impl.BigliettoDaoImpl;
import it.unisa.cineverse.model.dao.impl.CategoriaPostiDaoImpl;
import it.unisa.cineverse.model.dao.impl.FilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.PosterDaoImpl;
import it.unisa.cineverse.model.dao.impl.PostiDaoImpl;
import it.unisa.cineverse.model.dao.impl.ProiezioniDaoImpl;

/**
 * Servlet implementation class WelcomePrenotazionePosti
 */
@WebServlet("/WelcomePrenotazionePosti")
public class WelcomePrenotazionePosti extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private FilmDao film;
	private CategoriaPostiDao categoria;
	private ProiezioniDao proiezioni;
	private PosterDao poster;
	private PostiDao posti;
	private BigliettoDao biglietto;
	
    public WelcomePrenotazionePosti() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		if(ds == null) throw new ServletException("DataSource non disponibile nel contesto");

		film = new FilmDaoImpl(ds);
		categoria = new CategoriaPostiDaoImpl(ds);
		proiezioni = new ProiezioniDaoImpl(ds);
		poster = new PosterDaoImpl(ds);
		posti = new PostiDaoImpl(ds);
		biglietto = new BigliettoDaoImpl(ds);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_proiezione = Integer.parseInt( request.getParameter("id"));
		
		ProiezioneBean p = null;
		
		try {
			
			p = proiezioni.findById(id_proiezione);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<CategoriaPostiBean> c = null;
		try {	
			c = categoria.findAll();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FilmBean f = null;
		try {
			f = film.findbyId(p.getId_film());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			f.addPoster(poster.findByIdFilm(f.getId()) );
		} catch (SQLException e) {e.printStackTrace();}
		
		
		List<BigliettoBean> b = new ArrayList<BigliettoBean>();
		
		try {
			
			b = biglietto.findAllByIdProiezione(id_proiezione);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<PostiBean> poss = new ArrayList<PostiBean>();
		try {
			for(BigliettoBean t : b)
			{
				poss.add(posti.findById(t.getId_posto()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<PostiBean> creazioneposti = null;
		try {
			creazioneposti = posti.findAllByIdSala(p.getId_sale());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.setAttribute("film", f);
		request.setAttribute("proiezioni", p);
		request.setAttribute("categoria", c);
		request.setAttribute("posti", poss);
		request.setAttribute("postisala", creazioneposti);
		System.out.println(creazioneposti.get(0).getRow_label());
		//System.out.println(poss.get(0).getRow_label());
		request.getRequestDispatcher("/WEB-INF/views/prenotazioneposto.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
