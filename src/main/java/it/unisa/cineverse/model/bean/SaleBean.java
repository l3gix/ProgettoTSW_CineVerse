package it.unisa.cineverse.model.bean;

import java.util.ArrayList;
import java.util.List;

public class SaleBean 
{
	private int id;
	private int posti_totali;
	private String screen_type;
	
	private List<PostiBean> posto;
	private List<ProiezioneBean> proiezioni;
	
	public SaleBean()
	{
		posto = new ArrayList<PostiBean>();
		proiezioni = new ArrayList<ProiezioneBean>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPosti_totali() {
		return posti_totali;
	}

	public void setPosti_totali(int posti_totali) {
		this.posti_totali = posti_totali;
	}

	public String getScreen_type() {
		return screen_type;
	}

	public void setScreen_type(String screen_type) {
		this.screen_type = screen_type;
	}

	public List<PostiBean> getPosto() {
		return posto;
	}

	public void setPosto(List<PostiBean> posto) {
		this.posto = posto;
	}

	public List<ProiezioneBean> getProiezioni() {
		return proiezioni;
	}

	public void setProiezioni(List<ProiezioneBean> proiezioni) {
		this.proiezioni = proiezioni;
	}
	
	
	
}
