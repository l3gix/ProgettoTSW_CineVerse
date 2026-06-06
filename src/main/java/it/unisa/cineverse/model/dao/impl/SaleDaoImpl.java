package it.unisa.cineverse.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.SaleBean;
import it.unisa.cineverse.model.dao.SaleDao;

public class SaleDaoImpl implements SaleDao
{
	private static final String TABLE_NAME = "sale ";
	private DataSource ds = null;
	
	public SaleDaoImpl(DataSource ds)
	{
		this.ds = ds;
	}
	
	
	@Override
	public void save(SaleBean sale) throws SQLException {
		String sql = "INSERT INTO "+ TABLE_NAME+ " (posti_totali, screen_type)\n"
				+ "VALUES (?, ?);";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, sale.getPosti_totali());
			ps.setString(2, sale.getScreen_type());
			ps.executeUpdate();
		}
	}

	@Override
	public void update(SaleBean sale) throws SQLException {
		String sql = "UPDATE " + TABLE_NAME
				+ "SET posti_totali = ?,\n"
				+ "    screen_type = ?\n"
				+ "WHERE id = ?;";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, sale.getPosti_totali());
			ps.setString(2, sale.getScreen_type());
			ps.setInt(3, sale.getId());
			ps.executeUpdate();
		}
	}

	@Override
	public void delete(int id) throws SQLException {
		String sql = "DELETE FROM " + TABLE_NAME
				+ "WHERE id = ?;\n";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1,id);
			ps.executeUpdate();
		}
		
	}

	@Override
	public List<SaleBean> findAll() throws SQLException {
		List<SaleBean> sale = new ArrayList<SaleBean>();
		String sql = "SELECT *\n"
				+ "FROM " + TABLE_NAME;
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				){
			while(rs.next())
			{
				SaleBean bean = new SaleBean();
				bean.setId(rs.getInt("id"));
				bean.setPosti_totali(rs.getInt("posti_totali"));
				bean.setScreen_type(rs.getString("screen_type"));
				sale.add(bean);
			}
		}
		return sale;
	}

	@Override
	public SaleBean findById(int id) throws SQLException {
		SaleBean bean = new SaleBean();
		String sql = "SELECT *\n"
				+ "FROM " + TABLE_NAME
				+ "WHERE id = ?;\n";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
				ps.setInt(1, id);
				try (ResultSet rs = ps.executeQuery())
				{
					while(rs.next())
					{
						bean.setId(rs.getInt("id"));
						bean.setPosti_totali(rs.getInt("posti_totali"));
						bean.setScreen_type(rs.getString("screen_type"));
					}
				}
		}
		return bean;
	}

}
