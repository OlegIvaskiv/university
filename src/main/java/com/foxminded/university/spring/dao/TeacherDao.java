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

	boolean addTeacherToLecture(Teacher teacher, Lecture lecture);

	boolean removeTeacherFromLecture(Lecture lecture);

	boolean addTeacherToGroup(Teacher teacher, Group group);

	boolean removeTeacherFromGroup(Teacher teacher);
}
