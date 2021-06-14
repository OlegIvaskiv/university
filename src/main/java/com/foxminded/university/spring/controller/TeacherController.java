package com.foxminded.university.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.service.TeacherService;
import com.foxminded.university.spring.service.exception.ServiceException;

@Controller
public class TeacherController {

	private TeacherService teacherService;

	@Autowired
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@RequestMapping(value = "/teachers")
	public ModelAndView getAll(ModelAndView model) throws ServiceException {
		List<Teacher> teachers = teacherService.getAll();
		model.addObject("teachers", teachers);
		model.setViewName("teacher/list");
		return model;
	}

	@RequestMapping(value = "/teachers/create", method = RequestMethod.GET)
	public ModelAndView createAudience(ModelAndView model) {
		Teacher teacher = new Teacher();
		model.addObject("teacher", teacher);
		model.setViewName("teacher/create");
		return model;
	}

	@RequestMapping(value = "/teachers/create/save", method = RequestMethod.POST)
	public ModelAndView saveAudience(@ModelAttribute Teacher teacher) throws ServiceException {
		if (teacher.getId() == 0) {
			teacherService.create(teacher);
		} else {
			teacherService.update(teacher);
		}
		return new ModelAndView("redirect:/teachers");
	}

	@RequestMapping(value = "/teachers/edit{id}", method = RequestMethod.GET)
	public ModelAndView updateAudience(HttpServletRequest request) throws ServiceException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Teacher teacher = teacherService.getById(id);
		ModelAndView model = new ModelAndView("teacher/create");
		model.addObject("teacher", teacher);
		return model;
	}

	@RequestMapping(value = "/teachers/delete{id}", method = RequestMethod.POST)
	public ModelAndView deleteAudience(HttpServletRequest request) throws ServiceException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		teacherService.delete(id);
		return new ModelAndView("redirect:/teachers");
	}

}
