package com.foxminded.university.spring.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Course;
import com.foxminded.university.model.Date;
import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Student;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.dao.impl.LectureDaoImpl;
import com.foxminded.university.spring.service.exception.ServiceException;

@ExtendWith(MockitoExtension.class)
public class LectureServiceTest {
    @Mock
    private LectureDaoImpl lectureDaoimpl;

    @InjectMocks
    LectureServiceImpl lectureServiceImpl;

    public LectureServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void When_Get_Lecture_By_Id_Then_True() {
        Course course = new Course("Math");
        Audience audience = new Audience(246);
        Date date = new Date("30", "03", "2021");
        Group group = new Group(course, "BI-21");
        Student student = new Student(group, "Oleh Ivaskiv", "666666666", "OLEH@gmail.com", "London");
        Teacher teacher = new Teacher("Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley");

        assertDoesNotThrow(() -> {
            Mockito.lenient().when(lectureServiceImpl.getById(1))
                    .thenReturn(new Lecture(teacher, student, audience, date));
        });
    }

    @Test
    void When_Audience_By_Id_Not_Exixst_Then_Get_Exception() {
        assertThrows(Exception.class, () -> {
            when(lectureServiceImpl.getById(13)).thenThrow(new ServiceException("can not get lecture by this id"));
        });
    }

    @Test
    void When_Get_All_Lectures_Then_True() {
        Course course1 = new Course("Math");
        Audience audience1 = new Audience(246);
        Date date1 = new Date("30", "03", "2021");
        Group group1 = new Group(course1, "BI-21");
        Student student1 = new Student(group1, "Oleh Ivaskiv", "666666666", "OLEH@gmail.com", "London");
        Teacher teacher1 = new Teacher("Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley");
        Lecture lecture1 = new Lecture(teacher1, student1, audience1, date1);

        Course course2 = new Course("English");
        Audience audience2 = new Audience(201);
        Date date2 = new Date("30", "03", "2021");
        Group group2 = new Group(course2, "KN-21");
        Student student2 = new Student(group2, "Alex", "2222222", "Alex@gmail.com", "London");
        Teacher teacher2 = new Teacher("Tom Cruise", "1111112121", "Tom@Cruise.com", "New Yourk");
        Lecture lecture2 = new Lecture(teacher2, student2, audience2, date2);

        Course course3 = new Course("Biology");
        Audience audience3 = new Audience(146);
        Date date3 = new Date("30", "03", "2021");
        Group group3 = new Group(course3, "IK-21");
        Student student3 = new Student(group3, "Max", "0001110001", "Max@gmail.com", "London");
        Teacher teacher3 = new Teacher("Gipocrat", "9999944554", "MuskSpaceX.com", "Warsaw");
        Lecture lecture3 = new Lecture(teacher3, student3, audience3, date3);
        List<Lecture> expectedLectures = new LinkedList<Lecture>();
        expectedLectures.add(lecture1);
        expectedLectures.add(lecture2);
        expectedLectures.add(lecture3);

        assertDoesNotThrow(() -> {
            Mockito.lenient().when(lectureServiceImpl.getAll()).thenReturn(expectedLectures);
        });
    }

    @Test
    void When_Delete_Lecture_By_Id_Then_True() {
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(lectureServiceImpl.delete(3)).thenReturn(true);
        });
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_Delete_Not_Existing_Lecture_Then_Exception() {
        assertThrows(Exception.class, () -> {
            when(lectureServiceImpl.delete(16)).thenThrow(new ServiceException("can not delete lecture by this id"));
        });
    }

    @Test
    void When_Update_Lecture_Then_True() {
        Course course = new Course("Math");
        Audience audience = new Audience(246);
        Date date = new Date("30", "03", "2021");
        Group group = new Group(course, "BI-21");
        Student student = new Student(group, "******", "898489545", "OLEH@gmail.com", "Kyiv");
        Teacher teacher = new Teacher("*****", "0101010101", "MuskSpaceX.com", "Tokio");
        Lecture lecture = new Lecture(teacher, student, audience, date);

        assertDoesNotThrow(() -> {
            Mockito.lenient().when(lectureServiceImpl.update(lecture)).thenReturn(true);
        });
    }

    @Test
    void When_Update_Not_Existing_Lecture_Then_Exception() {
        Course course = new Course("Math");
        Audience audience = new Audience(246);
        Date date = new Date("30", "03", "2021");
        Group group = new Group(course, "BI-21");
        Student student = new Student(group, "******", "898489545", "OLEH@gmail.com", "Kyiv");
        Teacher teacher = new Teacher("*****", "0101010101", "MuskSpaceX.com", "Tokio");
        Lecture lecture = new Lecture(teacher, student, audience, date);

        assertThrows(Exception.class, () -> {
            when(lectureServiceImpl.update(lecture)).thenThrow(new ServiceException("can not update this lecture"));
        });
    }

}
