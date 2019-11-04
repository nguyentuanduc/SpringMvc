package com.spring.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	private String pathUtil;

	private int number = 1;

	private String getPath( HttpServletRequest request){
		if(pathUtil == null){
			pathUtil = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		}
		logger.info("getPath " + pathUtil);
		return pathUtil;
	}

	
	@RequestMapping("/hello")
	public String hello(Model model, HttpServletRequest request) {
		logger.info("hello  begin");
		model.addAttribute("greeting","hello MVC");
		number = number + 1;
		System.out.println(number);

		String path = getPath(request);
		model.addAttribute("path",path);
		logger.info("hello  end");
		return "hello";
	}
	
	@RequestMapping("/deny")
	public String deny(Model model, HttpServletRequest request) {
		logger.info("deny  begin");
		String path = getPath(request);
		model.addAttribute("path",path);
		logger.info("deny  end");
		return "common/deny";
	}
	
	@RequestMapping("/about")
	public String about(Model model, HttpServletRequest request) {
		logger.info("about  begin");
		System.out.println(number);
		model.addAttribute("greeting","hello MVC");
		String path = getPath(request);
		model.addAttribute("path",path);
		logger.info("about  end");
		return "about";
	}
}
