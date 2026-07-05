package it.unisa.cineverse.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.json.JSONObject;

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
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		String scelta = request.getParameter("azione");
		if(scelta.equals("pagamento"))
		{
			String selezionati = request.getParameter("selezionati");
			System.out.println(selezionati);
			
			return;
		}else 
		{
		
		int idPosto = Integer.parseInt(request.getParameter("id_posto"));
		int idProiezione = Integer.parseInt(request.getParameter("id_proiezione"));
		double prezzo = Double.parseDouble(request.getParameter("prezzo"));
		
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
			
			
			HttpSession session = request.getSession(); 
			if(session != null)
			{
				System.out.println("Esiste la sessione");
				List<BigliettoBean> cart = (List<BigliettoBean> )session.getAttribute("cart");
				if(cart == null)
				{
					cart = new ArrayList<BigliettoBean>();
					cart.add(b);
					
				}else 
				{
					cart.add(b);
				}
				
				CarrelloUtility.scadenzaCarello(session, "cart", cart, biglietto);
				
				JSONObject json = new JSONObject();
				LocalDateTime scadenza = LocalDateTime.now().plusMinutes(10);
				int ore = scadenza.getHour();
				int minuti = scadenza.getMinute();
				json.put("tempo", ore + ":" + minuti);
				out.print(json.toString());
			}
		}else {
			
			try {
				biglietto.deleteByIdPostoAndSala(idPosto, idProiezione);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			HttpSession session = request.getSession(false);//controlla se ce la sessione e nel caso non la crea
			if(session != null)
			{
				List<BigliettoBean> cart = (List<BigliettoBean> )session.getAttribute("cart");
				for (int i = 0; i < cart.size(); i++) {
			        BigliettoBean c = cart.get(i);

			        if (c.getId_posto() == b.getId_posto()) {
			            cart.remove(i);
			            break;
			        }
			    }
				
				CarrelloUtility.scadenzaCarello(session, "cart", cart, biglietto);
			}
			
			JSONObject json = new JSONObject();
			json.put("tempo", "");
			out.print(json.toString());
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
