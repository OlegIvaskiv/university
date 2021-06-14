package com.foxminded.university.spring.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;
import com.foxminded.university.spring.dao.GroupDao;
import com.foxminded.university.spring.dao.exception.DaoException;
import com.foxminded.university.spring.mapper.GroupMapper;

@Component
public class GroupDaoImpl implements GroupDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(GroupDaoImpl.class);
	private JdbcTemplate jdbcTemplate;
	private GroupMapper groupMapper;
	private static final String SQL_FIND_GROUP = "SELECT \"group\".group_id, course.course_id, course.course_name, \"group\".group_name FROM "
			+ "\"group\" LEFT JOIN course ON course.course_id = \"group\".course_id WHERE \"group\".group_id = ?;";

	private static final String SQL_GET_ALL = "SELECT \"group\".group_id, course.course_id, course.course_name, \"group\".group_name FROM "
			+ "\"group\" LEFT JOIN course ON course.course_id = \"group\".course_id ;";

	private static final String SQL_DELETE_GROUP = "DELETE FROM \"group\" WHERE group_id = ?;";
	private static final String SQL_UPDATE_GROUP = "UPDATE \"group\" SET course_id = ?, group_name = ? WHERE group_id = ?;";
	private static final String SQL_INSERT_GROUP = "INSERT INTO \"group\"(course_id, group_name) VALUES(?,?);";
	private static final String SQL_ADD_GROUP_TO_COURSE = "UPDATE \"group\" SET course_id = ? WHERE group_id = ?;";
	private static final String SQL_REMOVE_FEMOVE_FROME_COURSE = "UPDATE \"group\" SET course_id = NULL WHERE group_id = ?;";

	@Autowired
	public GroupDaoImpl(DataSource dataSource, GroupMapper groupMapper) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		this.groupMapper = groupMapper;
	}

	@Override
	public Group getById(int id) throws DaoException {
		LOGGER.debug("getting group by id : {}", id);
		try {
			Group group = jdbcTemplate.queryForObject(SQL_FIND_GROUP, new Object[] { id }, groupMapper);
			return group;
		} catch (DataAccessException e) {
			throw new DaoException("wrong id");
		}
	}

	@Override
	public List<Group> getAll() throws DaoException {
		LOGGER.debug("getting all groups");
		List<Group> groups = Collections.emptyList();
		try {
			groups = jdbcTemplate.query(SQL_GET_ALL, groupMapper);
			LOGGER.debug("returned groups : {}", groups);
			return groups;
		} catch (DataAccessException e) {
			throw new DaoException("wrong id");
		}
	}

	@Override
	public boolean delete(int id) throws DaoException {
		LOGGER.debug("deletion date by id : {}", id);
		try {
			return jdbcTemplate.update(SQL_DELETE_GROUP, id) > 0;
		} catch (DataAccessException e) {
			throw new DaoException("can not delete group by id");
		}
	}

	@Override
	public boolean update(Group group) throws DaoException {
		LOGGER.debug("updating group : {}", group);
		try {
			return jdbcTemplate.update(SQL_UPDATE_GROUP, group.getCourse().getId(), group.getName(), group.getId()) > 0;
		} catch (DataAccessException e) {
			throw new DaoException("group is not updated");
		}
	}

	@Override
	public boolean create(Group group) throws DaoException {
		LOGGER.debug("creating group : {}", group);
		try {
			return jdbcTemplate.update(SQL_INSERT_GROUP, group.getCourse().getId(), group.getName()) > 0;
		} catch (DataAccessException e) {
			throw new DaoException("group is not created");
		}
	}

	@Override
	public boolean addGroupToCourse(Group group, Course course) throws DaoException {
		LOGGER.debug("adding group : {} to course : {}", group, course);
		try {
			return jdbcTemplate.update(SQL_ADD_GROUP_TO_COURSE, course.getId(), group.getId()) > 0;
		} catch (DataAccessException e) {
			throw new DaoException("group is not added");
		}
	}

	@Override
	public boolean removeGroupFromCourse(Optional<Group> group) throws DaoException {
		LOGGER.debug("removing group from lecture : {}", group);
		try {
			return jdbcTemplate.update(SQL_REMOVE_FEMOVE_FROME_COURSE, group.get().getId()) > 0;
		} catch (DataAccessException e) {
			throw new DaoException("group is not aremoved");
		}
	}
}