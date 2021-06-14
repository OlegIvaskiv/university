package com.foxminded.university.spring.dao.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
import org.springframework.test.context.web.WebAppConfiguration;

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.config.UniversityConfiguration;
import com.foxminded.university.spring.dao.AudienceDao;
import com.foxminded.university.spring.dao.exception.DaoException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UniversityConfiguration.class })
@WebAppConfiguration
class AudienceDaoTest {
	private AudienceDao audienceDao;

	@Autowired
	public AudienceDaoTest(AudienceDao audienceDao) {
		this.audienceDao = audienceDao;
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Get_Audience_By_Id_Then_True() {
		Audience expectedAudience = new Audience(1, 246);
		assertDoesNotThrow(() -> {
			assertEquals(expectedAudience, audienceDao.getById(1));
		});

	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Audience_By_Id_Then_True() {
		assertDoesNotThrow(() -> {
			assertTrue(audienceDao.delete(3));
		});

	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_GetAll_Audience_Then_True() throws DaoException {
		Audience audience1 = new Audience(1, 246);
		Audience audience2 = new Audience(2, 201);
		Audience audience3 = new Audience(3, 146);

		List<Audience> expectedAudiences = new LinkedList<Audience>();
		expectedAudiences.add(audience1);
		expectedAudiences.add(audience2);
		expectedAudiences.add(audience3);

		assertDoesNotThrow(() -> {
			assertEquals(expectedAudiences, audienceDao.getAll());
		});

	}

	@Test
	@Sql({ "/test-tables.sql" })
	void When_Create_Audience_Then_True() {
		Audience audience = new Audience(276);
		assertDoesNotThrow(() -> {
			assertTrue(audienceDao.create(audience));
		});

	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Update_Audience_Then_True() {
		Audience audience = new Audience(1, 286);
		assertDoesNotThrow(() -> {
			assertTrue(audienceDao.update(audience));
		});

	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Add_Audience_To_Lecture_Then_True() {
		Optional<Audience> audience = Optional.ofNullable(new Audience(1, 0));
		Optional<Lecture> lecture = Optional.ofNullable(new Lecture(2, null, null, null, null));

		assertDoesNotThrow(() -> {
			assertEquals(audienceDao.addAudienceToLecture(audience, lecture), true);
		});

	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Remove_Audience_From_Lecture_Then_True() {
		Optional<Lecture> lecture = Optional.ofNullable(new Lecture(1, null, null, null, null));

		assertDoesNotThrow(() -> {
			assertTrue(audienceDao.removeAudiecnceFromLecture(lecture));
		});

	}
}
