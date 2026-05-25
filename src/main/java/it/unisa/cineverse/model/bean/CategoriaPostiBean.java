package it.unisa.cineverse.model.bean;

import java.util.ArrayList;
import java.util.List;

public class CategoriaPostiBean {//italiano 
	private String nome;
	private Double modifica_prezzo;
	private List<PostiBean> categoria_posti;
	
	public CategoriaPostiBean() {
		categoria_posti = new ArrayList<PostiBean>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getModifica_prezzo() {
		return modifica_prezzo;
	}

	public void setModifica_prezzo(Double modifica_prezzo) {
		this.modifica_prezzo = modifica_prezzo;
	}

	public List<CategoriaPostiBean> getCategoria_posti() {
		return categoria_posti;
	}

	public void setCategoria_posti(List<CategoriaPostiBean> categoria_posti) {
		this.categoria_posti = categoria_posti;
	}
	
	
}
