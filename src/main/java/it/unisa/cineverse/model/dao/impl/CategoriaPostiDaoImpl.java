package it.unisa.cineverse.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.BigliettoBean;
import it.unisa.cineverse.model.bean.CategoriaPostiBean;
import it.unisa.cineverse.model.dao.CategoriaPostiDao;

public class CategoriaPostiDaoImpl implements CategoriaPostiDao {
	private static final String TABLE_NAME ="categoria_posti "; 
	private DataSource ds= null;
	
	public CategoriaPostiDaoImpl(DataSource ds) {
		this.ds=ds;
	}
	
	@Override
	public synchronized void save(CategoriaPostiBean categoria) throws SQLException {
		String sql= "INSERT INTO" +TABLE_NAME +" (nome, modifica_prezzo)VALUES (?, ?)";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql)){
			ps.setString(1,categoria.getNome());
			ps.setDouble(2, categoria.getModifica_prezzo());
			ps.executeUpdate();
		}
		
	}
	
	@Override
	public synchronized void update(CategoriaPostiBean categoria) throws SQLException {
		String sql= "UPDATE" +TABLE_NAME +" SET modifica_prezzo = ? WHERE nome = ?";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql)){
			ps.setDouble(1, categoria.getModifica_prezzo());
			ps.setString(2,categoria.getNome());
			ps.executeUpdate();
		}
		
	}
	
	@Override
	public synchronized void delete(String name) throws SQLException {
		String sql = "DELETE FROM" +TABLE_NAME +" WHERE nome = ?";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql)){
			ps.setString(1,name);
			ps.executeUpdate();
		}
		
	}
	
	@Override
	public synchronized List<CategoriaPostiBean> findAll() throws SQLException {
		List<CategoriaPostiBean> categoria_posti = new ArrayList<CategoriaPostiBean>();
		String sql= "SELECT * FROM "+TABLE_NAME;
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				CategoriaPostiBean bean= new CategoriaPostiBean();
				bean.setNome(rs.getString("nome"));
				bean.setModifica_prezzo(rs.getDouble("modifica_prezzo"));
				categoria_posti.add(bean);	
			}
		}
		return categoria_posti;
	}
	
	@Override
	public synchronized CategoriaPostiBean findByName(String name) throws SQLException {
		CategoriaPostiBean bean= new CategoriaPostiBean();
		String sql =" SELECT * FROM " +TABLE_NAME +" WHERE nome = ?";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql);){
				ps.setString(1,name);
				try (ResultSet rs  = ps.executeQuery()){
					while(rs.next()) {
						bean.setNome(rs.getString("nome"));
						bean.setModifica_prezzo(rs.getDouble("modifica_prezzo"));
						
					}
				}
		}
		return bean;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
