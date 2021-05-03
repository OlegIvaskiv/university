package com.foxminded.university.spring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Date;
import com.foxminded.university.model.Lecture;

@Component
public interface DateDao extends GenericDao<Date> {
	Optional<Date> getById(int id);

	Optional<List<Date>> getAll();

	boolean delete(int id);

	boolean update(Date t);

	boolean create(Date t);

	boolean addDateToLecture(Optional<Date> date, Optional<Lecture> lecture);

	boolean removeDateFromLecture(Optional<Lecture> lecture);
}
