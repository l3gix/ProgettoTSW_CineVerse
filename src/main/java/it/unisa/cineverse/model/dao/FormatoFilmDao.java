package it.unisa.cineverse.model.dao;
import java.sql.SQLException;
import java.util.*;

import it.unisa.cineverse.model.bean.FormatoFilmBean;

public interface FormatoFilmDao{
	void save(FormatoFilmBean formato)throws SQLException;
	
	void update(FormatoFilmBean formato)throws SQLException;
	
	void delete(int id)throws SQLException;
	
	List<FormatoFilmBean> findAll()throws SQLException;
	
	FormatoFilmBean findbyId(int id)throws SQLException;

}
