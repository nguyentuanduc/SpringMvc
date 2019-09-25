package com.spring.app;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.persistence.SessionUtil;

public class App {

	@Autowired
	public  SessionUtil sessionUtil;
	
	
	public SessionUtil getSessionUtil() {
		return sessionUtil;
	}

	public void setSessionUtil(SessionUtil sessionUtil) {
		this.sessionUtil = sessionUtil;
	}
/*
	public static void main(String[] args) {
		SessionUtil instance = new SessionUtil();
		System.out.println(123);
		//App app = new App();
		//app.start();
		instance.updateHQLContact();
	}*/

	public  void start() {
		System.out.println(124);
		sessionUtil.listProducts();
	}
}
