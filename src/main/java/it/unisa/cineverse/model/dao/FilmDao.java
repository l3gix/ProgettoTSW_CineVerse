package it.unisa.cineverse.model.dao;
import java.sql.SQLException;
import java.util.*;

import it.unisa.cineverse.model.bean.FilmBean;

public interface FilmDao {
	void save(FilmBean film)throws SQLException;
	
	void update(FilmBean film)throws SQLException;
	
	public void updateStatusById(int id,String status) throws SQLException;

	void delete(int id)throws SQLException;
	
	List<FilmBean> findAll()throws SQLException;
	
	FilmBean findbyId(int id)throws SQLException;
	
	List<FilmBean> findAllNowShowing() throws SQLException;
	
	FilmBean findByIdAndNowShowing(int id)throws SQLException;
	
	public List<FilmBean> findComingSoon() throws SQLException;
	
	public List<FilmBean> searchFilm(String keyword) throws SQLException ;

}
