package com.foxminded.university.spring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Student;

@Component
public interface StudentDao extends GenericDao<Student> {
	Optional<Student> getById(int id);

	Optional<List<Student>> getAll();

	boolean delete(int id);

	boolean update(Student t);

	boolean create(Student t);

	boolean addStudentToLecture(Student student, Lecture lecture);

	boolean removeStudentFromLecture(Lecture lecture);

	boolean addStudentToGroup(Student student, Group group);

	boolean removeStudentFromGroup(Student student);
}
