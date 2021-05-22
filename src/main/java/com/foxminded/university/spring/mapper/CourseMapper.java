package com.foxminded.university.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Course;

@Component
public class CourseMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet resultSet, int i) throws SQLException {
        Course course = new Course();
        course.setId(resultSet.getInt("course_id"));
        course.setName(resultSet.getString("course_name"));
        return course;
    }
}