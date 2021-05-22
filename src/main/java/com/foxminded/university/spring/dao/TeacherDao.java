package com.foxminded.university.spring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.dao.exception.DaoException;

@Component
public interface TeacherDao extends GenericDao<Teacher> {
    Teacher getById(int id) throws DaoException;

    List<Teacher> getAll() throws DaoException;

    boolean delete(int id) throws DaoException;

    boolean update(Teacher t) throws DaoException;

    boolean create(Teacher t) throws DaoException;

    boolean addTeacherToLecture(Optional<Teacher> teacher, Optional<Lecture> lecture) throws DaoException;

    boolean removeTeacherFromLecture(Optional<Lecture> lecture) throws DaoException;

}
