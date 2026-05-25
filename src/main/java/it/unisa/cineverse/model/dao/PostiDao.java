package it.unisa.cineverse.model.dao;

import java.sql.SQLException;
import java.util.List;

import it.unisa.cineverse.model.bean.PostiBean;

public interface PostiDao 
{
	void save(PostiBean posti)throws SQLException;
	
	void update(PostiBean posti)throws SQLException;
	
	void delete(int id) throws SQLException;
	
	List<PostiBean> findAll() throws SQLException;
	
	PostiBean findById(int id) throws SQLException;
}
