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

import com.foxminded.university.model.Course;
import com.foxminded.university.spring.dao.CourseDao;
import com.foxminded.university.spring.mapper.CourseMapper;

@Component
public class CourseDAOimpl implements CourseDao {
	private static final Logger LOGGER = LogManager.getLogger(CourseDAOimpl.class);
	private JdbcTemplate jdbcTemplate;
	private CourseMapper courseMapper;
	private static final String SQL_FIND_COURSE = "SELECT * FROM course WHERE id = ?;";
	private static final String SQL_DELETE_COURSE = "DELETE FROM course WHERE id = ?;";
	private static final String SQL_UPDATE_COURSE = "UPDATE course SET  name = ? WHERE id = ?;";
	private static final String SQL_GET_ALL = "SELECT * FROM course;";
	private static final String SQL_INSERT_COURSE = "INSERT INTO course(id,  name) VALUES(?,?);";

	@Autowired
	public CourseDAOimpl(DataSource dataSource, CourseMapper courseMapper) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.courseMapper = courseMapper;
	}

	@Override
	public Optional<Course> getById(int id) {
		try {
			LOGGER.debug("getting course by id;");
			return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_COURSE, new Object[] { id }, courseMapper));
		} catch (EmptyResultDataAccessException exception) {
			LOGGER.debug("getting empty audience by id");
			return Optional.empty();
		}
	}

	@Override
	public Optional<List<Course>> getAll() {
		try {
			LOGGER.debug("getting all courses;");
			return Optional.ofNullable(jdbcTemplate.query(SQL_GET_ALL, courseMapper));
		} catch (EmptyResultDataAccessException exception) {
			LOGGER.debug("getting empty audience by id");
			return Optional.empty();
		}
	}

	@Override
	public boolean delete(int id) {
		LOGGER.debug("deletion course;");
		return jdbcTemplate.update(SQL_DELETE_COURSE, id) > 0;
	}

	@Override
	public boolean update(Course course) {
		LOGGER.debug("updating course;");
		return jdbcTemplate.update(SQL_UPDATE_COURSE, course.getName(), course.getId()) > 0;
	}

	@Override
	public boolean create(Course course) {
		LOGGER.debug("creating course;");
		return jdbcTemplate.update(SQL_INSERT_COURSE, course.getId(), course.getName()) > 0;
	}

}