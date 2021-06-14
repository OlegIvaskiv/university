package com.foxminded.university.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;
import com.foxminded.university.spring.dao.GroupDao;
import com.foxminded.university.spring.dao.exception.DaoException;
import com.foxminded.university.spring.service.GroupService;
import com.foxminded.university.spring.service.exception.ServiceException;

@Service
public class GroupServiceImpl implements GroupService {

	private GroupDao groupDao;

	@Autowired
	public GroupServiceImpl(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public Group getById(int id) throws ServiceException {
		try {
			return groupDao.getById(id);
		} catch (DaoException e) {
			throw new ServiceException("can not get group by this id");
		}
	}

	@Override
	public List<Group> getAll() throws ServiceException {
		try {
			return groupDao.getAll();
		} catch (DaoException e) {
			throw new ServiceException("can not get all groups");
		}
	}

	@Override
	public boolean delete(int id) throws ServiceException {
		try {
			return groupDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("can not delete group by this id");
		}
	}

	@Override
	public boolean update(Group group) throws ServiceException {
		try {
			return groupDao.update(group);
		} catch (DaoException e) {
			throw new ServiceException("can not update this group");
		}

	}

	@Override
	public boolean create(Group group) throws ServiceException {
		try {
			return groupDao.create(group);
		} catch (DaoException e) {
			throw new ServiceException("can not create group");
		}

	}

	@Override
	public boolean addGroupToCourse(Group group, Course course) throws ServiceException {
		try {
			return groupDao.addGroupToCourse(group, course);
		} catch (DaoException e) {
			throw new ServiceException("can not add group to course");
		}
	}

	@Override
	public boolean removeGroupFromCourse(Optional<Group> group) throws ServiceException {
		try {
			return groupDao.removeGroupFromCourse(group);
		} catch (DaoException e) {
			throw new ServiceException("can not remove group from course");
		}
	}
}
