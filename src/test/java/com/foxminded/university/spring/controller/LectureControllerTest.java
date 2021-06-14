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

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Course;
import com.foxminded.university.model.Date;
import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Student;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.config.WebConfig;
import com.foxminded.university.spring.dao.impl.AudienceDaoImpl;
import com.foxminded.university.spring.dao.impl.CourseDaoImpl;
import com.foxminded.university.spring.dao.impl.DateDaoImpl;
import com.foxminded.university.spring.dao.impl.GroupDaoImpl;
import com.foxminded.university.spring.dao.impl.LectureDaoImpl;
import com.foxminded.university.spring.dao.impl.StudentDaoImpl;
import com.foxminded.university.spring.dao.impl.TeacherDaoImpl;
import com.foxminded.university.spring.service.impl.AudienceServiceImpl;
import com.foxminded.university.spring.service.impl.CourseServiceImpl;
import com.foxminded.university.spring.service.impl.DateServiceImpl;
import com.foxminded.university.spring.service.impl.GroupServiceImpl;
import com.foxminded.university.spring.service.impl.LectureServiceImpl;
import com.foxminded.university.spring.service.impl.StudentServiceImpl;
import com.foxminded.university.spring.service.impl.TeacherServiceImpl;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class LectureControllerTest {
	private MockMvc mockMvc;
	@Autowired
	WebApplicationContext web;

	@Mock
	private LectureDaoImpl lecturesDaoimpl;
	@Mock
	private StudentDaoImpl studentDaoimpl;
	@Mock
	private TeacherDaoImpl teacherDaoimpl;
	@Mock
	private GroupDaoImpl groupDaoimpl;
	@Mock
	private CourseDaoImpl courseDaoimpl;
	@Mock
	private AudienceDaoImpl audienceDaoimpl;
	@Mock
	private DateDaoImpl dateDaoimpl;

	@Mock
	LectureServiceImpl lectureServiceImpl;
	@Mock
	StudentServiceImpl studentServiceImpl;
	@Mock
	TeacherServiceImpl teacherServiceImpl;
	@Mock
	GroupServiceImpl groupServiceImpl;
	@Mock
	CourseServiceImpl courseServiceImpl;
	@Mock
	AudienceServiceImpl audienceServiceImpl;
	@Mock
	DateServiceImpl dateServiceImpl;

	@InjectMocks
	LectureController lectureController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(lectureController).build();
	}

	@Test
	void When_Get_All_Then_Get_Form() throws Exception {
		mockMvc.perform(get("/lectures")).andExpect(status().isOk()).andExpect(view().name("lecture/list"));
	}

	@Test
	void When_Create_Lecture_Then_Get_Form() throws Exception {
		mockMvc.perform(get("/lectures/create")).andExpect(status().isOk()).andExpect(view().name("lecture/create"));
	}

	@Test
	void When_Get_All_Then_Show_All_Lectures() throws Exception {
		Course course1 = new Course("Math");
		Audience audience1 = new Audience(246);
		Date date1 = new Date("30", "03", "2021");
		Group group1 = new Group(course1, "BI-21");
		Student student1 = new Student(group1, "Oleh Ivaskiv", "666666666", "OLEH@gmail.com", "London");
		Teacher teacher1 = new Teacher("Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley");
		Lecture lecture1 = new Lecture(teacher1, student1, audience1, date1);

		Course course2 = new Course("English");
		Audience audience2 = new Audience(201);
		Date date2 = new Date("30", "03", "2021");
		Group group2 = new Group(course2, "KN-21");
		Student student2 = new Student(group2, "Alex", "2222222", "Alex@gmail.com", "London");
		Teacher teacher2 = new Teacher("Tom Cruise", "1111112121", "Tom@Cruise.com", "New Yourk");
		Lecture lecture2 = new Lecture(teacher2, student2, audience2, date2);

		Course course3 = new Course("Biology");
		Audience audience3 = new Audience(146);
		Date date3 = new Date("30", "03", "2021");
		Group group3 = new Group(course3, "IK-21");
		Student student3 = new Student(group3, "Max", "0001110001", "Max@gmail.com", "London");
		Teacher teacher3 = new Teacher("Gipocrat", "9999944554", "MuskSpaceX.com", "Warsaw");
		Lecture lecture3 = new Lecture(teacher3, student3, audience3, date3);
		List<Lecture> lectures = new LinkedList<Lecture>();
		lectures.add(lecture1);
		lectures.add(lecture2);
		lectures.add(lecture3);

		when(lectureServiceImpl.getAll()).thenReturn(lectures);

		mockMvc.perform(get("/lectures")).andExpect(status().isOk()).andExpect(view().name("lecture/list"))
				.andExpect(model().attribute("lectures", hasSize(3)));
		verify(lectureServiceImpl).getAll();
	}
}
