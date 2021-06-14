package com.foxminded.university.spring.service;

import java.util.List;

import com.foxminded.university.model.Course;
import com.foxminded.university.spring.service.exception.ServiceException;

public interface CourseService {
	Course getById(int id) throws ServiceException;

	List<Course> getAll() throws ServiceException;

	boolean delete(int id) throws ServiceException;

	boolean update(Course t) throws ServiceException;

	boolean create(Course t) throws ServiceException;
}
