package it.unisa.cineverse.model.bean;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class FilmBean {
	private int id;
	private String titolo;
	private String sinossi;
	private int durata_minuti;
	private String age_rating;
	private LocalDateTime data_rilascio;
	private String trailer_url;
	private String cast_film;
	private String status;
	private List<GenereBean> genere;
	private List<PosterBean> poster;
	private List<ProiezioneBean> proiezione;
	
	public FilmBean() {
		genere= new ArrayList<GenereBean>();
		poster= new ArrayList<PosterBean>();
		proiezione= new ArrayList<ProiezioneBean>();
		data_rilascio= null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getSinossi() {
		return sinossi;
	}

	public void setSinossi(String sinossi) {
		this.sinossi = sinossi;
	}

	public int getDurata_minuti() {
		return durata_minuti;
	}

	public void setDurata_minuti(int durata_minuti) {
		this.durata_minuti = durata_minuti;
	}

	public String getAge_rating() {
		return age_rating;
	}

	public void setAge_rating(String age_rating) {
		this.age_rating = age_rating;
	}

	public LocalDateTime getData_rilascio() {
		return data_rilascio;
	}

	public void setData_rilascio(LocalDateTime data_rilascio) {
		this.data_rilascio = data_rilascio;
	}

	public String getTrailer_url() {
		return trailer_url;
	}

	public void setTrailer_url(String trailer_url) {
		this.trailer_url = trailer_url;
	}

	public String getCast_film() {
		return cast_film;
	}

	public void setCast_film(String cast_film) {
		this.cast_film = cast_film;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<GenereBean> getGenere() {
		return genere;
	}

	public void setGenere(List<GenereBean> genere) {
		this.genere = genere;
	}

	public List<PosterBean> getPoster() {
		return poster;
	}

	public void setPoster(List<PosterBean> poster) {
		this.poster = poster;
	}

	public List<ProiezioneBean> getProiezione() {
		return proiezione;
	}

	public void setProiezione(List<ProiezioneBean> proiezione) {
		this.proiezione = proiezione;
	}
	
	

}
