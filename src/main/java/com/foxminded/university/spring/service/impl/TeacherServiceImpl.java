package com.foxminded.university.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.dao.TeacherDao;
import com.foxminded.university.spring.service.TeacherService;

@Component
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;

	@Override
	public Optional<Teacher> getById(int id) throws Exception {
		if (!teacherDao.getById(id).isEmpty()) {
			return teacherDao.getById(id);
		} else {
			throw new Exception("In DB no entity with this id");
		}
	}

	@Override
	public Optional<List<Teacher>> getAll() {
		return teacherDao.getAll();
	}

	@Override
	public boolean delete(int id) throws Exception {
		if (!teacherDao.getById(id).isEmpty()) {
			return teacherDao.delete(id);
		} else {
			throw new Exception("In DB no entity with this id");
		}

	}

	@Override
	public boolean update(Teacher teacher) throws Exception {
		if (!teacherDao.getById(teacher.getId()).isEmpty()) {
			return teacherDao.update(teacher);
		} else {
			throw new Exception("In DB no entity with this id");
		}

	}

	@Override
	public boolean create(Teacher teacher) {
		return teacherDao.create(teacher);
	}

	@Override
	public boolean addTeacherToLecture(Teacher teacher, Lecture lecture) {
		return teacherDao.addTeacherToLecture(teacher, lecture);
	}

	@Override
	public boolean removeTeacherFromLecture(Lecture lecture) {
		return teacherDao.removeTeacherFromLecture(lecture);
	}

	@Override
	public boolean addTeacherToGroup(Teacher teacher, Group group) {
		return teacherDao.addTeacherToGroup(teacher, group);
	}

	@Override
	public boolean removeTeacherFromGroup(Teacher teacher) {
		return teacherDao.removeTeacherFromGroup(teacher);
	}
}
