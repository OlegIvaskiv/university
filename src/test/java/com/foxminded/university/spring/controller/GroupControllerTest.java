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
import com.foxminded.university.spring.config.WebConfig;
import com.foxminded.university.spring.dao.impl.CourseDaoImpl;
import com.foxminded.university.spring.dao.impl.GroupDaoImpl;
import com.foxminded.university.spring.service.impl.CourseServiceImpl;
import com.foxminded.university.spring.service.impl.GroupServiceImpl;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class GroupControllerTest {
	private MockMvc mockMvc;
	@Autowired
	WebApplicationContext web;

	@Mock
	private GroupDaoImpl groupDaoimpl;
	@Mock
	private CourseDaoImpl courseDaoimpl;

	@Mock
	GroupServiceImpl groupServiceImpl;
	@Mock
	CourseServiceImpl courseServiceImpl;
	@InjectMocks
	GroupController groupController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(groupController).build();
	}

	@Test
	void When_Get_All_Then_Get_Form() throws Exception {
		mockMvc.perform(get("/groups")).andExpect(status().isOk()).andExpect(view().name("group/list"));
	}

	@Test
	void When_Create_Group_Then_Get_Form() throws Exception {
		mockMvc.perform(get("/groups/create")).andExpect(status().isOk()).andExpect(view().name("group/create"));
	}

	@Test
	void When_Get_All_Then_Show_All_Groups() throws Exception {
		Course course1 = new Course(1, "Math");
		Group group1 = new Group(1, course1, "BI-21");

		Course course2 = new Course(2, "English");
		Group group2 = new Group(2, course2, "KN-21");

		Course course3 = new Course(3, "Biology");
		Group group3 = new Group(3, course3, "IK-21");

		List<Group> groups = new LinkedList<Group>();
		groups.add(group1);
		groups.add(group2);
		groups.add(group3);

		when(groupServiceImpl.getAll()).thenReturn(groups);

		mockMvc.perform(get("/groups")).andExpect(status().isOk()).andExpect(view().name("group/list"))
				.andExpect(model().attribute("groups", hasSize(3)));
		verify(groupServiceImpl).getAll();
	}
}
