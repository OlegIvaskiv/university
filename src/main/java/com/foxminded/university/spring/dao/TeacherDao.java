package com.foxminded.university.spring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Teacher;

@Component
public interface TeacherDao extends GenericDao<Teacher> {
	Optional<Teacher> getById(int id);

	Optional<List<Teacher>> getAll();

	boolean delete(int id);

	boolean update(Teacher t);

	boolean create(Teacher t);

	boolean addTeacherToLecture(Optional<Teacher> teacher, Optional<Lecture> lecture);

	boolean removeTeacherFromLecture(Optional<Lecture> lecture);

	boolean addTeacherToGroup(Optional<Teacher> teacher, Optional<Group> group);

	boolean removeTeacherFromGroup(Optional<Teacher> teacher);
}
