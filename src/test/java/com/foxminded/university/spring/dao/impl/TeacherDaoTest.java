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

import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.config.UniversityConfiguration;
import com.foxminded.university.spring.dao.TeacherDao;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UniversityConfiguration.class })
@WebAppConfiguration
class TeacherDaoTest {
	private TeacherDao teacherDao;

	@Autowired
	public TeacherDaoTest(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Get_Teacher_By_Id_Then_True() {
		Teacher expectedTeacher = new Teacher(1, "Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley");

		assertDoesNotThrow(() -> {
			assertEquals(expectedTeacher, teacherDao.getById(1));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Teacher_By_Id_Then_True() {
		assertDoesNotThrow(() -> {
			assertTrue(teacherDao.delete(3));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Get_All_Teachers_Then_True() {

		Teacher teacher1 = new Teacher(1, "Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley");

		Teacher teacher2 = new Teacher(2, "Tom Cruise", "1111112121", "Tom@Cruise.com", "New Yourk");

		Teacher teacher3 = new Teacher(3, "Gipocrat", "9999944554", "MuskSpaceX.com", "Warsaw");

		List<Teacher> expectedTeachers = new LinkedList<Teacher>();
		expectedTeachers.add(teacher1);
		expectedTeachers.add(teacher2);
		expectedTeachers.add(teacher3);

		assertDoesNotThrow(() -> {
			assertEquals(expectedTeachers, teacherDao.getAll());
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Create_Teacher_Then_True() {
		Teacher teacher = new Teacher(8, "Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley");
		assertDoesNotThrow(() -> {
			assertTrue(teacherDao.create(teacher));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Update_Teacher_Then_True() {
		Teacher teacher = new Teacher(1, "7846", "22101", "MuskSpaceX.com", "Tokio");
		assertDoesNotThrow(() -> {
			assertTrue(teacherDao.update(teacher));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Remove_Teacher_From_Lecture_Then_True() {
		Optional<Lecture> lecture = Optional.of(new Lecture(1, null, null, null, null));
		assertDoesNotThrow(() -> {
			assertTrue(teacherDao.removeTeacherFromLecture(lecture));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Add_Teacher_To_Lecture_Then_True() {
		Optional<Lecture> lecture = Optional.of(new Lecture(1, null, null, null, null));
		Optional<Teacher> teacher = Optional.of(new Teacher(1, null, null, null, null));
		assertDoesNotThrow(() -> {
			assertTrue(teacherDao.addTeacherToLecture(teacher, lecture));
		});
	}

}
