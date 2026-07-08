package it.unisa.cineverse.model.dao;
import java.sql.SQLException;
import java.util.*;

import it.unisa.cineverse.model.bean.CategoriaPostiBean;

public interface CategoriaPostiDao {     
	void save(CategoriaPostiBean categoria)throws SQLException;
	
	void update(CategoriaPostiBean categoria)throws SQLException;
	
	void delete(String name)throws SQLException;
	
	List<CategoriaPostiBean> findAll()throws SQLException;
	
	CategoriaPostiBean findByName(String name)throws SQLException;
	
}
