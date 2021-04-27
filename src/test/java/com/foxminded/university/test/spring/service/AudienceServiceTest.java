package com.foxminded.university.test.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.config.UniversityConfiguration;
import com.foxminded.university.spring.dao.AudienceDao;
import com.foxminded.university.spring.dao.impl.LectureDAOimpl;
import com.foxminded.university.spring.service.impl.AudienceServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UniversityConfiguration.class })
public class AudienceServiceTest {

	@Mock
	private AudienceDao audienceDao;

	@Mock
	private LectureDAOimpl lectureDao;

	@InjectMocks
	private AudienceServiceImpl audienceService;

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	public void When_Get_Audience_By_Id_Then_True() throws Exception {
		Optional<Audience> expectedAudience = Optional.ofNullable(new Audience(1, 246));
		Optional<Audience> actualAudience = Optional.ofNullable(new Audience());
		actualAudience = audienceService.getById(1);
		assertEquals(expectedAudience, actualAudience);

		// given(audienceService.getById(1)).willReturn(null);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	public void When_Get_Audience_By_Not_Existing_Id_Then_Get_Exception() throws Exception {
		given(audienceService.getById(13)).willReturn(null);
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	public void When_Delete_Audience_By_Id_Then_True() throws Exception {
		assertTrue(audienceService.delete(3));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	public void When_Delete_Audience_By_Not_Existing_Id_Then_Exception() throws Exception {
		Throwable thrown = assertThrows(Exception.class, () -> {
			audienceService.delete(9);
		});
		assertNotNull(thrown.getMessage().equals("In DB no entity with this id"));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	public void When_Update_Audience_Then_True() throws Exception {
		Audience audience = new Audience(1, 286);
		assertTrue(audienceService.update(audience));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	public void When_Update_Not_Existing_Audience_Then_Exception() {
		Audience audience = new Audience(77, 2996);
		Throwable thrown = assertThrows(Exception.class, () -> {
			audienceService.update(audience);
		});
		assertNotNull(thrown.getMessage().equals("In DB no entity with this id"));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	public void When_Add_Audience_To_Lecture_Then_True() throws Exception {
		Optional<Audience> audience = Optional.ofNullable(new Audience(2, 201));
		Optional<Lecture> lecture = Optional.ofNullable(new Lecture(3, null, null, null, null));
		assertTrue(audienceService.addAudienceToLecture(audience, lecture));
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	public void When_GetAll_Audience_Then_True() throws Exception {
		Audience audience1 = new Audience(1, 246);
		Audience audience2 = new Audience(2, 201);
		Audience audience3 = new Audience(3, 146);

		Optional<List<Audience>> expectedAudiences = Optional.ofNullable(new LinkedList<Audience>());
		Optional<List<Audience>> actualAudiences = Optional.ofNullable(new LinkedList<Audience>());
		expectedAudiences.get().add(audience1);
		expectedAudiences.get().add(audience2);
		expectedAudiences.get().add(audience3);
		actualAudiences = audienceService.getAll();
		assertEquals(expectedAudiences, actualAudiences);
	}

}
