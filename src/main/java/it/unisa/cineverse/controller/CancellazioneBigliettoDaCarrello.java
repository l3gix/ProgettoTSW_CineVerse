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
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.BigliettoBean;
import it.unisa.cineverse.model.dao.BigliettoDao;
import it.unisa.cineverse.model.dao.impl.BigliettoDaoImpl;
import it.unisa.cineverse.model.dao.impl.FilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.PostiDaoImpl;
import it.unisa.cineverse.model.dao.impl.ProiezioniDaoImpl;

/**
 * Servlet implementation class CancellazioneBigliettoDaCarrello
 */
@WebServlet("/CancellazioneBigliettoDaCarrello")
public class CancellazioneBigliettoDaCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private BigliettoDao biglietto;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellazioneBigliettoDaCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		if(ds == null) throw new ServletException("DataSource non disponibile nel contesto");
		biglietto = new BigliettoDaoImpl(ds);
		
		
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idProiezioneParam = request.getParameter("id_proiezione");
        String[] postiParam = request.getParameterValues("id_posto");

        if (idProiezioneParam != null && postiParam != null) {

            int idProiezione = Integer.parseInt(idProiezioneParam);

            HttpSession session = request.getSession(false);

            if (session != null) {

                List<BigliettoBean> cart = (List<BigliettoBean>) session.getAttribute("cart");

                if (cart != null) {

                    for (int i = cart.size() - 1; i >= 0; i--) {

                        BigliettoBean b = cart.get(i);

                        if (b.getId_proiezione() == idProiezione) {

                            for (String postoParam : postiParam) {

                                int idPosto = Integer.parseInt(postoParam);

                                if (b.getId_posto() == idPosto) 
                                {
                                    cart.remove(i);
                                    try {
										biglietto.deleteByIdPostoAndSala(b.getId_posto(),b.getId_proiezione());
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
                                    
                                }
                            }
                        }
                    }

                    session.setAttribute("cart", cart);
                }
            }
        }
	        response.sendRedirect(request.getContextPath() + "/WelcomeCarrello");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
