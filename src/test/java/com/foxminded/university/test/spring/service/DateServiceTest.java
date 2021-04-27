package com.foxminded.university.test.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.foxminded.university.model.Date;
import com.foxminded.university.spring.config.UniversityConfiguration;
import com.foxminded.university.spring.service.DateService;
import com.foxminded.university.spring.service.impl.DateServiceImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UniversityConfiguration.class })
public class DateServiceTest {
	@Autowired
	private DateService dateService;

	@Autowired
	DateServiceTest(DateServiceImpl dateService) {
		this.dateService = dateService;
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Get_Date_By_Id_Then_True() throws Exception {
		Optional<Date> expectedDate = Optional.ofNullable(new Date(1, "30", "03", "2021"));
		Optional<Date> actualDate = Optional.ofNullable(new Date());
		actualDate = dateService.getById(1);
		assertEquals(expectedDate, actualDate);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Get_Date_By_Not_Existing_Id_Then_Exception() throws Exception {
		Throwable thrown = assertThrows(Exception.class, () -> {
			dateService.getById(15);
		});
		assertNotNull(thrown.getMessage().equals("In DB no entity with this id"));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_GetAll_Dates_Then_True() throws Exception {
		Date date1 = new Date(1, "30", "03", "2021");
		Date date2 = new Date(2, "30", "03", "2021");
		Date date3 = new Date(3, "30", "03", "2021");

		Optional<List<Date>> expectedDates = Optional.ofNullable(new LinkedList<Date>());
		Optional<List<Date>> actualDates = Optional.ofNullable(new LinkedList<Date>());
		expectedDates.get().add(date1);
		expectedDates.get().add(date2);
		expectedDates.get().add(date3);
		actualDates = dateService.getAll();
		assertEquals(expectedDates, actualDates);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Date_By_Id_Then_True() throws Exception {
		assertTrue(dateService.delete(3));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Not_Existing_Date_Then_Exception() {
		Throwable thrown = assertThrows(Exception.class, () -> {
			dateService.delete(13);
		});
		assertNotNull(thrown.getMessage().equals("In DB no entity with this id"));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Update_Date_Then_True() throws Exception {
		Date date = new Date(1, "99", "03", "2021");
		assertTrue(dateService.update(date));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Update_Not_Existing_Date_Then_Exception() {
		Date date = new Date(78, "99", "03", "2021");
		Throwable thrown = assertThrows(Exception.class, () -> {
			dateService.update(date);
		});
		assertNotNull(thrown.getMessage().equals("In DB no entity with this id"));
	}

}
