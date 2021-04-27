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
import com.foxminded.university.spring.config.UniversityConfiguration;
import com.foxminded.university.spring.service.CourseService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UniversityConfiguration.class })
public class CourseServiceTest {
	@Autowired
	private CourseService courseService;

	@Autowired
	CourseServiceTest(CourseService courseService) {
		this.courseService = courseService;
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Get_Course_By_Id_Then_True() throws Exception {
		Optional<Course> expectedCourse = Optional.ofNullable(new Course(1, "Math"));
		Optional<Course> actualCourse = Optional.ofNullable(new Course());
		actualCourse = courseService.getById(1);
		assertEquals(expectedCourse, actualCourse);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Course_By_Id_Not_Exixst_Then_Get_Empty() throws Exception {
		Throwable thrown = assertThrows(Exception.class, () -> {
			courseService.getById(15);
		});
		assertNotNull(thrown.getMessage().equals("In DB no entity with this id"));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_GetAll_Course_Then_True() throws Exception {
		Course course1 = new Course(1, "Math");
		Course course2 = new Course(2, "English");
		Course course3 = new Course(3, "Biology");

		Optional<List<Course>> expectedCourses = Optional.ofNullable(new LinkedList<Course>());
		Optional<List<Course>> actualCourses = Optional.ofNullable(new LinkedList<Course>());
		expectedCourses.get().add(course1);
		expectedCourses.get().add(course2);
		expectedCourses.get().add(course3);
		actualCourses = courseService.getAll();
		assertEquals(expectedCourses, actualCourses);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Course_By_Id_Then_True() throws Exception {
		assertTrue(courseService.delete(3));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Course_By_Not_Existing_Id_Then_Exception() throws Exception {
		Throwable thrown = assertThrows(Exception.class, () -> {
			courseService.delete(33);
		});
		assertNotNull(thrown.getMessage().equals("In DB no entity with this id"));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Update_Course_Then_True() throws Exception {
		Course course = new Course(1, "Biology");
		assertTrue(courseService.update(course));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Update_Not_Existing_Course_Then_Exception() throws Exception {
		Course course = new Course(1, "Biology");
		assertTrue(courseService.update(course));
	}

}
