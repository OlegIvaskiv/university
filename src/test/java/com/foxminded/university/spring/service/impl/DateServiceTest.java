package com.foxminded.university.spring.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.foxminded.university.model.Date;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.impl.DateDAOimpl;
import com.foxminded.university.spring.service.exception.ServiceException;

@ExtendWith(MockitoExtension.class)
public class DateServiceTest {

    @Mock
    private DateDAOimpl dateDaoImpl;

    @InjectMocks
    DateServiceImpl dateServiceImpl;

    public DateServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void When_Get_Date_By_Id_Then_True() {
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(dateServiceImpl.getById(1)).thenReturn(new Date("30", "03", "2021"));
        });
    }

    @Test
    void When_Get_Date_By_Not_Existing_Id_Then_Exception() {
        assertThrows(Exception.class, () -> {
            when(dateServiceImpl.getById(15)).thenThrow(new ServiceException("can not get student by this id"));
        });

    }

    @Test
    void When_GetAll_Dates_Then_True() {
        Date date1 = new Date("30", "03", "2021");
        Date date2 = new Date("30", "03", "2021");
        Date date3 = new Date("30", "03", "2021");

        List<Date> expectedDates = new LinkedList<Date>();
        expectedDates.add(date1);
        expectedDates.add(date2);
        expectedDates.add(date3);
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(dateServiceImpl.getAll()).thenReturn(expectedDates);
        });
    }

    @Test
    void When_Delete_Date_By_Id_Then_True() {
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(dateServiceImpl.delete(3)).thenReturn(true);
        });
    }

    @Test
    void When_Delete_Not_Existing_Date_Then_Exception() {
        assertThrows(Exception.class, () -> {
            when(dateServiceImpl.delete(13)).thenThrow(new ServiceException("can not delete data by this id"));
        });
    }

    @Test
    void When_Update_Date_Then_True() {
        Date date = new Date("99", "03", "2021");
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(dateServiceImpl.update(date)).thenReturn(true);
        });
    }

    @Test
    void When_Update_Not_Existing_Date_Then_Exception() {
        Date date = new Date("99", "03", "2021");
        assertThrows(Exception.class, () -> {
            when(dateServiceImpl.update(date)).thenThrow(new ServiceException("can not update this data"));
        });
    }

    @Test
    void When_Add_Date_To_Lecture_Then_True() {
        Optional<Date> date = Optional.ofNullable(new Date(null, null, null));
        Optional<Lecture> lecture = Optional.ofNullable(new Lecture(null, null, null, null));
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(dateServiceImpl.addDateToLecture(date, lecture)).thenReturn(true);
        });
    }

    @Test
    void When_Remove_Date_From_Lecture_Then_True() {
        Optional<Lecture> lecture = Optional.ofNullable(new Lecture(null, null, null, null));
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(dateServiceImpl.removeDateFromLecture(lecture)).thenReturn(true);
        });
    }

    @Test
    void When_Add_Not_Existing_Date_To_Lecture_Then_True() {
        Optional<Date> date = Optional.ofNullable(new Date(null, null, null));
        Optional<Lecture> lecture = Optional.ofNullable(new Lecture(null, null, null, null));
        assertThrows(Exception.class, () -> {
            when(dateServiceImpl.addDateToLecture(date, lecture))
                    .thenThrow(new Exception("can not add data to lecture"));
        });
    }

    @Test
    void When_Remove_Not_Existing_Date_From_Lecture_Then_True() {
        Optional<Lecture> lecture = Optional.ofNullable(new Lecture(null, null, null, null));
        assertThrows(Exception.class, () -> {
            when(dateServiceImpl.removeDateFromLecture(lecture))
                    .thenThrow(new Exception("can not remove data from lecture"));
        });
    }

}
