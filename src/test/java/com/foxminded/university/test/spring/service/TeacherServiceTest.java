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
import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.dao.impl.TeacherDAOimpl;
import com.foxminded.university.spring.service.impl.TeacherServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {
	@Mock
	private TeacherDAOimpl teacherDaoImpl;

	@InjectMocks
	TeacherServiceImpl teacherServiceImpl;

	TeacherServiceTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void When_Get_Teacher_By_Id_Then_True() throws Exception {
		Course course = new Course(1, "Math");
		Group group = new Group(1, course, "BI-21");
		assertThrows(Exception.class, () -> {
			given(teacherServiceImpl.getById(1)).willReturn(Optional
					.ofNullable(new Teacher(1, group, "Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley")));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Student_By_Id_Not_Exixst_Then_Get_Empty() throws Exception {
		assertThrows(Exception.class, () -> {
			when(teacherServiceImpl.getById(13)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
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
		expectedTeachers.get().add(teacher1);
		expectedTeachers.get().add(teacher2);
		expectedTeachers.get().add(teacher3);

		assertThrows(Exception.class, () -> {
			given(teacherServiceImpl.getAll()).willReturn(expectedTeachers);
		});
	}

	@Test
	void When_Delete_Teacher_By_Id_Then_True() throws Exception {
		assertThrows(Exception.class, () -> {
			given(teacherServiceImpl.delete(3)).willReturn(true);
		});
	}

	@Test
	void When_Delete_Not_Existing_Teacher_Then_Exception() throws Exception {
		assertThrows(Exception.class, () -> {
			when(teacherServiceImpl.delete(16)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_Update_Teacher_Then_True() throws Exception {
		Course course = new Course(1, "Math");
		Group group = new Group(1, course, "BI-21");
		Teacher teacher = new Teacher(1, group, "7846", "22101", "MuskSpaceX.com", "Tokio");
		assertThrows(Exception.class, () -> {
			given(teacherServiceImpl.update(teacher)).willReturn(true);
		});
	}

	@Test
	void When_Update_Not_Existing_Teacher_Then_Exception() throws Exception {
		Course course = new Course(1, "Math");
		Group group = new Group(1, course, "BI-21");
		Teacher teacher = new Teacher(1, group, "7846", "22101", "MuskSpaceX.com", "Tokio");
		assertThrows(Exception.class, () -> {
			when(teacherServiceImpl.update(teacher)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_Remove_Teacher_From_Lecture_Then_True() {
		Optional<Lecture> lecture = Optional.of(new Lecture(1, null, null, null, null));
		assertThrows(Exception.class, () -> {
			given(teacherServiceImpl.removeTeacherFromLecture(lecture)).willReturn(true);
		});
	}

	@Test
	void When_Add_Teacher_To_Lecture_Then_True() {
		Optional<Lecture> lecture = Optional.of(new Lecture(1, null, null, null, null));
		Optional<Teacher> teacher = Optional.of(new Teacher(2, null, null, null, null, null));
		assertThrows(Exception.class, () -> {
			given(teacherServiceImpl.addTeacherToLecture(teacher, lecture)).willReturn(true);
		});
	}

	@Test
	void When_Remove_Teacher_From_Group_Then_True() {
		Optional<Teacher> teacher = Optional.of(new Teacher(1, null, null, null, null, null));
		assertThrows(Exception.class, () -> {
			given(teacherServiceImpl.removeTeacherFromGroup(teacher)).willReturn(true);
		});
	}

	@Test
	void When_Add_Teacher_To_Group_Then_True() {
		Optional<Teacher> teacher = Optional.of(new Teacher(1, null, null, null, null, null));
		Optional<Group> group = Optional.of(new Group(1, null, null));
		assertThrows(Exception.class, () -> {
			given(teacherServiceImpl.addTeacherToGroup(teacher, group)).willReturn(true);
		});
	}

	@Test
	void When_Remove_Not_Existing_Teacher_Or_From_Not_Existing_Lecture_Then_Exception() {
		Optional<Lecture> lecture = Optional.of(new Lecture(1, null, null, null, null));
		assertThrows(Exception.class, () -> {
			given(teacherServiceImpl.removeTeacherFromLecture(lecture)).willReturn(true);
		});
	}

	@Test
	void When_Add_Not_Existing_Teacher_Or_To_Not_Existing_Lecture_Then_Exception() {
		Optional<Lecture> lecture = Optional.of(new Lecture(1, null, null, null, null));
		Optional<Teacher> teacher = Optional.of(new Teacher(2, null, null, null, null, null));
		assertThrows(Exception.class, () -> {
			given(teacherServiceImpl.addTeacherToLecture(teacher, lecture)).willReturn(true);
		});
	}

	@Test
	void When_Remove_Not_Existing_Teacher_Or_Not_Existing_From_Group_Then_Exception() {
		Optional<Teacher> teacher = Optional.of(new Teacher(1, null, null, null, null, null));
		assertThrows(Exception.class, () -> {
			given(teacherServiceImpl.removeTeacherFromGroup(teacher)).willReturn(true);
		});
	}

	@Test
	void When_Add_Not_Existing_Teacher_Or_To_Not_Existing_Group_Then_Exception() {
		Optional<Teacher> teacher = Optional.of(new Teacher(1, null, null, null, null, null));
		Optional<Group> group = Optional.of(new Group(1, null, null));
		assertThrows(Exception.class, () -> {
			given(teacherServiceImpl.addTeacherToGroup(teacher, group)).willReturn(true);
		});
	}

}
