package com.spring.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.spring.entity.Product;
import com.spring.persistence.SessionUtil;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "rest")
public class ProductRestController {

	private static final Logger logger = Logger.getLogger(ProductRestController.class);
	
	@Autowired
	public  SessionUtil sessionUtil;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Product> listProduct(){
		logger.info("listProduct  begin");
		List<Product> list = sessionUtil.listProductsDetail();
		logger.info("listProduct  end");
		return list;
	}
	
	@RequestMapping(value ="detail/{id}", method = RequestMethod.GET)
	public Product getProductById( @PathVariable(value = "id") String id) {
		logger.info("getProductById  begin");
		Product product = null;
		if(NumberUtils.isDigits(id)) {
			product = sessionUtil.getProductById(Integer.parseInt(id));
		}
		logger.info("getProductById  end");

		return product;
	}


	@RequestMapping(value ="/add", method = RequestMethod.POST)
	public Product processAddNewProductForm( @RequestBody Product newProduct, HttpServletRequest request) {
		logger.info("processAddNewProductForm  begin");
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
		logger.info("processAddNewProductForm  end");
		return product;
	}
	
	
	
	@RequestMapping(value ="/update", method = RequestMethod.PUT)
	public Product processUpdateProductForm(Model model,@RequestBody Product updateProduct, HttpServletRequest request) {
		logger.info("processUpdateProductForm  begin");
		logger.info("updateProduct  " + updateProduct.toString());
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
		logger.info("processUpdateProductForm  end");
		return product;
	}
	
	
	
	
	@RequestMapping(value ="/product/delete", method = RequestMethod.DELETE)
	public String delete(Model model,  HttpServletRequest request, @RequestParam("id") String producId) {
		logger.info("delete  begin");
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		System.out.println(path);
		model.addAttribute("path",path);
		Product product = null;
		if(NumberUtils.isDigits(producId)) {
			int id = Integer.parseInt(producId);
			product = sessionUtil.getProductById(id);
			sessionUtil.delete(product);
		}
		logger.info("delete  end");
		return "ok";
	}
	
	
	
}
