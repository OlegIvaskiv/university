package com.foxminded.university.spring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;

@Component
public interface GroupDao extends GenericDao<Group> {
	Optional<Group> getById(int id);

	Optional<List<Group>> getAll();

	boolean delete(int id);

	boolean update(Group t);

	boolean create(Group t);

	boolean addGroupToCourse(Group group, Course course);

	boolean removeGroupFromCourse(Group group);
}
