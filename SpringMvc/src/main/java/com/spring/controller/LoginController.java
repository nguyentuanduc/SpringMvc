package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping(value ="/login", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request) {
		logger.info("login  begin");
		String currentUserName = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		logger.info("currentUserName " + currentUserName);

		if(SecurityContextHolder.getContext().getAuthentication() != null &&
			SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
			//when Anonymous Authentication is enabled
			!(SecurityContextHolder.getContext().getAuthentication()
	        instanceof AnonymousAuthenticationToken)) {
			return "redirect:/";
		}
		
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		model.addAttribute("path",path);
		logger.info("login  end");
		return "login";
	}
	
	
	
	
	@RequestMapping(value = "errors", method = RequestMethod.GET)
    public String renderErrorPage(Model model, HttpServletRequest httpRequest) {
		logger.info("renderErrorPage  begin");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);
 
        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Resource not found";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            }
        }
		model.addAttribute("errorMsg",errorMsg);
		logger.info("renderErrorPage  end");
        return "common/errorPage";
    }
     
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
    }
	
}
