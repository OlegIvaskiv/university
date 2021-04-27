package com.foxminded.university.spring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Lecture;

@Component
public interface AudienceDao extends GenericDao<Audience> {

	Optional<Audience> getById(int id);

	Optional<List<Audience>> getAll();

	boolean delete(int id);

	boolean update(Audience t);

	boolean create(Audience t);

	boolean addAudienceToLecture(Optional<Audience> audience, Optional<Lecture> lecture);

	boolean removeAudiecnceFromLecture(Lecture lecture);
}
