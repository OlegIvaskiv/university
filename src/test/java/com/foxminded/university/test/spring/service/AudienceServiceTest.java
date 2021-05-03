package com.foxminded.university.test.spring.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.impl.AudienceDAOimpl;
import com.foxminded.university.spring.dao.impl.LectureDAOimpl;
import com.foxminded.university.spring.service.impl.AudienceServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AudienceServiceTest {
	@Mock
	private LectureDAOimpl lectureDaoimpl;

	@Mock
	private AudienceDAOimpl audienceDaoimpl;

	@InjectMocks
	AudienceServiceImpl audienceServiceImpl;

	public AudienceServiceTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void When_Get_Audience_By_Id_Then_True() {
		assertThrows(Exception.class, () -> {
			given(audienceServiceImpl.getById(1)).willReturn(Optional.ofNullable(new Audience(1, 246)));
		});
	}

	@Test
	public void When_Get_Audience_By_Not_Existing_Id_Then_Get_Exception() {
		assertThrows(Exception.class, () -> {
			when(audienceServiceImpl.getById(13)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	public void When_Delete_Audience_By_Id_Then_True() {
		assertThrows(Exception.class, () -> {
			given(audienceServiceImpl.delete(3)).willReturn(true);
		});
	}

	@Test

	public void When_Delete_Audience_By_Not_Existing_Id_Then_Exception() {
		assertThrows(Exception.class, () -> {
			when(audienceServiceImpl.delete(16)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	public void When_Update_Audience_Then_True() {
		Audience audience = new Audience(1, 286);
		assertThrows(Exception.class, () -> {
			given(audienceServiceImpl.update(audience)).willReturn(true);
		});
	}

	@Test
	public void When_Update_Not_Existing_Audience_Then_Exception() {
		Audience audience = new Audience(77, 2996);
		assertThrows(Exception.class, () -> {
			when(audienceServiceImpl.update(audience)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	public void When_Add_Audience_To_Lecture_Then_True() {
		Optional<Audience> audience = Optional.ofNullable(new Audience(2, 0));
		Optional<Lecture> lecture = Optional.ofNullable(new Lecture(3, null, null, null, null));
		assertThrows(Exception.class, () -> {
			given(audienceServiceImpl.addAudienceToLecture(audience, lecture)).willReturn(true);
		});
	}

	@Test
	public void When_Add_Not_Existing_Audience_To_Lecture_Then_Excption() {
		Optional<Audience> audience = Optional.ofNullable(new Audience(2, 0));
		Optional<Lecture> lecture = Optional.ofNullable(new Lecture(3, null, null, null, null));
		assertThrows(Exception.class, () -> {
			when(audienceServiceImpl.addAudienceToLecture(audience, lecture))
					.thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	public void When_GetAll_Audience_Then_True() {
		Audience audience1 = new Audience(1, 246);
		Audience audience2 = new Audience(2, 201);
		Audience audience3 = new Audience(3, 146);

		Optional<List<Audience>> expectedAudiences = Optional.ofNullable(new LinkedList<Audience>());
		expectedAudiences.get().add(audience1);
		expectedAudiences.get().add(audience2);
		expectedAudiences.get().add(audience3);

		assertThrows(Exception.class, () -> {
			given(audienceServiceImpl.getAll()).willReturn(expectedAudiences);
		});
	}

	@Test
	void When_Remove_Audience_From_Lecture_Then_True() {
		Optional<Lecture> lecture = Optional.ofNullable(new Lecture(3, null, null, null, null));
		assertThrows(Exception.class, () -> {
			given(audienceServiceImpl.removeAudiecnceFromLecture(lecture)).willReturn(true);
		});
	}

	@Test
	void When_Remove_Not_Excisting_Audience_From_Lecture_Then_Exception() {
		Optional<Lecture> lecture = Optional.ofNullable(new Lecture(3, null, null, null, null));
		assertThrows(Exception.class, () -> {
			when(audienceServiceImpl.removeAudiecnceFromLecture(lecture))
					.thenThrow(new Exception("In DB no entity with this id"));
		});
	}
}
