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

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.spring.dao.impl.AudienceDaoImpl;
import com.foxminded.university.spring.dao.impl.LectureDaoImpl;
import com.foxminded.university.spring.service.exception.ServiceException;

@ExtendWith(MockitoExtension.class)
public class AudienceServiceTest {
    @Mock
    private LectureDaoImpl lectureDaoimpl;

    @Mock
    private AudienceDaoImpl audienceDaoimpl;

    @InjectMocks
    AudienceServiceImpl audienceServiceImpl;

    public AudienceServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void When_Get_Audience_By_Id_Then_True() {
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(audienceServiceImpl.getById(1)).thenReturn(new Audience(246));
        });
    }

    @Test
    public void When_Get_Audience_By_Not_Existing_Id_Then_Get_Exception() {
        assertThrows(Exception.class, () -> {
            when(audienceServiceImpl.getById(13)).thenThrow(new ServiceException("can not get audience by this id"));
        });
    }

    @Test
    public void When_Delete_Audience_By_Id_Then_True() {
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(audienceServiceImpl.delete(3)).thenReturn(true);
        });
    }

    @Test

    public void When_Delete_Audience_By_Not_Existing_Id_Then_Exception() {
        assertThrows(Exception.class, () -> {
            when(audienceServiceImpl.delete(16)).thenThrow(new ServiceException("can not delete audience by this id"));
        });
    }

    @Test
    public void When_Update_Audience_Then_True() {
        Audience audience = new Audience(286);
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(audienceServiceImpl.update(audience)).thenReturn(true);
        });
    }

    @Test
    public void When_Update_Not_Existing_Audience_Then_Exception() {
        Audience audience = new Audience(2996);
        assertThrows(Exception.class, () -> {
            when(audienceServiceImpl.update(audience)).thenThrow(new ServiceException("can not update this audience"));
        });
    }

    @Test
    public void When_Add_Audience_To_Lecture_Then_True() {
        Optional<Audience> audience = Optional.ofNullable(new Audience(1, 0));
        Optional<Lecture> lecture = Optional.ofNullable(new Lecture(1, null, null, null, null));
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(audienceServiceImpl.addAudienceToLecture(audience, lecture)).thenReturn(true);
        });
    }

    @Test
    public void When_Add_Not_Existing_Audience_To_Lecture_Then_Excption() {
        Optional<Audience> audience = Optional.ofNullable(new Audience(0));
        Optional<Lecture> lecture = Optional.ofNullable(new Lecture(null, null, null, null));
        assertThrows(Exception.class, () -> {
            when(audienceServiceImpl.addAudienceToLecture(audience, lecture))
                    .thenThrow(new ServiceException("can not add audience to lecture"));
        });
    }

    @Test
    public void When_GetAll_Audience_Then_True() {
        Audience audience1 = new Audience(1, 246);
        Audience audience2 = new Audience(2, 201);
        Audience audience3 = new Audience(3, 146);

        List<Audience> expectedAudiences = new LinkedList<Audience>();
        expectedAudiences.add(audience1);
        expectedAudiences.add(audience2);
        expectedAudiences.add(audience3);

        assertDoesNotThrow(() -> {
            Mockito.lenient().when(audienceServiceImpl.getAll()).thenReturn(expectedAudiences);
        });
    }

    @Test
    void When_Remove_Audience_From_Lecture_Then_True() {
        Optional<Lecture> lecture = Optional.ofNullable(new Lecture(null, null, null, null));
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(audienceServiceImpl.removeAudiecnceFromLecture(lecture)).thenReturn(true);
        });
    }

    @Test
    void When_Remove_Not_Excisting_Audience_From_Lecture_Then_Exception() {
        Optional<Lecture> lecture = Optional.ofNullable(new Lecture(null, null, null, null));
        assertThrows(Exception.class, () -> {
            when(audienceServiceImpl.removeAudiecnceFromLecture(lecture))
                    .thenThrow(new ServiceException("can not remove audience from lecture"));
        });
    }
}
