package com.foxminded.university.spring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.exception.DaoException;

@Component
public interface AudienceDao extends GenericDao<Audience> {

	Audience getById(int id) throws DaoException;

	List<Audience> getAll() throws DaoException;

	boolean delete(int id) throws DaoException;

	boolean update(Audience t) throws DaoException;

	boolean create(Audience t) throws DaoException;

	boolean addAudienceToLecture(Optional<Audience> audience, Optional<Lecture> lecture) throws DaoException;

	boolean removeAudiecnceFromLecture(Optional<Lecture> lecture) throws DaoException;
}
