package com.foxminded.university.spring.service;

import java.util.List;
import java.util.Optional;

import com.foxminded.university.model.Date;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.service.exception.ServiceException;

public interface DateService {

	Date getById(int id) throws ServiceException;

	List<Date> getAll() throws ServiceException;

	boolean delete(int id) throws ServiceException;

	boolean update(Date t) throws ServiceException;

	boolean create(Date t) throws ServiceException;

	boolean addDateToLecture(Optional<Date> date, Optional<Lecture> lecture) throws ServiceException;

	boolean removeDateFromLecture(Optional<Lecture> lecture) throws ServiceException;
}
