package it.unisa.cineverse.model.bean;

import java.time.LocalDateTime;

public class PagamentoBean 
{
	private int id;
	private int id_prenotazione;
	private String provider;
	private String id_transazione_provider;
	private double costo;
	private String status;
	private LocalDateTime data_pagamento;
	private LocalDateTime data_creazione;
	
	public PagamentoBean()
	{
		data_pagamento = null;
		data_creazione = null;	
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

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getId_transazione_provider() {
		return id_transazione_provider;
	}

	public void setId_transazione_provider(String id_transazione_provider) {
		this.id_transazione_provider = id_transazione_provider;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public LocalDateTime getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(LocalDateTime data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

	public LocalDateTime getData_creazione() {
		return data_creazione;
	}

	public void setData_creazione(LocalDateTime data_creazione) {
		this.data_creazione = data_creazione;
	}
	
	
	
}
