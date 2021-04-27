package com.foxminded.university.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Date;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.DateDao;
import com.foxminded.university.spring.service.DateService;

@Component
public class DateServiceImpl implements DateService {
	@Autowired
	private DateDao dateDao;

	@Override
	public Optional<Date> getById(int id) throws Exception {
		if (!dateDao.getById(id).isEmpty()) {
			return dateDao.getById(id);
		} else {
			throw new Exception("In DB no entity with this id");
		}
	}

	@Override
	public Optional<List<Date>> getAll() {
		return dateDao.getAll();
	}

	@Override
	public boolean delete(int id) throws Exception {
		if (!dateDao.getById(id).isEmpty()) {
			return dateDao.delete(id);
		} else {
			throw new Exception("In DB no entity with this id");
		}
	}

	@Override
	public boolean update(Date date) throws Exception {
		if (!dateDao.getById(date.getId()).isEmpty()) {
			return dateDao.update(date);
		} else {
			throw new Exception("In DB no entity with this id");
		}

	}

	@Override
	public boolean create(Date date) {
		return dateDao.create(date);
	}

	@Override
	public boolean addDateToLecture(Optional<Date> date, Optional<Lecture> lecture) {
		return false;
	}

	@Override
	public boolean removeDateFromLecture(Optional<Lecture> lecture) {

		return false;
	}
}
