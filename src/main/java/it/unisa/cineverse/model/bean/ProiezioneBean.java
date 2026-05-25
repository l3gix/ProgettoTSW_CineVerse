package it.unisa.cineverse.model.bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProiezioneBean 
{
	private int id;
	private int id_film;
	private int id_sale;
	private int id_formato;
	private LocalDateTime starts;
	private LocalDateTime ends;
	private double prezzo_base;
	private String status;
	
	private List<BigliettoBean> biglietto;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ProiezioneBean()
	{
		starts = null;
		ends = null;
		biglietto = new ArrayList<BigliettoBean>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_film() {
		return id_film;
	}

	public void setId_film(int id_film) {
		this.id_film = id_film;
	}

	public int getId_sale() {
		return id_sale;
	}

	public void setId_sale(int id_sale) {
		this.id_sale = id_sale;
	}

	public int getId_formato() {
		return id_formato;
	}

	public void setId_formato(int id_formato) {
		this.id_formato = id_formato;
	}

	public LocalDateTime getStarts() {
		return starts;
	}

	public void setStarts(LocalDateTime starts) {
		this.starts = starts;
	}

	public LocalDateTime getEnds() {
		return ends;
	}

	public void setEnds(LocalDateTime ends) {
		this.ends = ends;
	}

	public double getPrezzo_base() {
		return prezzo_base;
	}

	public void setPrezzo_base(double prezzo_base) {
		this.prezzo_base = prezzo_base;
	}

	public List<BigliettoBean> getBiglietto() {
		return biglietto;
	}

	public void setBiglietto(List<BigliettoBean> biglietto) {
		this.biglietto = biglietto;
	}
	
	
	
}
