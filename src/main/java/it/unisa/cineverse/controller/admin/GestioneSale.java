package it.unisa.cineverse.controller.admin;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

import it.unisa.cineverse.model.bean.BigliettoBean;
import it.unisa.cineverse.model.bean.PostiBean;
import it.unisa.cineverse.model.bean.ProiezioneBean;
import it.unisa.cineverse.model.dao.BigliettoDao;
import it.unisa.cineverse.model.dao.FilmDao;
import it.unisa.cineverse.model.dao.PostiDao;
import it.unisa.cineverse.model.dao.ProiezioniDao;
import it.unisa.cineverse.model.dao.impl.BigliettoDaoImpl;
import it.unisa.cineverse.model.dao.impl.FilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.FormatoFilmDaoImpl;
import it.unisa.cineverse.model.dao.impl.PostiDaoImpl;
import it.unisa.cineverse.model.dao.impl.ProiezioniDaoImpl;
import it.unisa.cineverse.model.dao.impl.SaleDaoImpl;

/**
 * Servlet implementation class GestioneSale
 */
@WebServlet("/admin/GestioneSale")
public class GestioneSale extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private FilmDao film;
	private ProiezioniDao proiezione;
	private BigliettoDao biglietto;
	private PostiDao posti;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestioneSale() {
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
		biglietto = new BigliettoDaoImpl(ds);
		posti = new PostiDaoImpl(ds);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("application/json");
		 String action = request.getParameter("action");
		 PrintWriter out = response.getWriter();
		 
		 if("orari".equals(action))
		 {
			 int idfilm = Integer.parseInt(request.getParameter("idFilm"));
			 List<ProiezioneBean> pro = null;
			 try {
				pro = proiezione.findAllByIdFilm(idfilm);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 JSONArray json = new JSONArray();
			 for(ProiezioneBean t : pro)
			 {
				 JSONObject obj = new JSONObject();
				 obj.put("value", t.getStarts());
				 json.put(obj);
			 }
			 
			 out.print(json.toString());
		 }
		 
		 if("sale".equals(action))
		 {
			 int idFilmParam = Integer.parseInt(request.getParameter("idFilm"));
	         String orario = request.getParameter("orario");
	         LocalDateTime orarioDateTime = LocalDateTime.parse(orario);
	         
	         List<ProiezioneBean> pro = null;
			
				try {
					pro = proiezione.findAllByIdFilmAndOrario(idFilmParam, orarioDateTime);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				JSONArray array = new JSONArray();
				
				for (ProiezioneBean p : pro) {
			        JSONObject obj = new JSONObject();

			        obj.put("id_proiezione", p.getId());
			        obj.put("id_sala", p.getId_sale());

			        array.put(obj);
			    }

				out.print(array.toString());
	         
		 }
		 
		 if("posti".equals(action))
		 {
			 int idProiezioneParam = Integer.parseInt(request.getParameter("idProiezione"));
			 int idSale = Integer.parseInt(request.getParameter("idsale"));
			 
			 List<BigliettoBean> bi = null;
			 try {
				bi = biglietto.findAllByIdProiezione(idProiezioneParam);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 List<PostiBean> listaPosti = null;
			 try {
				listaPosti = posti.findAllByIdSala(idSale);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			  JSONArray array = new JSONArray();
			 
			 for (PostiBean posto : listaPosti) {

			        boolean occupato = false;

			        for (BigliettoBean b : bi) {
			            if (b.getId_posto() == posto.getId()) {
			                occupato = true;
			                break;
			            }
			        }

			        JSONObject obj = new JSONObject();

			        obj.put("id_posto", posto.getId());
			        obj.put("id_sala", posto.getId_sale());
			        obj.put("categoria", posto.getId_categoria_posti());
			        obj.put("row_label", posto.getRow_label());
			        obj.put("occupato", occupato);

			        array.put(obj);
			    }
			 
			 //System.out.println("JSON POSTI: " + array.toString());
			 
			 out.print(array.toString());
			 
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
