package com.foxminded.university.spring.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;
import com.foxminded.university.spring.dao.GroupDao;
import com.foxminded.university.spring.mapper.GroupMapper;

@Component
public class GroupDAOimpl implements GroupDao {
	private static final Logger LOGGER = LogManager.getLogger(GroupDAOimpl.class);
	private JdbcTemplate jdbcTemplate;
	private GroupMapper groupMapper;
	private static final String SQL_FIND_GROUP = "SELECT * FROM \"group\" WHERE id = ?;";
	private static final String SQL_DELETE_GROUP = "DELETE FROM \"group\" WHERE id = ?;";
	private static final String SQL_UPDATE_GROUP = "UPDATE \"group\" SET course_id = ?, name = ? WHERE id = ?;";
	private static final String SQL_GET_ALL = "SELECT * FROM \"group\";";
	private static final String SQL_INSERT_GROUP = "INSERT INTO \"group\"(id, course_id, name) VALUES(?,?,?);";
	private static final String SQL_ADD_GROUP_TO_COURSE = "UPDATE \"group\" SET course_id = ? WHERE id = ?;";
	private static final String SQL_REMOVE_FEMOVE_FROME_COURSE = "UPDATE \"group\" SET course_id = NULL WHERE id = ?;";

	@Autowired
	public GroupDAOimpl(DataSource dataSource, GroupMapper groupMapper) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		this.groupMapper = groupMapper;
	}

	@Override
	public Optional<Group> getById(int id) {
		try {
			LOGGER.debug("getting group by id");
			return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_GROUP, new Object[] { id }, groupMapper));
		} catch (EmptyResultDataAccessException exception) {
			LOGGER.debug("getting group by id");
			return Optional.empty();
		}

	}

	@Override
	public Optional<List<Group>> getAll() {
		try {
			LOGGER.debug("getting all groups");
			return Optional.ofNullable(jdbcTemplate.query(SQL_GET_ALL, groupMapper));
		} catch (EmptyResultDataAccessException exception) {
			LOGGER.debug("getting group by id");
			return Optional.empty();
		}

	}

	@Override
	public boolean delete(int id) {
		LOGGER.debug("deletion group");
		return jdbcTemplate.update(SQL_DELETE_GROUP, id) > 0;
	}

	@Override
	public boolean update(Group group) {
		LOGGER.debug("updating group");
		return jdbcTemplate.update(SQL_UPDATE_GROUP, group.getCourse().getId(), group.getName(), group.getId()) > 0;
	}

	@Override
	public boolean create(Group group) {

		LOGGER.debug("creating group");
		return jdbcTemplate.update(SQL_INSERT_GROUP, group.getId(), group.getCourse().getId(), group.getName()) > 0;
	}

	@Override
	public boolean addGroupToCourse(Optional<Group> group, Optional<Course> course) {
		LOGGER.debug("adding group to course");
		return jdbcTemplate.update(SQL_ADD_GROUP_TO_COURSE, course.get().getId(), group.get().getId()) > 0;
	}

	@Override
	public boolean removeGroupFromCourse(Optional<Group> group) {
		LOGGER.debug("removing group from course");
		return jdbcTemplate.update(SQL_REMOVE_FEMOVE_FROME_COURSE, group.get().getId()) > 0;
	}
}