package com.foxminded.university.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Teacher;

@Component
public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet resultSet, int i) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getInt("teacher_id"));
        teacher.setName(resultSet.getString("teacher_name"));
        teacher.setPhone(resultSet.getString("teacher_phone"));
        teacher.setEmail(resultSet.getString("teacher_email"));
        teacher.setAdress(resultSet.getString("teacher_adress"));
        return teacher;
    }
}