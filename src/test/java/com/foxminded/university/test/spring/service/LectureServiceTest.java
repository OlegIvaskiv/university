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

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Course;
import com.foxminded.university.model.Date;
import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Student;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.dao.impl.LectureDAOimpl;
import com.foxminded.university.spring.service.impl.LectureServiceImpl;

@ExtendWith(MockitoExtension.class)
public class LectureServiceTest {
	@Mock
	private LectureDAOimpl lectureDaoimpl;

	@InjectMocks
	LectureServiceImpl lectureServiceImpl;

	LectureServiceTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void When_Get_Lecture_By_Id_Then_True() {
		Course course = new Course(1, "Math");
		Audience audience = new Audience(1, 246);
		Date date = new Date(1, "30", "03", "2021");
		Group group = new Group(1, course, "BI-21");
		Student student = new Student(1, group, "Oleh Ivaskiv", "666666666", "OLEH@gmail.com", "London");
		Teacher teacher = new Teacher(1, group, "Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley");
		assertThrows(Exception.class, () -> {
			given(lectureServiceImpl.getById(1))
					.willReturn(Optional.ofNullable(new Lecture(1, teacher, student, audience, date)));
		});
	}

	@Test
	void When_Audience_By_Id_Not_Exixst_Then_Get_Exception() throws Exception {
		assertThrows(Exception.class, () -> {
			when(lectureServiceImpl.getById(13)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_Get_All_Lectures_Then_True() {
		Course course1 = new Course(1, "Math");
		Audience audience1 = new Audience(1, 246);
		Date date1 = new Date(1, "30", "03", "2021");
		Group group1 = new Group(1, course1, "BI-21");
		Student student1 = new Student(1, group1, "Oleh Ivaskiv", "666666666", "OLEH@gmail.com", "London");
		Teacher teacher1 = new Teacher(1, group1, "Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley");
		Lecture lecture1 = new Lecture(1, teacher1, student1, audience1, date1);

		Course course2 = new Course(2, "English");
		Audience audience2 = new Audience(2, 201);
		Date date2 = new Date(2, "30", "03", "2021");
		Group group2 = new Group(2, course2, "KN-21");
		Student student2 = new Student(2, group2, "Alex", "2222222", "Alex@gmail.com", "London");
		Teacher teacher2 = new Teacher(2, group2, "Tom Cruise", "1111112121", "Tom@Cruise.com", "New Yourk");
		Lecture lecture2 = new Lecture(2, teacher2, student2, audience2, date2);

		Course course3 = new Course(3, "Biology");
		Audience audience3 = new Audience(3, 146);
		Date date3 = new Date(3, "30", "03", "2021");
		Group group3 = new Group(3, course3, "IK-21");
		Student student3 = new Student(3, group3, "Max", "0001110001", "Max@gmail.com", "London");
		Teacher teacher3 = new Teacher(3, group3, "Gipocrat", "9999944554", "MuskSpaceX.com", "Warsaw");
		Lecture lecture3 = new Lecture(3, teacher3, student3, audience3, date3);
		Optional<List<Lecture>> expectedLectures = Optional.ofNullable(new LinkedList<Lecture>());
		expectedLectures.get().add(lecture1);
		expectedLectures.get().add(lecture2);
		expectedLectures.get().add(lecture3);
		assertThrows(Exception.class, () -> {
			given(lectureServiceImpl.getAll()).willReturn(expectedLectures);
		});

	}

	@Test
	void When_Delete_Lecture_By_Id_Then_True() {
		assertThrows(Exception.class, () -> {
			given(lectureServiceImpl.delete(3)).willReturn(true);
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Not_Existing_Lecture_Then_Exception() throws Exception {
		assertThrows(Exception.class, () -> {
			when(lectureServiceImpl.delete(16)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_Update_Lecture_Then_True() {
		Course course = new Course(1, "Math");
		Audience audience = new Audience(1, 246);
		Date date = new Date(1, "30", "03", "2021");
		Group group = new Group(1, course, "BI-21");
		Student student = new Student(1, group, "******", "898489545", "OLEH@gmail.com", "Kyiv");
		Teacher teacher = new Teacher(1, group, "*****", "0101010101", "MuskSpaceX.com", "Tokio");
		Lecture lecture = new Lecture(2, teacher, student, audience, date);
		assertThrows(Exception.class, () -> {
			given(lectureServiceImpl.update(lecture)).willReturn(true);
		});
	}

	@Test
	void When_Update_Not_Existing_Lecture_Then_Exception() {
		Course course = new Course(1, "Math");
		Audience audience = new Audience(1, 246);
		Date date = new Date(1, "30", "03", "2021");
		Group group = new Group(1, course, "BI-21");
		Student student = new Student(1, group, "******", "898489545", "OLEH@gmail.com", "Kyiv");
		Teacher teacher = new Teacher(1, group, "*****", "0101010101", "MuskSpaceX.com", "Tokio");
		Lecture lecture = new Lecture(26, teacher, student, audience, date);

		assertThrows(Exception.class, () -> {
			when(lectureServiceImpl.update(lecture)).thenThrow(new Exception("In DB no entity with this id"));
		});

	}

}
