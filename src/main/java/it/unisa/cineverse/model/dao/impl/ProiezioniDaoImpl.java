package it.unisa.cineverse.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.ProiezioneBean;
import it.unisa.cineverse.model.dao.ProiezioniDao;

public class ProiezioniDaoImpl implements ProiezioniDao
{
	private static final String TABLE_NAME = "proiezioni ";
	private DataSource ds = null;
	
	public ProiezioniDaoImpl(DataSource ds)
	{
		this.ds = ds;
	}
	
	@Override
	public synchronized void save(ProiezioneBean proiezioni) throws SQLException {
		String sql ="INSERT INTO " + TABLE_NAME
				+ "(id_film, id_sale, id_formato, starts, ends, prezzo_base, status)\n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?);";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, proiezioni.getId_film());
			ps.setInt(2, proiezioni.getId_sale());
			ps.setInt(3, proiezioni.getId_formato());
			ps.setTimestamp(4, Timestamp.valueOf(proiezioni.getStarts()));
			ps.setTimestamp(5, Timestamp.valueOf(proiezioni.getEnds()));
			ps.setDouble(6, proiezioni.getPrezzo_base());
			ps.setString(7, proiezioni.getStatus());
			ps.executeUpdate();
		}
	}

	@Override
	public synchronized void update(ProiezioneBean proiezioni) throws SQLException {
		String sql ="UPDATE " + TABLE_NAME
				+ "SET id_film = ?,\n"
				+ "    id_sale = ?,\n"
				+ "    id_formato = ?,\n"
				+ "    starts = ?,\n"
				+ "    ends = ?,\n"
				+ "    prezzo_base = ?,\n"
				+ "    status = ?\n"
				+ "WHERE id = ?;";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, proiezioni.getId_film());
			ps.setInt(2, proiezioni.getId_sale());
			ps.setInt(3, proiezioni.getId_formato());
			ps.setTimestamp(4, Timestamp.valueOf(proiezioni.getStarts()));
			ps.setTimestamp(5, Timestamp.valueOf(proiezioni.getEnds()));
			ps.setDouble(6, proiezioni.getPrezzo_base());
			ps.setString(7, proiezioni.getStatus());
			ps.setInt(8, proiezioni.getId());
			ps.executeUpdate();
		}
		
	}
	
	public synchronized void updateStatusById(int id,String status) throws SQLException {
		String sql ="UPDATE " + TABLE_NAME
				+ "SET status = ?\n"
				+ "WHERE id = ?;";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setString(1, status);
			ps.setInt(2, id);
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
	public synchronized List<ProiezioneBean> findAll() throws SQLException {
		List<ProiezioneBean> proiezione = new ArrayList<ProiezioneBean>();
		String sql ="SELECT *\n"
				+ "FROM " + TABLE_NAME;
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				){
			while(rs.next())
			{
				ProiezioneBean bean = new ProiezioneBean();
				bean.setId(rs.getInt("id"));
				bean.setId_film(rs.getInt("id_film"));
				bean.setId_sale(rs.getInt("id_sale"));
				bean.setId_formato(rs.getInt("id_formato"));
				bean.setStarts(rs.getTimestamp("starts").toLocalDateTime());
				bean.setEnds(rs.getTimestamp("ends").toLocalDateTime());
				bean.setPrezzo_base(rs.getDouble("prezzo_base"));
				bean.setStatus(rs.getString("status"));
				proiezione.add(bean);
			}
		}
		return proiezione;
	}

	@Override
	public synchronized ProiezioneBean findById(int id) throws SQLException {
		ProiezioneBean bean = new ProiezioneBean();
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
						bean.setId_sale(rs.getInt("id_sale"));
						bean.setId_formato(rs.getInt("id_formato"));
						bean.setStarts(rs.getTimestamp("starts").toLocalDateTime());
						bean.setEnds(rs.getTimestamp("ends").toLocalDateTime());
						bean.setPrezzo_base(rs.getDouble("prezzo_base"));
						bean.setStatus(rs.getString("status"));
					}
				}
		}
		return bean;
	}
	
	public synchronized List<ProiezioneBean> findAllByIdFilmAndDateAndScheduled( LocalDateTime date) throws SQLException {
		List<ProiezioneBean> proiezioni = new ArrayList<ProiezioneBean>();
		String sql = "SELECT *\n"
				+ "FROM " + TABLE_NAME
				+ " WHERE starts >= ?\n"
				+ " AND starts < ? "
				+ " AND status = ? ; ";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
				ps.setTimestamp(1, Timestamp.valueOf(date));
				ps.setTimestamp(2, Timestamp.valueOf(date.toLocalDate().plusDays(1).atStartOfDay()));
				System.out.println(date);
				System.out.println(Timestamp.valueOf(date.toLocalDate().plusDays(1).atStartOfDay()));
				
				ps.setString(3,"scheduled");
				try (ResultSet rs = ps.executeQuery())
				{
					while(rs.next())
					{
						ProiezioneBean bean = new ProiezioneBean();
						bean.setId(rs.getInt("id"));
						bean.setId_film(rs.getInt("id_film"));
						bean.setId_sale(rs.getInt("id_sale"));
						bean.setId_formato(rs.getInt("id_formato"));
						bean.setStarts(rs.getTimestamp("starts").toLocalDateTime());
						bean.setEnds(rs.getTimestamp("ends").toLocalDateTime());
						bean.setPrezzo_base(rs.getDouble("prezzo_base"));
						bean.setStatus(rs.getString("status"));
						proiezioni.add(bean);
					}
				}
		}
		return proiezioni;
	}
	@Override
	public synchronized List<ProiezioneBean> findAllByIdFilmAndDateAndScheduled(int id, LocalDateTime date) throws SQLException {
		List<ProiezioneBean> proiezioni = new ArrayList<ProiezioneBean>();
		String sql = "SELECT *\n"
				+ "FROM " + TABLE_NAME
				+ " WHERE starts >= ?\n"
				+ " AND starts < ? "
				+ " AND status = ? AND id_film = ?; ";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
				ps.setTimestamp(1, Timestamp.valueOf(date));
				ps.setTimestamp(2, Timestamp.valueOf(date.toLocalDate().plusDays(1).atStartOfDay()));
				System.out.println(date);
				System.out.println(Timestamp.valueOf(date.toLocalDate().plusDays(1).atStartOfDay()));
				
				ps.setString(3,"scheduled");
				ps.setInt(4, id);
				try (ResultSet rs = ps.executeQuery())
				{
					while(rs.next())
					{
						ProiezioneBean bean = new ProiezioneBean();
						bean.setId(rs.getInt("id"));
						bean.setId_film(rs.getInt("id_film"));
						bean.setId_sale(rs.getInt("id_sale"));
						bean.setId_formato(rs.getInt("id_formato"));
						bean.setStarts(rs.getTimestamp("starts").toLocalDateTime());
						bean.setEnds(rs.getTimestamp("ends").toLocalDateTime());
						bean.setPrezzo_base(rs.getDouble("prezzo_base"));
						bean.setStatus(rs.getString("status"));
						proiezioni.add(bean);
					}
				}
		}
		return proiezioni;
	}
	
	public synchronized List<ProiezioneBean> findAllByIdFilm(int id) throws SQLException {
		List<ProiezioneBean> proiezioni = new ArrayList<ProiezioneBean>();
		String sql = "SELECT *\n"
				+ "FROM " + TABLE_NAME
				+ " WHERE id_film = ?; ";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
				ps.setInt(1, id);
				try (ResultSet rs = ps.executeQuery())
				{
					while(rs.next())
					{
						ProiezioneBean bean = new ProiezioneBean();
						bean.setId(rs.getInt("id"));
						bean.setId_film(rs.getInt("id_film"));
						bean.setId_sale(rs.getInt("id_sale"));
						bean.setId_formato(rs.getInt("id_formato"));
						bean.setStarts(rs.getTimestamp("starts").toLocalDateTime());
						bean.setEnds(rs.getTimestamp("ends").toLocalDateTime());
						bean.setPrezzo_base(rs.getDouble("prezzo_base"));
						bean.setStatus(rs.getString("status"));
						proiezioni.add(bean);
					}
				}
		}
		return proiezioni;
	}
	
	public synchronized List<ProiezioneBean> findAllByIdFilmAndOrario(int id,LocalDateTime orario) throws SQLException {
		List<ProiezioneBean> proiezioni = new ArrayList<ProiezioneBean>();
		String sql = "SELECT *\n"
				+ "FROM " + TABLE_NAME
				+ " WHERE id_film = ? AND starts = ? ";
		try(Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
				ps.setInt(1, id);
				ps.setTimestamp(2, Timestamp.valueOf(orario));
				try (ResultSet rs = ps.executeQuery())
				{
					while(rs.next())
					{
						ProiezioneBean bean = new ProiezioneBean();
						bean.setId(rs.getInt("id"));
						bean.setId_film(rs.getInt("id_film"));
						bean.setId_sale(rs.getInt("id_sale"));
						bean.setId_formato(rs.getInt("id_formato"));
						bean.setStarts(rs.getTimestamp("starts").toLocalDateTime());
						bean.setEnds(rs.getTimestamp("ends").toLocalDateTime());
						bean.setPrezzo_base(rs.getDouble("prezzo_base"));
						bean.setStatus(rs.getString("status"));
						proiezioni.add(bean);
					}
				}
		}
		return proiezioni;
	}
	
}
