package it.unisa.cineverse.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.PostiBean;
import it.unisa.cineverse.model.dao.PostiDao;

public class PostiDaoImpl implements PostiDao 
{
	private static final String TABLE_NAME = "posti ";
	private DataSource ds = null;
	
	public PostiDaoImpl(DataSource ds)
	{
		this.ds = ds;
	}
	
	@Override
	public synchronized void save(PostiBean posti) throws SQLException {
		String sql = "INSERT INTO "+TABLE_NAME +" (id_sale, id_categoria_posti, row_label)\n"
				+ "VALUES (?, ?, ?);";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, posti.getId_sale());
			ps.setString(2, posti.getId_categoria_posti());
			ps.setString(3, posti.getRow_label());
			ps.executeUpdate();
		}
	}

	@Override
	public synchronized void update(PostiBean posti) throws SQLException {
		String sql = "UPDATE " + TABLE_NAME
				+ "SET id_sale = ?,\n"
				+ "    id_categoria_posti = ?,\n"
				+ "    row_label = ?\n"
				+ "WHERE id = ?";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, posti.getId_sale());
			ps.setString(2, posti.getId_categoria_posti());
			ps.setString(3, posti.getRow_label());
			ps.setInt(4, posti.getId());
			ps.executeUpdate();
		}
		
	}

	@Override
	public synchronized void delete(int id) throws SQLException {
		String sql = "DELETE FROM " + TABLE_NAME
				+ "WHERE id = ?";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		
	}

	@Override
	public synchronized List<PostiBean> findAll() throws SQLException {
		List<PostiBean> posti = new ArrayList<PostiBean>();
		String sql = "SELECT *\n"
				+ "FROM " + TABLE_NAME;
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				){
			while(rs.next())
			{
				PostiBean bean = new PostiBean();
				bean.setId(rs.getInt("id"));
				bean.setId_sale(rs.getInt("id_sale"));
				bean.setId_categoria_posti(rs.getString("id_categoria_posti"));
				bean.setRow_label(rs.getString("id_categoria_posti"));
				posti.add(bean);
			}
			
		}
		return posti;
	}

	@Override
	public synchronized PostiBean findById(int id) throws SQLException {
		PostiBean bean = new PostiBean();
		String sql ="SELECT *\n"
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
						bean.setId_sale(rs.getInt("id_sale"));
						bean.setId_categoria_posti(rs.getString("id_categoria_posti"));
						bean.setRow_label(rs.getString("id_categoria_posti"));
					}
				}
		}
		return bean;
	}

}
