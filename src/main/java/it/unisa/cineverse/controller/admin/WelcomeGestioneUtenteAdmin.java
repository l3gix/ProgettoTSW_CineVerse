package it.unisa.cineverse.controller.admin;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.PagamentoBean;
import it.unisa.cineverse.model.bean.PrenotazioniBean;
import it.unisa.cineverse.model.bean.UtentiBean;
import it.unisa.cineverse.model.dao.PagamentoDao;
import it.unisa.cineverse.model.dao.PrenotazioniDao;
import it.unisa.cineverse.model.dao.UtentiDao;
import it.unisa.cineverse.model.dao.impl.FilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.FormatoFilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.PagamentoDaoImpl;
import it.unisa.cineverse.model.dao.impl.PrenotazioniDaoImpl;
import it.unisa.cineverse.model.dao.impl.ProiezioniDaoImpl;
import it.unisa.cineverse.model.dao.impl.SaleDaoImpl;
import it.unisa.cineverse.model.dao.impl.UtentiDaoImpl;

/**
 * Servlet implementation class WelcomeGestioneUtenteAdmin
 */
@WebServlet("/admin/WelcomeGestioneUtenteAdmin")
public class WelcomeGestioneUtenteAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UtentiDao utenti;
	private PrenotazioniDao prenotazione;
	private PagamentoDao pagamento;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeGestioneUtenteAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		if(ds == null) throw new ServletException("DataSource non disponibile nel contesto");
		
		utenti = new UtentiDaoImpl(ds);
		prenotazione = new PrenotazioniDaoImpl(ds);
		pagamento = new PagamentoDaoImpl(ds);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<UtentiBean> u = null;
		
		try {
			u = utenti.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<PrenotazioniBean> p = null;
		
		try {
			p = prenotazione.findAll();
			for(PrenotazioniBean t : p)
			{
				List<PagamentoBean> pag = pagamento.findAllByIdPrenotazione(t.getId());
				t.setPagamento(pag);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("utenti", u);
		request.setAttribute("prenotazioni",p);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/gestioneutenti.jsp").forward(request, response);

		
			}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
