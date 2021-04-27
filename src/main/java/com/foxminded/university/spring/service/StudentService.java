package com.foxminded.university.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Student;

@Component
public interface StudentService {
	Optional<Student> getById(int id) throws Exception;

	Optional<List<Student>> getAll();

	boolean delete(int id) throws Exception;

	boolean update(Student t) throws Exception;

	boolean create(Student t);

	boolean addStudentToLecture(Student student, Lecture lecture);

	boolean removeStudentFromLecture(Lecture lecture);

	boolean addStudentToGroup(Student student, Group group);

	boolean removeStudentFromGroup(Student student);
}
