package it.unisa.cineverse.controller.common;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.BigliettoBean;
import it.unisa.cineverse.model.bean.FilmBean;
import it.unisa.cineverse.model.bean.PrenotazioniBean;
import it.unisa.cineverse.model.bean.ProiezioneBean;
import it.unisa.cineverse.model.bean.UtentiBean;
import it.unisa.cineverse.model.dao.BigliettoDao;
import it.unisa.cineverse.model.dao.FilmDao;
import it.unisa.cineverse.model.dao.PostiDao;
import it.unisa.cineverse.model.dao.PrenotazioniDao;
import it.unisa.cineverse.model.dao.ProiezioniDao;
import it.unisa.cineverse.model.dao.impl.BigliettoDaoImpl;
import it.unisa.cineverse.model.dao.impl.FilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.PostiDaoImpl;
import it.unisa.cineverse.model.dao.impl.PrenotazioniDaoImpl;
import it.unisa.cineverse.model.dao.impl.ProiezioniDaoImpl;

/**
 * Servlet implementation class WelcomeListaBiglietti
 */
@WebServlet("/common/WelcomeListaBiglietti")
public class WelcomeListaBiglietti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	private FilmDao film;
	private ProiezioniDao proiezioni;
	private PostiDao posti;
	private BigliettoDao biglietto;
	private PrenotazioniDao prenotazione;
	
    public WelcomeListaBiglietti() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		if(ds == null) throw new ServletException("DataSource non disponibile nel contesto");
		
		film = new FilmDaoImpl(ds);
		proiezioni = new ProiezioniDaoImpl(ds);
		posti = new PostiDaoImpl(ds);
		biglietto = new BigliettoDaoImpl(ds);
		prenotazione = new PrenotazioniDaoImpl(ds);
		
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);

	    
	    UtentiBean utente = null;
	    if (session != null) {
	        utente = (UtentiBean) session.getAttribute("utente");
	    }
	    
	    //cerco tutti gli id degli untenti per cercare i biglietti che ha comprato
	    List<PrenotazioniBean> pre = new ArrayList<PrenotazioniBean>();
	    
	    try {
			pre = prenotazione.findAllByidUtente(utente.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    List<List<FilmBean>> listaPagamenti = new ArrayList<>();
	    for(PrenotazioniBean t : pre)
	    {
	    	List<BigliettoBean> bt = new ArrayList<BigliettoBean>();
	    	
	    	try {
	    		bt = biglietto.findAllByIdPrenotazione(t.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	List<BigliettoBean> bigliettocarrello = new ArrayList<>();
	    	for(BigliettoBean temp : bt)
	    	{
	    		bigliettocarrello.add(temp);
	    	}
	    	
	    
	    
	    
	    List<FilmBean> filmdamandare = new ArrayList<>();

	    for (BigliettoBean bigliettoCarrello : bigliettocarrello) {

	        ProiezioneBean proiezioneTemp = null;

	        try {
	            proiezioneTemp = proiezioni.findById(bigliettoCarrello.getId_proiezione());
	        } catch (SQLException e) {
	            e.printStackTrace();
	            continue;
	        }

	        if (proiezioneTemp == null) {
	            continue;
	        }

	        FilmBean filmNelCarrello = null;

	        // Cerco se il film è già presente nella lista
	        for (FilmBean f : filmdamandare) {
	            if (f.getId() == proiezioneTemp.getId_film()) {
	                filmNelCarrello = f;
	                break;
	            }
	        }

	        // Se il film non esiste ancora, lo prendo dal DB e lo aggiungo
	        if (filmNelCarrello == null) {
	            try {
	                filmNelCarrello = film.findbyId(proiezioneTemp.getId_film());
	            } catch (SQLException e) {
	                e.printStackTrace();
	                continue;
	            }

	            if (filmNelCarrello == null) {
	                continue;
	            }

	            filmdamandare.add(filmNelCarrello);
	        }

	        ProiezioneBean proiezioneNelFilm = null;

	        // Cerco se questa proiezione è già dentro quel film
	        if (filmNelCarrello.getProiezione() != null) {
	            for (ProiezioneBean p : filmNelCarrello.getProiezione()) {
	                if (p.getId() == proiezioneTemp.getId()) {
	                    proiezioneNelFilm = p;
	                    break;
	                }
	            }
	        }

	        // Se la proiezione non esiste ancora dentro il film, la aggiungo
	        if (proiezioneNelFilm == null) {
	            proiezioneNelFilm = proiezioneTemp;
	            filmNelCarrello.addProiezione(proiezioneNelFilm);
	        }

	        // Aggiungo il biglietto alla proiezione giusta
	        proiezioneNelFilm.addBiglietto(bigliettoCarrello);
	    	}
	    
	    	
	    
	    	listaPagamenti.add(filmdamandare);
	    }
	    
		request.setAttribute("listafilm", listaPagamenti);
		
		request.getRequestDispatcher("/WEB-INF/views/common/listabiglietti.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
