package com.foxminded.university.spring.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Course;
import com.foxminded.university.spring.dao.exception.DaoException;

@Component
public interface CourseDao extends GenericDao<Course> {
	Course getById(int id) throws DaoException;

	List<Course> getAll() throws DaoException;

	boolean delete(int id) throws DaoException;

	boolean update(Course t) throws DaoException;

	boolean create(Course t) throws DaoException;
}
