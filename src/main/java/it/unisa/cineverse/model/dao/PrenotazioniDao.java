package it.unisa.cineverse.model.dao;

import java.sql.SQLException;
import java.util.List;

import it.unisa.cineverse.model.bean.PrenotazioniBean;

public interface PrenotazioniDao 
{
	void save(PrenotazioniBean prenotazioni)throws SQLException;
	
	void update(PrenotazioniBean prenotazioni)throws SQLException;
	
	void delete(int id) throws SQLException;
	
	List<PrenotazioniBean> findAll()throws SQLException;
	
	PrenotazioniBean findById(int id)throws SQLException;
	
}
