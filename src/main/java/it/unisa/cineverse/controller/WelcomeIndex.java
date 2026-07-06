package it.unisa.cineverse.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.cj.x.protobuf.MysqlxCrud.Find;

import it.unisa.cineverse.model.bean.FilmBean;
import it.unisa.cineverse.model.bean.ProiezioneBean;
import it.unisa.cineverse.model.dao.FilmDao;
import it.unisa.cineverse.model.dao.PosterDao;
import it.unisa.cineverse.model.dao.ProiezioniDao;
import it.unisa.cineverse.model.dao.impl.FilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.PosterDaoImpl;
import it.unisa.cineverse.model.dao.impl.ProiezioniDaoImpl;
import it.unisa.cineverse.model.dao.impl.UtentiDaoImpl;

/**
 * Servlet implementation class Index
 */
@WebServlet("/WelcomeIndex")
public class WelcomeIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private FilmDao film;
    private ProiezioniDao proiezione;
    private PosterDao poster;
    
    public WelcomeIndex() {
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
		poster = new PosterDaoImpl(ds);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dateParam = request.getParameter("date");

		LocalDateTime selectedDate;


	    if (dateParam == null || dateParam.isBlank()) {
	        selectedDate = LocalDateTime.now();
	    } else {
	        LocalDateTime giornoScelto = LocalDateTime.parse(dateParam);
	        LocalDateTime oggi = LocalDateTime.now();

	        if (giornoScelto.equals(oggi)) {
	            selectedDate = LocalDateTime.now();
	        } else {
	            selectedDate = giornoScelto.toLocalDate().atStartOfDay();
	        }
	    }
		
	    List<ProiezioneBean> proiezionigiorno = null;
	    try {
			proiezionigiorno = proiezione.findAllByIdFilmAndDateAndScheduled( selectedDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    List<FilmBean> fildamandare = new ArrayList<FilmBean>();
	    for (ProiezioneBean p : proiezionigiorno) {

	        FilmBean filmTrovato = null;

	        // Controllo se il film è già presente nella lista
	        for (FilmBean f : fildamandare) {
	            if (f.getId() == p.getId_film()) {
	                filmTrovato = f;
	                break;
	            }
	        }

	        // Se non esiste ancora, lo prendo dal database e lo aggiungo
	        if (filmTrovato == null) {
	            try {
	                filmTrovato = film.findbyId(p.getId_film());
	                filmTrovato.addPoster(poster.findByIdFilm(filmTrovato.getId()));
	                fildamandare.add(filmTrovato);
	            } catch (SQLException e) {
	                e.printStackTrace();
	                continue;
	            }
	        }

	        // Aggiungo la proiezione al film corretto
	        filmTrovato.addProiezione(p);
	    }
	    
	    /*
		List<FilmBean> films = null;
		 try {
			films = film.findAllNowShowing();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 for (FilmBean f : films)
		 {
			 try {
				f.setProiezione(proiezione.findAllByIdFilmAndDateAndScheduled( selectedDate) );
			 } catch (SQLException e) { e.printStackTrace();}
			
			 try {
				f.addPoster(poster.findByIdFilm(f.getId()) );
			} catch (SQLException e) {e.printStackTrace();}
			  
		 }
		 */
		 //System.out.println(films);
		 request.setAttribute("films", fildamandare);
		 request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
