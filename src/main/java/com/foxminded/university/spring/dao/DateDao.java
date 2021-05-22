package com.foxminded.university.spring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Date;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.exception.DaoException;

@Component
public interface DateDao extends GenericDao<Date> {
	Date getById(int id) throws DaoException;

	List<Date> getAll() throws DaoException;

	boolean delete(int id) throws DaoException;

	boolean update(Date t) throws DaoException;

	boolean create(Date t) throws DaoException;

	boolean addDateToLecture(Optional<Date> date, Optional<Lecture> lecture) throws DaoException;

	boolean removeDateFromLecture(Optional<Lecture> lecture) throws DaoException;
}
