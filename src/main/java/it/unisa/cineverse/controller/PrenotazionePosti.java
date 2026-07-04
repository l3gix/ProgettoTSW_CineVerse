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

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.BigliettoBean;
import it.unisa.cineverse.model.bean.PrenotazioniBean;
import it.unisa.cineverse.model.dao.BigliettoDao;
import it.unisa.cineverse.model.dao.PrenotazioniDao;
import it.unisa.cineverse.model.dao.impl.BigliettoDaoImpl;
import it.unisa.cineverse.model.dao.impl.CategoriaPostiDaoImpl;
import it.unisa.cineverse.model.dao.impl.FilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.PosterDaoImpl;
import it.unisa.cineverse.model.dao.impl.PostiDaoImpl;
import it.unisa.cineverse.model.dao.impl.PrenotazioniDaoImpl;
import it.unisa.cineverse.model.dao.impl.ProiezioniDaoImpl;

/**
 * Servlet implementation class PrenotazionePosti
 */
@WebServlet("/PrenotazionePosti")
public class PrenotazionePosti extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private PrenotazioniDao prenotazione;
	private BigliettoDao biglietto;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrenotazionePosti() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		if(ds == null) throw new ServletException("DataSource non disponibile nel contesto");

		biglietto = new BigliettoDaoImpl(ds);
		prenotazione = new PrenotazioniDaoImpl(ds);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idPosto = Integer.parseInt(request.getParameter("id_posto"));
		int idProiezione = Integer.parseInt(request.getParameter("id_proiezione"));
		double prezzo = Double.parseDouble(request.getParameter("prezzo"));
		String scelta = request.getParameter("azione");
		
		System.out.println(idPosto + "\n" + idProiezione + "\n" + prezzo );
		BigliettoBean b = new BigliettoBean();
		b.setId_proiezione(idProiezione);
		b.setId_posto(idPosto);
		b.setPrezzo(prezzo);
		b.setStatus("cart");
		b.setData_creazione(LocalDateTime.now());
		
		if(scelta.equals("aggiungi"))
		{
			try {
				biglietto.saveWhitOutIdPrenotazione(b);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			
			try {
				biglietto.deleteByIdPostoAndSala(idPosto, idProiezione);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
