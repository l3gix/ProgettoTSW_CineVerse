package it.unisa.cineverse.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.unisa.cineverse.model.bean.BigliettoBean;
import it.unisa.cineverse.model.dao.BigliettoDao;
import jakarta.servlet.http.HttpSession;

public class CarrelloUtility 
{
	private static final ScheduledExecutorService sc = Executors.newScheduledThreadPool(1); // azione da compiere dopo un determinato tempo
	
	public static void scadenzaCarello(HttpSession sessione , String nome_attributo , List<BigliettoBean> b ,BigliettoDao biglietto)
	{
		sessione.setAttribute(nome_attributo, b);
		
		sc.schedule(() -> {
			List<BigliettoBean> temp = (List<BigliettoBean>)sessione.getAttribute(nome_attributo);
			for(BigliettoBean t : temp)
			{
				try {
					biglietto.deleteByIdPostoAndSala(t.getId_posto(), t.getId_proiezione());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			sessione.removeAttribute(nome_attributo);
		}, 1, TimeUnit.MINUTES);
		
	}
}
