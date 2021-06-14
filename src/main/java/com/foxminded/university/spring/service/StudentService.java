package com.foxminded.university.spring.service;

import java.util.List;
import java.util.Optional;

import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Student;
import com.foxminded.university.spring.service.exception.ServiceException;

public interface StudentService {
	Student getById(int id) throws ServiceException;

	List<Student> getAll() throws ServiceException;

	boolean delete(int id) throws ServiceException;

	boolean update(Student t) throws ServiceException;

	boolean create(Student t) throws ServiceException;

	boolean addStudentToGroup(Optional<Student> student, Optional<Group> group) throws ServiceException;

	boolean removeStudentFromGroup(Optional<Student> student) throws ServiceException;

	boolean addStudentToLecture(Optional<Student> student, Optional<Lecture> lecture) throws ServiceException;

	boolean removeStudentFromLecture(Optional<Lecture> lecture) throws ServiceException;
}
