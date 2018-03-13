package com.prapps.tutorial.spring.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class PageController {

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/index.html", method = RequestMethod.GET)
	public ModelAndView home() {
		Map<String, Object> models = new HashMap<>();
		models.put("message", "Hello World !");
		return new ModelAndView("index", models);
	}
	
	@RequestMapping(value="/error", method = RequestMethod.GET)
	public ModelAndView error() {
		Map<String, Object> models = new HashMap<>();
		models.put("errorMessage", "Error Happened");
		return new ModelAndView("error", models);
	}
	
	@RequestMapping(value = "/manage/login", method = RequestMethod.POST)
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public ModelAndView manage() {
		Map<String, Object> models = new HashMap<>();
		models.put("message", "welcome to manage dashboard");
		return new ModelAndView("manage", models);
	}
	

	
	@RequestMapping(value = "/manage/save", method = RequestMethod.POST)
	public ModelAndView manageUpdate(@ModelAttribute("message") String message) {
		Map<String, Object> models = new HashMap<>();
		models.put("message", message);
		return new ModelAndView("manage", models);
	}
}
