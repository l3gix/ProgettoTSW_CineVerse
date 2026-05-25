package it.unisa.cineverse.model.dao;

import java.sql.SQLException;
import java.util.List;

import it.unisa.cineverse.model.bean.ProiezioneBean;

public interface ProiezioniDao 
{
	void save(ProiezioneBean proiezioni)throws SQLException;
	
	void update(ProiezioneBean proiezioni)throws SQLException;
	
	void delete(int id)throws SQLException;
	
	List<ProiezioneBean> findAll()throws SQLException;
	
	ProiezioneBean findById(int id)throws SQLException;
}
