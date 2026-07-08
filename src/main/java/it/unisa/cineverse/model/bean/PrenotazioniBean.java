package it.unisa.cineverse.model.bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioniBean 
{
	private int id;
	private String id_utenti;
	private String status;
	private double importo_totale;
	private LocalDateTime scadenza;
	private LocalDateTime creazione;
	
	private List<BigliettoBean> biglietto;
	private List<PagamentoBean> pagamento;
	
	public PrenotazioniBean()
	{
		scadenza = null;
		creazione = null;
		biglietto = new ArrayList<BigliettoBean>();
		pagamento = new ArrayList<PagamentoBean>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getId_utenti() {
		return id_utenti;
	}

	public void setId_utenti(String id_utenti) {
		this.id_utenti = id_utenti;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getImporto_totale() {
		return importo_totale;
	}

	public void setImporto_totale(double importo_totale) {
		this.importo_totale = importo_totale;
	}

	public LocalDateTime getScadenza() {
		return scadenza;
	}

	public void setScadenza(LocalDateTime scadenza) {
		this.scadenza = scadenza;
	}

	public LocalDateTime getCreazione() {
		return creazione;
	}

	public void setCreazione(LocalDateTime creazione) {
		this.creazione = creazione;
	}

	public List<BigliettoBean> getBiglietto() {
		return biglietto;
	}

	public void setBiglietto(List<BigliettoBean> biglietto) {
		this.biglietto = biglietto;
	}

	public List<PagamentoBean> getPagamento() {
		return pagamento;
	}

	public void setPagamento(List<PagamentoBean> pagamento) {
		this.pagamento = pagamento;
	}
	
	
}
