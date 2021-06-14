package com.foxminded.university.spring.service;

import java.util.List;

import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.service.exception.ServiceException;

public interface LectureService {
	Lecture getById(int id) throws ServiceException;

	List<Lecture> getAll() throws ServiceException;

	boolean delete(int id) throws ServiceException;

	boolean update(Lecture t) throws ServiceException;

	boolean create(Lecture t) throws ServiceException;
}
