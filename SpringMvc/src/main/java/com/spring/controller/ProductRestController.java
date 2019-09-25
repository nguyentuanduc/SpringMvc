package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.Product;
import com.spring.persistence.SessionUtil;

@RestController
@RequestMapping(value = "rest/product")
public class ProductRestController {

	@Autowired
	public  SessionUtil sessionUtil;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Product> listProduct(){
		System.out.println("ProductRestController products");
		List<Product> list = sessionUtil.listProducts();
		
		return list;
	}
}
