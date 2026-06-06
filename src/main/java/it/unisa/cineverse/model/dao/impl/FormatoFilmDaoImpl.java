package it.unisa.cineverse.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.FilmBean;
import it.unisa.cineverse.model.bean.FormatoFilmBean;
import it.unisa.cineverse.model.dao.FormatoFilmDao;

public class FormatoFilmDaoImpl implements FormatoFilmDao {
	private static final String TABLE_NAME ="formato_film "; 
	private DataSource ds= null;
	
	public FormatoFilmDaoImpl(DataSource ds) {
		this.ds= ds;
	}
	@Override
	public synchronized void save(FormatoFilmBean formato) throws SQLException {
		String sql = "INSERT INTO" +TABLE_NAME +" (name) VALUES (?)";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql)){
			ps.setString(1, formato.getName());
			ps.executeUpdate();
		}
	}

	@Override
	public synchronized void update(FormatoFilmBean formato) throws SQLException {
		String sql = "UPDATE" +TABLE_NAME +"film SET name = ? WHERE id = ?";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql)){
			ps.setString(1, formato.getName());
			ps.setInt(2, formato.getId());
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
	public synchronized List<FormatoFilmBean> findAll() throws SQLException {
		List<FormatoFilmBean> formato_film = new ArrayList<FormatoFilmBean>();
		String sql= "SELECT * FROM " +TABLE_NAME;
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				FormatoFilmBean bean = new FormatoFilmBean();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				formato_film.add(bean);
			}
		}
		return formato_film;
	}

	@Override
	public synchronized FormatoFilmBean findbyId(int id) throws SQLException {
		FormatoFilmBean bean = new FormatoFilmBean();
		String sql=" SELECT * FROM " +TABLE_NAME +" WHERE id = ?";
		
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
