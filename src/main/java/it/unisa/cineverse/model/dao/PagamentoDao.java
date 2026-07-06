package it.unisa.cineverse.model.dao;

import java.sql.SQLException;
import java.util.*;

import it.unisa.cineverse.model.bean.PagamentoBean;

public interface PagamentoDao 
{
	void save(PagamentoBean pagamento) throws SQLException;
	
	void update(PagamentoBean pagamento) throws SQLException;
	
	void delete(int id) throws SQLException;
	
	List<PagamentoBean> findAll() throws SQLException;
	
	List<PagamentoBean> findAllByIdPrenotazione(int id) throws SQLException;
	
	PagamentoBean findById(int id) throws SQLException;
	
	
	
}
