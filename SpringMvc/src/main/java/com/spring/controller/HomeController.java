package com.spring.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class);
	
//	@Autowired
//	public  SessionUtil sessionUtil;
	
	@RequestMapping("/hello")
	public String hello(Model model, HttpServletRequest request) {
		logger.info("hello  begin");
		model.addAttribute("greeting","hello MVC");
		
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		model.addAttribute("path",path);
		logger.info("hello  end");
		return "hello";
	}
	
	@RequestMapping("/deny")
	public String deny(Model model, HttpServletRequest request) {
		logger.info("deny  begin");
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		model.addAttribute("path",path);
		logger.info("deny  end");
		return "common/deny";
	}
	
	@RequestMapping("/about")
	public String about(Model model, HttpServletRequest request) {
		logger.info("about  begin");
		model.addAttribute("greeting","hello MVC");
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		model.addAttribute("path",path);
		logger.info("about  end");
		return "about";
	}
}
