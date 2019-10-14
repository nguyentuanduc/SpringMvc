package com.spring.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.entity.Contact;
import com.spring.entity.Product;
import com.spring.persistence.SessionUtil;

@Controller
public class ProductController {

	@Autowired
	public  SessionUtil sessionUtil;

	
	
	@RequestMapping("/product/all")
	public String list(Model model,  HttpServletRequest request) {
		//System.out.println("-----------------------------");
		//sessionUtil.getProductById();
		//System.out.println("-----------------------------");
		System.out.println("products");
		String currentUserName = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		
		List<Product> list = sessionUtil.listProducts();
		model.addAttribute("greeting","hello MVC");
		
		model.addAttribute("products",list);
		
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		System.out.println("-----------------------------");
		System.out.println(path);
		System.out.println("-----------------------------");
		model.addAttribute("path",path);
		model.addAttribute("username",currentUserName);
		return "products";
	}

	@RequestMapping(value ="/product/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("newProduct", product);
		return "addProduct";
	}

	@RequestMapping(value ="/product/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, BindingResult result, HttpServletRequest request) {
		System.out.println(newProduct);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        //newProduct.setCreated(timestamp);
		/*Product product = sessionUtil.addProduct(newProduct);
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
		
		System.out.println(newProduct);
		return "redirect:/product/all";*/
        
        return "addProduct";
	}

	//Products [id=0, name=null, description=null, unit_price=null,
	//manufacturer=null, category=null, condition=null, unitsInStock=0, unitsInOrder=0, discontinued=null]

	@RequestMapping(value ="/product", method = RequestMethod.GET)
	public String getProductById(Model model,  HttpServletRequest request, @RequestParam("id") String producId) {
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		System.out.println("-----------------------------");
		System.out.println(path);
		System.out.println("-----------------------------");
		model.addAttribute("path",path);
		Product product = null;
		if(NumberUtils.isDigits(producId)) {
			int id = Integer.parseInt(producId);
			product = sessionUtil.getProductById(id);
			model.addAttribute("product",product);
		}
		
		return "product";
	}

	@RequestMapping(value ="/product/update", method = RequestMethod.GET)
	public String update(Model model,  HttpServletRequest request, @RequestParam("id") String producId) {
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		System.out.println("-----------------------------");
		System.out.println(path);
		System.out.println("-----------------------------");
		model.addAttribute("path",path);
		Product product = null;
		if(NumberUtils.isDigits(producId)) {
			int id = Integer.parseInt(producId);
			product = sessionUtil.getProductById(id);
			model.addAttribute("product",product);
		}
		model.addAttribute("updateProduct", product);
		return "updateProduct";
	}
	
	@RequestMapping(value ="/product/update", method = RequestMethod.POST)
	public String processUpdateProductForm(Model model,@ModelAttribute("updateProduct") Product updateProduct, BindingResult result, HttpServletRequest request) {
		String path1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		System.out.println("-----------------------------");
		System.out.println(path1);
		System.out.println("-----------------------------");
		model.addAttribute("path",path1);
		
		System.out.println(updateProduct);
		sessionUtil.updateProduct(updateProduct);
		MultipartFile productImage = updateProduct.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		if (productImage!=null && !productImage.isEmpty()) {
			try {
					System.out.println(rootDirectory);
					productImage.transferTo(new File(rootDirectory + "resources//img//"+ updateProduct.getId() + ".jpg"));
			}
			catch (Exception e) { throw new RuntimeException("Product Image saving failed", e);
			}
		}
		int id = updateProduct.getId();
		Product product = sessionUtil.getProductById(id);
		model.addAttribute("product",product);
		System.out.println(updateProduct);
		return "product";
	}
	
	
	@RequestMapping(value ="/product/delete", method = RequestMethod.GET)
	public String delete(Model model,  HttpServletRequest request, @RequestParam("id") String producId) {
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		System.out.println("-----------------------------");
		System.out.println(path);
		System.out.println("-----------------------------");
		model.addAttribute("path",path);
		Product product = null;
		if(NumberUtils.isDigits(producId)) {
			int id = Integer.parseInt(producId);
			product = sessionUtil.getProductById(id);
			sessionUtil.delete(product);
			
		}
		return "redirect:/product/all";
	}
	
	@RequestMapping("/product/limit")
	public String getListProductBylimitTime(Model model,  HttpServletRequest request, @RequestParam("day") String day) {
		//System.out.println("-----------------------------");
		//sessionUtil.getProductById();
		//System.out.println("-----------------------------");
		System.out.println("getListProductBylimitTime");
		String currentUserName = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		List<Product> list = null;
		if(NumberUtils.isDigits(day)) {
			int numberOfDay = Integer.parseInt(day);
			list = sessionUtil.getListProductsByTime(numberOfDay);

		}
		
		model.addAttribute("greeting","hello MVC");
		
		model.addAttribute("products",list);
		
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		System.out.println("-----------------------------");
		System.out.println(path);
		System.out.println("-----------------------------");
		model.addAttribute("path",path);
		model.addAttribute("username",currentUserName);
		return "products";
	}
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "name", "unitPrice", "description", "manufacturer", "category",
				"unitsInStock", "condition", "unitsInOrder", "discontinued", "productImage", "listCategory");
	}













}
