package com.foxminded.university.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.service.exception.ServiceException;

@Component
public interface TeacherService {
    Teacher getById(int id) throws Exception;

    List<Teacher> getAll() throws Exception;

    boolean delete(int id) throws Exception;

    boolean update(Teacher t) throws Exception;

    boolean create(Teacher t) throws ServiceException;

    boolean removeTeacherFromLecture(Optional<Lecture> lecture) throws Exception;

    boolean addTeacherToLecture(Optional<Teacher> teacher, Optional<Lecture> lecture) throws Exception;
}
