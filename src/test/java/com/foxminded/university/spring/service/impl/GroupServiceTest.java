package com.foxminded.university.spring.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;
import com.foxminded.university.spring.dao.impl.GroupDaoImpl;
import com.foxminded.university.spring.service.exception.ServiceException;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

	@Mock
	private GroupDaoImpl groupDaoimpl;

	@InjectMocks
	GroupServiceImpl groupServiceImpl;

	public GroupServiceTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void When_Get_Group_By_Id_Then_True() {
		Course course = new Course(1, "Math");
		assertDoesNotThrow(() -> {
			Mockito.lenient().when(groupServiceImpl.getById(1)).thenReturn(new Group(1, course, "BI-21"));
		});
	}

	@Test
	void When_Group_By_Id_Not_Exixst_Then_Get_Empty() {
		assertThrows(Exception.class, () -> {
			when(groupServiceImpl.getById(13)).thenThrow(new ServiceException("can not get group by this id"));
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

		List<Group> expectedGroups = new LinkedList<Group>();
		expectedGroups.add(group1);
		expectedGroups.add(group2);
		expectedGroups.add(group3);

		assertDoesNotThrow(() -> {
			Mockito.lenient().when(groupServiceImpl.getAll()).thenReturn(expectedGroups);
		});

	}

	@Test
	void When_Update_Group_Then_True() {
		Course course = new Course(1, "Math");
		Group group = new Group(1, course, "TR-21");
		assertDoesNotThrow(() -> {
			Mockito.lenient().when(groupServiceImpl.update(group)).thenReturn(true);
		});
	}

	@Test
	void When_Update_Not_Existing_Group_Then_Exception() {
		Course course = new Course(1, "Math");
		Group group = new Group(1, course, "TR-21");
		assertThrows(Exception.class, () -> {
			when(groupServiceImpl.update(group)).thenThrow(new ServiceException("can not update this group"));
		});
	}

	@Test
	void When_Delete_Group_By_Id_Then_True() {
		assertDoesNotThrow(() -> {
			Mockito.lenient().when(groupServiceImpl.delete(3)).thenReturn(true);
		});
	}

	@Test
	void When_Delete_Group_By_Not_Existing_Id_Then_Exception() {
		assertThrows(Exception.class, () -> {
			when(groupServiceImpl.delete(3)).thenThrow(new ServiceException("can not delete group by this id"));
		});
	}

	@Test
	void When_Remove_Group_From_Course_Then_True() {
		Optional<Group> group = Optional.of(new Group(1, null, "TR-21"));

		assertDoesNotThrow(() -> {
			Mockito.lenient().when(groupServiceImpl.removeGroupFromCourse(group)).thenReturn(true);
		});
	}

	@Test
	void When_Add_Group_To_Course_Then_True() {
		Course course = new Course(null);
		Group group = new Group(null, null);

		assertDoesNotThrow(() -> {
			Mockito.lenient().when(groupServiceImpl.addGroupToCourse(group, course)).thenReturn(true);
		});
	}

	@Test
	void When_Remove_Group_From_Not_Existing_Course_Then_Exception() {
		Optional<Group> group = Optional.of(new Group(1, null, "TR-21"));
		assertThrows(Exception.class, () -> {
			when(groupServiceImpl.removeGroupFromCourse(group))
					.thenThrow(new ServiceException("can not remove group from course"));
		});
	}

	@Test
	void When_Add_Group_To_Not_Existing_Course_Then_True() {
		Course course = new Course(null);
		Group group = new Group(null, null);
		assertThrows(Exception.class, () -> {
			when(groupServiceImpl.addGroupToCourse(group, course))
					.thenThrow(new ServiceException("can not add group to course"));
		});
	}

}
