package com.foxminded.university.spring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public interface GenericDao<T> {
	Optional<T> getById(int id);

	Optional<List<T>> getAll();

	boolean delete(int id);

	boolean update(T t);

	boolean create(T t);
}