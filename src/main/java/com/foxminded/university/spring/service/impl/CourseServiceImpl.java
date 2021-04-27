package com.foxminded.university.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Course;
import com.foxminded.university.spring.dao.CourseDao;
import com.foxminded.university.spring.service.CourseService;

@Component
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;

	@Override
	public Optional<Course> getById(int id) throws Exception {
		if (!courseDao.getById(id).isEmpty()) {
			return courseDao.getById(id);
		} else {
			throw new Exception("In DB no entity with this id");
		}

	}

	@Override
	public Optional<List<Course>> getAll() throws Exception {
		if (!courseDao.getAll().isEmpty()) {
			return courseDao.getAll();
		} else {
			throw new Exception("In DB no entity with this id");
		}
	}

	@Override
	public boolean delete(int id) throws Exception {
		if (!courseDao.getById(id).isEmpty()) {
			return courseDao.delete(id);
		} else {
			throw new Exception("In DB no entity with this id");
		}
	}

	@Override
	public boolean update(Course course) throws Exception {
		if (!courseDao.getById(course.getId()).isEmpty()) {
			return courseDao.update(course);
		} else {
			throw new Exception("In DB no entity with this id");
		}
	}

	@Override
	public boolean create(Course course) {
		return courseDao.create(course);
	}

}
