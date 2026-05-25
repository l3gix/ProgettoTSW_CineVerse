package it.unisa.cineverse.model.dao;
import java.sql.SQLException;
import java.util.*;

import it.unisa.cineverse.model.bean.GenereBean;

public interface GenereDao{
	void save(GenereBean genere)throws SQLException;
	
	void update(GenereBean genere)throws SQLException;
	
	void delete(int id)throws SQLException;
	
	List<GenereBean> findAll()throws SQLException;
	
	GenereBean findbyId(int id)throws SQLException;

}
