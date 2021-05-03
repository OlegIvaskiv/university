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

import com.foxminded.university.model.Date;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.impl.DateDAOimpl;
import com.foxminded.university.spring.service.impl.DateServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DateServiceTest {

	@Mock
	private DateDAOimpl dateDaoImpl;

	@InjectMocks
	DateServiceImpl dateServiceImpl;

	DateServiceTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void When_Get_Date_By_Id_Then_True() {
		assertThrows(Exception.class, () -> {
			given(dateServiceImpl.getById(1)).willReturn(Optional.ofNullable(new Date(1, "30", "03", "2021")));
		});

	}

	@Test
	void When_Get_Date_By_Not_Existing_Id_Then_Exception() {
		assertThrows(Exception.class, () -> {
			when(dateServiceImpl.getById(15)).thenThrow(new Exception("In DB no entity with this id"));
		});

	}

	@Test
	void When_GetAll_Dates_Then_True() {
		Date date1 = new Date(1, "30", "03", "2021");
		Date date2 = new Date(2, "30", "03", "2021");
		Date date3 = new Date(3, "30", "03", "2021");

		Optional<List<Date>> expectedDates = Optional.ofNullable(new LinkedList<Date>());
		expectedDates.get().add(date1);
		expectedDates.get().add(date2);
		expectedDates.get().add(date3);
		assertThrows(Exception.class, () -> {
			given(dateServiceImpl.getAll()).willReturn(expectedDates);
		});

	}

	@Test
	void When_Delete_Date_By_Id_Then_True() {
		assertThrows(Exception.class, () -> {
			given(dateServiceImpl.delete(3)).willReturn(true);
		});
	}

	@Test
	void When_Delete_Not_Existing_Date_Then_Exception() {
		assertThrows(Exception.class, () -> {
			when(dateServiceImpl.delete(13)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_Update_Date_Then_True() throws Exception {
		Date date = new Date(1, "99", "03", "2021");
		assertThrows(Exception.class, () -> {
			given(dateServiceImpl.update(date)).willReturn(true);
		});
	}

	@Test
	void When_Update_Not_Existing_Date_Then_Exception() {
		Date date = new Date(78, "99", "03", "2021");
		assertThrows(Exception.class, () -> {
			when(dateServiceImpl.update(date)).thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_Add_Date_To_Lecture_Then_True() {
		Optional<Date> date = Optional.ofNullable(new Date(2, null, null, null));
		Optional<Lecture> lecture = Optional.ofNullable(new Lecture(1, null, null, null, null));
		assertThrows(Exception.class, () -> {
			given(dateServiceImpl.addDateToLecture(date, lecture)).willReturn(true);
		});
	}

	@Test
	void When_Remove_Date_From_Lecture_Then_True() {
		Optional<Lecture> lecture = Optional.ofNullable(new Lecture(1, null, null, null, null));
		assertThrows(Exception.class, () -> {
			given(dateServiceImpl.removeDateFromLecture(lecture)).willReturn(true);
		});
	}

	@Test
	void When_Add_Not_Existing_Date_To_Lecture_Then_True() {
		Optional<Date> date = Optional.ofNullable(new Date(2, null, null, null));
		Optional<Lecture> lecture = Optional.ofNullable(new Lecture(1, null, null, null, null));
		assertThrows(Exception.class, () -> {
			when(dateServiceImpl.addDateToLecture(date, lecture))
					.thenThrow(new Exception("In DB no entity with this id"));
		});
	}

	@Test
	void When_Remove_Not_Existing_Date_From_Lecture_Then_True() {
		Optional<Lecture> lecture = Optional.ofNullable(new Lecture(1, null, null, null, null));
		assertThrows(Exception.class, () -> {
			when(dateServiceImpl.removeDateFromLecture(lecture))
					.thenThrow(new Exception("In DB no entity with this id"));
		});
	}

}
