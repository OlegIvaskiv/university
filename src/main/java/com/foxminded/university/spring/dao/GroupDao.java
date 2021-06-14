package com.foxminded.university.spring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;
import com.foxminded.university.spring.dao.exception.DaoException;

@Component
public interface GroupDao extends GenericDao<Group> {
	Group getById(int id) throws DaoException;

	List<Group> getAll() throws DaoException;

	boolean delete(int id) throws DaoException;

	boolean update(Group t) throws DaoException;

	boolean create(Group t) throws DaoException;

	boolean addGroupToCourse(Group group, Course course) throws DaoException;

	boolean removeGroupFromCourse(Optional<Group> group) throws DaoException;
}
