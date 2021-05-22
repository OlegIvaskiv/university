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
import org.springframework.test.context.jdbc.Sql;

import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.dao.impl.TeacherDAOimpl;
import com.foxminded.university.spring.service.exception.ServiceException;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {
    @Mock
    private TeacherDAOimpl teacherDaoImpl;

    @InjectMocks
    TeacherServiceImpl teacherServiceImpl;

    public TeacherServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void When_Get_Teacher_By_Id_Then_True() {
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(teacherServiceImpl.getById(1))
                    .thenReturn(new Teacher("Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley"));
        });
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_Student_By_Not_Existing_Id_Then_Exception() {
        assertThrows(Exception.class, () -> {
            when(teacherServiceImpl.getById(13)).thenThrow(new ServiceException("can not get teacher by this id"));
        });
    }

    @Test
    void When_Get_All_Teachers_Then_True() {
        Teacher teacher1 = new Teacher("Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley");

        Teacher teacher2 = new Teacher("Tom Cruise", "1111112121", "Tom@Cruise.com", "New Yourk");

        Teacher teacher3 = new Teacher("Gipocrat", "9999944554", "MuskSpaceX.com", "Warsaw");

        List<Teacher> expectedTeachers = new LinkedList<Teacher>();
        expectedTeachers.add(teacher1);
        expectedTeachers.add(teacher2);
        expectedTeachers.add(teacher3);

        assertDoesNotThrow(() -> {
            Mockito.lenient().when(teacherServiceImpl.getAll()).thenReturn(expectedTeachers);
        });
    }

    @Test
    void When_Delete_Teacher_By_Id_Then_True() {
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(teacherServiceImpl.delete(3)).thenReturn(true);
        });
    }

    @Test
    void When_Delete_Not_Existing_Teacher_Then_Exception() {
        assertThrows(Exception.class, () -> {
            when(teacherServiceImpl.delete(16)).thenThrow(new ServiceException("can not delete teacher by this id"));
        });
    }

    @Test
    void When_Update_Teacher_Then_True() {
        Teacher teacher = new Teacher("7846", "22101", "MuskSpaceX.com", "Tokio");
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(teacherServiceImpl.update(teacher)).thenReturn(true);
        });
    }

    @Test
    void When_Update_Not_Existing_Teacher_Then_Exception() {
        Teacher teacher = new Teacher("7846", "22101", "MuskSpaceX.com", "Tokio");
        assertThrows(Exception.class, () -> {
            when(teacherServiceImpl.update(teacher)).thenThrow(new ServiceException("can not update this teacher"));
        });
    }

    @Test
    void When_Remove_Teacher_From_Lecture_Then_True() {
        Optional<Lecture> lecture = Optional.of(new Lecture(null, null, null, null));
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(teacherServiceImpl.removeTeacherFromLecture(lecture)).thenReturn(true);
        });
    }

    @Test
    void When_Add_Teacher_To_Lecture_Then_True() {
        Optional<Lecture> lecture = Optional.of(new Lecture(null, null, null, null));
        Optional<Teacher> teacher = Optional.of(new Teacher(null, null, null, null));
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(teacherServiceImpl.addTeacherToLecture(teacher, lecture)).thenReturn(true);
        });
    }

    @Test
    void When_Remove_Not_Existing_Teacher_Or_From_Not_Existing_Lecture_Then_Exception() {
        Optional<Lecture> lecture = Optional.of(new Lecture(null, null, null, null));
        assertThrows(Exception.class, () -> {
            when(teacherServiceImpl.removeTeacherFromLecture(lecture))
                    .thenThrow(new ServiceException("can not remove teacher from lecture"));
        });
    }

    @Test
    void When_Add_Not_Existing_Teacher_Or_To_Not_Existing_Lecture_Then_Exception() {
        Optional<Lecture> lecture = Optional.of(new Lecture(null, null, null, null));
        assertThrows(Exception.class, () -> {
            when(teacherServiceImpl.removeTeacherFromLecture(lecture))
                    .thenThrow(new ServiceException("can not add teacher to lecture"));
        });
    }

}
