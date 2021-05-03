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
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Student;
import com.foxminded.university.spring.dao.impl.StudentDAOimpl;
import com.foxminded.university.spring.service.impl.StudentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
	@Mock
	private StudentDAOimpl studentDaoImpl;

	@InjectMocks
	StudentServiceImpl studentServiceImpl;

	StudentServiceTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void When_Get_Student_By_Id_Then_True() {
		Course course = new Course(1, "Math");
		Group group = new Group(1, course, "BI-21");
		assertThrows(Exception.class, () -> {
			given(studentServiceImpl.getById(1)).willReturn(Optional
					.ofNullable(new Student(1, group, "Oleh Ivaskiv", "666666666", "OLEH@gmail.com", "London")));
		});
	}

	@Test
	void When_Student_By_Id_Not_Exixst_Then_Exception() {
		assertThrows(Exception.class, () -> {
			when(studentServiceImpl.getById(15)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_Get_All_Students_Then_True() {
		Course course1 = new Course(1, "Math");
		Group group1 = new Group(1, course1, "BI-21");
		Student student1 = new Student(1, group1, "Oleh Ivaskiv", "666666666", "OLEH@gmail.com", "London");

		Course course2 = new Course(2, "English");
		Group group2 = new Group(2, course2, "KN-21");
		Student student2 = new Student(2, group2, "Alex", "2222222", "Alex@gmail.com", "London");

		Course course3 = new Course(3, "Biology");
		Group group3 = new Group(3, course3, "IK-21");
		Student student3 = new Student(3, group3, "Max", "0001110001", "Max@gmail.com", "London");

		Optional<List<Student>> expectedStudents = Optional.ofNullable(new LinkedList<Student>());
		expectedStudents.get().add(student1);
		expectedStudents.get().add(student2);
		expectedStudents.get().add(student3);

		assertThrows(Exception.class, () -> {
			given(studentServiceImpl.getAll()).willReturn(expectedStudents);
		});

	}

	@Test
	void When_Delete_Student_By_Id_Then_True() {
		assertThrows(Exception.class, () -> {
			given(studentServiceImpl.delete(3)).willReturn(true);
		});
	}

	@Test
	void When_Delete_Not_Existing_Student_Then_Exception() {
		assertThrows(Exception.class, () -> {
			when(studentServiceImpl.delete(16)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_Update_Student_Then_True() {
		Course course = new Course(1, "Math");
		Group group = new Group(1, course, "BI-21");
		Student student = new Student(1, group, "******", "8984745", "OLEH@gmail.com", "Kyiv");
		assertThrows(Exception.class, () -> {
			given(studentServiceImpl.update(student)).willReturn(true);
		});
	}

	@Test
	void When_Update_NotExisting_Student_Then_Exception() {
		Course course = new Course(1, "Math");
		Group group = new Group(1, course, "BI-21");
		Student student = new Student(18, group, "******", "8984745", "OLEH@gmail.com", "Kyiv");
		assertThrows(Exception.class, () -> {
			when(studentServiceImpl.update(student)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_Add_Student_To_Group_Then_True() {
		Optional<Student> student = Optional.of(new Student(1, null, null, null, null, null));
		Optional<Group> group = Optional.of(new Group(1, null, null));
		assertThrows(Exception.class, () -> {
			given(studentServiceImpl.addStudentToGroup(student, group)).willReturn(true);
		});
	}

	@Test
	void When_Remove_Student_From_Group_Then_True() {
		Optional<Student> student = Optional.of(new Student(1, null, null, null, null, null));
		assertThrows(Exception.class, () -> {
			given(studentServiceImpl.removeStudentFromGroup(student)).willReturn(true);
		});
	}

	@Test
	void When_Remove_Student_From_Lecture_Then_True() {
		Optional<Lecture> lecture = Optional.of(new Lecture(1, null, null, null, null));
		assertThrows(Exception.class, () -> {
			given(studentServiceImpl.removeStudentFromLecture(lecture)).willReturn(true);
		});
	}

	@Test
	void When_Add_Student_To_Lecture_Then_True() {
		Optional<Student> student = Optional.of(new Student(1, null, null, null, null, null));
		Optional<Lecture> lecture = Optional.of(new Lecture(1, null, null, null, null));
		assertThrows(Exception.class, () -> {
			given(studentServiceImpl.addStudentToLecture(student, lecture)).willReturn(true);
		});
	}

	@Test
	void When_Add_Not_Existing_Student_Or_To_Not_Existing_Group_Then_Exception() {
		Optional<Student> student = Optional.of(new Student(1, null, null, null, null, null));
		Optional<Group> group = Optional.of(new Group(1, null, null));
		assertThrows(Exception.class, () -> {
			when(studentServiceImpl.addStudentToGroup(student, group))
					.thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_Remove_Not_Existing_Student_Or_From_Not_Existing_Group_Then_Exception() {
		Optional<Student> student = Optional.of(new Student(1, null, null, null, null, null));
		assertThrows(Exception.class, () -> {
			when(studentServiceImpl.removeStudentFromGroup(student))
					.thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_Remove_Not_Existing_Student_Or_From_Not_Existing_Lecture_Then_Exception() {
		Optional<Lecture> lecture = Optional.of(new Lecture(1, null, null, null, null));
		assertThrows(Exception.class, () -> {
			when(studentServiceImpl.removeStudentFromLecture(lecture))
					.thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_Add_Not_Existing_Student_Or_To_Not_Existing_Lecture_Then_Exception() {
		Optional<Student> student = Optional.of(new Student(1, null, null, null, null, null));
		Optional<Lecture> lecture = Optional.of(new Lecture(1, null, null, null, null));
		assertThrows(Exception.class, () -> {
			when(studentServiceImpl.addStudentToLecture(student, lecture))
					.thenThrow(new Exception("In DB no entity with this id"));
		});
	}
}
