package com.foxminded.university.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.model.Course;
import com.foxminded.university.spring.dao.CourseDao;
import com.foxminded.university.spring.dao.exception.DaoException;
import com.foxminded.university.spring.service.CourseService;
import com.foxminded.university.spring.service.exception.ServiceException;

@Service
public class CourseServiceImpl implements CourseService {

	private CourseDao courseDao;

	@Autowired
	public CourseServiceImpl(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	@Override
	public Course getById(int id) throws ServiceException {
		try {
			return courseDao.getById(id);
		} catch (DaoException e) {
			throw new ServiceException("can not get course by this id");
		}

	}

	@Override
	public List<Course> getAll() throws ServiceException {
		try {
			return courseDao.getAll();
		} catch (DaoException e) {
			throw new ServiceException("can not get all courses");
		}
	}

	@Override
	public boolean delete(int id) throws ServiceException {
		try {
			return courseDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("can not delete course by this id");
		}
	}

	@Override
	public boolean update(Course course) throws ServiceException {
		try {
			return courseDao.update(course);
		} catch (DaoException e) {
			throw new ServiceException("can not update this course");
		}
	}

	@Override
	public boolean create(Course course) throws ServiceException {
		try {
			return courseDao.create(course);
		} catch (DaoException e) {
			throw new ServiceException("can not create course");
		}
	}

}
