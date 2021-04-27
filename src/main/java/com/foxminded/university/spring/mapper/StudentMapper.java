package com.foxminded.university.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Group;
import com.foxminded.university.model.Student;
import com.foxminded.university.spring.dao.GenericDao;

@Component
public class StudentMapper implements RowMapper<Student> {
	private GenericDao<Group> groupGenericDao;

	@Autowired
	StudentMapper(GenericDao<Group> groupGenericDao) {
		this.groupGenericDao = groupGenericDao;
	}

	public Student mapRow(ResultSet resultSet, int i) throws SQLException {
		Student student = new Student();
		student.setId(resultSet.getInt("id"));
		student.setGroup(groupGenericDao.getById(resultSet.getInt("group_id")).orElse(null));
		student.setName(resultSet.getString("name"));
		student.setPhone(resultSet.getString("phone"));
		student.setEmail(resultSet.getString("email"));
		student.setAdress(resultSet.getString("adress"));
		return student;
	}
}