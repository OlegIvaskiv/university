package com.foxminded.university.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Date;
import com.foxminded.university.model.Lecture;

@Component
public interface DateService {

	Optional<Date> getById(int id) throws Exception;

	Optional<List<Date>> getAll();

	boolean delete(int id) throws Exception;

	boolean update(Date t) throws Exception;

	boolean create(Date t);

	boolean addDateToLecture(Optional<Date> date, Optional<Lecture> lecture);

	boolean removeDateFromLecture(Optional<Lecture> lecture);
}
