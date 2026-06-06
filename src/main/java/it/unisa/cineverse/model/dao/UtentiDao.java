package it.unisa.cineverse.model.dao;

import java.sql.SQLException;
import java.util.List;

import it.unisa.cineverse.model.bean.UtentiBean;

public interface UtentiDao 
{
	void save(UtentiBean utenti)throws SQLException;
	
	void update(UtentiBean utenti)throws SQLException;
	
	void delete(String email)throws SQLException;
	
	List<UtentiBean> findAll()throws SQLException;
	
	UtentiBean findByEmail(String email)throws SQLException;
	

}
