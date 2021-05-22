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

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Course;
import com.foxminded.university.model.Date;
import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Student;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.config.UniversityConfiguration;
import com.foxminded.university.spring.dao.LectureDao;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UniversityConfiguration.class })
class LectureDaoTest {
    private LectureDao lectureDao;

    @Autowired
    public LectureDaoTest(LectureDao lectureDao) {
        this.lectureDao = lectureDao;
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_Get_Lecture_By_Id_Then_True() {
        Course course = new Course(1, "Math");
        Audience audience = new Audience(1, 246);
        Date date = new Date(1, "30", "03", "2021");
        Group group = new Group(1, course, "BI-21");
        Student student = new Student(1, group, "Oleh Ivaskiv", "666666666", "OLEH@gmail.com", "London");
        Teacher teacher = new Teacher(1, "Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley");
        Lecture expectedLecture = new Lecture(1, teacher, student, audience, date);

        assertDoesNotThrow(() -> {
            assertEquals(expectedLecture, lectureDao.getById(1));
        });
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_Delete_Lecture_By_Id_Then_True() {
        assertDoesNotThrow(() -> {
            assertTrue(lectureDao.delete(3));
        });
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data2.sql" })
    void When_Get_All_Lectures_Then_True() {
        Course course1 = new Course(1, "Math");
        Audience audience1 = new Audience(1, 246);
        Date date1 = new Date(1, "30", "03", "2021");
        Group group1 = new Group(1, course1, "BI-21");
        Student student1 = new Student(1, group1, "Oleh Ivaskiv", "666666666", "OLEH@gmail.com", "London");
        Teacher teacher1 = new Teacher(1, "Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley");
        Lecture lecture1 = new Lecture(1, teacher1, student1, audience1, date1);

        Course course2 = new Course(2, "English");
        Audience audience2 = new Audience(2, 201);
        Date date2 = new Date(2, "30", "03", "2021");
        Group group2 = new Group(2, course2, "KN-21");
        Student student2 = new Student(2, group2, "Alex", "2222222", "Alex@gmail.com", "London");
        Teacher teacher2 = new Teacher(2, "Tom Cruise", "1111112121", "Tom@Cruise.com", "New Yourk");
        Lecture lecture2 = new Lecture(2, teacher2, student2, audience2, date2);

        Course course3 = new Course(3, "Biology");
        Audience audience3 = new Audience(3, 146);
        Date date3 = new Date(3, "30", "03", "2021");
        Group group3 = new Group(3, course3, "IK-21");
        Student student3 = new Student(3, group3, "Max", "0001110001", "Max@gmail.com", "London");
        Teacher teacher3 = new Teacher(3, "Gipocrat", "9999944554", "MuskSpaceX.com", "Warsaw");
        Lecture lecture3 = new Lecture(3, teacher3, student3, audience3, date3);
        List<Lecture> expectedLectures = new LinkedList<Lecture>();
        expectedLectures.add(lecture1);
        expectedLectures.add(lecture2);
        expectedLectures.add(lecture3);

        assertDoesNotThrow(() -> {
            assertEquals(expectedLectures, lectureDao.getAll());
        });
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_Create_Lecture_Then_True() {
        Course course = new Course(1, "Math");
        Audience audience = new Audience(1, 246);
        Date date = new Date(1, "30", "03", "2021");
        Group group = new Group(course, "BI-21");
        Student student = new Student(3, group, "Oleh Ivaskiv", "666666666", "OLEH@gmail.com", "London");
        Teacher teacher = new Teacher(3, "Elon Musk", "0101010101", "MuskSpaceX.com", "Silicon valley");
        Lecture lecture = new Lecture(4, teacher, student, audience, date);
        assertDoesNotThrow(() -> {
            assertTrue(lectureDao.create(lecture));
        });
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_Update_Lecture_Then_True() {
        Course course = new Course("Math");
        Audience audience = new Audience(1, 246);
        Date date = new Date(2, "30", "03", "2021");
        Group group = new Group(1, course, "BI-21");
        Student student = new Student(3, group, "******", "898489545", "OLEH@gmail.com", "Kyiv");
        Teacher teacher = new Teacher(3, "*****", "0101010101", "MuskSpaceX.com", "Tokio");
        Lecture lecture = new Lecture(3, teacher, student, audience, date);
        assertDoesNotThrow(() -> {
            assertTrue(lectureDao.update(lecture));
        });
    }
}
