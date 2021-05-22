package com.foxminded.university.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.LectureDao;
import com.foxminded.university.spring.dao.exception.DaoException;
import com.foxminded.university.spring.service.LectureService;
import com.foxminded.university.spring.service.exception.ServiceException;

@Component
public class LectureServiceImpl implements LectureService {
	@Autowired
	private LectureDao lectureDao;

	@Override
	public Lecture getById(int id) throws ServiceException {
		try {
			return lectureDao.getById(id);
		} catch (DaoException e) {
			e.getStackTrace();
			throw new ServiceException("can not get lecture by this id");
		}
	}

	@Override
	public List<Lecture> getAll() throws ServiceException {
		try {
			return lectureDao.getAll();
		} catch (DaoException e) {
			throw new ServiceException("can not get all lectures");
		}
	}

	@Override
	public boolean delete(int id) throws ServiceException {
		try {
			return lectureDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("can not delete lecture by this id");
		}
	}

	@Override
	public boolean update(Lecture lecture) throws ServiceException {
		try {
			return lectureDao.update(lecture);
		} catch (DaoException e) {
			throw new ServiceException("can not update this lecture");
		}
	}

	@Override
	public boolean create(Lecture lecture) throws ServiceException {
		try {
			return lectureDao.create(lecture);
		} catch (DaoException e) {
			throw new ServiceException("can not create lecture");
		}

	}
}
