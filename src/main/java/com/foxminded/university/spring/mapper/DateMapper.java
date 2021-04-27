package com.foxminded.university.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Date;
@Component
public class DateMapper implements RowMapper<Date> {

	public Date mapRow(ResultSet resultSet, int i) throws SQLException {
		Date date = new Date();
		date.setId(resultSet.getInt("id"));
		date.setDay(resultSet.getString("day"));
		date.setMounth(resultSet.getString("mounth"));
		date.setYear(resultSet.getString("year"));
		return date;
	}
}