package com.foxminded.university.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;

@Component
public interface GroupService {
	Optional<Group> getById(int id) throws Exception;

	Optional<List<Group>> getAll();

	boolean delete(int id) throws Exception;

	boolean update(Group t) throws Exception;

	boolean create(Group t);

	boolean addGroupToCourse(Group group, Course course);

	boolean removeGroupFromCourse(Group group);
}
