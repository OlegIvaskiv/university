package com.foxminded.university.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Teacher;

@Component
public interface TeacherService {
	Optional<Teacher> getById(int id) throws Exception;

	Optional<List<Teacher>> getAll();

	boolean delete(int id) throws Exception;

	boolean update(Teacher t) throws Exception;

	boolean create(Teacher t);

	boolean addTeacherToLecture(Teacher teacher, Lecture lecture);

	boolean removeTeacherFromLecture(Lecture lecture);

	boolean addTeacherToGroup(Teacher teacher, Group group);

	boolean removeTeacherFromGroup(Teacher teacher);
}
