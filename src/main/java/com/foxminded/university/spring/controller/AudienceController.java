package com.foxminded.university.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.foxminded.university.model.Audience;
import com.foxminded.university.spring.service.AudienceService;
import com.foxminded.university.spring.service.exception.ServiceException;

@Controller
public class AudienceController {

	private AudienceService audienceService;

	@Autowired
	public AudienceController(AudienceService audienceService) {
		this.audienceService = audienceService;
	}

	@RequestMapping(value = "/audiences")
	public String getAll(Model model) throws ServiceException {
		List<Audience> audiences = audienceService.getAll();
		model.addAttribute("audiences", audiences);
		return "audience/list";
	}

	@RequestMapping(value = "/audiences/create", method = RequestMethod.GET)
	public ModelAndView createAudience(ModelAndView model) {
		Audience audience = new Audience();
		model.addObject("audience", audience);
		model.setViewName("audience/create");
		return model;
	}

	@RequestMapping(value = "/audiences/create/save", method = RequestMethod.POST)
	public ModelAndView saveAudience(@ModelAttribute Audience audience) throws ServiceException {
		if (audience.getId() == 0) {
			audienceService.create(audience);
		} else {
			audienceService.update(audience);
		}
		return new ModelAndView("redirect:/audiences");
	}

	@RequestMapping(value = "audiences/edit{id}", method = RequestMethod.GET)
	public ModelAndView updateAudience(HttpServletRequest request) throws ServiceException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Audience audience = audienceService.getById(id);
		ModelAndView model = new ModelAndView("audience/create");
		model.addObject("audience", audience);
		return model;
	}

	@RequestMapping(value = "audiences/delete{id}", method = RequestMethod.POST)
	public ModelAndView deleteAudience(HttpServletRequest request) throws ServiceException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		audienceService.delete(id);
		return new ModelAndView("redirect:/audiences");
	}

}