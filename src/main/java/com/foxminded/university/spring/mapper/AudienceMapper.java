package com.foxminded.university.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Audience;

@Component
public class AudienceMapper implements RowMapper<Audience> {
    @Override
    public Audience mapRow(ResultSet resultSet, int i) throws SQLException {
        Audience audience = new Audience();
        audience.setId(resultSet.getInt("audience_id"));
        audience.setNumber(resultSet.getInt("audience_number"));
        return audience;
    }
}
