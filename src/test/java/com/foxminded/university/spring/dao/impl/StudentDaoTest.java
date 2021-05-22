package com.foxminded.university.spring.dao.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Student;
import com.foxminded.university.spring.config.UniversityConfiguration;
import com.foxminded.university.spring.dao.StudentDao;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UniversityConfiguration.class })
class StudentDaoTest {
    private StudentDao studentDao;

    @Autowired
    public StudentDaoTest(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })

    void When_Get_Student_By_Id_Then_True() {
        Course course = new Course(1, "Math");
        Group group = new Group(1, course, "BI-21");
        Student expectedStudent = new Student(1, group, "Oleh Ivaskiv", "666666666", "OLEH@gmail.com", "London");

        assertDoesNotThrow(() -> {
            assertEquals(expectedStudent, studentDao.getById(1));
        });
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data2.sql" })
    void When_Get_All_Students_Then_True() {
        Course course1 = new Course(1, "Math");
        Group group1 = new Group(1, course1, "BI-21");
        Student student1 = new Student(1, group1, "Oleh Ivaskiv", "666666666", "OLEH@gmail.com", "London");

        Course course2 = new Course(2, "English");
        Group group2 = new Group(2, course2, "KN-21");
        Student student2 = new Student(2, group2, "Alex", "2222222", "Alex@gmail.com", "London");

        Course course3 = new Course(3, "Biology");
        Group group3 = new Group(3, course3, "IK-21");
        Student student3 = new Student(3, group3, "Max", "0001110001", "Max@gmail.com", "London");

        List<Student> expectedStudents = new LinkedList<Student>();
        expectedStudents.add(student1);
        expectedStudents.add(student2);
        expectedStudents.add(student3);

        assertDoesNotThrow(() -> {
            assertEquals(expectedStudents, studentDao.getAll());
        });
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_Create_Student_Then_True() {
        Course course = new Course(1, "Math");
        Group group = new Group(1, course, "BI-21");
        Student student = new Student(6, group, "Oleh Ivaskiv", "666666666", "OLEH@gmail.com", "London");
        assertDoesNotThrow(() -> {
            assertTrue(studentDao.create(student));
        });
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_Add_Student_To_Group_Then_True() {
        Optional<Student> student = Optional.of(new Student(3, null, null, null, null, null));
        Optional<Group> group = Optional.of(new Group(3, null, null));
        assertDoesNotThrow(() -> {
            assertTrue(studentDao.addStudentToGroup(student, group));
        });
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_Remove_Student_From_Group_Then_True() {
        Optional<Student> student = Optional.of(new Student(3, null, null, null, null, null));
        assertDoesNotThrow(() -> {
            assertTrue(studentDao.removeStudentFromGroup(student));
        });
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_Remove_Student_From_Lecture_Then_True() {
        Optional<Lecture> lecture = Optional.of(new Lecture(3, null, null, null, null));
        assertDoesNotThrow(() -> {
            assertTrue(studentDao.removeStudentFromLecture(lecture));
        });
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_Add_Student_To_Lecture_Then_True() {
        Optional<Student> student = Optional.of(new Student(1, null, null, null, null, null));
        Optional<Lecture> lecture = Optional.of(new Lecture(1, null, null, null, null));
        assertDoesNotThrow(() -> {
            assertTrue(studentDao.addStudentToLecture(student, lecture));
        });
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_Update_Student_Then_True() {
        Course course = new Course(1, "Math");
        Group group = new Group(1, course, "BI-21");
        Student student = new Student(1, group, "******", "8984745", "OLEH@gmail.com", "Kyiv");
        assertDoesNotThrow(() -> {
            assertTrue(studentDao.update(student));
        });
    }

    @Test
    @Sql({ "/test-tables.sql", "/test-data.sql" })
    void When_Delete_Student_By_Id_Then_True() {
        assertDoesNotThrow(() -> {
            assertTrue(studentDao.delete(3));
        });
    }

}
