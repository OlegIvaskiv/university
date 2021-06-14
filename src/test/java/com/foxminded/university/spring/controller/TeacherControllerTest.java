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

import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.config.WebConfig;
import com.foxminded.university.spring.dao.impl.TeacherDaoImpl;
import com.foxminded.university.spring.service.impl.TeacherServiceImpl;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class TeacherControllerTest {
	private MockMvc mockMvc;
	@Autowired
	WebApplicationContext web;

	@Mock
	private TeacherDaoImpl teacherDaoimpl;

	@Mock
	TeacherServiceImpl teacherServiceImpl;
	@InjectMocks
	TeacherController teacherController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(teacherController).build();
	}

	@Test
	void When_Get_All_Then_Get_Form() throws Exception {
		mockMvc.perform(get("/teachers")).andExpect(status().isOk()).andExpect(view().name("teacher/list"));
	}

	@Test
	void When_Create_Teacher_Then_Get_Form() throws Exception {
		mockMvc.perform(get("/teachers/create")).andExpect(status().isOk()).andExpect(view().name("teacher/create"));
	}

	@Test
	void When_Get_All_Then_Show_All_Teacher() throws Exception {
		Teacher teacher1 = new Teacher(1, "Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley");

		Teacher teacher2 = new Teacher(2, "Tom Cruise", "1111112121", "Tom@Cruise.com", "New Yourk");

		Teacher teacher3 = new Teacher(3, "Gipocrat", "9999944554", "MuskSpaceX.com", "Warsaw");

		List<Teacher> teachers = new LinkedList<Teacher>();
		teachers.add(teacher1);
		teachers.add(teacher2);
		teachers.add(teacher3);

		when(teacherServiceImpl.getAll()).thenReturn(teachers);

		mockMvc.perform(get("/teachers")).andExpect(status().isOk()).andExpect(view().name("teacher/list"))
				.andExpect(model().attribute("teachers", hasSize(3)));
		verify(teacherServiceImpl).getAll();
	}
}
