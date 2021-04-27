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
import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.config.UniversityConfiguration;
import com.foxminded.university.spring.dao.TeacherDao;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UniversityConfiguration.class })
class TeacherDaoTest {
	private TeacherDao teacherDao;

	@Autowired
	TeacherDaoTest(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Get_Teacher_By_Id_Then_True() {
		Course course = new Course(1, "Math");
		Group group = new Group(1, course, "BI-21");
		Optional<Teacher> expectedTeacher = Optional
				.ofNullable(new Teacher(1, group, "Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley"));
		Optional<Teacher> actualTeacher = Optional.ofNullable(new Teacher());
		actualTeacher = teacherDao.getById(1);
		assertEquals(expectedTeacher, actualTeacher);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Teacher_By_Id_Then_True() {
		assertTrue(teacherDao.delete(3));
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

		actualLectures = teacherDao.getAll();
		assertEquals(expectedTeachers, actualLectures);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Create_Teacher_Then_True() {
		Course course = new Course(1, "Math");
		Group group = new Group(1, course, "BI-21");
		Teacher teacher = new Teacher(4, group, "Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley");
		assertTrue(teacherDao.create(teacher));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Update_Teacher_Then_True() {
		Course course = new Course(1, "Math");
		Group group = new Group(1, course, "BI-21");
		Teacher teacher = new Teacher(1, group, "7846", "22101", "MuskSpaceX.com", "Tokio");
		assertTrue(teacherDao.update(teacher));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Remove_Teacher_From_Lecture_Then_True() {
		Lecture lecture = new Lecture(1, null, null, null, null);
		assertTrue(teacherDao.removeTeacherFromLecture(lecture));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Add_Teacher_To_Lecture_Then_True() {
		Lecture lecture = new Lecture(1, null, null, null, null);
		Teacher teacher = new Teacher(2, null, null, null, null, null);
		assertTrue(teacherDao.addTeacherToLecture(teacher, lecture));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Remove_Teacher_From_Group_Then_True() {
		Teacher teacher = new Teacher(1, null, null, null, null, null);
		assertTrue(teacherDao.removeTeacherFromGroup(teacher));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Add_Teacher_To_Group_Then_True() {
		Teacher teacher = new Teacher(1, null, null, null, null, null);
		Group group = new Group(1, null, null);
		assertTrue(teacherDao.addTeacherToGroup(teacher, group));
	}

}
