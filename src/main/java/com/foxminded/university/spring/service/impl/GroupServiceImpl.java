package com.foxminded.university.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;
import com.foxminded.university.spring.dao.GroupDao;
import com.foxminded.university.spring.service.GroupService;

@Component
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupDao groupDao;

	@Override
	public Optional<Group> getById(int id) throws Exception {
		if (!groupDao.getById(id).isEmpty()) {
			return groupDao.getById(id);
		} else {
			throw new Exception("In DB no entity with this id");
		}
	}

	@Override
	public Optional<List<Group>> getAll() {
		return groupDao.getAll();
	}

	@Override
	public boolean delete(int id) throws Exception {
		if (!groupDao.getById(id).isEmpty()) {
			return groupDao.delete(id);
		} else {
			throw new Exception("In DB no entity with this id");
		}
	}

	@Override
	public boolean update(Group group) throws Exception {
		if (!groupDao.getById(group.getId()).isEmpty()) {
			return groupDao.update(group);
		} else {
			throw new Exception("In DB no entity with this id");
		}

	}

	@Override
	public boolean create(Group group) {
		return groupDao.create(group);
	}

	@Override
	public boolean addGroupToCourse(Group group, Course course) {
		return groupDao.addGroupToCourse(group, course);
	}

	@Override
	public boolean removeGroupFromCourse(Group group) {
		return groupDao.removeGroupFromCourse(group);
	}
}
