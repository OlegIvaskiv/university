package com.foxminded.university.test.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;
import com.foxminded.university.spring.config.UniversityConfiguration;
import com.foxminded.university.spring.service.GroupService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UniversityConfiguration.class })
public class GroupServiceTest {
	@Autowired
	private GroupService groupService;

	@Autowired
	GroupServiceTest(GroupService groupService) {
		this.groupService = groupService;
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Get_Group_By_Id_Then_True() throws Exception {
		Course course = new Course(1, "Math");
		Optional<Group> expectedGroup = Optional.ofNullable(new Group(1, course, "BI-21"));
		Optional<Group> actualGroup = Optional.ofNullable(new Group());
		actualGroup = groupService.getById(1);
		assertEquals(expectedGroup, actualGroup);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Group_By_Id_Not_Exixst_Then_Get_Empty() throws Exception {
		Throwable thrown = assertThrows(Exception.class, () -> {
			groupService.getById(15);
		});
		assertNotNull(thrown.getMessage().equals("In DB no entity with this id"));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data2.sql" })
	void When_GetAll_Groups_Then_True() {
		Course course1 = new Course(1, "Math");
		Group group1 = new Group(1, course1, "BI-21");

		Course course2 = new Course(2, "English");
		Group group2 = new Group(2, course2, "KN-21");

		Course course3 = new Course(3, "Biology");
		Group group3 = new Group(3, course3, "IK-21");

		Optional<List<Group>> expectedGroups = Optional.ofNullable(new LinkedList<Group>());
		Optional<List<Group>> actualGroups = Optional.ofNullable(new LinkedList<Group>());
		expectedGroups.get().add(group1);
		expectedGroups.get().add(group2);
		expectedGroups.get().add(group3);

		actualGroups = groupService.getAll();
		assertEquals(expectedGroups, actualGroups);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Update_Group_Then_True() throws Exception {
		Course course = new Course(1, "Math");
		Group group = new Group(1, course, "TR-21");
		assertTrue(groupService.update(group));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Update_Not_Existing_Group_Then_Exception() {
		Course course = new Course(1, "Math");
		Group group = new Group(89, course, "TR-21");
		Throwable thrown = assertThrows(Exception.class, () -> {
			groupService.update(group);
		});
		assertNotNull(thrown.getMessage().equals("In DB no entity with this id"));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Group_By_Id_Then_True() throws Exception {
		assertTrue(groupService.delete(3));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Group_By_Not_Existing_Id_Then_Exception() throws Exception {
		assertTrue(groupService.delete(3));
	}

}
