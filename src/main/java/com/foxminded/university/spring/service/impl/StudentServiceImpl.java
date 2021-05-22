package com.foxminded.university.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Student;
import com.foxminded.university.spring.dao.StudentDao;
import com.foxminded.university.spring.dao.exception.DaoException;
import com.foxminded.university.spring.service.StudentService;
import com.foxminded.university.spring.service.exception.ServiceException;

@Component
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;

	@Override
	public Student getById(int id) throws ServiceException {
		try {
			return studentDao.getById(id);
		} catch (DaoException e) {
			e.getStackTrace();
			throw new ServiceException("can not get student by this id");
		}
	}

	@Override
	public List<Student> getAll() throws ServiceException {
		try {
			return studentDao.getAll();
		} catch (DaoException e) {
			throw new ServiceException("can not get all students");
		}
	}

	@Override
	public boolean delete(int id) throws ServiceException {
		try {
			return studentDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("can not delete student by this id");
		}
	}

	@Override
	public boolean update(Student student) throws ServiceException {
		try {
			return studentDao.update(student);
		} catch (DaoException e) {
			throw new ServiceException("can not update this student");
		}
	}

	@Override
	public boolean create(Student student) throws ServiceException {
		try {
			return studentDao.create(student);
		} catch (DaoException e) {
			throw new ServiceException("can not create student");
		}

	}

	@Override
	public boolean addStudentToLecture(Optional<Student> student, Optional<Lecture> lecture) throws ServiceException {
		try {
			return studentDao.addStudentToLecture(student, lecture);
		} catch (DaoException e) {
			throw new ServiceException("can not add stident to lecture");
		}
	}

	@Override
	public boolean removeStudentFromLecture(Optional<Lecture> lecture) throws ServiceException {
		try {
			return studentDao.removeStudentFromLecture(lecture);
		} catch (DaoException e) {
			throw new ServiceException("can not remove student from lecture");
		}
	}

	@Override
	public boolean addStudentToGroup(Optional<Student> student, Optional<Group> group) throws ServiceException {
		try {
			return studentDao.addStudentToGroup(student, group);
		} catch (DaoException e) {
			throw new ServiceException("can not add student to group");
		}
	}

	@Override
	public boolean removeStudentFromGroup(Optional<Student> student) throws ServiceException {
		try {
			return studentDao.removeStudentFromGroup(student);
		} catch (DaoException e) {
			throw new ServiceException("can not remove student from group");
		}
	}
}
