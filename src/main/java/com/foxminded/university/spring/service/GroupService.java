package com.foxminded.university.spring.service;

import java.util.List;
import java.util.Optional;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;
import com.foxminded.university.spring.service.exception.ServiceException;

public interface GroupService {
	Group getById(int id) throws ServiceException;

	List<Group> getAll() throws ServiceException;

	boolean delete(int id) throws ServiceException;

	boolean update(Group t) throws ServiceException;

	boolean create(Group t) throws ServiceException;

	boolean removeGroupFromCourse(Optional<Group> group) throws ServiceException;

	boolean addGroupToCourse(Group group, Course course) throws ServiceException;
}
