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

import com.foxminded.university.model.Course;
import com.foxminded.university.spring.dao.impl.CourseDaoImpl;
import com.foxminded.university.spring.service.exception.ServiceException;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    private CourseDaoImpl courseServiceDaoimpl;

    @InjectMocks
    CourseServiceImpl courseServiceImpl;

    public CourseServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test

    void When_Get_Course_By_Id_Then_True() {
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(courseServiceImpl.getById(1)).thenReturn(new Course("Math"));
        });
    }

    @Test
    void When_Course_By_Id_Not_Exixst_Then_Get_Empty() {
        assertThrows(Exception.class, () -> {
            when(courseServiceImpl.getById(1)).thenThrow(new ServiceException("can not get course by this id"));
        });
    }

    @Test
    void When_GetAll_Course_Then_True() {
        Course course1 = new Course("Math");
        Course course2 = new Course("English");
        Course course3 = new Course("Biology");

        List<Course> expectedCourses = new LinkedList<Course>();
        expectedCourses.add(course1);
        expectedCourses.add(course2);
        expectedCourses.add(course3);

        assertDoesNotThrow(() -> {
            Mockito.lenient().when(courseServiceImpl.getAll()).thenReturn(expectedCourses);
        });
    }

    @Test
    void When_Delete_Course_By_Id_Then_True() {
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(courseServiceImpl.delete(3)).thenReturn(true);
        });
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_Delete_Course_By_Not_Existing_Id_Then_Exception() {
        assertThrows(Exception.class, () -> {
            when(courseServiceImpl.delete(16)).thenThrow(new ServiceException("can not delete course by this id"));
        });
    }

    @Test
    void When_Update_Course_Then_True() {
        Course course = new Course("Biology");
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(courseServiceImpl.update(course)).thenReturn(true);
        });
    }

    @Test
    void When_Update_Not_Existing_Course_Then_Exception() {
        Course course = new Course("Biology");
        assertThrows(Exception.class, () -> {
            when(courseServiceImpl.update(course)).thenThrow(new ServiceException("can not update this course"));
        });
    }

}
