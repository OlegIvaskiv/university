package com.foxminded.university.test.spring.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.config.UniversityConfiguration;
import com.foxminded.university.spring.dao.DateDao;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UniversityConfiguration.class })
class DateDaoTest {
	private DateDao dateDao;

	@Autowired
	DateDaoTest(DateDao dateDao) {
		this.dateDao = dateDao;
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Get_Date_By_Id_Then_True() {
		Optional<Date> expectedDate = Optional.ofNullable(new Date(1, "30", "03", "2021"));
		Optional<Date> actualDate = Optional.ofNullable(new Date());
		actualDate = dateDao.getById(1);
		assertEquals(expectedDate, actualDate);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Date_By_Id_Then_True() {
		assertTrue(dateDao.delete(3));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_GetAll_Dates_Then_True() {
		Date date1 = new Date(1, "30", "03", "2021");
		Date date2 = new Date(2, "30", "03", "2021");
		Date date3 = new Date(3, "30", "03", "2021");

		Optional<List<Date>> expectedDates = Optional.ofNullable(new LinkedList<Date>());
		Optional<List<Date>> actualDates = Optional.ofNullable(new LinkedList<Date>());
		expectedDates.get().add(date1);
		expectedDates.get().add(date2);
		expectedDates.get().add(date3);
		actualDates = dateDao.getAll();
		assertEquals(expectedDates, actualDates);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Create_Date_Then_True() {
		Date date = new Date(4, "30", "03", "2021");
		assertTrue(dateDao.create(date));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Update_Date_Then_True() {
		Date date = new Date(1, "99", "03", "2021");
		assertTrue(dateDao.update(date));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Add_Date_To_Lecture_Then_True() {
		Optional<Date> date = Optional.ofNullable(new Date(2, null, null, null));
		Optional<Lecture> lecture = Optional.ofNullable(new Lecture(1, null, null, null, null));
		assertTrue(dateDao.addDateToLecture(date, lecture));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Remove_Date_From_Lecture_Then_True() {
		Optional<Lecture> lecture = Optional.ofNullable(new Lecture(1, null, null, null, null));
		assertTrue(dateDao.removeDateFromLecture(lecture));
	}

}
