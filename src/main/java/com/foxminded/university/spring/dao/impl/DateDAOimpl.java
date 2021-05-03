package com.foxminded.university.spring.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Date;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.DateDao;
import com.foxminded.university.spring.mapper.DateMapper;

@Component
public class DateDAOimpl implements DateDao {
	private static final Logger LOGGER = LogManager.getLogger(DateDAOimpl.class);
	private JdbcTemplate jdbcTemplate;
	private DateMapper dateMapper;
	private static final String SQL_FIND_DATE = "SELECT * FROM date WHERE id = ?;";
	private static final String SQL_DELETE_DATE = "DELETE FROM date WHERE id = ?;";
	private static final String SQL_UPDATE_DATE = "UPDATE date SET  day = ?, mounth = ?, year = ? WHERE id = ?;";
	private static final String SQL_GET_ALL = "SELECT * FROM date;";
	private static final String SQL_INSERT_DATE = "INSERT INTO date(id,  day, mounth, year) VALUES(?,?,?,?);";
	private static final String SQL_ADD_DATE = "UPDATE lecture SET date_id = ? WHERE id = ?;";
	private static final String SQL_REMOVE_DATE = "UPDATE lecture SET date_id = NULL WHERE id = ?;";

	@Autowired
	public DateDAOimpl(DataSource dataSource, DateMapper dateMapper) {
		LOGGER.debug("getting date by id");
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.dateMapper = dateMapper;
	}

	@Override
	public Optional<Date> getById(int id) {
		try {
			LOGGER.debug("getting date by id");
			return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_DATE, new Object[] { id }, dateMapper));
		} catch (EmptyResultDataAccessException exception) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<List<Date>> getAll() {
		try {
			LOGGER.debug("getting all dates");
			return Optional.ofNullable(jdbcTemplate.query(SQL_GET_ALL, dateMapper));
		} catch (EmptyResultDataAccessException exception) {
			LOGGER.debug("getting empty dates");
			return Optional.empty();
		}

	}

	@Override
	public boolean delete(int id) {
		LOGGER.debug("deletion date");
		return jdbcTemplate.update(SQL_DELETE_DATE, id) > 0;
	}

	@Override
	public boolean update(Date date) {
		LOGGER.debug("updating date");
		return jdbcTemplate.update(SQL_UPDATE_DATE, date.getDay(), date.getMounth(), date.getYear(), date.getId()) > 0;
	}

	@Override
	public boolean create(Date date) {
		LOGGER.debug("creating date");
		return jdbcTemplate.update(SQL_INSERT_DATE, date.getId(), date.getDay(), date.getMounth(), date.getYear()) > 0;
	}

	@Override
	public boolean addDateToLecture(Optional<Date> date, Optional<Lecture> lecture) {
		LOGGER.debug("adding date to lecture");
		return jdbcTemplate.update(SQL_ADD_DATE, date.get().getId(), lecture.get().getId()) > 0;
	}

	@Override
	public boolean removeDateFromLecture(Optional<Lecture> lecture) {
		LOGGER.debug("removing date from lecture");
		return jdbcTemplate.update(SQL_REMOVE_DATE, lecture.get().getId()) > 0;
	}

}