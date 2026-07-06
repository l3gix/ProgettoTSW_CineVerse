package it.unisa.cineverse.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.BigliettoBean;
import it.unisa.cineverse.model.bean.PagamentoBean;
import it.unisa.cineverse.model.bean.PrenotazioniBean;
import it.unisa.cineverse.model.bean.UtentiBean;
import it.unisa.cineverse.model.dao.BigliettoDao;
import it.unisa.cineverse.model.dao.PagamentoDao;
import it.unisa.cineverse.model.dao.PrenotazioniDao;
import it.unisa.cineverse.model.dao.impl.BigliettoDaoImpl;
import it.unisa.cineverse.model.dao.impl.FilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.PagamentoDaoImpl;
import it.unisa.cineverse.model.dao.impl.PosterDaoImpl;
import it.unisa.cineverse.model.dao.impl.PrenotazioniDaoImpl;
import it.unisa.cineverse.model.dao.impl.ProiezioniDaoImpl;

/**
 * Servlet implementation class Pagamento
 */
@WebServlet("/Pagamento")
public class Pagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	private PagamentoDao pagamento;
	private PrenotazioniDao prenotazione;
	private BigliettoDao biglietto;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pagamento() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		if(ds == null) throw new ServletException("DataSource non disponibile nel contesto");
		
		pagamento = new PagamentoDaoImpl(ds);
		prenotazione = new PrenotazioniDaoImpl(ds);
		biglietto = new BigliettoDaoImpl(ds);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		double totale = Double.parseDouble(request.getParameter("costototale"));
		String provider = request.getParameter("provider");
		HttpSession session = request.getSession(false);
		
		UtentiBean user =(UtentiBean) session.getAttribute("utente");
		List<BigliettoBean> big = (List<BigliettoBean>) session.getAttribute("cart");
		
		PrenotazioniBean p = new PrenotazioniBean();
		p.setId_utenti(user.getEmail());
		p.setStatus("paid");
		p.setImporto_totale(totale);
		p.setScadenza(LocalDateTime.now());
		p.setCreazione(LocalDateTime.now());
		
		try {
			prenotazione.save(p);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PagamentoBean pag = new PagamentoBean();
		pag.setId_prenotazione(p.getId());
		pag.setProvider(provider);
		pag.setId_transazione_provider(provider.toUpperCase() + "-TX-" + String.format("%010d", new Random().nextLong(10_000_000_000L)));
		pag.setCosto(totale);
		pag.setStatus("paid");
		pag.setData_creazione(LocalDateTime.now());
		pag.setData_pagamento(LocalDateTime.now());
		
		try {
			pagamento.save(pag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for ( BigliettoBean b : big)
		{
			System.out.println(b);
			try {
				biglietto.updateIdPrenotazioneAndStatusByIdProiezioneAndIdPosto(p.getId(), "paid", b.getId_proiezione(), b.getId_posto());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		session.removeAttribute("cart"); // rimuovi la sessione
		
		response.sendRedirect(request.getContextPath() +"/WelcomeIndex");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
