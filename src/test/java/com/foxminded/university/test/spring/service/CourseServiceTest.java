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
import org.springframework.test.context.jdbc.Sql;

import com.foxminded.university.model.Course;
import com.foxminded.university.spring.dao.impl.CourseDAOimpl;
import com.foxminded.university.spring.service.impl.CourseServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
	@Mock
	private CourseDAOimpl courseServiceDaoimpl;

	@InjectMocks
	CourseServiceImpl courseServiceImpl;

	CourseServiceTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test

	void When_Get_Course_By_Id_Then_True() {
		assertThrows(Exception.class, () -> {
			given(courseServiceImpl.getById(1)).willReturn(Optional.ofNullable(new Course(1, "Math")));
		});

	}

	@Test
	void When_Course_By_Id_Not_Exixst_Then_Get_Empty() {
		assertThrows(Exception.class, () -> {
			when(courseServiceImpl.getById(1)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_GetAll_Course_Then_True() {
		Course course1 = new Course(1, "Math");
		Course course2 = new Course(2, "English");
		Course course3 = new Course(3, "Biology");

		Optional<List<Course>> expectedCourses = Optional.ofNullable(new LinkedList<Course>());
		expectedCourses.get().add(course1);
		expectedCourses.get().add(course2);
		expectedCourses.get().add(course3);

		assertThrows(Exception.class, () -> {
			given(courseServiceImpl.getAll()).willReturn(expectedCourses);
		});
	}

	@Test
	void When_Delete_Course_By_Id_Then_True() {
		assertThrows(Exception.class, () -> {
			given(courseServiceImpl.delete(3)).willReturn(true);
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Course_By_Not_Existing_Id_Then_Exception() {
		assertThrows(Exception.class, () -> {
			when(courseServiceImpl.delete(16)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_Update_Course_Then_True() throws Exception {
		Course course = new Course(1, "Biology");
		assertThrows(Exception.class, () -> {
			given(courseServiceImpl.update(course)).willReturn(true);
		});
	}

	@Test
	void When_Update_Not_Existing_Course_Then_Exception() throws Exception {
		Course course = new Course(1, "Biology");
		assertThrows(Exception.class, () -> {
			when(courseServiceImpl.update(course)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

}
