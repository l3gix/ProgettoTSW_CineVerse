package it.unisa.cineverse.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.PosterBean;
import it.unisa.cineverse.model.dao.PosterDao;

public class PosterDaoImpl implements PosterDao
{
	private static final String TABLE_NAME = "poster ";
	private DataSource ds = null;
	
	public PosterDaoImpl(DataSource ds)
	{
		this.ds = ds;
	}
	
	@Override
	public synchronized void save(PosterBean poster) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME +"(id_film, copertina, banner)\n"
				+ "VALUES (?, ?, ?);";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, poster.getId_film());
			ps.setString(2, poster.getCopertina());
			ps.setString(3, poster.getBanner());
			ps.executeUpdate();
		}
		
	}
	@Override
	public synchronized void update(PosterBean poster) throws SQLException {
		String sql = "UPDATE \n" + TABLE_NAME
				+ "SET id_film = ?,\n"
				+ "    copertina = ?,\n"
				+ "    banner = ?\n"
				+ "WHERE id = ?;";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, poster.getId_film());
			ps.setString(2, poster.getCopertina());
			ps.setString(3, poster.getBanner());
			ps.setInt(4, poster.getId());
			ps.executeUpdate();
		}
		
	}
	@Override
	public synchronized void delete(int id) throws SQLException {
		String sql = "DELETE FROM " + TABLE_NAME 
				+ "WHERE id = ?;";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		
	}
	@Override
	public synchronized List<PosterBean> findAll() throws SQLException {
		List<PosterBean> poster = new ArrayList<PosterBean>();
		String sql = "SELECT *\n"
				+ "FROM " + TABLE_NAME;
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				){
			while(rs.next())
			{
				PosterBean bean = new PosterBean();
				bean.setId(rs.getInt("id"));
				bean.setId_film(rs.getInt("id_film"));
				bean.setCopertina(rs.getString("copertina"));
				bean.setBanner(rs.getString("banner"));
				poster.add(bean);
			}
		}
		return poster;
	}
	@Override
	public synchronized PosterBean findById(int id) throws SQLException {
		PosterBean bean = new PosterBean();
		String sql = "SELECT *\n"
				+ "FROM " + TABLE_NAME
				+ "WHERE id = ?;";

		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
				ps.setInt(1, id);
				try (ResultSet rs = ps.executeQuery())
				{
					while(rs.next())
					{
						bean.setId(rs.getInt("id"));
						bean.setId_film(rs.getInt("id_film"));
						bean.setCopertina(rs.getString("copertina"));
						bean.setBanner(rs.getString("banner"));
						
					}
				}
		}
		return bean;
	}

	@Override
	public synchronized PosterBean findByIdFilm(int id) throws SQLException {
		PosterBean bean = null;
		String sql = "SELECT *\n"
				+ "FROM " + TABLE_NAME
				+ "WHERE id_film = ?;";

		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
				ps.setInt(1, id);
				try (ResultSet rs = ps.executeQuery())
				{
					while(rs.next())
					{
						bean = new PosterBean();
						bean.setId(rs.getInt("id"));
						bean.setId_film(rs.getInt("id_film"));
						bean.setCopertina(rs.getString("copertina"));
						bean.setBanner(rs.getString("banner"));
						
					}
				}
		}
		return bean;
	}
	
	
	
}
