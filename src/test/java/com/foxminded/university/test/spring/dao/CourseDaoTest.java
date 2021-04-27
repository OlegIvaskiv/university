package com.foxminded.university.test.spring.dao;

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

import com.foxminded.university.model.Course;
import com.foxminded.university.spring.config.UniversityConfiguration;
import com.foxminded.university.spring.dao.CourseDao;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UniversityConfiguration.class })
class CourseDaoTest {
	private CourseDao courseDao;

	@Autowired
	CourseDaoTest(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Get_Course_By_Id_Then_True() {
		Optional<Course> expectedCourse = Optional.ofNullable(new Course(1, "Math"));
		Optional<Course> actualCourse = Optional.ofNullable(new Course());
		actualCourse = courseDao.getById(1);
		assertEquals(expectedCourse, actualCourse);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Course_By_Id_Then_True() {
		assertTrue(courseDao.delete(3));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Create_Course_Then_True() {
		Course course = new Course(4, "Math");
		assertTrue(courseDao.create(course));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_GetAll_Course_Then_True() {
		Course course1 = new Course(1, "Math");
		Course course2 = new Course(2, "English");
		Course course3 = new Course(3, "Biology");

		Optional<List<Course>> expectedCourses = Optional.ofNullable(new LinkedList<Course>());
		Optional<List<Course>> actualCourses = Optional.ofNullable(new LinkedList<Course>());
		expectedCourses.get().add(course1);
		expectedCourses.get().add(course2);
		expectedCourses.get().add(course3);
		actualCourses = courseDao.getAll();
		assertEquals(expectedCourses, actualCourses);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Update_Course_Then_True() {
		Course course = new Course(1, "Biology");
		assertTrue(courseDao.update(course));
	}

}
