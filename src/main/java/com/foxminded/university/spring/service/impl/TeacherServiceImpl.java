package com.foxminded.university.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.dao.TeacherDao;
import com.foxminded.university.spring.dao.exception.DaoException;
import com.foxminded.university.spring.service.TeacherService;
import com.foxminded.university.spring.service.exception.ServiceException;

@Component
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher getById(int id) throws ServiceException {
        try {
            return teacherDao.getById(id);
        } catch (DaoException e) {
            e.getStackTrace();
            throw new ServiceException("can not get teacher by this id");
        }
    }

    @Override
    public List<Teacher> getAll() throws ServiceException {
        try {
            return teacherDao.getAll();
        } catch (DaoException e) {
            e.getStackTrace();
            throw new ServiceException("can not get all teachers");
        }
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        try {
            return teacherDao.delete(id);
        } catch (DaoException e) {
            e.getStackTrace();
            throw new ServiceException("can not delete teacher by this id");
        }
    }

    @Override
    public boolean update(Teacher teacher) throws ServiceException {
        try {
            return teacherDao.update(teacher);
        } catch (DaoException e) {
            e.getStackTrace();
            throw new ServiceException("can not update this teacher");
        }
    }

    @Override
    public boolean create(Teacher teacher) throws ServiceException {
        try {
            return teacherDao.create(teacher);
        } catch (DaoException e) {
            throw new ServiceException("can not create teacher");
        }

    }

    @Override
    public boolean addTeacherToLecture(Optional<Teacher> teacher, Optional<Lecture> lecture) throws ServiceException {
        try {
            return teacherDao.addTeacherToLecture(teacher, lecture);
        } catch (DaoException e) {
            throw new ServiceException("can not add teacher to lecture");
        }
    }

    @Override
    public boolean removeTeacherFromLecture(Optional<Lecture> lecture) throws ServiceException {
        try {
            return teacherDao.removeTeacherFromLecture(lecture);
        } catch (DaoException e) {
            throw new ServiceException("can not remove teacher from lecture");
        }
    }

}
