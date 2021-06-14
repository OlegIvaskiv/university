package com.foxminded.university.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.foxminded.university.model.Group;
import com.foxminded.university.model.Student;
import com.foxminded.university.spring.dto.StudentRequestCreator;
import com.foxminded.university.spring.service.GroupService;
import com.foxminded.university.spring.service.StudentService;
import com.foxminded.university.spring.service.exception.ServiceException;

@Controller
public class StudentController {

	private StudentService studentService;

	private GroupService groupService;

	@Autowired
	public StudentController(StudentService studentService, GroupService groupService) {
		this.groupService = groupService;
		this.studentService = studentService;
	}

	@RequestMapping(value = "/students")
	public ModelAndView getAll(ModelAndView model) throws ServiceException {
		List<Student> students = studentService.getAll();
		model.addObject("students", students);
		model.setViewName("student/list");
		return model;
	}

	@RequestMapping(value = "/students/create", method = RequestMethod.GET)
	public ModelAndView createAudience(ModelAndView model) throws ServiceException {
		StudentRequestCreator studentRequest = new StudentRequestCreator();
		List<Group> groups = groupService.getAll();
		model.addObject("studentRequest", studentRequest);
		model.addObject("groups", groups);
		model.setViewName("student/create");
		return model;
	}

	@RequestMapping(value = "/students/create/save", method = RequestMethod.POST)
	public ModelAndView saveAudience(@ModelAttribute StudentRequestCreator studentRequest) throws ServiceException {

		if (studentRequest.getId() == 0) {
			Student student = new Student(groupService.getById(studentRequest.getGroupId()), studentRequest.getName(),
					studentRequest.getPhone(), studentRequest.getEmail(), studentRequest.getAdress());
			studentService.create(student);
		} else {
			Student student = new Student(studentRequest.getId(), groupService.getById(studentRequest.getGroupId()),
					studentRequest.getName(), studentRequest.getPhone(), studentRequest.getEmail(),
					studentRequest.getAdress());
			studentService.update(student);
		}
		return new ModelAndView("redirect:/students");
	}

	@RequestMapping(value = "/students/edit{id}", method = RequestMethod.GET)
	public ModelAndView updateAudience(HttpServletRequest request) throws ServiceException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Student student = studentService.getById(id);
		StudentRequestCreator studentRequest = new StudentRequestCreator(id, student.getGroup().getId(),
				student.getName(), student.getPhone(), student.getEmail(), student.getAdress());
		List<Group> groups = groupService.getAll();
		ModelAndView model = new ModelAndView("student/create");
		model.addObject("studentRequest", studentRequest);
		model.addObject("groups", groups);
		return model;
	}

	@RequestMapping(value = "/students/delete{id}", method = RequestMethod.POST)
	public ModelAndView deleteAudience(HttpServletRequest request) throws ServiceException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		studentService.delete(id);
		return new ModelAndView("redirect:/students");
	}
}
