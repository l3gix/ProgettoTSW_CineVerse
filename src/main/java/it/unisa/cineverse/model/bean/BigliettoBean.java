package it.unisa.cineverse.model.bean;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class BigliettoBean {
	private int id;
	private int id_prenotazione;
	private int id_proiezione;
	private int id_posto;
	private Double prezzo;
	private String status;
	private LocalDateTime data_creazione;
	
	
	public BigliettoBean() {
		data_creazione= null;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getId_prenotazione() {
		return id_prenotazione;
	}


	public void setId_prenotazione(int id_prenotazione) {
		this.id_prenotazione = id_prenotazione;
	}


	public int getId_proiezione() {
		return id_proiezione;
	}


	public void setId_proiezione(int id_proiezione) {
		this.id_proiezione = id_proiezione;
	}


	public int getId_posto() {
		return id_posto;
	}


	public void setId_posto(int id_posto) {
		this.id_posto = id_posto;
	}


	public Double getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public LocalDateTime getData_creazione() {
		return data_creazione;
	}


	public void setData_creazione(LocalDateTime data_creazione) {
		this.data_creazione = data_creazione;
	}
	
	
	
	
	
	
	
}
