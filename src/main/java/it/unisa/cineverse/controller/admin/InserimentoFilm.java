package it.unisa.cineverse.controller.admin;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.FilmBean;
import it.unisa.cineverse.model.bean.PosterBean;
import it.unisa.cineverse.model.dao.FilmDao;
import it.unisa.cineverse.model.dao.PosterDao;
import it.unisa.cineverse.model.dao.impl.FilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.PosterDaoImpl;
import it.unisa.cineverse.model.dao.impl.ProiezioniDaoImpl;

/**
 * Servlet implementation class inserimentoFilm
 */
@WebServlet("/admin/InserimentoFilm")
@MultipartConfig
public class InserimentoFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FilmDao film;
	private PosterDao poster;
    public InserimentoFilm() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		if(ds == null) throw new ServletException("DataSource non disponibile nel contesto");
		
		film = new FilmDaoImpl(ds);
		poster = new PosterDaoImpl(ds);
		
	}
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titolo= request.getParameter("titolo");
		String sinossi= request.getParameter("sinossi");
		String classificazione_eta= request.getParameter("ageRating");
		String cast = request.getParameter("cast");
		int durata=Integer.parseInt(request.getParameter("durataMinuti"));
		LocalDateTime data_rilascio = LocalDate.parse(request.getParameter("dataRilascio")).atStartOfDay();
		String link_trailer= request.getParameter("trailer");
		String classificazione= request.getParameter("status");
		
		FilmBean f = new FilmBean(
				titolo,
				sinossi,
				durata,
				classificazione_eta,
				data_rilascio,
				link_trailer,
				cast,
				classificazione
				);
		
		try {
			film.save(f);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Part copertina = request.getPart("img1");
		Part banner = request.getPart("img2");
		
		if(copertina != null && banner != null )
		{
			String copertinaoriginal = copertina.getSubmittedFileName();
			String banneroriginal = banner.getSubmittedFileName();
			
			if(copertinaoriginal != null && !copertinaoriginal.isEmpty() && copertina.getSize() > 0)
			{
				String uniqueFileNameCopertina = buildUniqueFileName(copertina);
				String uniqueFileNameBanner = buildUniqueFileName(banner);
				
				String uploadCopertina = getServletContext().getRealPath(File.separator + "img/poster" + File.separator + uniqueFileNameCopertina);
				String uploadBanner = getServletContext().getRealPath(File.separator + "img/banner" + File.separator + uniqueFileNameBanner);
				
				PosterBean p = new PosterBean();
				p.setCopertina("img/poster/" + uniqueFileNameCopertina);
				p.setBanner("img/banner/" + uniqueFileNameBanner);
				p.setId_film(f.getId());
				
				System.out.println("film id"+f.getId());
				
				try {
					copertina.write(uploadCopertina);
					banner.write(uploadBanner);
					poster.save(p);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/admin/WelcomeGestioneFilmAdmin");
	}
	
	private String buildUniqueFileName(Part part)
	{
		String originalName = part.getSubmittedFileName();
		String extension;
		if(originalName.contains("."))
		{
			extension = originalName.substring(originalName.lastIndexOf("."));
		}else 
		{
			extension = "";
		}
		
		return UUID.randomUUID() + extension;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
