package com.foxminded.university.spring.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Course;
import com.foxminded.university.spring.dao.CourseDao;
import com.foxminded.university.spring.dao.exception.DaoException;
import com.foxminded.university.spring.mapper.CourseMapper;

@Component
public class CourseDaoImpl implements CourseDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseDaoImpl.class);
    private JdbcTemplate jdbcTemplate;
    private CourseMapper courseMapper;
    private static final String SQL_FIND_COURSE = "SELECT * FROM course WHERE course_id = ?;";
    private static final String SQL_DELETE_COURSE = "DELETE FROM course WHERE course_id = ?;";
    private static final String SQL_UPDATE_COURSE = "UPDATE course SET  course_name = ? WHERE course_id = ?;";
    private static final String SQL_GET_ALL = "SELECT * FROM course;";
    private static final String SQL_INSERT_COURSE = "INSERT INTO course(course_name) VALUES(?);";

    @Autowired
    public CourseDaoImpl(DataSource dataSource, CourseMapper courseMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.courseMapper = courseMapper;
    }

    @Override
    public Course getById(int id) throws DaoException {
        LOGGER.debug("getting course by id : {}", id);
        try {
            Course course = jdbcTemplate.queryForObject(SQL_FIND_COURSE, new Object[] { id }, courseMapper);
            LOGGER.debug("returned course : {}", course);
            return course;
        } catch (DataAccessException e) {
            throw new DaoException("wrong id");
        }
    }

    @Override
    public List<Course> getAll() throws DaoException {
        LOGGER.debug("getting all courses");
        List<Course> courses = Collections.emptyList();
        try {
            courses = jdbcTemplate.query(SQL_GET_ALL, courseMapper);
            LOGGER.debug("returned courses : {}", courses);
            return courses;
        } catch (DataAccessException e) {
            throw new DaoException("getting empty audience by id");
        }

    }

    @Override
    public boolean delete(int id) throws DaoException {
        LOGGER.debug("deletion course by id : {}", id);
        try {
            return jdbcTemplate.update(SQL_DELETE_COURSE, id) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("course is not deleted");
        }
    }

    @Override
    public boolean update(Course course) throws DaoException {
        LOGGER.debug("updating course : {}", course);
        try {
            return jdbcTemplate.update(SQL_UPDATE_COURSE, course.getName(), course.getId()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("course is not updated");
        }
    }

    @Override
    public boolean create(Course course) throws DaoException {
        LOGGER.debug("creating course : {}", course);
        try {
            return jdbcTemplate.update(SQL_INSERT_COURSE, course.getName()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("course is not created");
        }
    }

}