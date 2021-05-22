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

import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Student;
import com.foxminded.university.spring.dao.StudentDao;
import com.foxminded.university.spring.dao.exception.DaoException;
import com.foxminded.university.spring.mapper.StudentMapper;

@Component
public class StudentDAOimpl implements StudentDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentDAOimpl.class);
    private JdbcTemplate jdbcTemplate;
    private StudentMapper studentMapper;
    private static final String SQL_FIND_STUDENT = "SELECT student.student_id,\"group\".group_id, \"group\".group_name, course.course_id, course.course_name,"
            + " student.student_name, student.student_phone, student.student_email, student.student_adress FROM student LEFT JOIN \"group\" ON "
            + "\"group\".group_id = student.group_id LEFT JOIN course ON course.course_id = \"group\".group_id WHERE student_id = ?;";

    private static final String SQL_UPDATE_STUDENT = "UPDATE student SET  group_id = ?, student_name = ?, student_phone = ?, student_email = ?,"
            + " student_adress = ? WHERE student_id = ?;";

    private static final String SQL_GET_ALL = "SELECT student.student_id,\"group\".group_id, \"group\".group_name, course.course_id, course.course_name, "
            + "student.student_name, student.student_phone, student.student_email, student.student_adress FROM student LEFT JOIN \"group\" ON "
            + "\"group\".group_id = student.group_id LEFT JOIN course ON course.course_id = \"group\".group_id;";

    private static final String SQL_INSERT_STUDENT = "INSERT INTO student (student_id, group_id, student_name, student_phone, student_email, student_adress)"
            + " VALUES(?,?,?,?,?,?);";

    private static final String SQL_ADD_STUDENT_TO_LECTURE = "UPDATE lecture SET student_id = ? WHERE lecture_id = ?;";
    private static final String SQL_DELETE_STUDENT = "DELETE FROM student WHERE student_id = ?;";
    private static final String SQL_REMOVE_STUDENT_FROM_LECTURE = "UPDATE lecture SET student_id = NULL WHERE lecture_id = ?;";
    private static final String SQL_ADD_STUDENT_TO_GROUP = "UPDATE student SET group_id = ? WHERE student_id = ?;";
    private static final String SQL_REMOVE_STUDENT_FROM_GROUP = "UPDATE student SET group_id = NULL WHERE student_id = ?;";

    @Autowired
    public StudentDAOimpl(DataSource dataSource, StudentMapper studentMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.studentMapper = studentMapper;
    }

    @Override
    public Student getById(int id) throws DaoException {
        LOGGER.debug("getting student by id : {}", id);
        try {
            Student student = jdbcTemplate.queryForObject(SQL_FIND_STUDENT, new Object[] { id }, studentMapper);
            LOGGER.debug("returned student : {}", student);
            return student;
        } catch (DataAccessException e) {
            throw new DaoException("wrong id");
        }
    }

    @Override
    public List<Student> getAll() throws DaoException {
        LOGGER.debug("getting all students");
        List<Student> students = Collections.emptyList();
        try {
            students = jdbcTemplate.query(SQL_GET_ALL, studentMapper);
            LOGGER.debug("returned audiences : {}", students);
            return students;
        } catch (DataAccessException e) {
            throw new DaoException("getting empty student");
        }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        LOGGER.debug("deletion student by id : {}", id);
        try {
            return jdbcTemplate.update(SQL_DELETE_STUDENT, id) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("student is not deleted");
        }
    }

    @Override
    public boolean update(Student student) throws DaoException {
        LOGGER.debug("updating student : {}", student);
        try {
            return jdbcTemplate.update(SQL_UPDATE_STUDENT, student.getGroup().getId(), student.getName(),
                    student.getPhone(), student.getEmail(), student.getAdress(), student.getId()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("date is not updated");
        }
    }

    @Override
    public boolean create(Student student) throws DaoException {
        LOGGER.debug("creating student : {}", student);
        try {
            return jdbcTemplate.update(SQL_INSERT_STUDENT, student.getId(), student.getGroup().getId(),
                    student.getName(), student.getPhone(), student.getEmail(), student.getAdress()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("date is not created");
        }
    }

    @Override
    public boolean addStudentToLecture(Optional<Student> student, Optional<Lecture> lecture) throws DaoException {
        LOGGER.debug("adding student : {} to lecture : {}", student, lecture);
        try {
            return jdbcTemplate.update(SQL_ADD_STUDENT_TO_LECTURE, student.get().getId(), lecture.get().getId()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("student is not added");
        }
    }

    @Override
    public boolean removeStudentFromLecture(Optional<Lecture> lecture) throws DaoException {
        LOGGER.debug("removing student from lecture : {}", lecture);
        try {
            return jdbcTemplate.update(SQL_REMOVE_STUDENT_FROM_LECTURE, lecture.get().getId()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("student is not removed from lecture");
        }
    }

    @Override
    public boolean addStudentToGroup(Optional<Student> student, Optional<Group> group) throws DaoException {
        LOGGER.debug("adding student : {} to group : {}", student, group);
        try {
            return jdbcTemplate.update(SQL_ADD_STUDENT_TO_GROUP, group.get().getId(), student.get().getId()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("student is not added");
        }
    }

    @Override
    public boolean removeStudentFromGroup(Optional<Student> student) throws DaoException {
        LOGGER.debug("removing student from group : {}", student);
        try {
            return jdbcTemplate.update(SQL_REMOVE_STUDENT_FROM_GROUP, student.get().getId()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("student is not removed from group");
        }
    }
}
