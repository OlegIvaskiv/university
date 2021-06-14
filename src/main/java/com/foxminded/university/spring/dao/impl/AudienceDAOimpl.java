package com.foxminded.university.spring.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.AudienceDao;
import com.foxminded.university.spring.dao.exception.DaoException;
import com.foxminded.university.spring.mapper.AudienceMapper;

@Component
public class AudienceDaoImpl implements AudienceDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(AudienceDaoImpl.class);

    private JdbcTemplate jdbcTemplate;
    private AudienceMapper audienceMapper;
    private static final String SQL_FIND_AUDIENCE = "SELECT * FROM audience WHERE audience_id = ?;";
    private static final String SQL_DELETE_AUDIENCE = "DELETE FROM audience WHERE audience_id = ?;";
    private static final String SQL_UPDATE_AUDIENCE = "UPDATE audience SET audience_number = ? WHERE audience_id = ?;";
    private static final String SQL_GET_ALL = "SELECT * FROM audience;";
    private static final String SQL_INSERT_AUDIENCE = "INSERT INTO audience(audience_number) VALUES(?);";
    private static final String SQL_ADD_AUDIENCE = "UPDATE lecture SET audience_id = ? WHERE lecture_id = ?;";
    private static final String SQL_REMOVE_AUDIENCE = "UPDATE lecture SET audience_id = NULL WHERE lecture_id = ?;";

    @Autowired
    public AudienceDaoImpl(DataSource dataSource, AudienceMapper audienceMapper) {
        this.audienceMapper = audienceMapper;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Audience getById(int id) throws DaoException {
        LOGGER.debug("getting audience by id : {}", id);
        try {
            Audience audience = jdbcTemplate.queryForObject(SQL_FIND_AUDIENCE, new Object[] { id }, audienceMapper);
            LOGGER.debug("returned audience : {}", audience);
            return audience;
        } catch (DataAccessException e) {
            throw new DaoException("wrong id");
        }
    }

    @Override
    public List<Audience> getAll() throws DaoException {
        LOGGER.debug("getting all audiences");
        List<Audience> audiences = Collections.emptyList();
        try {
            audiences = jdbcTemplate.query(SQL_GET_ALL, audienceMapper);
            LOGGER.debug("returned audiences : {}", audiences);
            return audiences;
        } catch (DataAccessException e) {
            throw new DaoException("empty audience by id");
        }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        LOGGER.debug("deletion audience by id : {}", id);
        try {
            return jdbcTemplate.update(SQL_DELETE_AUDIENCE, id) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("audience is not deleted");
        }
    }

    @Override
    public boolean update(Audience audience) throws DaoException {
        LOGGER.debug("updating audience : {}", audience);
        try {
            return jdbcTemplate.update(SQL_UPDATE_AUDIENCE, audience.getNumber(), audience.getId()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("audience is no tdeleted");
        }
    }

    @Override
    public boolean create(Audience audience) throws DaoException {
        LOGGER.debug("creating audience : {}", audience);
        try {
            return jdbcTemplate.update(SQL_INSERT_AUDIENCE, audience.getNumber()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("audience is not created");
        }
    }

    @Override
    public boolean addAudienceToLecture(Optional<Audience> audience, Optional<Lecture> lecture) throws DaoException {
        LOGGER.debug("adding audience : {} to lecture : {}", audience, lecture);
        try {
            return jdbcTemplate.update(SQL_ADD_AUDIENCE, audience.get().getId(), lecture.get().getId()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("audience is not added");
        }
    }

    @Override
    public boolean removeAudiecnceFromLecture(Optional<Lecture> lecture) throws DaoException {
        LOGGER.debug("removing audience from lecture : {}", lecture);
        try {
            return jdbcTemplate.update(SQL_REMOVE_AUDIENCE, lecture.get().getId()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("audience is not removed");
        }
    }

}