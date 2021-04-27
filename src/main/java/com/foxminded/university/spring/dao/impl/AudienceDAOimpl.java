
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

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.AudienceDao;
import com.foxminded.university.spring.mapper.AudienceMapper;

@Component
public class AudienceDAOimpl implements AudienceDao {
	private static final Logger LOGGER = LogManager.getLogger(AudienceDAOimpl.class);
	private JdbcTemplate jdbcTemplate;
	private AudienceMapper audienceMapper;
	private static final String SQL_FIND_AUDIENCE = "SELECT * FROM audience WHERE id = ?;";
	private static final String SQL_DELETE_AUDIENCE = "DELETE FROM audience WHERE id = ?;";
	private static final String SQL_UPDATE_AUDIENCE = "UPDATE audience SET number = ? WHERE id = ?;";
	private static final String SQL_GET_ALL = "SELECT * FROM audience;";
	private static final String SQL_INSERT_AUDIENCE = "INSERT INTO audience(id, number) VALUES(?,?);";
	private static final String SQL_ADD_AUDIENCE = "UPDATE lecture SET audience_id = ? WHERE id = ?;";
	private static final String SQL_REMOVE_AUDIENCE = "UPDATE lecture SET audience_id = NULL WHERE id = ?;";

	@Autowired
	public AudienceDAOimpl(DataSource dataSource, AudienceMapper audienceMapper) {
		this.audienceMapper = audienceMapper;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Optional<Audience> getById(int id) {
		try {
			LOGGER.debug("getting audience by id");
			return Optional
					.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_AUDIENCE, new Object[] { id }, audienceMapper));
		} catch (EmptyResultDataAccessException exception) {
			LOGGER.debug("getting empty audience by id");
			return Optional.empty();
		}
	}

	@Override
	public Optional<List<Audience>> getAll() {
		try {
			LOGGER.debug("getting all audiences");
			return Optional.ofNullable(jdbcTemplate.query(SQL_GET_ALL, audienceMapper));
		} catch (EmptyResultDataAccessException exception) {
			LOGGER.debug("getting empty audiences by id");
			return Optional.empty();
		}
	}

	@Override
	public boolean delete(int id) {
		LOGGER.debug("deletion audience");
		return jdbcTemplate.update(SQL_DELETE_AUDIENCE, id) > 0;
	}

	@Override
	public boolean update(Audience audience) {
		LOGGER.debug("updating audience");
		return jdbcTemplate.update(SQL_UPDATE_AUDIENCE, audience.getNumber(), audience.getId()) > 0;
	}

	@Override
	public boolean create(Audience audience) {
		LOGGER.debug("creating audience");
		return jdbcTemplate.update(SQL_INSERT_AUDIENCE, audience.getId(), audience.getNumber()) > 0;
	}

	@Override
	public boolean addAudienceToLecture(Optional<Audience> audience, Optional<Lecture> lecture) {
		if (lecture.get().getAudience() == null) {
			LOGGER.debug("adding audience to lecture");
			return jdbcTemplate.update(SQL_ADD_AUDIENCE, audience.get().getId(), lecture.get().getId()) > 0;
		}
		return false;
	}

	@Override
	public boolean removeAudiecnceFromLecture(Lecture lecture) {
		LOGGER.debug("removing audience from lecture");
		return jdbcTemplate.update(SQL_REMOVE_AUDIENCE, lecture.getId()) > 0;
	}

}