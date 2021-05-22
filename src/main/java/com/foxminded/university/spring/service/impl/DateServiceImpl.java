package com.foxminded.university.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Date;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.DateDao;
import com.foxminded.university.spring.dao.exception.DaoException;
import com.foxminded.university.spring.service.DateService;
import com.foxminded.university.spring.service.exception.ServiceException;

@Component
public class DateServiceImpl implements DateService {
	@Autowired
	private DateDao dateDao;

	@Override
	public Date getById(int id) throws ServiceException {
		try {
			return dateDao.getById(id);
		} catch (DaoException e) {
			throw new ServiceException("can not get date by this id");
		}
	}

	@Override
	public List<Date> getAll() throws ServiceException {
		try {
			return dateDao.getAll();
		} catch (DaoException e) {
			throw new ServiceException("can not get all datas");
		}
	}

	@Override
	public boolean delete(int id) throws ServiceException {
		try {
			return dateDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("can not delete data by this id");
		}
	}

	@Override
	public boolean update(Date date) throws ServiceException {
		try {
			return dateDao.update(date);
		} catch (DaoException e) {
			throw new ServiceException("can not update this data");
		}

	}

	@Override
	public boolean create(Date date) throws ServiceException {
		try {
			return dateDao.create(date);
		} catch (DaoException e) {
			throw new ServiceException("can not create student");
		}

	}

	@Override
	public boolean addDateToLecture(Optional<Date> date, Optional<Lecture> lecture) throws ServiceException {
		try {
			return dateDao.addDateToLecture(date, lecture);
		} catch (DaoException e) {
			throw new ServiceException("can not add data to lecture");
		}
	}

	@Override
	public boolean removeDateFromLecture(Optional<Lecture> lecture) throws ServiceException {
		try {
			return dateDao.removeDateFromLecture(lecture);
		} catch (DaoException e) {
			throw new ServiceException("can not remove data from lecture");
		}
	}
}
