package it.unisa.cineverse.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.PagamentoBean;
import it.unisa.cineverse.model.dao.PagamentoDao;

public class PagamentoDaoImpl implements PagamentoDao
{
	private static final String TABLE_NAME = "pagamento ";
	private DataSource ds = null;
	
	public PagamentoDaoImpl(DataSource ds)
	{
		this.ds = ds;
	}

	@Override
	public synchronized void save(PagamentoBean pagamento) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME 
				+ "(id_prenotazione, provider, id_transazione_provider, costo, status, data_pagamento)\n"
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, pagamento.getId_prenotazione());
			ps.setString(2, pagamento.getProvider());
			ps.setString(3, pagamento.getId_transazione_provider());
			ps.setDouble(4, pagamento.getCosto());
			ps.setString(5, pagamento.getStatus());
			ps.setTimestamp(6, Timestamp.valueOf(pagamento.getData_pagamento()));
			ps.executeUpdate();	
		}
		
	}

	@Override
	public synchronized void update(PagamentoBean pagamento) throws SQLException {
		String sql = "UPDATE " + TABLE_NAME
				+ "SET id_prenotazione = ?,\n"
				+ "    provider = ?,\n"
				+ "    id_transazione_provider = ?,\n"
				+ "    costo = ?,\n"
				+ "    status = ?,\n"
				+ "    data_pagamento = ?\n"
				+ "WHERE id = ?";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, pagamento.getId_prenotazione());
			ps.setString(2, pagamento.getProvider());
			ps.setString(3, pagamento.getId_transazione_provider());
			ps.setDouble(4, pagamento.getCosto());
			ps.setString(5, pagamento.getStatus());
			ps.setTimestamp(6, Timestamp.valueOf(pagamento.getData_pagamento()));
			ps.setInt(7, pagamento.getId());
			ps.executeUpdate();	
		}
		
		
	}

	@Override
	public synchronized void delete(int id) throws SQLException {
		String sql = "DELETE FROM " + TABLE_NAME
				+ " WHERE id = ?";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, id);
			ps.executeUpdate();	
		}
	}

	@Override
	public synchronized List<PagamentoBean> findAll() throws SQLException {
		List<PagamentoBean> pagamento = new ArrayList<PagamentoBean>();
		String sql = "SELECT * " 
				+ " FROM " + TABLE_NAME;
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				){
			while(rs.next())
			{
				PagamentoBean bean = new PagamentoBean();
				bean.setId(rs.getInt("id"));
				bean.setId_prenotazione(rs.getInt("id_prenotazione"));
				bean.setProvider(rs.getString("provider"));
				bean.setId_transazione_provider(rs.getString("id_transazione_provider"));
				bean.setCosto(rs.getDouble("costo"));
				bean.setStatus(rs.getString("status"));
				bean.setData_pagamento(rs.getTimestamp("data_pagamento").toLocalDateTime());
				bean.setData_creazione(rs.getTimestamp("data_creazione").toLocalDateTime());
				pagamento.add(bean);
				
			}
			
		}
		return pagamento;
	}

	@Override
	public synchronized PagamentoBean findById(int id) throws SQLException {
		PagamentoBean bean = new PagamentoBean();
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
						bean.setId_prenotazione(rs.getInt("id_prenotazione"));
						bean.setProvider(rs.getString("provider"));
						bean.setId_transazione_provider(rs.getString("id_transazione_provider"));
						bean.setCosto(rs.getDouble("costo"));
						bean.setStatus(rs.getString("status"));
						bean.setData_pagamento(rs.getTimestamp("data_pagamento").toLocalDateTime());
						bean.setData_creazione(rs.getTimestamp("data_creazione").toLocalDateTime());
						
					}
				}
		}
		return bean;
	}
	
}
