package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value ="/login", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request) {
		String currentUserName = null;

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		System.out.println("currentUserName " +currentUserName );

		if(SecurityContextHolder.getContext().getAuthentication() != null &&
			SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
			//when Anonymous Authentication is enabled
			!(SecurityContextHolder.getContext().getAuthentication()
	        instanceof AnonymousAuthenticationToken)) {
			return "redirect:/";
		}
		
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		model.addAttribute("path",path);
		System.out.println("login");
		return "login";
	}
	
	
	
}
