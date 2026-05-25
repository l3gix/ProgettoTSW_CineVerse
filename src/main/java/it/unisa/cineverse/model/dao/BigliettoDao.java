package it.unisa.cineverse.model.dao;
import java.sql.SQLException;
import java.util.*;

import it.unisa.cineverse.model.bean.BigliettoBean;


public interface BigliettoDao {
	
 void save(BigliettoBean biglietto) throws SQLException;
 
 void update(BigliettoBean biglietto) throws SQLException;
 
 void delete(int id)throws SQLException;
 
 List<BigliettoBean> findAll()throws SQLException;
 
 BigliettoBean findbyId(int id)throws SQLException;
		
	
	
	
	
	
	
	

}

