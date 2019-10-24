package com.spring.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.spring.entity.Category;
import com.spring.entity.Product;
import com.spring.persistence.SessionUtil;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
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

@Controller
public class ProductController {

	@Autowired
	public  SessionUtil sessionUtil;
	
	private static final Logger logger = Logger.getLogger(ProductController.class);
	
	@RequestMapping("/product/all")
	public String list(Model model,  HttpServletRequest request) {
		logger.info("list begin ");
		String currentUserName = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		
		List<Product> list = sessionUtil.listProducts();
		model.addAttribute("greeting","hello MVC");
		model.addAttribute("products",list);
		
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		model.addAttribute("path",path);
		model.addAttribute("username",currentUserName);
		logger.info("list end ");

		return "products";
	}

	@RequestMapping(value ="/product/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		logger.info("getAddNewProductForm begin ");
		Product product = new Product();
		List<Category> categorys = sessionUtil.listCategory();
		model.addAttribute("newProduct", product);
		logger.info("newProduct  " + product.toString());
		model.addAttribute("categorys", categorys);
		logger.info("getAddNewProductForm end ");

		return "addProduct";
	}

	@RequestMapping(value ="/product/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct, BindingResult result, HttpServletRequest request, Model model) {
		logger.info("processAddNewProductForm begin ");
		logger.info("newProduct begin " + newProduct.toString());
		if(result.hasErrors()) {
			List<Category> categorys = sessionUtil.listCategory();
			model.addAttribute("categorys", categorys);
			return "addProduct";
		}
		logger.info("newProduct" + newProduct.toString());

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
		
		System.out.println(newProduct);
		logger.info("processAddNewProductForm end ");
		return "redirect:/product/all";
	}

	@RequestMapping(value ="/remove", method = RequestMethod.GET)
	public String removeProductPublish(Model model,  HttpServletRequest request, @RequestParam("id") String producId) {
		logger.info("removeProductPublish begin ");
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		model.addAttribute("path",path);
		Product product = null;
		if(NumberUtils.isDigits(producId)) {
			int id = Integer.parseInt(producId);
			product = sessionUtil.getProductById(id);
			sessionUtil.removePublish(product);
			model.addAttribute("product",product);
		}
		logger.info("removeProductPublish end ");
		return "product";
	}



	@RequestMapping(value ="/product", method = RequestMethod.GET)
	public String getProductById(Model model,  HttpServletRequest request, @RequestParam("id") String producId) {
		logger.info("getProductById begin ");
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		model.addAttribute("path",path);
		Product product = null;
		if(NumberUtils.isDigits(producId)) {
			int id = Integer.parseInt(producId);
			product = sessionUtil.getProductById(id);
			model.addAttribute("product",product);
		}
		
		logger.info("getProductById end ");
		return "product";
	}

	@RequestMapping(value ="/product/update", method = RequestMethod.GET)
	public String update(Model model,  HttpServletRequest request, @RequestParam("id") String producId) {
		logger.info("update get begin ");
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

		model.addAttribute("path",path);
		Product product = null;
		if(NumberUtils.isDigits(producId)) {
			int id = Integer.parseInt(producId);
			product = sessionUtil.getProductById(id);
			List<Category> listCategorys = sessionUtil.listCategory();
			Set<Category> setCategorys = product.getCategorys();
			List<Category> filterCategorys = new ArrayList<Category>();
			filterCategorys.addAll(listCategorys);
			
			
			for(Category element_set : setCategorys) {
				for(Category element_list : listCategorys) {
					if(element_set.getCategory_id().equals(element_list.getCategory_id())) {
					filterCategorys.remove(element_list);
					}
				}
			}
			
			model.addAttribute("filterCategorys", filterCategorys);
			model.addAttribute("owncategorys", setCategorys);
		}

		model.addAttribute("updateProduct", product);
		logger.info("updateProduct  "+ product.toString());
		logger.info("update end ");

		return "updateProduct";
	}
	
	@RequestMapping(value ="/product/update", method = RequestMethod.POST)
	public String processUpdateProductForm(Model model,@ModelAttribute("updateProduct") Product updateProduct, BindingResult result, HttpServletRequest request) {
		logger.info("processUpdateProductForm begin ");
		logger.info("updateProduct " + updateProduct.toString());
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		model.addAttribute("path",path);

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
		logger.info("processUpdateProductForm end ");
		return "product";
	}
	
	
	@RequestMapping(value ="/product/delete", method = RequestMethod.GET)
	public String delete(Model model,  HttpServletRequest request, @RequestParam("id") String producId) {
		logger.info("delete begin ");
		logger.info("delete  producId " + producId);
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		model.addAttribute("path",path);
		Product product = null;
		if(NumberUtils.isDigits(producId)) {
			int id = Integer.parseInt(producId);
			product = sessionUtil.getProductById(id);
			sessionUtil.delete(product);
		}
		logger.info("delete begin ");
		return "redirect:/product/all";
	}
	
	@RequestMapping("/product/limit")
	public String getListProductBylimitTime(Model model,  HttpServletRequest request, @RequestParam("day") String day) {
		logger.info("getListProductBylimitTime begin ");
		logger.info("day  " + day);
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
		model.addAttribute("path",path);
		model.addAttribute("username",currentUserName);
		logger.info("getListProductBylimitTime end ");
		return "products";
	}
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "name", "unitPrice", "description",
				"unitsInStock", "condition", "unitsInOrder", "discontinued", "productImage", "listCategory", "listPublish", "categorys", "publishs");
	}













}
