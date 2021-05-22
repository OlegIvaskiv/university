package com.foxminded.university.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Student;

@Component
public class StudentMapper implements RowMapper<Student> {
    private GroupMapper groupMapper;

    @Autowired
    public StudentMapper(DataSource dataSource, GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("student_id"));
        student.setGroup(groupMapper.mapRow(resultSet, i));
        student.setName(resultSet.getString("student_name"));
        student.setPhone(resultSet.getString("student_phone"));
        student.setEmail(resultSet.getString("student_email"));
        student.setAdress(resultSet.getString("student_adress"));
        return student;
    }
}