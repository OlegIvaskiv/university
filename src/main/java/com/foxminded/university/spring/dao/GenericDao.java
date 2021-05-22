package com.foxminded.university.spring.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxminded.university.spring.dao.exception.DaoException;

@Component
public interface GenericDao<T> {
	T getById(int id) throws DaoException;

	List<T> getAll() throws DaoException;

	boolean delete(int id) throws DaoException;

	boolean update(T t) throws DaoException;

	boolean create(T t) throws DaoException;
}