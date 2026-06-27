package it.unisa.cineverse.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.PrenotazioniBean;
import it.unisa.cineverse.model.dao.PrenotazioniDao;

public class PrenotazioniDaoImpl implements PrenotazioniDao
{
	private static final String TABLE_NAME = "prenotazione ";
	private DataSource ds = null;
	
	public PrenotazioniDaoImpl(DataSource ds)
	{
		this.ds = ds;
	}
	
	@Override
	public synchronized void save(PrenotazioniBean prenotazioni) throws SQLException {
		String sql ="INSERT INTO "+ TABLE_NAME + " (id_utenti, status, importo_totale, scadenza)\n"
				+ "VALUES (?, ?, ?, ?);";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setString(1, prenotazioni.getId_utenti());
			ps.setString(2, prenotazioni.getStatus());
			ps.setDouble(3, prenotazioni.getImporto_totale());
			ps.setTimestamp(4, Timestamp.valueOf(prenotazioni.getScadenza()));
			ps.executeUpdate();
		}
		
	}

	@Override
	public synchronized void update(PrenotazioniBean prenotazioni) throws SQLException {
		String sql = "UPDATE " + TABLE_NAME
				+ "SET id_utenti = ?,\n"
				+ "    status = ?,\n"
				+ "    importo_totale = ?,\n"
				+ "    scadenza = ?\n"
				+ "WHERE id = ?;";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setString(1, prenotazioni.getId_utenti());
			ps.setString(2, prenotazioni.getStatus());
			ps.setDouble(3, prenotazioni.getImporto_totale());
			ps.setTimestamp(4, Timestamp.valueOf(prenotazioni.getScadenza()));
			ps.setInt(5, prenotazioni.getId());
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
	public synchronized List<PrenotazioniBean> findAll() throws SQLException {
		List<PrenotazioniBean> prenotazioni = new ArrayList<PrenotazioniBean>();
		String sql = "SELECT *\n"
				+ "FROM " + TABLE_NAME;
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				){
			while(rs.next())
			{
				PrenotazioniBean bean = new PrenotazioniBean();
				bean.setId(rs.getInt("id"));
				bean.setId_utenti(rs.getString("id_utenti"));
				bean.setStatus(rs.getString("status"));
				bean.setImporto_totale(rs.getDouble("importo_totale"));
				bean.setScadenza(rs.getTimestamp("scadenza").toLocalDateTime());
				bean.setCreazione(rs.getTimestamp("creazione").toLocalDateTime());
				prenotazioni.add(bean);
			}
		}
		return prenotazioni;
	}

	@Override
	public synchronized PrenotazioniBean findById(int id) throws SQLException {
		PrenotazioniBean bean = new PrenotazioniBean();
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
						bean.setId_utenti(rs.getString("id_utenti"));
						bean.setStatus(rs.getString("status"));
						bean.setImporto_totale(rs.getDouble("importo_totale"));
						bean.setScadenza(rs.getTimestamp("scadenza").toLocalDateTime());
						bean.setCreazione(rs.getTimestamp("creazione").toLocalDateTime());
					}
				}
		}
		return bean;
	}

}
