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

	Optional<List<Teacher>> getAll() throws Exception;

	boolean delete(int id) throws Exception;

	boolean update(Teacher t) throws Exception;

	boolean create(Teacher t);

	boolean removeTeacherFromLecture(Optional<Lecture> lecture) throws Exception;

	boolean addTeacherToGroup(Optional<Teacher> teacher, Optional<Group> group) throws Exception;

	boolean removeTeacherFromGroup(Optional<Teacher> teacher) throws Exception;

	boolean addTeacherToLecture(Optional<Teacher> teacher, Optional<Lecture> lecture) throws Exception;
}
