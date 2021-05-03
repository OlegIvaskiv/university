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
import com.foxminded.university.model.Student;
import com.foxminded.university.spring.dao.StudentDao;
import com.foxminded.university.spring.mapper.StudentMapper;

@Component
public class StudentDAOimpl implements StudentDao {
	private static final Logger LOGGER = LogManager.getLogger(StudentDAOimpl.class);
	private JdbcTemplate jdbcTemplate;
	private StudentMapper studentMapper;
	private static final String SQL_FIND_STUDENT = "SELECT * FROM student WHERE id = ?;";
	private static final String SQL_DELETE_STUDENT = "DELETE FROM student WHERE id = ?;";
	private static final String SQL_UPDATE_STUDENT = "UPDATE student SET  group_id = ?, name = ?, phone = ?, email = ?, adress = ? WHERE id = ?;";
	private static final String SQL_GET_ALL = "SELECT * FROM student;";
	private static final String SQL_INSERT_STUDENT = "INSERT INTO student (id, group_id, name, phone, email, adress) VALUES(?,?,?,?,?,?);";
	private static final String SQL_ADD_STUDENT_TO_LECTURE = "UPDATE lecture SET student_id = ? WHERE id = ?;";
	private static final String SQL_REMOVE_STUDENT_FROM_LECTURE = "UPDATE lecture SET student_id = NULL WHERE id = ?;";
	private static final String SQL_ADD_STUDENT_TO_GROUP = "UPDATE student SET group_id = ? WHERE id = ?;";
	private static final String SQL_REMOVE_STUDENT_FROM_GROUP = "UPDATE student SET group_id = NULL WHERE id = ?;";

	@Autowired
	public StudentDAOimpl(DataSource dataSource, StudentMapper studentMapper) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.studentMapper = studentMapper;
	}

	@Override
	public Optional<Student> getById(int id) {
		try {
			LOGGER.debug("getting student by id");
			return Optional
					.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_STUDENT, new Object[] { id }, studentMapper));
		} catch (EmptyResultDataAccessException exception) {
			LOGGER.debug("getting empty dates");
			return Optional.empty();
		}
	}

	@Override
	public Optional<List<Student>> getAll() {
		try {
			LOGGER.debug("getting all students");
			return Optional.ofNullable(jdbcTemplate.query(SQL_GET_ALL, studentMapper));
		} catch (EmptyResultDataAccessException exception) {
			LOGGER.debug("getting empty dates");
			return Optional.empty();
		}
	}

	@Override
	public boolean delete(int id) {
		LOGGER.debug("deletion student");
		return jdbcTemplate.update(SQL_DELETE_STUDENT, id) > 0;
	}

	@Override
	public boolean update(Student student) {
		LOGGER.debug("updating student");
		return jdbcTemplate.update(SQL_UPDATE_STUDENT, student.getGroup().getId(), student.getName(),
				student.getPhone(), student.getEmail(), student.getAdress(), student.getId()) > 0;
	}

	@Override
	public boolean create(Student student) {

		LOGGER.debug("creating student");
		return jdbcTemplate.update(SQL_INSERT_STUDENT, student.getId(), student.getGroup().getId(), student.getName(),
				student.getPhone(), student.getEmail(), student.getAdress()) > 0;
	}

	@Override
	public boolean addStudentToLecture(Optional<Student> student, Optional<Lecture> lecture) {
		LOGGER.debug("adding student to lecture");
		return jdbcTemplate.update(SQL_ADD_STUDENT_TO_LECTURE, student.get().getId(), lecture.get().getId()) > 0;

	}

	@Override
	public boolean removeStudentFromLecture(Optional<Lecture> lecture) {
		LOGGER.debug("removing student from lecture");
		return jdbcTemplate.update(SQL_REMOVE_STUDENT_FROM_LECTURE, lecture.get().getId()) > 0;
	}

	@Override
	public boolean addStudentToGroup(Optional<Student> student, Optional<Group> group) {
		LOGGER.debug("adding student to group");
		return jdbcTemplate.update(SQL_ADD_STUDENT_TO_GROUP, group.get().getId(), student.get().getId()) > 0;

	}

	@Override
	public boolean removeStudentFromGroup(Optional<Student> student) {
		LOGGER.debug("removing student from group");
		return jdbcTemplate.update(SQL_REMOVE_STUDENT_FROM_GROUP, student.get().getId()) > 0;
	}
}
