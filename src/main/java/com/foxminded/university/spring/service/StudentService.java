package com.foxminded.university.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Student;
import com.foxminded.university.spring.service.exception.ServiceException;

@Component
public interface StudentService {
	Student getById(int id) throws Exception;

	List<Student> getAll() throws Exception;

	boolean delete(int id) throws Exception;

	boolean update(Student t) throws Exception;

	boolean create(Student t) throws ServiceException;

	boolean addStudentToGroup(Optional<Student> student, Optional<Group> group) throws Exception;

	boolean removeStudentFromGroup(Optional<Student> student) throws Exception;

	boolean addStudentToLecture(Optional<Student> student, Optional<Lecture> lecture) throws Exception;

	boolean removeStudentFromLecture(Optional<Lecture> lecture) throws Exception;
}
