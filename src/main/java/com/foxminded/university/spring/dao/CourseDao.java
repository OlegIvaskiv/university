package com.foxminded.university.spring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Course;

@Component
public interface CourseDao extends GenericDao<Course> {
	Optional<Course> getById(int id);

	Optional<List<Course>> getAll();

	boolean delete(int id);

	boolean update(Course t);

	boolean create(Course t);
}
