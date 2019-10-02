package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value ="/login", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request) {
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		model.addAttribute("path",path);
		System.out.println("login");
		return "login";
	}
	
	
	
}
