package com.spring.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.spring.entity.Product;
import com.spring.entity.UserCustom;
import com.spring.persistence.SessionUtil;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "rest")
public class ProductRestController {

	@Autowired
	public  SessionUtil sessionUtil;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Product> listProduct(){
		System.out.println("ProductRestController products");
		List<Product> list = sessionUtil.listProductsDetail();
		
		return list;
	}
	
	@RequestMapping(value ="detail/{id}", method = RequestMethod.GET)
	public Product getProductById( @PathVariable(value = "id") String id) {
		System.out.println("-----------------------------");
		System.out.println("-----------------------------");
		Product product = null;
		if(NumberUtils.isDigits(id)) {
			product = sessionUtil.getProductById(Integer.parseInt(id));
		}
		
		return product;
	}


	@RequestMapping(value ="/add", method = RequestMethod.POST)
	public Product processAddNewProductForm( @RequestBody Product newProduct, HttpServletRequest request) {
		System.out.println(newProduct);
		
		Product product = sessionUtil.addProduct(newProduct);
		MultipartFile productImage = newProduct.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		if (productImage!=null && !productImage.isEmpty()) {
			try {
					System.out.println(rootDirectory);
					productImage.transferTo(new File(rootDirectory + "resources//img//"+ product.getId() + ".jpg"));
			}
			catch (Exception e) { throw new RuntimeException("Product Image saving failed", e);
			}
		}
		return product;
	}
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserCustom> listUser(){
		System.out.println("ProductRestController products");
		List<UserCustom> list = sessionUtil.listUser();
		
		return list;
	}
}
