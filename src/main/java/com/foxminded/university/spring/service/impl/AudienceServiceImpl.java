package com.foxminded.university.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.AudienceDao;
import com.foxminded.university.spring.service.AudienceService;

@Component
public class AudienceServiceImpl implements AudienceService {

	@Autowired
	private AudienceDao audienceDao;

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
		if (lecture.get().getAudience().equals(null)) {
			if (!audienceDao.getById(audience.get().getId()).isEmpty()) {
				return audienceDao.addAudienceToLecture(audience, lecture);
			} else {
				throw new Exception("In DB no entity with this id");
			}
		} else {
			throw new Exception("The lecture has already has the audience");
		}
	}

	@Override
	public boolean removeAudiecnceFromLecture(Optional<Lecture> lecture) throws Exception {
		if (!lecture.get().getAudience().equals(null)) {
			return audienceDao.removeAudiecnceFromLecture(lecture);
		} else {
			throw new Exception("The lecture has already has the audience");
		}
	}
}
