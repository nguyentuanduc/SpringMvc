package com.spring.app;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.entity.Product;
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

	public static void main(String[] args) {
		
		
		App app = new App();
		app.start();
		/*SessionUtil instance = new SessionUtil();
		System.out.println(123);
		//App app = new App();
		app.start();
		instance.getAllProductDetails1();*/
	}

	public  void start() {
		System.out.println(124);
		// sessionUtil.listProducts();
		SessionUtil instance = new SessionUtil();

		instance.listUser();
		
		
		/*Product product = instance.getProductById(14);
		product.getPublishs().clear();

		// Publish publish = new Publish();
		// publish.setName("1111");
		// set one to many
		
		Set<String> listPublish = new HashSet<String>();
		listPublish.add("aaa3");

		product.setListPublish(listPublish);
		
		instance.updateProduct(product);*/
		
	}
}
