package it.unisa.cineverse.model.bean;

import java.util.List;
import java.util.ArrayList;

public class PostiBean 
{
	private int id;
	private int id_sale;
	private String id_categoria_posti;
	private String row_label;
	private List<BigliettoBean> biglietto;
	
	public PostiBean()
	{
		biglietto = new ArrayList<BigliettoBean>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_sale() {
		return id_sale;
	}

	public void setId_sale(int id_sale) {
		this.id_sale = id_sale;
	}

	public String getId_categoria_posti() {
		return id_categoria_posti;
	}

	public void setId_categoria_posti(String id_categoria_posti) {
		this.id_categoria_posti = id_categoria_posti;
	}

	public String getRow_label() {
		return row_label;
	}

	public void setRow_label(String row_label) {
		this.row_label = row_label;
	}

	public List<BigliettoBean> getBiglietto() {
		return biglietto;
	}

	public void setBiglietto(List<BigliettoBean> biglietto) {
		this.biglietto = biglietto;
	}
	
	
	
}
