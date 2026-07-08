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
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.FilmBean;
import it.unisa.cineverse.model.dao.FilmDao;
import it.unisa.cineverse.model.dao.PosterDao;
import it.unisa.cineverse.model.dao.ProiezioniDao;
import it.unisa.cineverse.model.dao.impl.FilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.PosterDaoImpl;
import it.unisa.cineverse.model.dao.impl.ProiezioniDaoImpl;

/**
 * Servlet implementation class PaginaFilm
 */
@WebServlet("/PaginaFilm")
public class PaginaFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FilmDao film;
    private ProiezioniDao proiezione;
    private PosterDao poster;
    
    public PaginaFilm() {
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
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dateParam = request.getParameter("date");
		int id = Integer.parseInt(request.getParameter("id"));
		
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
		
		
		FilmBean filmpagina = null;
		 try {
			 filmpagina = film.findByIdAndNowShowing(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			 try {
				 filmpagina.setProiezione(proiezione.findAllByIdFilmAndDateAndScheduled(filmpagina.getId(), selectedDate) );
			 } catch (SQLException e) { e.printStackTrace();}
			
			 try {
				 filmpagina.addPoster(poster.findByIdFilm(filmpagina.getId()) );
			} catch (SQLException e) {e.printStackTrace();}
			  
		
		 
		 System.out.println(filmpagina);
		 request.setAttribute("filmpagina", filmpagina);
		 request.getRequestDispatcher("/WEB-INF/views/paginafilm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
