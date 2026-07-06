package it.unisa.cineverse.model.dao;
import java.sql.SQLException;
import java.util.*;

import it.unisa.cineverse.model.bean.BigliettoBean;


public interface BigliettoDao {
	
 void save(BigliettoBean biglietto) throws SQLException;
 
 void saveWhitOutIdPrenotazione(BigliettoBean biglietto) throws SQLException ;
 
 void update(BigliettoBean biglietto) throws SQLException;
 
 void updateIdPrenotazioneAndStatusByIdProiezioneAndIdPosto(int id_prenotazione , String status ,int id_proiezione,int id_posto) throws SQLException ;
 
 void delete(int id) throws SQLException;
 
 void deleteByIdPostoAndSala(int id_posto,int id_proiezione) throws SQLException;
 
 List<BigliettoBean> findAll()throws SQLException;
 
 BigliettoBean findbyId(int id)throws SQLException;
 
 List<BigliettoBean> findAllByIdProiezione(int id)throws SQLException;
 
List<BigliettoBean> findAllByIdPrenotazione(int id) throws SQLException;
		
	
	
	
	
	
	
	

}

