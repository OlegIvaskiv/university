package com.foxminded.university.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Date;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.service.exception.ServiceException;

@Component
public interface DateService {

	Date getById(int id) throws Exception;

	List<Date> getAll() throws Exception;

	boolean delete(int id) throws Exception;

	boolean update(Date t) throws Exception;

	boolean create(Date t) throws ServiceException;

	boolean addDateToLecture(Optional<Date> date, Optional<Lecture> lecture) throws Exception;

	boolean removeDateFromLecture(Optional<Lecture> lecture) throws Exception;
}
