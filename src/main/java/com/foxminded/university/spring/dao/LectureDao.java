package com.foxminded.university.spring.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.exception.DaoException;

@Component
public interface LectureDao extends GenericDao<Lecture> {
	Lecture getById(int id) throws DaoException;

	List<Lecture> getAll() throws DaoException;

	boolean delete(int id) throws DaoException;

	boolean update(Lecture t) throws DaoException;

	boolean create(Lecture t) throws DaoException;
}
