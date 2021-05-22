package com.foxminded.university.spring.dao.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.foxminded.university.model.Course;
import com.foxminded.university.spring.config.UniversityConfiguration;
import com.foxminded.university.spring.dao.CourseDao;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UniversityConfiguration.class })
class CourseDaoTest {
    private CourseDao courseDao;

    @Autowired
    public CourseDaoTest(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_Get_Course_By_Id_Then_True() {
        Course expectedCourse = new Course(1, "Math");

        assertDoesNotThrow(() -> {
            assertEquals(courseDao.getById(1), expectedCourse);
        });

    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_Delete_Course_By_Id_Then_True() {
        assertDoesNotThrow(() -> {
            assertTrue(courseDao.delete(3));
        });
    }

    @Test
    @Sql({ "/test-tables.sql" })
    void When_Create_Course_Then_True() {
        Course course = new Course("Math");
        assertDoesNotThrow(() -> {
            assertTrue(courseDao.create(course));
        });
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_GetAll_Course_Then_True() {
        Course course1 = new Course(1, "Math");
        Course course2 = new Course(2, "English");
        Course course3 = new Course(3, "Biology");

        List<Course> expectedCourses = new LinkedList<Course>();
        expectedCourses.add(course1);
        expectedCourses.add(course2);
        expectedCourses.add(course3);

        assertDoesNotThrow(() -> {
            assertEquals(expectedCourses, courseDao.getAll());
        });
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_Update_Course_Then_True() {
        Course course = new Course(2, "Biology");

        assertDoesNotThrow(() -> {
            assertTrue(courseDao.update(course));
        });
    }

}
