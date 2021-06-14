package com.foxminded.university.spring.dao.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;
import com.foxminded.university.spring.config.UniversityConfiguration;
import com.foxminded.university.spring.dao.GroupDao;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UniversityConfiguration.class })
@WebAppConfiguration
class GroupDaoTest {
	private GroupDao groupDao;

	@Autowired
	public GroupDaoTest(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Get_Group_By_Id_Then_True() {
		Course course = new Course(1, "Math");
		Group expectedGroup = new Group(1, course, "BI-21");

		assertDoesNotThrow(() -> {
			assertEquals(expectedGroup, groupDao.getById(1));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Group_By_Id_Then_True() {
		assertDoesNotThrow(() -> {
			assertTrue(groupDao.delete(3));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_GetAll_Groups_Then_True() {
		Course course1 = new Course(1, "Math");
		Group group1 = new Group(1, course1, "BI-21");

		Course course2 = new Course(2, "English");
		Group group2 = new Group(2, course2, "KN-21");

		Course course3 = new Course(0, null);
		Group group3 = new Group(3, course3, "IK-21");

		List<Group> expectedGroups = new LinkedList<Group>();
		expectedGroups.add(group1);
		expectedGroups.add(group2);
		expectedGroups.add(group3);

		assertDoesNotThrow(() -> {
			assertEquals(expectedGroups, groupDao.getAll());
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Create_Group_Then_True() {
		Course course = new Course(1, "Math");
		Group group = new Group(course, "BI-21");
		assertDoesNotThrow(() -> {
			assertTrue(groupDao.create(group));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Update_Group_Then_True() {
		Course course = new Course(1, "Math");
		Group group = new Group(1, course, "TR-21");
		assertDoesNotThrow(() -> {
			assertTrue(groupDao.update(group));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Remove_Group_From_Course_Then_True() {
		Optional<Group> group = Optional.of(new Group(2, null, "TR-21"));
		assertDoesNotThrow(() -> {
			assertTrue(groupDao.removeGroupFromCourse(group));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Add_Group_To_Course_Then_True() {
		Course course = new Course(1, null);
		Group group = new Group(2, null, null);
		assertDoesNotThrow(() -> {
			assertTrue(groupDao.addGroupToCourse(group, course));
		});
	}

}
