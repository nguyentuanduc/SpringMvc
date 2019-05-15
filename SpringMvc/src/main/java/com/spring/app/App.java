package com.spring.app;

import com.spring.persistence.SessionUtil;

public class App {

	public static void main(String[] args) {
		SessionUtil instance = new SessionUtil();
		instance.listContactDetail();
		
	}

	
}
