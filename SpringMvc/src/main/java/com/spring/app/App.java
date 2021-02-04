package com.spring.app;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
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
		int m = 0;

		int n=5;//it is the number to be checked    
		m = n/2; 

		String test = "abcdaefgb";
		String[] array = test.split("");
		Hashtable<String,Integer> ht =new Hashtable<String, Integer>(); 

		for (String element : array) {
			System.out.println(element);
			
			if(ht.get(element) == null){
				ht.put(element,1); 
			}
			else {
				Integer value = ht.get(element);
				ht.put(element,value +1); 
			}
		}
		
		 // getting keySet() into Set
		 Set<String> setOfCountries = ht.keySet();
 
		 // for-each loop
		 for(String key : setOfCountries) {
  
			 System.out.println("Country : "  + key 
					 + "\t\t President : "  + ht.get(key));
		 }
		//App app = new App();
		///app.start();
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
		Product product = new Product();
		product.setCondition("new");
		System.out.println(product);
		Product result = instance.addProduct(product);
		System.out.println(result);
		
		
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
