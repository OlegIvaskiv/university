package com.foxminded.university.spring.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
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
import com.foxminded.university.spring.config.WebConfig;
import com.foxminded.university.spring.dao.impl.CourseDaoImpl;
import com.foxminded.university.spring.service.impl.CourseServiceImpl;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class CourseControllerTest {
	private MockMvc mockMvc;
	@Autowired
	WebApplicationContext web;

	@Mock
	private CourseDaoImpl courseDaoimpl;

	@Mock
	CourseServiceImpl courseServiceImpl;
	@InjectMocks
	CourseController courseController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
	}

	@Test
	void When_Get_All_Then_Get_Form() throws Exception {
		mockMvc.perform(get("/courses")).andExpect(status().isOk()).andExpect(view().name("course/list"));
	}

	@Test
	void When_Create_Courses_Then_Get_Form() throws Exception {
		mockMvc.perform(get("/courses/create")).andExpect(status().isOk()).andExpect(view().name("course/create"));
	}

	@Test
	void When_Get_All_Then_Show_All_Courses() throws Exception {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Math"));
		courses.add(new Course("English"));

		when(courseServiceImpl.getAll()).thenReturn(courses);

		mockMvc.perform(get("/courses")).andExpect(status().isOk()).andExpect(view().name("course/list"))
				.andExpect(model().attribute("courses", hasSize(2)));
		verify(courseServiceImpl).getAll();
	}

}
