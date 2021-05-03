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
	public Optional<List<Teacher>> getAll() throws Exception {
		if (!teacherDao.getAll().isEmpty()) {
			return teacherDao.getAll();
		} else {
			throw new Exception("In DB no entity with this id");
		}
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
	public boolean addTeacherToLecture(Optional<Teacher> teacher, Optional<Lecture> lecture) throws Exception {
		if (lecture.get().getStudent().equals(null)) {
			if (!teacherDao.getById(teacher.get().getId()).isEmpty()) {
				return teacherDao.addTeacherToLecture(teacher, lecture);
			} else {
				throw new Exception("In DB no entity with this id");
			}
		} else {
			throw new Exception("The lecture has already has the student");
		}
	}

	@Override
	public boolean removeTeacherFromLecture(Optional<Lecture> lecture) throws Exception {
		if (!lecture.get().getStudent().equals(null)) {
			return teacherDao.removeTeacherFromLecture(lecture);
		} else {
			throw new Exception("The lecture has already has the audience");
		}
	}

	@Override
	public boolean addTeacherToGroup(Optional<Teacher> teacher, Optional<Group> group) throws Exception {
		if (teacher.get().getGroup().equals(null)) {
			if (!teacherDao.getById(teacher.get().getId()).isEmpty()) {
				return teacherDao.addTeacherToGroup(teacher, group);
			} else {
				throw new Exception("In DB no entity with this id");
			}
		} else {
			throw new Exception("The student has already has the group");
		}
	}

	@Override
	public boolean removeTeacherFromGroup(Optional<Teacher> teacher) throws Exception {
		if (!teacher.get().getGroup().equals(null)) {
			return teacherDao.removeTeacherFromGroup(teacher);
		} else {
			throw new Exception("The lecture has already has the audience");
		}
	}
}
