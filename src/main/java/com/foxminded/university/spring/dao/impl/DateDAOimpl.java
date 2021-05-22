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

import com.foxminded.university.model.Date;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.DateDao;
import com.foxminded.university.spring.dao.exception.DaoException;
import com.foxminded.university.spring.mapper.DateMapper;

@Component
public class DateDAOimpl implements DateDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateDAOimpl.class);
    private JdbcTemplate jdbcTemplate;
    private DateMapper dateMapper;
    private static final String SQL_FIND_DATE = "SELECT * FROM date WHERE date_id = ?;";
    private static final String SQL_DELETE_DATE = "DELETE FROM date WHERE date_id = ?;";
    private static final String SQL_UPDATE_DATE = "UPDATE date SET  day = ?, mounth = ?, year = ? WHERE date_id = ?;";
    private static final String SQL_GET_ALL = "SELECT * FROM date;";
    private static final String SQL_INSERT_DATE = "INSERT INTO date(day, mounth, year) VALUES(?,?,?);";
    private static final String SQL_ADD_DATE = "UPDATE lecture SET date_id = ? WHERE lecture_id = ?;";
    private static final String SQL_REMOVE_DATE = "UPDATE lecture SET date_id = NULL WHERE lecture_id = ?;";

    @Autowired
    public DateDAOimpl(DataSource dataSource, DateMapper dateMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dateMapper = dateMapper;
    }

    @Override
    public Date getById(int id) throws DaoException {
        LOGGER.debug("getting date by id : {}", id);
        try {
            Date date = jdbcTemplate.queryForObject(SQL_FIND_DATE, new Object[] { id }, dateMapper);
            LOGGER.debug("returned date : {}", date);
            return date;
        } catch (DataAccessException e) {
            throw new DaoException("wrong id");
        }
    }

    @Override
    public List<Date> getAll() throws DaoException {
        LOGGER.debug("getting all dates");
        List<Date> dates = Collections.emptyList();
        try {
            dates = jdbcTemplate.query(SQL_GET_ALL, dateMapper);
            LOGGER.debug("returned dates : {}", dates);
            return dates;
        } catch (DataAccessException e) {
            throw new DaoException("getting empty dates");
        }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        LOGGER.debug("deletion date by id : {}", id);
        try {
            return jdbcTemplate.update(SQL_DELETE_DATE, id) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("date is not deleted");
        }
    }

    @Override
    public boolean update(Date date) throws DaoException {
        LOGGER.debug("updating dates : {}", date);
        try {
            return jdbcTemplate.update(SQL_UPDATE_DATE, date.getDay(), date.getMounth(), date.getYear(),
                    date.getId()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("date is not updated");
        }
    }

    @Override
    public boolean create(Date date) throws DaoException {
        LOGGER.debug("creating date : {}", date);
        try {
            return jdbcTemplate.update(SQL_INSERT_DATE, date.getDay(), date.getMounth(), date.getYear()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("date is not created");
        }
    }

    @Override
    public boolean addDateToLecture(Optional<Date> date, Optional<Lecture> lecture) throws DaoException {
        LOGGER.debug("adding date : {} to lecture : {}", date, lecture);
        try {
            return jdbcTemplate.update(SQL_ADD_DATE, date.get().getId(), lecture.get().getId()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("date is not added");
        }
    }

    @Override
    public boolean removeDateFromLecture(Optional<Lecture> lecture) throws DaoException {
        LOGGER.debug("removing date from lecture : {}", lecture);
        try {
            return jdbcTemplate.update(SQL_REMOVE_DATE, lecture.get().getId()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("date is not removeed");
        }
    }

}