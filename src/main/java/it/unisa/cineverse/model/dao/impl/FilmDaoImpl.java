package it.unisa.cineverse.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.cineverse.model.bean.CategoriaPostiBean;
import it.unisa.cineverse.model.bean.FilmBean;
import it.unisa.cineverse.model.dao.FilmDao;
import it.unisa.cineverse.model.dao.ProiezioniDao;

public class FilmDaoImpl implements FilmDao{
	private static final String TABLE_NAME ="film "; 
	private DataSource ds= null;
	
	public FilmDaoImpl(DataSource ds) {
		this.ds=ds;
	}

	@Override
	public synchronized void save(FilmBean film) throws SQLException {
		String sql= "INSERT INTO " +TABLE_NAME +"(titolo, sinossi, durata_minuti, age_rating, data_rilascio, trailer_url, cast_film, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			ps.setString(1, film.getTitolo());
			ps.setString(2, film.getSinossi());
			ps.setInt(3, film.getDurata_minuti());
			ps.setString(4, film.getAge_rating());
			ps.setTimestamp(5, Timestamp.valueOf(film.getData_rilascio()));
			ps.setString(6, film.getTrailer_url());
			ps.setString(7, film.getCast_film());
			ps.setString(8, film.getStatus());
			ps.executeUpdate();
			
			 try (ResultSet rs = ps.getGeneratedKeys()) {
		            if (rs.next()) {
		                int idFilmAppenaInserito = rs.getInt(1);
		                film.setId(idFilmAppenaInserito);

		                System.out.println("ID FILM GENERATO: " + idFilmAppenaInserito);
		            } else {
		                throw new SQLException("Nessun ID generato per il film");
		            }
		        }
		}
	}

	@Override
	public synchronized void update(FilmBean film) throws SQLException {
		String sql= "UPDATE " +TABLE_NAME +" SET titolo = ?, sinossi = ?, durata_minuti = ?, age_rating = ?,  data_rilascio = ?, trailer_url = ?, cast_film = ?, status = ? WHERE id = ?";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql)){
			ps.setString(1, film.getTitolo());
			ps.setString(2, film.getSinossi());
			ps.setInt(3, film.getDurata_minuti());
			ps.setString(4, film.getAge_rating());
			ps.setTimestamp(5, Timestamp.valueOf(film.getData_rilascio()));
			ps.setString(6, film.getTrailer_url());
			ps.setString(7, film.getCast_film());
			ps.setString(8, film.getStatus());
			ps.setInt(9, film.getId());
			ps.executeUpdate();
		}
	}
	
	public synchronized void updateStatusById(int id,String status) throws SQLException {
		String sql= "UPDATE " +TABLE_NAME +" SET status = ? WHERE id = ?";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql)){
			ps.setString(1, status);
			ps.setInt(2, id);
			ps.executeUpdate();
		}
	}

	@Override
	public synchronized void delete(int id) throws SQLException {
		
		String sql= "DELETE FROM " +TABLE_NAME +" WHERE id = ?";
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		
	}

	@Override
	public synchronized List<FilmBean> findAll() throws SQLException {
		List<FilmBean> film = new ArrayList<FilmBean>();
		String sql="SELECT * FROM " +TABLE_NAME;
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				FilmBean bean= new FilmBean();
				bean.setId(rs.getInt("id"));
				bean.setTitolo(rs.getString("titolo"));
				bean.setSinossi(rs.getString("sinossi"));
				bean.setDurata_minuti(rs.getInt("durata_minuti"));
				bean.setAge_rating(rs.getString("age_rating"));
				bean.setData_rilascio(rs.getTimestamp("data_rilascio").toLocalDateTime());
				bean.setTrailer_url(rs.getString("trailer_url"));
				bean.setCast_film(rs.getString("cast_film"));
				bean.setStatus(rs.getString("status"));
				film.add(bean);
				}
		}
		
		return film;
	}

	@Override
	public synchronized FilmBean findbyId(int id) throws SQLException {
		FilmBean bean = new FilmBean();
		String sql= "SELECT * FROM " +TABLE_NAME +" WHERE id = ?";
		
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql);){
				ps.setInt(1,id);
				try (ResultSet rs  = ps.executeQuery()){
					while(rs.next()) {
						bean.setId(rs.getInt("id"));
						bean.setTitolo(rs.getString("titolo"));
						bean.setSinossi(rs.getString("sinossi"));
						bean.setDurata_minuti(rs.getInt("durata_minuti"));
						bean.setAge_rating(rs.getString("age_rating"));
						bean.setData_rilascio(rs.getTimestamp("data_rilascio").toLocalDateTime());
						bean.setTrailer_url(rs.getString("trailer_url"));
						bean.setCast_film(rs.getString("cast_film"));
						bean.setStatus(rs.getString("status"));
						
					}
				}
		}
		return bean;
	
		

	}

	@Override
	public synchronized List<FilmBean> findAllNowShowing() throws SQLException {
		List<FilmBean> film = new ArrayList<FilmBean>();
		String sql="SELECT * FROM " +TABLE_NAME + " WHERE status = 'now_showing'"; // prendo solo i film che sono attulente in corso
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				FilmBean bean= new FilmBean();
				bean.setId(rs.getInt("id"));
				bean.setTitolo(rs.getString("titolo"));
				bean.setSinossi(rs.getString("sinossi"));
				bean.setDurata_minuti(rs.getInt("durata_minuti"));
				bean.setAge_rating(rs.getString("age_rating"));
				bean.setData_rilascio(rs.getTimestamp("data_rilascio").toLocalDateTime());
				bean.setTrailer_url(rs.getString("trailer_url"));
				bean.setCast_film(rs.getString("cast_film"));
				bean.setStatus(rs.getString("status"));
				film.add(bean);
				}
		}
		
		return film;
	
	}

	@Override
	public synchronized FilmBean findByIdAndNowShowing(int id) throws SQLException {
		FilmBean bean = null;
		String sql= "SELECT * FROM " +TABLE_NAME +" WHERE id = ? AND status = 'now_showing'";
		
		try(Connection connection=ds.getConnection();
				PreparedStatement ps= connection.prepareStatement(sql);){
				ps.setInt(1,id);
				try (ResultSet rs  = ps.executeQuery()){
					while(rs.next()) {
						bean = new FilmBean();
						bean.setId(rs.getInt("id"));
						bean.setTitolo(rs.getString("titolo"));
						bean.setSinossi(rs.getString("sinossi"));
						bean.setDurata_minuti(rs.getInt("durata_minuti"));
						bean.setAge_rating(rs.getString("age_rating"));
						bean.setData_rilascio(rs.getTimestamp("data_rilascio").toLocalDateTime());
						bean.setTrailer_url(rs.getString("trailer_url"));
						bean.setCast_film(rs.getString("cast_film"));
						bean.setStatus(rs.getString("status"));
						
					}
				}
		}
		return bean;
	
	}
	
	public synchronized List<FilmBean> findComingSoon() throws SQLException {
	    List<FilmBean> films = new ArrayList<>();

	    String sql =
	        "SELECT f.*, x.prima_proiezione " +
	        "FROM film f " +
	        "JOIN ( " +
	        "    SELECT id_film, MIN(starts) AS prima_proiezione " +
	        "    FROM proiezioni " +
	        "    WHERE starts > NOW() " +
	        "    AND status = 'scheduled' " +
	        "    GROUP BY id_film " +
	        ") x ON f.id = x.id_film " +
	        "ORDER BY x.prima_proiezione ASC";

	    try (Connection connection = ds.getConnection();
	         PreparedStatement ps = connection.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            FilmBean film = new FilmBean();

	            film.setId(rs.getInt("id"));
	            film.setTitolo(rs.getString("titolo"));
	            film.setSinossi(rs.getString("sinossi"));
	            film.setDurata_minuti(rs.getInt("durata_minuti"));
	            film.setAge_rating(rs.getString("age_rating"));
	            film.setTrailer_url(rs.getString("trailer_url"));
	            film.setCast_film(rs.getString("cast_film"));
	            film.setStatus(rs.getString("status"));


	            films.add(film);
	        }
	    }

	    return films;
	}

	@Override
	public synchronized List<FilmBean> searchFilm(String keyword) throws SQLException {
		 List<FilmBean> films = new ArrayList<>();

		    String sql =
		        "SELECT * " +
		        "FROM film " +
		        "WHERE titolo LIKE ? " +
		        "ORDER BY titolo ASC " +
		        "LIMIT 10";
		    
		    try(Connection connection=ds.getConnection();
					PreparedStatement ps= connection.prepareStatement(sql);){
		    		ps.setString(1, "%" + keyword + "%");
					try (ResultSet rs  = ps.executeQuery()){
						while(rs.next()) {
			            FilmBean film = new FilmBean();

			            film.setId(rs.getInt("id"));
			            film.setTitolo(rs.getString("titolo"));
			            film.setSinossi(rs.getString("sinossi"));
			            film.setDurata_minuti(rs.getInt("durata_minuti"));
			            film.setAge_rating(rs.getString("age_rating"));
			            film.setTrailer_url(rs.getString("trailer_url"));
			            film.setCast_film(rs.getString("cast_film"));
			            film.setStatus(rs.getString("status"));


			            films.add(film);
			        }
					}
			    }

			    return films;
		    
	}
	
	
	

}
