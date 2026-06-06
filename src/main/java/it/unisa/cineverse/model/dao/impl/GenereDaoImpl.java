package it.unisa.cineverse.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.GenereBean;
import it.unisa.cineverse.model.dao.GenereDao;

public class GenereDaoImpl implements GenereDao{
	private static final String TABLE_NAME ="genere "; 
	private DataSource ds= null;
	
	public GenereDaoImpl(DataSource ds) {
		this.ds=ds;
	}
	@Override
	public synchronized void save(GenereBean genere) throws SQLException {
		String sql = "INSERT INTO" +TABLE_NAME +" (name) VALUES (?)";
		try(Connection connection=ds.getConnection();
		PreparedStatement ps= connection.prepareStatement(sql)){
			ps.setString(1, genere.getName());
			ps.executeUpdate();
		}
		
	}

	@Override
	public synchronized void update(GenereBean genere) throws SQLException {
		String sql = "UPDATE" +TABLE_NAME +" SET name = ? WHERE id = ?";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql)){
			ps.setString(1, genere.getName());
			ps.setInt(2, genere.getId());
			ps.executeUpdate();
		}
		
	}

	@Override
	public synchronized void delete(int id) throws SQLException {
		String sql= "DELETE FROM" +TABLE_NAME +" WHERE id = ?";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		
	}

	@Override
	public synchronized List<GenereBean> findAll() throws SQLException {
		List <GenereBean> genere = new ArrayList<GenereBean>();
		String sql ="SELECT * FROM" +TABLE_NAME;
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				GenereBean bean = new GenereBean();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				genere.add(bean);
			}
		}
		return genere;
	}

	@Override
	public synchronized GenereBean findbyId(int id) throws SQLException {
		GenereBean bean = new GenereBean();
		String sql = "SELECT * FROM" +TABLE_NAME +"WHERE id = ?";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql);){
				ps.setInt(1,id);
				try (ResultSet rs  = ps.executeQuery()){
					while(rs.next()) {
						bean.setId(rs.getInt("id"));
						bean.setName(rs.getString("name"));
					}
				}
		}	
		
		return bean;
	}

}
