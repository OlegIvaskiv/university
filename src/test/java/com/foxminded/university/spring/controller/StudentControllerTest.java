package com.foxminded.university.spring.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;
import com.foxminded.university.model.Student;
import com.foxminded.university.spring.config.WebConfig;
import com.foxminded.university.spring.dao.impl.CourseDaoImpl;
import com.foxminded.university.spring.dao.impl.GroupDaoImpl;
import com.foxminded.university.spring.dao.impl.StudentDaoImpl;
import com.foxminded.university.spring.service.impl.CourseServiceImpl;
import com.foxminded.university.spring.service.impl.GroupServiceImpl;
import com.foxminded.university.spring.service.impl.StudentServiceImpl;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class StudentControllerTest {
	private MockMvc mockMvc;
	@Autowired
	WebApplicationContext web;

	@Mock
	private StudentDaoImpl studentDaoimpl;
	@Mock
	private GroupDaoImpl groupDaoimpl;
	@Mock
	private CourseDaoImpl courseDaoimpl;

	@Mock
	StudentServiceImpl studentServiceImpl;

	@Mock
	GroupServiceImpl groupServiceImpl;
	@Mock
	CourseServiceImpl courseServiceImpl;

	@InjectMocks
	StudentController studentController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
	}

	@Test
	void When_Get_All_Then_Get_Form() throws Exception {
		mockMvc.perform(get("/students")).andExpect(status().isOk()).andExpect(view().name("student/list"));
	}

	@Test
	void When_Create_Student_Then_Get_Form() throws Exception {
		mockMvc.perform(get("/students/create")).andExpect(status().isOk()).andExpect(view().name("student/create"));
	}

	@Test
	void when_Get_All_Then_Show_All_Audience() throws Exception {
		Course course1 = new Course(1, "Math");
		Group group1 = new Group(1, course1, "BI-21");
		Student student1 = new Student(1, group1, "Oleh Ivaskiv", "666666666", "OLEH@gmail.com", "London");

		Course course2 = new Course(2, "English");
		Group group2 = new Group(2, course2, "KN-21");
		Student student2 = new Student(2, group2, "Alex", "2222222", "Alex@gmail.com", "London");

		Course course3 = new Course(0, null);
		Group group3 = new Group(0, course3, null);
		Student student3 = new Student(3, group3, "Max", "0001110001", "Max@gmail.com", "London");

		List<Student> students = new LinkedList<Student>();
		students.add(student1);
		students.add(student2);
		students.add(student3);

		when(studentServiceImpl.getAll()).thenReturn(students);

		mockMvc.perform(get("/students")).andExpect(status().isOk()).andExpect(view().name("student/list"))
				.andExpect(model().attribute("students", hasSize(3)));
		verify(studentServiceImpl).getAll();
	}

}
