package com.foxminded.university.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.foxminded.university.model.Date;
import com.foxminded.university.spring.service.DateService;
import com.foxminded.university.spring.service.exception.ServiceException;

@Controller
public class DateController {

	DateService dateService;

	@Autowired
	public DateController(DateService dateService) {
		this.dateService = dateService;
	}

	@RequestMapping(value = "/dates")
	public ModelAndView getAll(ModelAndView model) throws ServiceException {
		List<Date> dates = dateService.getAll();
		model.addObject("dates", dates);
		model.setViewName("date/list");
		return model;
	}

	@RequestMapping(value = "/dates/create", method = RequestMethod.GET)
	public ModelAndView createAudience(ModelAndView model) {
		Date date = new Date();
		model.addObject("date", date);
		model.setViewName("date/create");
		return model;
	}

	@RequestMapping(value = "/dates/create/save", method = RequestMethod.POST)
	public ModelAndView saveAudience(@ModelAttribute Date date) throws ServiceException {
		if (date.getId() == 0) {
			dateService.create(date);
		} else {
			dateService.update(date);
		}
		return new ModelAndView("redirect:/dates");
	}

	@RequestMapping(value = "dates/edit", method = RequestMethod.GET)
	public ModelAndView updateAudience(HttpServletRequest request) throws ServiceException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Date date = dateService.getById(id);
		ModelAndView model = new ModelAndView("date/create");
		model.addObject("date", date);
		return model;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteAudience(HttpServletRequest request) throws ServiceException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		dateService.delete(id);
		return new ModelAndView("redirect:/dates");
	}

}
