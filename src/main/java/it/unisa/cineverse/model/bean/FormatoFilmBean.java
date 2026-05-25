package it.unisa.cineverse.model.bean;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class FormatoFilmBean {
	private int id;
	private String name;
	private List<ProiezioneBean> proiezioni;
	
	public FormatoFilmBean() {
		proiezioni = new ArrayList<ProiezioneBean>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProiezioneBean> getProiezioni() {
		return proiezioni;
	}

	public void setProiezioni(List<ProiezioneBean> proiezioni) {
		this.proiezioni = proiezioni;
	}
	
	
}
