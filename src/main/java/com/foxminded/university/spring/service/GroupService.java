package com.foxminded.university.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;
import com.foxminded.university.spring.service.exception.ServiceException;

@Component
public interface GroupService {
	Group getById(int id) throws Exception;

	List<Group> getAll() throws Exception;

	boolean delete(int id) throws Exception;

	boolean update(Group t) throws Exception;

	boolean create(Group t) throws ServiceException;

	boolean removeGroupFromCourse(Optional<Group> group) throws Exception;

	boolean addGroupToCourse(Optional<Group> group, Optional<Course> course) throws Exception;
}
