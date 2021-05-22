package com.foxminded.university.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.AudienceDao;
import com.foxminded.university.spring.dao.exception.DaoException;
import com.foxminded.university.spring.service.AudienceService;
import com.foxminded.university.spring.service.exception.ServiceException;

@Component
public class AudienceServiceImpl implements AudienceService {
	@Autowired
	private AudienceDao audienceDao;

	@Override
	public Audience getById(int id) throws ServiceException {
		try {
			return audienceDao.getById(id);
		} catch (DaoException e) {
			throw new ServiceException("can not get audience by this id");
		}
	}

	@Override
	public List<Audience> getAll() throws ServiceException {
		try {
			return audienceDao.getAll();
		} catch (DaoException e) {
			throw new ServiceException("can not get all audiences");
		}
	}

	@Override
	public boolean delete(int id) throws ServiceException {
		try {
			return audienceDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("can not delete audience by this id");
		}
	}

	@Override
	public boolean update(Audience audience) throws ServiceException {
		try {
			return audienceDao.update(audience);
		} catch (DaoException e) {
			throw new ServiceException("can not update this audience");
		}
	}

	@Override
	public boolean create(Audience audience) throws ServiceException {
		try {
			return audienceDao.create(audience);
		} catch (DaoException e) {
			throw new ServiceException("can not create audience");
		}
	}

	@Override
	public boolean addAudienceToLecture(Optional<Audience> audience, Optional<Lecture> lecture)
			throws ServiceException {
		try {
			return audienceDao.addAudienceToLecture(audience, lecture);
		} catch (DaoException e) {
			throw new ServiceException("can not add audience to lecture");
		}
	}

	@Override
	public boolean removeAudiecnceFromLecture(Optional<Lecture> lecture) throws ServiceException {
		try {
			return audienceDao.removeAudiecnceFromLecture(lecture);
		} catch (DaoException e) {
			throw new ServiceException("can not remove audience from lecture");
		}
	}
}
