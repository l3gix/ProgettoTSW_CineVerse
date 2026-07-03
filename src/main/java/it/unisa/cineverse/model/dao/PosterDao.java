package it.unisa.cineverse.model.dao;

import java.sql.SQLException;
import java.util.List;

import it.unisa.cineverse.model.bean.PosterBean;

public interface PosterDao 
{
	void save(PosterBean poster)throws SQLException;
	
	void update(PosterBean poster)throws SQLException;
	
	void delete(int id)throws SQLException;
	
	List<PosterBean> findAll()throws SQLException;
	
	PosterBean findById(int id)throws SQLException;
	
	PosterBean findByIdFilm(int id)throws SQLException;
	
}
