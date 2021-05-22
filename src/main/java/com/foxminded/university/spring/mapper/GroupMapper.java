package com.foxminded.university.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Group;

@Component
public class GroupMapper implements RowMapper<Group> {
    private CourseMapper courseMapper;

    @Autowired
    public GroupMapper(DataSource dataSource, CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public Group mapRow(ResultSet resultSet, int i) throws SQLException {
        Group group = new Group();
        group.setId(resultSet.getInt("group_id"));
        group.setCourse(courseMapper.mapRow(resultSet, i));
        group.setName(resultSet.getString("group_name"));
        return group;
    }
}