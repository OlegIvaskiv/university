package com.foxminded.university.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.service.exception.ServiceException;

@Component
public interface AudienceService {

	Audience getById(int id) throws ServiceException;

	List<Audience> getAll() throws ServiceException;

	boolean delete(int id) throws ServiceException;

	boolean update(Audience t) throws ServiceException;

	boolean create(Audience t) throws ServiceException;

	boolean addAudienceToLecture(Optional<Audience> audience, Optional<Lecture> lecture) throws ServiceException;

	boolean removeAudiecnceFromLecture(Optional<Lecture> lecture) throws ServiceException;

}
