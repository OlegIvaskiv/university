package com.foxminded.university.spring.service;

import java.util.List;
import java.util.Optional;

import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.service.exception.ServiceException;

public interface TeacherService {
	Teacher getById(int id) throws ServiceException;

	List<Teacher> getAll() throws ServiceException;

	boolean delete(int id) throws ServiceException;

	boolean update(Teacher t) throws ServiceException;

	boolean create(Teacher t) throws ServiceException;

	boolean removeTeacherFromLecture(Optional<Lecture> lecture) throws ServiceException;

	boolean addTeacherToLecture(Optional<Teacher> teacher, Optional<Lecture> lecture) throws ServiceException;
}
