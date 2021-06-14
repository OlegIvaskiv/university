package com.foxminded.university.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.foxminded.university.model.Course;
import com.foxminded.university.spring.service.CourseService;
import com.foxminded.university.spring.service.exception.ServiceException;

@Controller
public class CourseController {

	CourseService courseService;

	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@RequestMapping(value = "/courses")
	public ModelAndView getAll(ModelAndView model) throws ServiceException {
		List<Course> courses = courseService.getAll();
		model.addObject("courses", courses);
		model.setViewName("course/list");
		return model;
	}

	@RequestMapping(value = "/courses/create", method = RequestMethod.GET)
	public ModelAndView createAudience(ModelAndView model) {
		Course course = new Course();
		model.addObject("course", course);
		model.setViewName("course/create");
		return model;
	}

	@RequestMapping(value = "/courses/create/save", method = RequestMethod.POST)
	public ModelAndView saveAudience(@ModelAttribute Course course) throws ServiceException {
		if (course.getId() == 0) {
			courseService.create(course);
		} else {
			courseService.update(course);
		}
		return new ModelAndView("redirect:/courses");
	}

	@RequestMapping(value = "courses/edit{id}", method = RequestMethod.GET)
	public ModelAndView updateAudience(HttpServletRequest request) throws ServiceException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Course course = courseService.getById(id);
		ModelAndView model = new ModelAndView("course/create");
		model.addObject("course", course);
		return model;
	}

	@RequestMapping(value = "courses/delete{id}", method = RequestMethod.POST)
	public ModelAndView deleteAudience(HttpServletRequest request) throws ServiceException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		courseService.delete(id);
		return new ModelAndView("redirect:/courses");
	}
}
