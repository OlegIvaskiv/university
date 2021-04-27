package com.foxminded.university.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Group;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.dao.GenericDao;

@Component
public class TeacherMapper implements RowMapper<Teacher> {
	private GenericDao<Group> groupGenericDao;

	@Autowired
	TeacherMapper(GenericDao<Group> groupGenericDao) {
		this.groupGenericDao = groupGenericDao;
	}

	public Teacher mapRow(ResultSet resultSet, int i) throws SQLException {
		Teacher teacher = new Teacher();
		teacher.setId(resultSet.getInt("id"));
		teacher.setGroup(groupGenericDao.getById(resultSet.getInt("group_id")).orElse(null));
		teacher.setName(resultSet.getString("name"));
		teacher.setPhone(resultSet.getString("phone"));
		teacher.setEmail(resultSet.getString("email"));
		teacher.setAdress(resultSet.getString("adress"));
		return teacher;
	}
}
