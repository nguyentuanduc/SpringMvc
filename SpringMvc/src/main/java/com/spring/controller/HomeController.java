package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.entity.Contact;
import com.spring.persistence.SessionUtil;

@Controller
public class HomeController {

	@Autowired
	public  SessionUtil sessionUtil;
	
	@RequestMapping("/hello")
	public String hello(Model model) {
		System.out.println("hrelo");
		List<Contact> list = sessionUtil.listContact();
		model.addAttribute("greeting","hello MVC");
		model.addAttribute("contacts",list);
		return "hello";
	}
	
	@RequestMapping("/contacts")
	public String listContact(Model model) {
		System.out.println("hrelo");
		List<Contact> list = sessionUtil.listContact();
		model.addAttribute("greeting","hello MVC");
		model.addAttribute("contacts",list);
		return "contacts";
	}
	
	@RequestMapping("/about")
	public String about(Model model) {
		System.out.println("hrelo");
		List<Contact> list = sessionUtil.listContact();
		model.addAttribute("greeting","hello MVC");
		model.addAttribute("contacts",list);
		return "about";
	}
}
