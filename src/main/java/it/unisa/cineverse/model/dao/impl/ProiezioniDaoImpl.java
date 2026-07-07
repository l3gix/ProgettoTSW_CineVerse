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
	
	public synchronized boolean deleteIfNoBiglietti(int idProiezione) throws SQLException {

	    String sql = "DELETE FROM proiezioni " +
	                 "WHERE id = ? " +
	                 "AND NOT EXISTS ( " +
	                 "    SELECT 1 " +
	                 "    FROM biglietto " +
	                 "    WHERE biglietto.id_proiezione = proiezioni.id " +
	                 ");";

	    try (Connection connection = ds.getConnection();
	         PreparedStatement ps = connection.prepareStatement(sql)) {

	        ps.setInt(1, idProiezione);

	        int rows = ps.executeUpdate();

	        return rows > 0;
	    }
	}
	
	public synchronized boolean saveIfSalaDisponibile(ProiezioneBean p) throws SQLException {

	    String sql = 
	        "INSERT INTO proiezioni " +
	        "(id_film, id_sale, id_formato, starts, ends, prezzo_base, status) " +
	        "SELECT ?, ?, ?, ?, ?, ?, ? " +
	        "WHERE ? >= NOW() " +
	        "AND ? < ? " +
	        "AND NOT EXISTS ( " +
	        "    SELECT 1 " +
	        "    FROM proiezioni " +
	        "    WHERE id_sale = ? " +
	        "    AND status = 'scheduled' " +
	        "    AND starts < ? " +
	        "    AND ends > ? " +
	        ")";

	    try (Connection con = ds.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        Timestamp starts = Timestamp.valueOf(p.getStarts());
	        Timestamp ends = Timestamp.valueOf(p.getEnds());

	        ps.setInt(1, p.getId_film());
	        ps.setInt(2, p.getId_sale());
	        ps.setInt(3, p.getId_formato());
	        ps.setTimestamp(4, starts);
	        ps.setTimestamp(5, ends);
	        ps.setDouble(6, p.getPrezzo_base());
	        ps.setString(7, p.getStatus());

	        ps.setTimestamp(8, starts);

	        ps.setTimestamp(9, starts);
	        ps.setTimestamp(10, ends);

	        ps.setInt(11, p.getId_sale());
	        ps.setTimestamp(12, ends);
	        ps.setTimestamp(13, starts);

	        int righeInserite = ps.executeUpdate();

	        return righeInserite > 0;
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
	
	
	public synchronized ProiezioneBean findPrimaProiezioneByFilm(int idFilm) throws SQLException {
	    ProiezioneBean proiezione = null;

	    String sql =
	        "SELECT * " +
	        "FROM proiezioni " +
	        "WHERE id_film = ? " +
	        "AND starts > NOW() " +
	        "AND status = 'scheduled' " +
	        "ORDER BY starts ASC " +
	        "LIMIT 1";

	    try (Connection connection = ds.getConnection();
	         PreparedStatement ps = connection.prepareStatement(sql)) {

	        ps.setInt(1, idFilm);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                proiezione = new ProiezioneBean();

	                proiezione.setId(rs.getInt("id"));
	                proiezione.setId_film(rs.getInt("id_film"));
	                proiezione.setId_sale(rs.getInt("id_sale"));
	                proiezione.setId_formato(rs.getInt("id_formato"));

	                Timestamp starts = rs.getTimestamp("starts");
	                if (starts != null) {
	                    proiezione.setStarts(starts.toLocalDateTime());
	                }

	                Timestamp ends = rs.getTimestamp("ends");
	                if (ends != null) {
	                    proiezione.setEnds(ends.toLocalDateTime());
	                }

	                proiezione.setPrezzo_base(rs.getDouble("prezzo_base"));
	                proiezione.setStatus(rs.getString("status"));
	            }
	        }
	    }

	    return proiezione;
	}
	
}
