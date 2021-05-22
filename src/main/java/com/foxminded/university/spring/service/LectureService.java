package com.foxminded.university.spring.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.service.exception.ServiceException;

@Component
public interface LectureService {
	Lecture getById(int id) throws Exception;

	List<Lecture> getAll() throws Exception;

	boolean delete(int id) throws Exception;

	boolean update(Lecture t) throws Exception;

	boolean create(Lecture t) throws ServiceException;
}
