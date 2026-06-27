package it.unisa.cineverse.model.bean;

import java.util.ArrayList;
import java.util.List;

public class UtentiBean 
{
	private String email;
	private String password_hash;
	private String nome;
	private String cognome;
	private String phone;
	private String ruolo;
	
	private List<PrenotazioniBean> prenotazione;
	
	public UtentiBean()
	{
		prenotazione = new ArrayList<PrenotazioniBean>();
	}


	public UtentiBean(String email, String password_hash, String nome, String cognome, String phone) {
		super();
		this.email = email;
		this.password_hash = password_hash;
		this.nome = nome;
		this.cognome = cognome;
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword_hash() {
		return password_hash;
	}

	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public List<PrenotazioniBean> getPrenotazione() {
		return prenotazione;
	}


	public void setPrenotazione(List<PrenotazioniBean> prenotazione) {
		this.prenotazione = prenotazione;
	}
	
	
}
