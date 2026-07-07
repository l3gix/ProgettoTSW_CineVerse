package it.unisa.cineverse.util;

import java.util.List;

import it.unisa.cineverse.model.bean.BigliettoBean;
import it.unisa.cineverse.model.bean.PostiBean;

public class PostiUtility 
{
	 public static String getPostiPrenotati(List<BigliettoBean> biglietti, List<PostiBean> posti) {
	        StringBuilder risultato = new StringBuilder();

	        if (biglietti == null || posti == null) {
	            return "";
	        }

	        for (BigliettoBean biglietto : biglietti) {
	            for (PostiBean posto : posti) {
	            	System.out.println("posto" + posto.getId());
	                if (biglietto.getId_posto() == posto.getId()) {

	                    if (risultato.length() > 0) {
	                        risultato.append(", ");
	                    }

	                    risultato.append(posto.getRow_label());

	                    break;
	                }
	            }
	        }

	        return risultato.toString();
	    }
	 
	 public static String getPostiPrenotati(BigliettoBean biglietto, List<PostiBean> posti) {
	       
	        System.out.println("biglietto :" + biglietto.getId_posto());
	        for (PostiBean posto : posti) {
	            if (biglietto.getId_posto() == posto.getId()) {
	                return String.valueOf(posto.getRow_label());
	            }
	        }

	        return "";
	    }
}
