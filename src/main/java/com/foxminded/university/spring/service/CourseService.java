package com.foxminded.university.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Course;

@Component
public interface CourseService {
	Optional<Course> getById(int id) throws Exception;

	Optional<List<Course>> getAll() throws Exception;

	boolean delete(int id) throws Exception;

	boolean update(Course t) throws Exception;

	boolean create(Course t);
}
