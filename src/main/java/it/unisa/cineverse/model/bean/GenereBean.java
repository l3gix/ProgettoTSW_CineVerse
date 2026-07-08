package it.unisa.cineverse.model.bean;

import java.util.ArrayList;
import java.util.List;

public class GenereBean {
	private int id;
	private String name;
	private List<FilmBean> film;
	
	public GenereBean() {
		film= new ArrayList<FilmBean>();
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
	public List<FilmBean> getFilm() {
		return film;
	}
	public void setFilm(List<FilmBean> film) {
		this.film = film;
	}
	
	
	
}
