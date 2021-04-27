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
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.config.UniversityConfiguration;
import com.foxminded.university.spring.service.TeacherService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UniversityConfiguration.class })
public class TeacherServiceTest {
	@Autowired
	private TeacherService teacherService;

	@Autowired
	TeacherServiceTest(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Get_Teacher_By_Id_Then_True() throws Exception {
		Course course = new Course(1, "Math");
		Group group = new Group(1, course, "BI-21");
		Optional<Teacher> expectedTeacher = Optional
				.ofNullable(new Teacher(1, group, "Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley"));
		Optional<Teacher> actualTeacher = Optional.ofNullable(new Teacher());
		actualTeacher = teacherService.getById(1);
		assertEquals(expectedTeacher, actualTeacher);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Student_By_Id_Not_Exixst_Then_Get_Empty() throws Exception {
		Throwable thrown = assertThrows(Exception.class, () -> {
			teacherService.getById(15);
		});
		assertNotNull(thrown.getMessage().equals("In DB no entity with this id"));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data2.sql" })
	void When_Get_All_Teachers_Then_True() {
		Course course1 = new Course(1, "Math");
		Group group1 = new Group(1, course1, "BI-21");
		Teacher teacher1 = new Teacher(1, group1, "Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley");

		Course course2 = new Course(2, "English");
		Group group2 = new Group(2, course2, "KN-21");
		Teacher teacher2 = new Teacher(2, group2, "Tom Cruise", "1111112121", "Tom@Cruise.com", "New Yourk");

		Course course3 = new Course(3, "Biology");
		Group group3 = new Group(3, course3, "IK-21");
		Teacher teacher3 = new Teacher(3, group3, "Gipocrat", "9999944554", "MuskSpaceX.com", "Warsaw");

		Optional<List<Teacher>> expectedTeachers = Optional.ofNullable(new LinkedList<Teacher>());
		Optional<List<Teacher>> actualLectures = Optional.ofNullable(new LinkedList<Teacher>());
		expectedTeachers.get().add(teacher1);
		expectedTeachers.get().add(teacher2);
		expectedTeachers.get().add(teacher3);

		actualLectures = teacherService.getAll();
		assertEquals(expectedTeachers, actualLectures);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Teacher_By_Id_Then_True() throws Exception {
		assertTrue(teacherService.delete(3));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Not_Existing_Teacher_Then_Exception() throws Exception {
		Throwable thrown = assertThrows(Exception.class, () -> {
			teacherService.delete(43);
		});
		assertNotNull(thrown.getMessage().equals("In DB no entity with this id"));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Update_Teacher_Then_True() throws Exception {
		Course course = new Course(1, "Math");
		Group group = new Group(1, course, "BI-21");
		Teacher teacher = new Teacher(1, group, "7846", "22101", "MuskSpaceX.com", "Tokio");
		assertTrue(teacherService.update(teacher));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Update_Not_Existing_Teacher_Then_Exception() throws Exception {
		Course course = new Course(1, "Math");
		Group group = new Group(1, course, "BI-21");
		Teacher teacher = new Teacher(1, group, "7846", "22101", "MuskSpaceX.com", "Tokio");
		assertTrue(teacherService.update(teacher));
	}
}
