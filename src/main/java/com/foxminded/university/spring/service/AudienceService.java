package com.foxminded.university.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Lecture;

@Component
public interface AudienceService {

	Optional<Audience> getById(int id) throws Exception;

	Optional<List<Audience>> getAll() throws Exception;

	boolean delete(int id) throws Exception;

	boolean update(Audience t) throws Exception;

	boolean create(Audience t);

	boolean addAudienceToLecture(Optional<Audience> audience, Optional<Lecture> lecture) throws Exception;

	boolean removeAudiecnceFromLecture(Lecture lecture);

}
