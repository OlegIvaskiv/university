package com.foxminded.university.spring.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Course;
import com.foxminded.university.spring.service.exception.ServiceException;

@Component
public interface CourseService {
	Course getById(int id) throws Exception;

	List<Course> getAll() throws Exception;

	boolean delete(int id) throws Exception;

	boolean update(Course t) throws Exception;

	boolean create(Course t) throws ServiceException;
}
