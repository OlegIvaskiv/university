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
	public Optional<List<Student>> getAll() throws Exception {
		if (!studentDao.getAll().isEmpty()) {
			return studentDao.getAll();
		} else {
			throw new Exception("In DB no entity with this id");
		}
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
	public boolean addStudentToLecture(Optional<Student> student, Optional<Lecture> lecture) throws Exception {
		if (lecture.get().getStudent().equals(null)) {
			if (!studentDao.getById(student.get().getId()).isEmpty()) {
				return studentDao.addStudentToLecture(student, lecture);
			} else {
				throw new Exception("In DB no entity with this id");
			}
		} else {
			throw new Exception("The lecture has already has the student");
		}
	}

	@Override
	public boolean removeStudentFromLecture(Optional<Lecture> lecture) throws Exception {
		if (!lecture.get().getStudent().equals(null)) {
			return studentDao.removeStudentFromLecture(lecture);
		} else {
			throw new Exception("The lecture has already has the audience");
		}
	}

	@Override
	public boolean addStudentToGroup(Optional<Student> student, Optional<Group> group) throws Exception {
		if (student.get().getGroup().equals(null)) {
			if (!studentDao.getById(student.get().getId()).isEmpty()) {
				return studentDao.addStudentToGroup(student, group);
			} else {
				throw new Exception("In DB no entity with this id");
			}
		} else {
			throw new Exception("The student has already has the group");
		}
	}

	@Override
	public boolean removeStudentFromGroup(Optional<Student> student) throws Exception {
		if (!student.get().getGroup().equals(null)) {
			return studentDao.removeStudentFromGroup(student);
		} else {
			throw new Exception("The lecture has already has the audience");
		}
	}
}
