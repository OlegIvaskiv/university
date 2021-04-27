package com.foxminded.university.spring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Lecture;

@Component
public interface LectureDao extends GenericDao<Lecture> {
	Optional<Lecture> getById(int id);

	Optional<List<Lecture>> getAll();

	boolean delete(int id);

	boolean update(Lecture t);

	boolean create(Lecture t);
}
