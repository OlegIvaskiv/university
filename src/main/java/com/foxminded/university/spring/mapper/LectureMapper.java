package com.foxminded.university.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Lecture;

@Component
public class LectureMapper implements RowMapper<Lecture> {
    private TeacherMapper teacherMapper;
    private StudentMapper studentMapper;
    private AudienceMapper audienceMapper;
    private DateMapper dateMapper;

    @Autowired
    public LectureMapper(TeacherMapper teacherMapper, StudentMapper studentMapper, AudienceMapper audienceMapper,
            DateMapper dateMapper) {
        this.audienceMapper = audienceMapper;
        this.dateMapper = dateMapper;
        this.studentMapper = studentMapper;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public Lecture mapRow(ResultSet resultSet, int i) throws SQLException {
        Lecture lecture = new Lecture();
        lecture.setId(resultSet.getInt("lecture_id"));
        lecture.setTeacher(teacherMapper.mapRow(resultSet, i));
        lecture.setStudent(studentMapper.mapRow(resultSet, i));
        lecture.setAudience(audienceMapper.mapRow(resultSet, i));
        lecture.setDate(dateMapper.mapRow(resultSet, i));
        return lecture;
    }

}