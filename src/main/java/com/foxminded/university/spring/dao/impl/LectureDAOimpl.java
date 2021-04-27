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

import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.LectureDao;
import com.foxminded.university.spring.mapper.LectureMapper;

@Component
public class LectureDAOimpl implements LectureDao {
	private static final Logger LOGGER = LogManager.getLogger(LectureDAOimpl.class);
	private JdbcTemplate jdbcTemplate;
	private LectureMapper lectureMapper;
	private static final String SQL_FIND_LECTURE = "SELECT * FROM lecture WHERE id = ?;";
	private static final String SQL_DELETE_LECTURE = "DELETE FROM lecture WHERE id = ?;";
	private static final String SQL_UPDATE_LECTURE = "UPDATE lecture SET  teacher_id = ?, student_id = ?, audience_id = ?, date_id = ?  WHERE lecture.id = ?;";
	private static final String SQL_GET_ALL = "SELECT * FROM lecture;";
	private static final String SQL_INSERT_LECTURE = "INSERT INTO lecture(id, teacher_id, student_id, audience_id, date_id ) VALUES(?,?,?,?,?);";

	@Autowired
	public LectureDAOimpl(DataSource dataSource, LectureMapper lectureMapper) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.lectureMapper = lectureMapper;
	}

	@Override
	public Optional<Lecture> getById(int id) {
		try {
			LOGGER.debug("getting lecture by id");
			return Optional
					.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_LECTURE, new Object[] { id }, lectureMapper));
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("field if lecture is null");
			return Optional.empty();
		}
	}

	@Override
	public Optional<List<Lecture>> getAll() {
		try {
			LOGGER.debug("getting all dates");
			return Optional.ofNullable(jdbcTemplate.query(SQL_GET_ALL, lectureMapper));
		} catch (EmptyResultDataAccessException exception) {
			LOGGER.debug("getting empty dates");
			return Optional.empty();
		}

	}

	@Override
	public boolean delete(int id) {
		LOGGER.debug("deletion lecture");
		return jdbcTemplate.update(SQL_DELETE_LECTURE, id) > 0;
	}

	@Override
	public boolean update(Lecture lecture) {
		LOGGER.debug("updating lecture");
		return jdbcTemplate.update(SQL_UPDATE_LECTURE, lecture.getTeacher().getId(), lecture.getStudent().getId(),
				lecture.getAudience().getId(), lecture.getDate().getId(), lecture.getId()) > 0;
	}

	@Override
	public boolean create(Lecture lecture) {
		LOGGER.debug("creating lecture");
		return jdbcTemplate.update(SQL_INSERT_LECTURE, lecture.getId(), lecture.getTeacher().getId(),
				lecture.getStudent().getId(), lecture.getAudience().getId(), lecture.getDate().getId()) > 0;
	}

}