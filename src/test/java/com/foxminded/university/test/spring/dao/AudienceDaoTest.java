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

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.config.UniversityConfiguration;
import com.foxminded.university.spring.dao.AudienceDao;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UniversityConfiguration.class })
class AudienceDaoTest {
	private AudienceDao audienceDao;

	@Autowired
	AudienceDaoTest(AudienceDao audienceDao) {
		this.audienceDao = audienceDao;
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Get_Audience_By_Id_Then_True() {
		Optional<Audience> expectedAudience = Optional.ofNullable(new Audience(1, 246));
		Optional<Audience> actualAudience = Optional.ofNullable(new Audience());
		actualAudience = audienceDao.getById(1);
		assertEquals(actualAudience, expectedAudience);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Audience_By_Id_Then_True() {
		assertTrue(audienceDao.delete(3));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_GetAll_Audience_Then_True() {
		Audience audience1 = new Audience(1, 246);
		Audience audience2 = new Audience(2, 201);
		Audience audience3 = new Audience(3, 146);

		Optional<List<Audience>> expectedAudiences = Optional.ofNullable(new LinkedList<Audience>());
		Optional<List<Audience>> actualAudiences = Optional.ofNullable(new LinkedList<Audience>());
		expectedAudiences.get().add(audience1);
		expectedAudiences.get().add(audience2);
		expectedAudiences.get().add(audience3);
		actualAudiences = audienceDao.getAll();
		assertEquals(expectedAudiences, actualAudiences);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Create_Audience_Then_True() {
		Audience audience = new Audience(4, 246);
		assertTrue(audienceDao.create(audience));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Update_Audience_Then_True() {
		Audience audience = new Audience(1, 286);
		assertTrue(audienceDao.update(audience));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Add_Audience_To_Lecture_Then_True() {
		Optional<Audience> audience = Optional.ofNullable(new Audience(2, 0));
		Optional<Lecture> lecture = Optional.ofNullable(new Lecture(3, null, null, null, null));
		assertEquals(audienceDao.addAudienceToLecture(audience, lecture), true);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Remove_Audience_From_Lecture_Then_True() {
		Optional<Lecture> lecture = Optional.ofNullable(new Lecture(1, null, null, null, null));
		assertTrue(audienceDao.removeAudiecnceFromLecture(lecture));
	}
}
