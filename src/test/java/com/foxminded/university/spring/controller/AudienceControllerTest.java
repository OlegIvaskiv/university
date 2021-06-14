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

import com.foxminded.university.model.Audience;
import com.foxminded.university.spring.config.WebConfig;
import com.foxminded.university.spring.dao.impl.AudienceDaoImpl;
import com.foxminded.university.spring.service.impl.AudienceServiceImpl;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class AudienceControllerTest {
	private MockMvc mockMvc;
	@Autowired
	WebApplicationContext web;

	@Mock
	private AudienceDaoImpl audienceDaoimpl;

	@Mock
	AudienceServiceImpl audienceServiceImpl;
	@InjectMocks
	AudienceController audienceController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(audienceController).build();
	}

	@Test
	void When_Get_All_Then_Get_Form() throws Exception {
		mockMvc.perform(get("/audiences")).andExpect(status().isOk()).andExpect(view().name("audience/list"));
	}

	@Test
	void When_Create_Audience_Then_Get_Form() throws Exception {
		mockMvc.perform(get("/audiences/create")).andExpect(status().isOk()).andExpect(view().name("audience/create"));
	}

	@Test
	void When_Get_All_Then_Show_All_Audience() throws Exception {
		List<Audience> audiences = new ArrayList<>();
		audiences.add(new Audience(246));
		audiences.add(new Audience(201));
		audiences.add(new Audience(146));
		when(audienceServiceImpl.getAll()).thenReturn(audiences);

		mockMvc.perform(get("/audiences")).andExpect(status().isOk()).andExpect(view().name("audience/list"))
				.andExpect(model().attribute("audiences", hasSize(3)));
		verify(audienceServiceImpl).getAll();
	}

}
