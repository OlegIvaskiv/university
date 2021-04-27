package com.foxminded.university.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Student;
import com.foxminded.university.spring.dao.StudentDao;
import com.foxminded.university.spring.service.StudentService;

@Component
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;

	@Override
	public Optional<Student> getById(int id) throws Exception {
		if (!studentDao.getById(id).isEmpty()) {
			return studentDao.getById(id);
		} else {
			throw new Exception("In DB no entity with this id");
		}
	}

	@Override
	public Optional<List<Student>> getAll() {
		return studentDao.getAll();
	}

	@Override
	public boolean delete(int id) throws Exception {
		if (!studentDao.getById(id).isEmpty()) {
			return studentDao.delete(id);
		} else {
			throw new Exception("In DB no entity with this id");
		}
	}

	@Override
	public boolean update(Student student) throws Exception {
		if (!studentDao.getById(student.getId()).isEmpty()) {
			return studentDao.update(student);
		} else {
			throw new Exception("In DB no entity with this id");
		}
	}

	@Override
	public boolean create(Student student) {
		return studentDao.create(student);
	}

	@Override
	public boolean addStudentToLecture(Student student, Lecture lecture) {
		return studentDao.addStudentToLecture(student, lecture);
	}

	@Override
	public boolean removeStudentFromLecture(Lecture lecture) {
		return studentDao.removeStudentFromLecture(lecture);
	}

	@Override
	public boolean addStudentToGroup(Student student, Group group) {
		return studentDao.addStudentToGroup(student, group);
	}

	@Override
	public boolean removeStudentFromGroup(Student student) {
		return studentDao.removeStudentFromGroup(student);
	}
}
