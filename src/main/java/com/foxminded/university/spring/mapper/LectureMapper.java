package com.foxminded.university.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Date;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Student;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.dao.GenericDao;

@Component
public class LectureMapper implements RowMapper<Lecture> {
	private GenericDao<Teacher> teacherGenericDao;
	private GenericDao<Student> studentGenericDao;
	private GenericDao<Audience> audienceGenericDao;
	private GenericDao<Date> dateGenericDao;

	@Autowired
	LectureMapper(GenericDao<Teacher> teacherGenericDao, GenericDao<Student> studentGenericDao,
			GenericDao<Audience> audienceGenericDao, GenericDao<Date> dateGenericDao) {
		this.audienceGenericDao = audienceGenericDao;
		this.dateGenericDao = dateGenericDao;
		this.studentGenericDao = studentGenericDao;
		this.teacherGenericDao = teacherGenericDao;
	}

	public Lecture mapRow(ResultSet resultSet, int i) throws SQLException {
		Lecture lecture = new Lecture();
		lecture.setId(resultSet.getInt("id"));
		lecture.setTeacher(teacherGenericDao.getById(resultSet.getInt("teacher_id")).orElse(null));
		lecture.setStudent(studentGenericDao.getById(resultSet.getInt("student_id")).orElse(null));
		lecture.setAudience(audienceGenericDao.getById(resultSet.getInt("audience_id")).orElse(null));
		lecture.setDate(dateGenericDao.getById(resultSet.getInt("date_id")).orElse(null));
		return lecture;
	}

}