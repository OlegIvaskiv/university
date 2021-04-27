package com.foxminded.university.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.impl.AudienceDAOimpl;
import com.foxminded.university.spring.dao.impl.LectureDAOimpl;
import com.foxminded.university.spring.service.AudienceService;

@Component
public class AudienceServiceImpl implements AudienceService {

	private AudienceDAOimpl audienceDao;

	private LectureDAOimpl lectureDao;

	private static final Logger LOGGER = LogManager.getLogger(AudienceServiceImpl.class);

	public AudienceServiceImpl(AudienceDAOimpl audienceDao, LectureDAOimpl lectureDao) {
		this.audienceDao = audienceDao;
		this.lectureDao = lectureDao;
	}

	@Override
	public Optional<Audience> getById(int id) throws Exception {
		if (!audienceDao.getById(id).isEmpty()) {
			return audienceDao.getById(id);
		} else {
			throw new Exception("In DB no entity with this id");
		}
	}

	@Override
	public Optional<List<Audience>> getAll() throws Exception {
		if (!audienceDao.getAll().isEmpty()) {
			return audienceDao.getAll();
		} else {
			throw new Exception("In DB no entity with this id");
		}
	}

	@Override
	public boolean delete(int id) throws Exception {
		if (!audienceDao.getById(id).isEmpty()) {
			return audienceDao.delete(id);
		} else {
			throw new Exception("In DB no entity with this id");
		}
	}

	@Override
	public boolean update(Audience audience) throws Exception {
		if (!audienceDao.getById(audience.getId()).isEmpty()) {
			return audienceDao.update(audience);
		} else {
			throw new Exception("In DB no entity with this id");
		}

	}

	@Override
	public boolean create(Audience audience) {
		return audienceDao.create(audience);
	}

	@Override
	public boolean addAudienceToLecture(Optional<Audience> audience, Optional<Lecture> lecture) throws Exception {
		if (lecture.get().getAudience() == null) {
			if (lectureDao.getById(lecture.get().getId()).equals(lecture)) {
				return audienceDao.addAudienceToLecture(audience, lecture);
			} else {
				LOGGER.error("The object of lecture differs from lecture in DB");
				return false;
			}
		} else {
			LOGGER.error("The lecture have already related with audience, update lecture");
			return false;
		}
	}

	@Override
	public boolean removeAudiecnceFromLecture(Lecture lecture) {
		return audienceDao.removeAudiecnceFromLecture(lecture);
	}

}
