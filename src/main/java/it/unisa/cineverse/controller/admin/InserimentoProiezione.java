package it.unisa.cineverse.controller.admin;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.ProiezioneBean;
import it.unisa.cineverse.model.dao.ProiezioniDao;
import it.unisa.cineverse.model.dao.impl.FilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.FormatoFilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.ProiezioniDaoImpl;
import it.unisa.cineverse.model.dao.impl.SaleDaoImpl;

/**
 * Servlet implementation class InserimentoProiezione
 */
@WebServlet("/admin/InserimentoProiezione")
public class InserimentoProiezione extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
	private ProiezioniDao proiezioni;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserimentoProiezione() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		if(ds == null) throw new ServletException("DataSource non disponibile nel contesto");
		
		proiezioni = new ProiezioniDaoImpl(ds);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idfilm =Integer.parseInt(request.getParameter("id_film"));
		int idsala =Integer.parseInt(request.getParameter("id_sale"));
		int idformato =Integer.parseInt(request.getParameter("id_formato"));
		
		LocalDateTime start = LocalDateTime.parse(request.getParameter("starts"));
		LocalDateTime end = LocalDateTime.parse(request.getParameter("ends"));
		
		double prezzo = Double.parseDouble(request.getParameter("prezzo_base"));
		String status = request.getParameter("status");
		
		
		ProiezioneBean p = new ProiezioneBean();
		p.setId_film(idfilm);
		p.setId_sale(idsala);
		p.setId_formato(idformato);
		p.setStarts(start);
		p.setEnds(end);
		p.setPrezzo_base(prezzo);
		p.setStatus(status);
		
		try {
			proiezioni.save(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
