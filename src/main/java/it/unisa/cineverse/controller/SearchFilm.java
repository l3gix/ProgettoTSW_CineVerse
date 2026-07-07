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

import org.json.JSONArray;
import org.json.JSONObject;

import it.unisa.cineverse.model.bean.FilmBean;
import it.unisa.cineverse.model.dao.FilmDao;
import it.unisa.cineverse.model.dao.impl.FilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.PosterDaoImpl;
import it.unisa.cineverse.model.dao.impl.ProiezioniDaoImpl;

/**
 * Servlet implementation class SearchFilm
 */
@WebServlet("/SearchFilm")
public class SearchFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FilmDao film;
    public SearchFilm() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		if(ds == null) throw new ServletException("DataSource non disponibile nel contesto");
		
		film = new FilmDaoImpl(ds);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 String q = request.getParameter("q");

	        response.setContentType("application/json");

	        JSONArray jsonArray = new JSONArray();

	        if (q == null || q.trim().length() < 2) {
	            response.getWriter().print(jsonArray.toString());
	            return;
	        }

	       
	            List<FilmBean> films = new ArrayList<FilmBean>();
				try {
					films = film.searchFilm(q.trim());
				} catch (SQLException e) {
					JSONObject errore = new JSONObject();
		            

		            response.getWriter().print(errore.toString());
					e.printStackTrace();
					return;
				}

	            for (FilmBean film : films) {
	                JSONObject jsonFilm = new JSONObject();

	                jsonFilm.put("id", film.getId());
	                jsonFilm.put("titolo", film.getTitolo());

	                jsonArray.put(jsonFilm);
	                System.out.println(jsonArray);
	            }
	            

	            response.getWriter().print(jsonArray.toString());

	       
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
