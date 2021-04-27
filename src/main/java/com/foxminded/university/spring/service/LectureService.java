package com.foxminded.university.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Lecture;

@Component
public interface LectureService {
	Optional<Lecture> getById(int id) throws Exception;

	Optional<List<Lecture>> getAll();

	boolean delete(int id) throws Exception;

	boolean update(Lecture t) throws Exception;

	boolean create(Lecture t);
}
