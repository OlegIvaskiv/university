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

import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.dao.TeacherDao;
import com.foxminded.university.spring.dao.exception.DaoException;
import com.foxminded.university.spring.mapper.TeacherMapper;

@Component
public class TeacherDAOimpl implements TeacherDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherDAOimpl.class);
    private JdbcTemplate jdbcTemplate;
    private TeacherMapper teacherMapper;
    private static final String SQL_FIND_TEACHER = "SELECT teacher.teacher_id, teacher.teacher_name, teacher.teacher_phone, teacher.teacher_email, teacher.teacher_adress FROM teacher  WHERE teacher_id = ?;";
    private static final String SQL_DELETE_TEACHER = "DELETE FROM teacher WHERE teacher_id = ?;";
    private static final String SQL_UPDATE_TEACHER = "UPDATE teacher SET teacher_name = ?, teacher_phone = ?, teacher_email = ?, teacher_adress = ? WHERE teacher_id = ?;";
    private static final String SQL_GET_ALL = "SELECT teacher.teacher_id, teacher.teacher_name, teacher.teacher_phone, teacher.teacher_email, teacher.teacher_adress FROM teacher;";
    private static final String SQL_INSERT_TEACHER = "INSERT INTO teacher(teacher_id, teacher_name, teacher_phone, teacher_email, teacher_adress) VALUES(?,?,?,?,?);";
    private static final String SQL_ADD_TEACHER_TO_LECTURE = "UPDATE lecture SET teacher_id = ? WHERE lecture_id = ?;";
    private static final String SQL_REMOVE_TEACHER_FROM_LECTURE = "UPDATE lecture SET teacher_id = NULL WHERE lecture_id = ?;";

    @Autowired
    public TeacherDAOimpl(DataSource dataSource, TeacherMapper teacherMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.teacherMapper = teacherMapper;
    }

    @Override
    public Teacher getById(int id) throws DaoException {
        LOGGER.debug("getting teacher by id : {}", id);
        try {
            Teacher teacher = jdbcTemplate.queryForObject(SQL_FIND_TEACHER, new Object[] { id }, teacherMapper);
            LOGGER.debug("returned teacher : {}", teacher);
            return teacher;
        } catch (DataAccessException e) {
            throw new DaoException("wrong id");
        }
    }

    @Override
    public List<Teacher> getAll() throws DaoException {
        LOGGER.debug("getting all teachers");
        List<Teacher> teachers = Collections.emptyList();
        try {
            teachers = jdbcTemplate.query(SQL_GET_ALL, teacherMapper);
            LOGGER.debug("returned teachers : {}", teachers);
            return teachers;
        } catch (DataAccessException e) {
            throw new DaoException("getting all teachers");
        }

    }

    @Override
    public boolean delete(int id) throws DaoException {
        LOGGER.debug("deletion teacher by id : {}", id);
        try {
            return jdbcTemplate.update(SQL_DELETE_TEACHER, id) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("teacher is not deleted");
        }
    }

    @Override
    public boolean update(Teacher teacher) throws DaoException {
        LOGGER.debug("updating audience : {}", teacher);
        try {
            return jdbcTemplate.update(SQL_UPDATE_TEACHER, teacher.getName(), teacher.getPhone(), teacher.getEmail(),
                    teacher.getAdress(), teacher.getId()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("teacher is not updated");
        }
    }

    @Override
    public boolean create(Teacher teacher) throws DaoException {
        LOGGER.debug("creating teacher : {}", teacher);
        try {
            return jdbcTemplate.update(SQL_INSERT_TEACHER, teacher.getId(), teacher.getName(), teacher.getPhone(),
                    teacher.getEmail(), teacher.getAdress()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("teacher is not created");
        }
    }

    @Override
    public boolean addTeacherToLecture(Optional<Teacher> teacher, Optional<Lecture> lecture) throws DaoException {
        LOGGER.debug("adding teacher : {} to lecture : {}", teacher, lecture);
        try {
            return jdbcTemplate.update(SQL_ADD_TEACHER_TO_LECTURE, teacher.get().getId(), lecture.get().getId()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("teacher is not added");
        }
    }

    @Override
    public boolean removeTeacherFromLecture(Optional<Lecture> lecture) throws DaoException {
        LOGGER.debug("removing teacher from lecture : {}", lecture);
        try {
            return jdbcTemplate.update(SQL_REMOVE_TEACHER_FROM_LECTURE, lecture.get().getId()) > 0;
        } catch (DataAccessException e) {
            throw new DaoException("teacher is not removed from lecture");
        }
    }

}
