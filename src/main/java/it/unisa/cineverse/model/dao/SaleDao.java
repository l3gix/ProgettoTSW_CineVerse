package it.unisa.cineverse.model.dao;

import java.sql.SQLException;
import java.util.List;

import it.unisa.cineverse.model.bean.SaleBean;

public interface SaleDao 
{
	void save(SaleBean sale)throws SQLException;
	
	void update(SaleBean sale)throws SQLException;
	
	void delete(int id)throws SQLException;
	
	List<SaleBean> findAll()throws SQLException;
	
	SaleBean findById()throws SQLException;
}
