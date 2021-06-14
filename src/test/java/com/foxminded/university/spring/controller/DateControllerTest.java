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

import com.foxminded.university.model.Date;
import com.foxminded.university.spring.config.WebConfig;
import com.foxminded.university.spring.dao.impl.DateDaoImpl;
import com.foxminded.university.spring.service.impl.DateServiceImpl;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class DateControllerTest {
	private MockMvc mockMvc;
	@Autowired
	WebApplicationContext web;

	@Mock
	private DateDaoImpl dateDaoimpl;

	@Mock
	DateServiceImpl dateServiceImpl;
	@InjectMocks
	DateController dateController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(dateController).build();
	}

	@Test
	void When_Get_All_Then_Get_Form() throws Exception {
		mockMvc.perform(get("/dates")).andExpect(status().isOk()).andExpect(view().name("date/list"));
	}

	@Test
	void When_Create_Dates_Then_Get_Form() throws Exception {
		mockMvc.perform(get("/dates/create")).andExpect(status().isOk()).andExpect(view().name("date/create"));
	}

	@Test
	void When_Get_All_Then_Show_All_Dates() throws Exception {
		List<Date> dates = new ArrayList<>();
		dates.add(new Date("10", "03", "2016"));
		dates.add(new Date("17", "03", "2016"));
		dates.add(new Date("10", "03", "2019"));
		when(dateServiceImpl.getAll()).thenReturn(dates);

		mockMvc.perform(get("/dates")).andExpect(status().isOk()).andExpect(view().name("date/list"))
				.andExpect(model().attribute("dates", hasSize(3)));
		verify(dateServiceImpl).getAll();
	}

}
