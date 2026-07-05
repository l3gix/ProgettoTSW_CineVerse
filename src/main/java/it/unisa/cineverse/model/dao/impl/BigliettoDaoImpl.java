	package it.unisa.cineverse.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.BigliettoBean;
import it.unisa.cineverse.model.dao.BigliettoDao;

public class BigliettoDaoImpl implements BigliettoDao{
	private static final String TABLE_NAME ="biglietto "; 
	private DataSource ds= null;
	
	public BigliettoDaoImpl(DataSource ds) {
		this.ds=ds;
		}

	@Override
	public synchronized void save(BigliettoBean biglietto) throws SQLException {
		String sql = "INSERT INTO "+ TABLE_NAME
				+"(id_prenotazione, id_proiezione, id_posto, prezzo, status)"
				+"VALUES (?, ?, ?, ?, ?)";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql)){
			ps.setInt(1, biglietto.getId_prenotazione());
			ps.setInt(2, biglietto.getId_proiezione());
			ps.setInt(3, biglietto.getId_posto());
			ps.setDouble(4, biglietto.getPrezzo());
			ps.setString(5, biglietto.getStatus());
			ps.executeUpdate();
		}
	}
	
	public synchronized void saveWhitOutIdPrenotazione(BigliettoBean biglietto) throws SQLException {
		String sql = "INSERT INTO "+ TABLE_NAME
				+"( id_proiezione, id_posto, prezzo, status)"
				+"VALUES ( ?, ?, ?, ?)";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql)){
			ps.setInt(1, biglietto.getId_proiezione());
			ps.setInt(2, biglietto.getId_posto());
			ps.setDouble(3, biglietto.getPrezzo());
			ps.setString(4, biglietto.getStatus());
			ps.executeUpdate();
		}
	}

	@Override
	public synchronized void update(BigliettoBean biglietto) throws SQLException {
		String sql = "UPDATE " +TABLE_NAME+ "SET id_prenotazione = ?,id_proiezione = ?,id_posto = ?,prezzo = ?"
				+ "status = ?, WHERE id = ?";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql)){
			ps.setInt(1, biglietto.getId_prenotazione());
			ps.setInt(2, biglietto.getId_proiezione());
			ps.setInt(3, biglietto.getId_posto());
			ps.setDouble(4, biglietto.getPrezzo());
			ps.setString(5, biglietto.getStatus());
			ps.setInt(6, biglietto.getId());
			ps.executeUpdate();
		}
	}

	@Override
	public synchronized void delete(int id) throws SQLException {
		String sql = "DELETE FROM "+TABLE_NAME+" WHERE id = ?";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		
	}
	
	@Override
	public synchronized void deleteByIdPostoAndSala(int id_posto, int id_proiezione) throws SQLException {
		String sql = "DELETE FROM "+TABLE_NAME+" WHERE id_posto = ? AND id_proiezione = ?";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql)){
			ps.setInt(1, id_posto);
			ps.setInt(2, id_proiezione);
			ps.executeUpdate();
		}
		
	}

	@Override
	public synchronized List<BigliettoBean> findAll() throws SQLException {
		List<BigliettoBean> biglietto = new ArrayList<BigliettoBean>();
		String sql = "SELECT * FROM" +TABLE_NAME;
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				BigliettoBean bean = new BigliettoBean();
				bean.setId(rs.getInt("id"));
				bean.setId_prenotazione(rs.getInt("id_prenotazione"));
				bean.setId_proiezione(rs.getInt("id_proiezione"));
				bean.setId_posto(rs.getInt("id_posto"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setStatus(rs.getString("status"));
				bean.setData_creazione(rs.getTimestamp("data_creazione").toLocalDateTime());
				biglietto.add(bean);
			}
			
		}
		return biglietto;
	}

	@Override
	public synchronized BigliettoBean findbyId(int id) throws SQLException {
		BigliettoBean bean = new BigliettoBean();
		String sql = "SELECT *FROM " +TABLE_NAME +" WHERE id = ?";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql);){
				ps.setInt(1, id);
				try (ResultSet rs  = ps.executeQuery()){
					while(rs.next()) {
						bean.setId(rs.getInt("id"));
						bean.setId_prenotazione(rs.getInt("id_prenotazione"));
						bean.setId_proiezione(rs.getInt("id_proiezione"));
						bean.setId_posto(rs.getInt("id_posto"));
						bean.setPrezzo(rs.getDouble("prezzo"));
						bean.setStatus(rs.getString("status"));
						bean.setData_creazione(rs.getTimestamp("data_creazione").toLocalDateTime());
					}
				}
			
		}
		return bean;
	}

	@Override
	public synchronized List<BigliettoBean> findAllByIdProiezione(int id) throws SQLException {
		List<BigliettoBean> biglietto = new ArrayList<BigliettoBean>();
		String sql = "SELECT * FROM " +TABLE_NAME +" WHERE id_proiezione = ?";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql);){
				ps.setInt(1, id);
				try (ResultSet rs  = ps.executeQuery()){
					while(rs.next()) {
						
						BigliettoBean bean = new BigliettoBean();
						bean.setId(rs.getInt("id"));
						bean.setId_prenotazione(rs.getInt("id_prenotazione"));
						bean.setId_proiezione(rs.getInt("id_proiezione"));
						bean.setId_posto(rs.getInt("id_posto"));
						bean.setPrezzo(rs.getDouble("prezzo"));
						bean.setStatus(rs.getString("status"));
						bean.setData_creazione(rs.getTimestamp("data_creazione").toLocalDateTime());
						biglietto.add(bean);
					}
				}
			
		}
		return biglietto;
	}

	
}
