package com.foxminded.university.spring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Student;
import com.foxminded.university.spring.dao.exception.DaoException;

@Component
public interface StudentDao extends GenericDao<Student> {
	Student getById(int id) throws DaoException;

	List<Student> getAll() throws DaoException;

	boolean delete(int id) throws DaoException;

	boolean update(Student t) throws DaoException;

	boolean create(Student t) throws DaoException;

	boolean addStudentToLecture(Optional<Student> student, Optional<Lecture> lecture) throws DaoException;

	boolean removeStudentFromLecture(Optional<Lecture> lecture) throws DaoException;

	boolean removeStudentFromGroup(Optional<Student> student) throws DaoException;

	boolean addStudentToGroup(Optional<Student> student, Optional<Group> group) throws DaoException;
}
