package com.foxminded.university.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.LectureDao;
import com.foxminded.university.spring.service.LectureService;

@Component
public class LectureServiceImpl implements LectureService {
	@Autowired
	private LectureDao lectureDao;

	@Override
	public Optional<Lecture> getById(int id) throws Exception {
		if (!lectureDao.getById(id).isEmpty()) {
			return lectureDao.getById(id);
		} else {
			throw new Exception("In DB no entity with this id");
		}
	}

	@Override
	public Optional<List<Lecture>> getAll() {
		return lectureDao.getAll();
	}

	@Override
	public boolean delete(int id) throws Exception {
		if (!lectureDao.getById(id).isEmpty()) {
			return lectureDao.delete(id);
		} else {
			throw new Exception("In DB no entity with this id");
		}
	}

	@Override
	public boolean update(Lecture lecture) throws Exception {
		if (!lectureDao.getById(lecture.getId()).isEmpty()) {
			return lectureDao.update(lecture);
		} else {
			throw new Exception("In DB no entity with this id");
		}
	}

	@Override
	public boolean create(Lecture lecture) {
		return lectureDao.create(lecture);
	}
}
