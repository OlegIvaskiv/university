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
import com.foxminded.university.model.Group;
import com.foxminded.university.spring.dto.GroupRequestCreator;
import com.foxminded.university.spring.service.CourseService;
import com.foxminded.university.spring.service.GroupService;
import com.foxminded.university.spring.service.exception.ServiceException;

@Controller
public class GroupController {

	private GroupService groupService;

	private CourseService courseService;

	@Autowired
	public GroupController(GroupService groupService, CourseService courseService) {
		this.groupService = groupService;
		this.courseService = courseService;
	}

	@RequestMapping(value = "/groups")
	public ModelAndView getAll(ModelAndView model) throws ServiceException {
		List<Group> groups = groupService.getAll();
		model.addObject("groups", groups);
		model.setViewName("group/list");
		return model;
	}

	@RequestMapping(value = "/groups/create", method = RequestMethod.GET)
	public ModelAndView createAudience(ModelAndView model) throws ServiceException {
		GroupRequestCreator groupRequest = new GroupRequestCreator();
		List<Course> courses = courseService.getAll();
		model.addObject("groupRequest", groupRequest);
		model.addObject("courses", courses);
		model.setViewName("group/create");
		return model;
	}

	@RequestMapping(value = "/groups/create/save", method = RequestMethod.POST)
	public ModelAndView saveAudience(@ModelAttribute GroupRequestCreator groupRequest) throws ServiceException {
		if (groupRequest.getId() == 0) {
			Group group = new Group(courseService.getById(groupRequest.getCourseId()), groupRequest.getName());
			groupService.create(group);
		} else {
			Group groupUpdate = new Group(groupRequest.getId(), courseService.getById(groupRequest.getCourseId()),
					groupRequest.getName());
			groupService.update(groupUpdate);
		}
		return new ModelAndView("redirect:/groups");
	}

	@RequestMapping(value = "/groups/edit{id}", method = RequestMethod.GET)
	public ModelAndView updateAudience(HttpServletRequest request) throws ServiceException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Group group = groupService.getById(id);
		GroupRequestCreator groupRequest = new GroupRequestCreator(id, group.getCourse().getId(), group.getName());
		List<Course> courses = courseService.getAll();
		ModelAndView model = new ModelAndView("group/create");
		model.addObject("groupRequest", groupRequest);
		model.addObject("courses", courses);
		return model;
	}

	@RequestMapping(value = "/groups/delete{id}", method = RequestMethod.POST)
	public ModelAndView deleteAudience(HttpServletRequest request) throws ServiceException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		groupService.delete(id);
		return new ModelAndView("redirect:/groups");
	}
}
