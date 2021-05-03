package com.foxminded.university.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;

@Component
public interface GroupService {
	Optional<Group> getById(int id) throws Exception;

	Optional<List<Group>> getAll() throws Exception;

	boolean delete(int id) throws Exception;

	boolean update(Group t) throws Exception;

	boolean create(Group t);

	boolean removeGroupFromCourse(Optional<Group> group) throws Exception;

	boolean addGroupToCourse(Optional<Group> group, Optional<Course> course) throws Exception;
}
