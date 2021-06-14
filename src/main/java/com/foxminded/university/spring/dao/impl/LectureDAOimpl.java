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

import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.LectureDao;
import com.foxminded.university.spring.dao.exception.DaoException;
import com.foxminded.university.spring.mapper.LectureMapper;

@Component
public class LectureDaoImpl implements LectureDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(LectureDaoImpl.class);
	private JdbcTemplate jdbcTemplate;
	private LectureMapper lectureMapper;
	private static final String SQL_FIND_LECTURE = "SELECT lecture.lecture_id, date.date_id, audience.audience_id, student.student_id, teacher.teacher_id, "
			+ "date.day, date.mounth, date.year, audience.audience_number, teacher.teacher_name, teacher.teacher_phone, teacher.teacher_email, "
			+ "teacher.teacher_adress, student.student_name, student.student_phone, student.student_email, student.student_adress, \"group\".group_id, "
			+ "\"group\".group_name, course.course_id, course.course_name FROM lecture LEFT JOIN audience ON lecture.audience_id = audience.audience_id "
			+ "LEFT JOIN date ON lecture.date_id = date.date_id LEFT JOIN teacher ON lecture.teacher_id = teacher.teacher_id LEFT JOIN student ON "
			+ "lecture.student_id = student.student_id LEFT JOIN \"group\" ON student.group_id = \"group\".group_id LEFT JOIN course ON "
			+ "\"group\".course_id = course.course_id WHERE lecture.lecture_id = ?;";

	private static final String SQL_GET_ALL = "SELECT lecture.lecture_id, date.date_id, audience.audience_id, student.student_id, teacher.teacher_id, "
			+ "date.day, date.mounth, date.year, audience.audience_number, teacher.teacher_name, teacher.teacher_phone, teacher.teacher_email, "
			+ "teacher.teacher_adress, student.student_name, student.student_phone, student.student_email, student.student_adress, \"group\".group_id, "
			+ "\"group\".group_name, course.course_id, course.course_name FROM lecture LEFT JOIN audience ON lecture.audience_id = audience.audience_id "
			+ "LEFT JOIN date ON lecture.date_id = date.date_id LEFT JOIN teacher ON lecture.teacher_id = teacher.teacher_id LEFT JOIN student ON "
			+ "lecture.student_id = student.student_id LEFT JOIN \"group\" ON student.group_id = \"group\".group_id LEFT JOIN course ON "
			+ "\"group\".course_id = course.course_id;";

	private static final String SQL_DELETE_LECTURE = "DELETE FROM lecture WHERE lecture_id = ?;";
	private static final String SQL_UPDATE_LECTURE = "UPDATE lecture SET  teacher_id = ?, student_id = ?, audience_id = ?, date_id = ?  WHERE lecture_id = ?;";
	private static final String SQL_INSERT_LECTURE = "INSERT INTO lecture(teacher_id, student_id, audience_id, date_id ) VALUES(?,?,?,?);";

	@Autowired
	public LectureDaoImpl(DataSource dataSource, LectureMapper lectureMapper) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.lectureMapper = lectureMapper;
	}

	public LectureDaoImpl() {

	}

	@Override
	public Lecture getById(int id) throws DaoException {
		LOGGER.debug("getting lecture by id : {}", id);
		try {
			Lecture lecture = jdbcTemplate.queryForObject(SQL_FIND_LECTURE, new Object[] { id }, lectureMapper);
			LOGGER.debug("returned lecture : {}", lecture);
			return lecture;
		} catch (DataAccessException e) {
			throw new DaoException("wrong id");
		}
	}

	@Override
	public List<Lecture> getAll() throws DaoException {
		LOGGER.debug("getting all lectures");
		List<Lecture> lectures = Collections.emptyList();
		try {
			lectures = jdbcTemplate.query(SQL_GET_ALL, lectureMapper);
			LOGGER.debug("returned lecture : {}", lectures);
			return lectures;
		} catch (DataAccessException e) {
			throw new DaoException("getting empty lecture");
		}

	}

	@Override
	public boolean delete(int id) throws DaoException {
		LOGGER.debug("deletion lecture by id : {}", id);
		try {
			return jdbcTemplate.update(SQL_DELETE_LECTURE, id) > 0;
		} catch (DataAccessException e) {
			throw new DaoException("lecture is not deleted");
		}
	}

	@Override
	public boolean update(Lecture lecture) throws DaoException {
		LOGGER.debug("updating audience : {}", lecture);
		try {
			return jdbcTemplate.update(SQL_UPDATE_LECTURE, lecture.getTeacher().getId(), lecture.getStudent().getId(),
					lecture.getAudience().getId(), lecture.getDate().getId(), lecture.getId()) > 0;
		} catch (DataAccessException e) {
			throw new DaoException("lecture is not updated");
		}
	}

	@Override
	public boolean create(Lecture lecture) throws DaoException {
		LOGGER.debug("creating lecture : {}", lecture);
		try {
			return jdbcTemplate.update(SQL_INSERT_LECTURE, lecture.getTeacher().getId(), lecture.getStudent().getId(),
					lecture.getAudience().getId(), lecture.getDate().getId()) > 0;
		} catch (DataAccessException e) {
			throw new DaoException("lecture is not created");
		}
	}

}