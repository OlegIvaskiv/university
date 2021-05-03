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

import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.dao.TeacherDao;
import com.foxminded.university.spring.mapper.TeacherMapper;

@Component
public class TeacherDAOimpl implements TeacherDao {
	private static final Logger LOGGER = LogManager.getLogger(TeacherDAOimpl.class);
	private JdbcTemplate jdbcTemplate;
	private TeacherMapper teacherMapper;
	private static final String SQL_FIND_TEACHER = "SELECT * FROM teacher WHERE id = ?;";
	private static final String SQL_DELETE_TEACHER = "DELETE FROM teacher WHERE id = ?;";
	private static final String SQL_UPDATE_TEACHER = "UPDATE teacher SET group_id = ?, name = ?, phone = ?, email = ?, adress = ? WHERE id = ?;";
	private static final String SQL_GET_ALL = "SELECT * FROM teacher;";
	private static final String SQL_INSERT_TEACHER = "INSERT INTO teacher(id, group_id, name, phone, email, adress) VALUES(?,?,?,?,?,?);";
	private static final String SQL_ADD_TEACHER_TO_LECTURE = "UPDATE lecture SET teacher_id = ? WHERE id = ?;";
	private static final String SQL_REMOVE_TEACHER_FROM_LECTURE = "UPDATE lecture SET teacher_id = NULL WHERE id = ?;";
	private static final String SQL_ADD_TEACHER_TO_GROUP = "UPDATE teacher SET group_id = ? WHERE id = ?;";
	private static final String SQL_REMOVE_TEACHER_FROM_GROUP = "UPDATE teacher SET group_id = NULL WHERE id = ?;";

	@Autowired
	public TeacherDAOimpl(DataSource dataSource, TeacherMapper teacherMapper) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.teacherMapper = teacherMapper;
	}

	@Override
	public Optional<Teacher> getById(int id) {
		try {
			LOGGER.debug("getting teacher by id");
			return Optional
					.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_TEACHER, new Object[] { id }, teacherMapper));
		} catch (EmptyResultDataAccessException exception) {
			LOGGER.debug("getting all teachers");
			return Optional.empty();
		}

	}

	@Override
	public Optional<List<Teacher>> getAll() {
		try {
			LOGGER.debug("getting all teachers");
			return Optional.ofNullable(jdbcTemplate.query(SQL_GET_ALL, teacherMapper));
		} catch (EmptyResultDataAccessException exception) {
			LOGGER.debug("getting all teachers");
			return Optional.empty();
		}

	}

	@Override
	public boolean delete(int id) {
		LOGGER.debug("deletion teacher");
		return jdbcTemplate.update(SQL_DELETE_TEACHER, id) > 0;
	}

	@Override
	public boolean update(Teacher teacher) {
		LOGGER.debug("updating teacher");
		return jdbcTemplate.update(SQL_UPDATE_TEACHER, teacher.getGroup().getId(), teacher.getName(),
				teacher.getPhone(), teacher.getEmail(), teacher.getAdress(), teacher.getId()) > 0;
	}

	@Override
	public boolean create(Teacher teacher) {

		LOGGER.debug("creating teacher");
		return jdbcTemplate.update(SQL_INSERT_TEACHER, teacher.getId(), teacher.getGroup().getId(), teacher.getName(),
				teacher.getPhone(), teacher.getEmail(), teacher.getAdress()) > 0;
	}

	@Override
	public boolean addTeacherToLecture(Optional<Teacher> teacher, Optional<Lecture> lecture) {
		LOGGER.debug("adding teacher to lecture");
		return jdbcTemplate.update(SQL_ADD_TEACHER_TO_LECTURE, teacher.get().getId(), lecture.get().getId()) > 0;
	}

	@Override
	public boolean removeTeacherFromLecture(Optional<Lecture> lecture) {
		LOGGER.debug("removing teacher from lecture");
		return jdbcTemplate.update(SQL_REMOVE_TEACHER_FROM_LECTURE, lecture.get().getId()) > 0;
	}

	@Override
	public boolean addTeacherToGroup(Optional<Teacher> teacher, Optional<Group> group) {
		LOGGER.debug("adding teacher to group");
		return jdbcTemplate.update(SQL_ADD_TEACHER_TO_GROUP, group.get().getId(), teacher.get().getId()) > 0;
	}

	@Override
	public boolean removeTeacherFromGroup(Optional<Teacher> teacher) {
		LOGGER.debug("removing teacher from group");
		return jdbcTemplate.update(SQL_REMOVE_TEACHER_FROM_GROUP, teacher.get().getId()) > 0;
	}
}
