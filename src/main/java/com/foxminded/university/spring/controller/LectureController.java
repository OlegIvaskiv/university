package com.foxminded.university.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.foxminded.university.model.Audience;
import com.foxminded.university.model.Date;
import com.foxminded.university.model.Lecture;
import com.foxminded.university.model.Student;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.spring.dto.LectureRequestCreator;
import com.foxminded.university.spring.service.AudienceService;
import com.foxminded.university.spring.service.DateService;
import com.foxminded.university.spring.service.LectureService;
import com.foxminded.university.spring.service.StudentService;
import com.foxminded.university.spring.service.TeacherService;
import com.foxminded.university.spring.service.exception.ServiceException;

@Controller
public class LectureController {

	private LectureService lectureService;

	private StudentService studentService;

	private TeacherService teacherService;

	private AudienceService audienceService;

	private DateService dateService;

	@Autowired
	public LectureController(LectureService lectureService, StudentService studentService,
			TeacherService teacherService, AudienceService audienceService, DateService dateService) {
		this.audienceService = audienceService;
		this.lectureService = lectureService;
		this.studentService = studentService;
		this.teacherService = teacherService;
		this.dateService = dateService;
	}

	@RequestMapping(value = "/lectures")
	public ModelAndView getAll(ModelAndView model) throws ServiceException {
		List<Lecture> lectures = lectureService.getAll();
		model.addObject("lectures", lectures);
		model.setViewName("lecture/list");
		return model;
	}

	@RequestMapping(value = "/lectures/create", method = RequestMethod.GET)
	public ModelAndView createAudience(ModelAndView model) throws ServiceException {
		LectureRequestCreator lectureRequest = new LectureRequestCreator();
		List<Student> students = studentService.getAll();
		List<Teacher> teachers = teacherService.getAll();
		List<Audience> audiences = audienceService.getAll();
		List<Date> dates = dateService.getAll();
		model.addObject("lectureRequest", lectureRequest);
		model.addObject("students", students);
		model.addObject("teachers", teachers);
		model.addObject("audiences", audiences);
		model.addObject("dates", dates);
		model.setViewName("lecture/create");
		return model;
	}

	@RequestMapping(value = "/lectures/create/save", method = RequestMethod.POST)
	public ModelAndView saveAudience(@ModelAttribute LectureRequestCreator lectureRequest) throws ServiceException {
		Student student = studentService.getById(lectureRequest.getStudentId());
		Teacher teacher = teacherService.getById(lectureRequest.getTeacherId());
		Audience audience = audienceService.getById(lectureRequest.getAudienceId());
		Date date = dateService.getById(lectureRequest.getDateId());

		Lecture lectureCreate = new Lecture(teacher, student, audience, date);
		if (lectureRequest.getId() == 0) {
			lectureService.create(lectureCreate);
		} else {
			Lecture lectureUpdate = new Lecture(lectureRequest.getId(), teacher, student, audience, date);
			lectureService.update(lectureUpdate);
		}
		return new ModelAndView("redirect:/lectures");
	}

	@RequestMapping(value = "/lectures/edit{id}", method = RequestMethod.GET)
	public ModelAndView updateAudience(HttpServletRequest request) throws ServiceException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Lecture lecture = lectureService.getById(id);
		List<Student> students = studentService.getAll();
		List<Teacher> teachers = teacherService.getAll();
		List<Audience> audiences = audienceService.getAll();
		List<Date> dates = dateService.getAll();
		LectureRequestCreator lectureRequest = new LectureRequestCreator(id, lecture.getTeacher().getId(),
				lecture.getStudent().getId(), lecture.getAudience().getId(), lecture.getDate().getId());
		ModelAndView model = new ModelAndView("lecture/create");
		model.addObject("lectureRequest", lectureRequest);
		model.addObject("students", students);
		model.addObject("teachers", teachers);
		model.addObject("audiences", audiences);
		model.addObject("dates", dates);
		return model;
	}

	@RequestMapping(value = "/lectures/delete{id}", method = RequestMethod.POST)
	public ModelAndView deleteAudience(HttpServletRequest request) throws ServiceException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		lectureService.delete(id);
		return new ModelAndView("redirect:/lectures");
	}

}
