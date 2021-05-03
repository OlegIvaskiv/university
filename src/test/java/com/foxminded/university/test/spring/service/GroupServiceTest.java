package com.foxminded.university.test.spring.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;
import com.foxminded.university.spring.dao.impl.GroupDAOimpl;
import com.foxminded.university.spring.service.impl.GroupServiceImpl;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

	@Mock
	private GroupDAOimpl groupDaoimpl;

	@InjectMocks
	GroupServiceImpl groupServiceImpl;

	GroupServiceTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void When_Get_Group_By_Id_Then_True() {
		Course course = new Course(1, "Math");
		assertThrows(Exception.class, () -> {
			given(groupServiceImpl.getById(1)).willReturn(Optional.ofNullable(new Group(1, course, "BI-21")));
		});
	}

	@Test
	void When_Group_By_Id_Not_Exixst_Then_Get_Empty() {
		assertThrows(Exception.class, () -> {
			when(groupServiceImpl.getById(13)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_GetAll_Groups_Then_True() {
		Course course1 = new Course(1, "Math");
		Group group1 = new Group(1, course1, "BI-21");

		Course course2 = new Course(2, "English");
		Group group2 = new Group(2, course2, "KN-21");

		Course course3 = new Course(3, "Biology");
		Group group3 = new Group(3, course3, "IK-21");

		Optional<List<Group>> expectedGroups = Optional.ofNullable(new LinkedList<Group>());
		expectedGroups.get().add(group1);
		expectedGroups.get().add(group2);
		expectedGroups.get().add(group3);

		assertThrows(Exception.class, () -> {
			given(groupServiceImpl.getAll()).willReturn(expectedGroups);
		});

	}

	@Test
	void When_Update_Group_Then_True() {
		Course course = new Course(1, "Math");
		Group group = new Group(1, course, "TR-21");
		assertThrows(Exception.class, () -> {
			given(groupServiceImpl.update(group)).willReturn(true);
		});
	}

	@Test
	void When_Update_Not_Existing_Group_Then_Exception() {
		Course course = new Course(1, "Math");
		Group group = new Group(89, course, "TR-21");
		assertThrows(Exception.class, () -> {
			when(groupServiceImpl.update(group)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_Delete_Group_By_Id_Then_True() {
		assertThrows(Exception.class, () -> {
			given(groupServiceImpl.delete(3)).willReturn(true);
		});
	}

	@Test
	void When_Delete_Group_By_Not_Existing_Id_Then_Exception() {
		assertThrows(Exception.class, () -> {
			when(groupServiceImpl.delete(3)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_Remove_Group_From_Course_Then_True() {
		Optional<Group> group = Optional.of(new Group(1, null, "TR-21"));
		assertThrows(Exception.class, () -> {
			given(groupServiceImpl.removeGroupFromCourse(group)).willReturn(true);
		});
	}

	@Test
	void When_Add_Group_To_Course_Then_True() {
		Optional<Course> course = Optional.of(new Course(1, null));
		Optional<Group> group = Optional.of(new Group(2, null, null));
		assertThrows(Exception.class, () -> {
			given(groupServiceImpl.addGroupToCourse(group, course)).willReturn(true);
		});
	}

	@Test
	void When_Remove_Group_From_Not_Existing_Course_Then_Exception() {
		Optional<Group> group = Optional.of(new Group(1, null, "TR-21"));
		assertThrows(Exception.class, () -> {
			when(groupServiceImpl.removeGroupFromCourse(group))
					.thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_Add_Group_To_Not_Existing_Course_Then_True() {
		Optional<Course> course = Optional.of(new Course(1, null));
		Optional<Group> group = Optional.of(new Group(2, null, null));
		assertThrows(Exception.class, () -> {
			when(groupServiceImpl.addGroupToCourse(group, course))
					.thenThrow(new Exception("In DB no entity with this id"));
		});
	}

}
