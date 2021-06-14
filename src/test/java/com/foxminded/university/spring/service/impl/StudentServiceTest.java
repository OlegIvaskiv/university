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

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Group;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Student;
import com.foxminded.university.spring.dao.impl.StudentDaoImpl;
import com.foxminded.university.spring.service.exception.ServiceException;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentDaoImpl studentDaoImpl;

    @InjectMocks
    StudentServiceImpl studentServiceImpl;

    public StudentServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void When_Get_Student_By_Id_Then_True() {
        Course course = new Course("Math");
        Group group = new Group(course, "BI-21");
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(studentServiceImpl.getById(1))
                    .thenReturn(new Student(group, "Oleh Ivaskiv", "666666666", "OLEH@gmail.com", "London"));
        });
    }

    @Test
    void When_Student_By_Id_Not_Exixst_Then_Exception() {
        assertThrows(Exception.class, () -> {
            when(studentServiceImpl.getById(15)).thenThrow(new ServiceException("can not get student by this id"));
        });
    }

    @Test
    void When_Get_All_Students_Then_True() {
        Course course1 = new Course("Math");
        Group group1 = new Group(course1, "BI-21");
        Student student1 = new Student(group1, "Oleh Ivaskiv", "666666666", "OLEH@gmail.com", "London");

        Course course2 = new Course("English");
        Group group2 = new Group(course2, "KN-21");
        Student student2 = new Student(group2, "Alex", "2222222", "Alex@gmail.com", "London");

        Course course3 = new Course("Biology");
        Group group3 = new Group(course3, "IK-21");
        Student student3 = new Student(group3, "Max", "0001110001", "Max@gmail.com", "London");

        List<Student> expectedStudents = new LinkedList<Student>();
        expectedStudents.add(student1);
        expectedStudents.add(student2);
        expectedStudents.add(student3);

        assertDoesNotThrow(() -> {
            Mockito.lenient().when(studentServiceImpl.getAll()).thenReturn(expectedStudents);
        });

    }

    @Test
    void When_Delete_Student_By_Id_Then_True() {
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(studentServiceImpl.delete(3)).thenReturn(true);
        });
    }

    @Test
    void When_Delete_Not_Existing_Student_Then_Exception() {
        assertThrows(Exception.class, () -> {
            when(studentServiceImpl.delete(16)).thenThrow(new ServiceException("can not delete student by this id"));
        });

    }

    @Test
    void When_Update_Student_Then_True() {
        Course course = new Course("Math");
        Group group = new Group(course, "BI-21");
        Student student = new Student(group, "******", "8984745", "OLEH@gmail.com", "Kyiv");
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(studentServiceImpl.update(student)).thenReturn(true);
        });
    }

    @Test
    void When_Update_NotExisting_Student_Then_Exception() {
        Course course = new Course("Math");
        Group group = new Group(course, "BI-21");
        Student student = new Student(group, "******", "8984745", "OLEH@gmail.com", "Kyiv");
        assertThrows(Exception.class, () -> {
            when(studentServiceImpl.update(student)).thenThrow(new ServiceException("can not update this student"));
        });
    }

    @Test
    void When_Add_Student_To_Group_Then_True() {
        Optional<Student> student = Optional.of(new Student(null, null, null, null, null));
        Optional<Group> group = Optional.of(new Group(null, null));
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(studentServiceImpl.addStudentToGroup(student, group)).thenReturn(true);
        });

    }

    @Test
    void When_Remove_Student_From_Group_Then_True() {
        Optional<Student> student = Optional.of(new Student(null, null, null, null, null));
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(studentServiceImpl.removeStudentFromGroup(student)).thenReturn(true);
        });
    }

    @Test
    void When_Remove_Student_From_Lecture_Then_True() {
        Optional<Lecture> lecture = Optional.of(new Lecture(null, null, null, null));
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(studentServiceImpl.removeStudentFromLecture(lecture)).thenReturn(true);
        });
    }

    @Test
    void When_Add_Student_To_Lecture_Then_True() {
        Optional<Student> student = Optional.of(new Student(null, null, null, null, null));
        Optional<Lecture> lecture = Optional.of(new Lecture(null, null, null, null));
        assertDoesNotThrow(() -> {
            Mockito.lenient().when(studentServiceImpl.addStudentToLecture(student, lecture)).thenReturn(true);
        });
    }

    @Test
    void When_Add_Not_Existing_Student_Or_To_Not_Existing_Group_Then_Exception() {
        Optional<Student> student = Optional.of(new Student(null, null, null, null, null));
        Optional<Group> group = Optional.of(new Group(null, null));
        assertThrows(Exception.class, () -> {
            when(studentServiceImpl.addStudentToGroup(student, group))
                    .thenThrow(new ServiceException("can not add student to group"));
        });
    }

    @Test
    void When_Remove_Not_Existing_Student_Or_From_Not_Existing_Group_Then_Exception() {
        Optional<Student> student = Optional.of(new Student(null, null, null, null, null));
        assertThrows(Exception.class, () -> {
            when(studentServiceImpl.removeStudentFromGroup(student))
                    .thenThrow(new ServiceException("can not remove student from group"));
        });
    }

    @Test
    void When_Remove_Not_Existing_Student_Or_From_Not_Existing_Lecture_Then_Exception() {
        Optional<Lecture> lecture = Optional.of(new Lecture(null, null, null, null));
        assertThrows(Exception.class, () -> {
            when(studentServiceImpl.removeStudentFromLecture(lecture))
                    .thenThrow(new ServiceException("can not remove student from lecture"));
        });
    }

    @Test
    void When_Add_Not_Existing_Student_Or_To_Not_Existing_Lecture_Then_Exception() {
        Optional<Student> student = Optional.of(new Student(null, null, null, null, null));
        Optional<Lecture> lecture = Optional.of(new Lecture(null, null, null, null));
        assertThrows(Exception.class, () -> {
            when(studentServiceImpl.addStudentToLecture(student, lecture))
                    .thenThrow(new ServiceException("can not add stident to lecture"));
        });
    }
}
