package com.foxminded.university.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;
import com.foxminded.university.spring.dao.GenericDao;

@Component
public class GroupMapper implements RowMapper<Group> {

	private GenericDao<Course> courseGenericDao;

	@Autowired
	GroupMapper(GenericDao<Course> courseGenericDao) {
		this.courseGenericDao = courseGenericDao;
	}

	public Group mapRow(ResultSet resultSet, int i) throws SQLException {
		Group group = new Group();
		group.setId(resultSet.getInt("id"));
		group.setName(resultSet.getString("name"));
		group.setCourse(courseGenericDao.getById(resultSet.getInt("course_id")).orElse(null));
		return group;
	}
}