package it.unisa.cineverse.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.UtentiBean;
import it.unisa.cineverse.model.dao.UtentiDao;

public class UtentiDaoImpl implements UtentiDao
{
	private static final String TABLE_NAME = "utenti ";
	private DataSource ds = null;
	
	public UtentiDaoImpl(DataSource ds)
	{
		this.ds = ds;
	}

	@Override
	public synchronized void save(UtentiBean utenti) throws SQLException {
		String sql = "INSERT INTO "+TABLE_NAME+" (email, password_hash, nome, cognome, phone)\n"
				+ "VALUES (?, ?, ?, ?, ?);";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setString(1, utenti.getEmail());
			ps.setString(2,utenti.getPassword_hash());
			ps.setString(3,utenti.getNome());
			ps.setString(4, utenti.getCognome());
			ps.setString(5, utenti.getPhone());
			ps.executeUpdate();
		}
		
	}

	@Override
	public synchronized void update(UtentiBean utenti) throws SQLException {
		String sql = "UPDATE " + TABLE_NAME
				+ "SET password_hash = ?,\n"
				+ "    nome = ?,\n"
				+ "    cognome = ?,\n"
				+ "    phone = ?,\n"
				+ "    ruolo = ?\n"
				+ "WHERE email = ?;\n";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setString(1,utenti.getPassword_hash());
			ps.setString(2,utenti.getNome());
			ps.setString(3, utenti.getCognome());
			ps.setString(4, utenti.getPhone());
			ps.setString(5, utenti.getRuolo());
			ps.setString(6, utenti.getEmail());
			ps.executeUpdate();
		}
		
	}

	@Override
	public synchronized void delete(String email) throws SQLException {
		String sql = "DELETE FROM " + TABLE_NAME
				+ "WHERE email = ?;";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setString(1, email);
			ps.executeUpdate();
		}
		
	}

	@Override
	public synchronized List<UtentiBean> findAll() throws SQLException {
		List<UtentiBean> utenti = new ArrayList<UtentiBean>();
		String sql = "SELECT *\n"
				+ "FROM " + TABLE_NAME;
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				){
			while(rs.next())
			{
				UtentiBean bean = new UtentiBean();
				bean.setEmail(rs.getString("email"));
				bean.setPassword_hash(rs.getString("password_hash"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPhone(rs.getString("phone"));
				bean.setRuolo(rs.getString("ruolo"));
				utenti.add(bean);
			}
		}
		return utenti;
	}

	@Override
	public synchronized UtentiBean findByEmail(String email) throws SQLException {
		UtentiBean bean = new UtentiBean();
		String sql = "SELECT *\n"
				+ "FROM " + TABLE_NAME
				+ "WHERE email = ?;";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
				ps.setString(1, email);
				try (ResultSet rs = ps.executeQuery())
				{
					while(rs.next())
					{
						bean.setEmail(rs.getString("email"));
						bean.setPassword_hash(rs.getString("password_hash"));
						bean.setNome(rs.getString("nome"));
						bean.setCognome(rs.getString("cognome"));
						bean.setPhone(rs.getString("phone"));
						bean.setRuolo(rs.getString("ruolo"));
					}
				}
		}
		return bean;
	}

	@Override
	public synchronized UtentiBean findByEmailAndPassword(String email, String password) throws SQLException {
		UtentiBean bean = null;
		String sql = "SELECT *\n"
				+ "FROM " + TABLE_NAME
				+ "WHERE email = ? AND password_hash = ?;";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
				ps.setString(1, email);
				ps.setString(2, password);
				try (ResultSet rs = ps.executeQuery())
				{
					while(rs.next())
					{
						bean = new UtentiBean();
						bean.setEmail(rs.getString("email"));
						bean.setPassword_hash(rs.getString("password_hash"));
						bean.setNome(rs.getString("nome"));
						bean.setCognome(rs.getString("cognome"));
						bean.setPhone(rs.getString("phone"));
						bean.setRuolo(rs.getString("ruolo"));
					}
				}
		}
		return bean;
	}
	
	

}
