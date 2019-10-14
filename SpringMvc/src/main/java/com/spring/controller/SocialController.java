package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.authority.UserInformation;
import com.spring.social.MySocialUserDetails;
import com.spring.social.MyUserAccount;
import com.spring.social.MyUserAccountDAO;
import com.spring.social.MyUserAccountForm;
import com.spring.social.MyUserAccountValidator;
import com.spring.social.SecurityUtil;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

@Controller
// Need to use RedirectAttributes
@EnableWebMvc
public class SocialController {

	private static final Logger logger = Logger.getLogger(SocialController.class);

	@Autowired
	private MyUserAccountDAO myUserAccountDAO;

	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	private UsersConnectionRepository connectionRepository;

	@Autowired
	private MyUserAccountValidator myUserAccountValidator;

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == MyUserAccountForm.class) {
			dataBinder.setValidator(myUserAccountValidator);
		}
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String homePage(Model model) {
		logger.info("homePage ");
		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if (userDetails != null) {
				System.out.println(userDetails.getPassword());
				System.out.println(userDetails.getUsername());
				System.out.println(userDetails.isEnabled());

				model.addAttribute("userDetails", userDetails);
			}
		} catch (Exception e) {
		}
		return "index";
	}

	/*@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		logger.info("welcomePage ");
		return "social/login";
	}*/

	// User login via Social,
	// but not allow access basic info.
	// webapp will redirect to /signin.
	@RequestMapping(value = { "/signin" }, method = RequestMethod.GET)
	public String signInPage(Model model) {
		logger.info("signInPage ");
		return "redirect:/login";
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
	public String signupPage(WebRequest request, Model model) {
		logger.info("signupPage ");
		ProviderSignInUtils providerSignInUtils //
				= new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);

		Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);

		//
		MyUserAccountForm myForm = null;
		//
		if (connection != null) {
			myForm = new MyUserAccountForm(connection);
		} else {
			myForm = new MyUserAccountForm();
		}
		model.addAttribute("myForm", myForm);
		return "social/signup_custom";
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public String signupSave(WebRequest request, //
			Model model, //
			@ModelAttribute("myForm") @Validated MyUserAccountForm accountForm, //
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {
		logger.info("signupSave  POST");
		// If validation has error.
		if (result.hasErrors()) {
			return "social/signup";
		}

		MyUserAccount registered = null;

		try {
			registered = myUserAccountDAO.registerNewUserAccount(accountForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("errorMessage", "Error " + ex.getMessage());
			return "signup";
		}

		if (accountForm.getSignInProvider() != null) {
			ProviderSignInUtils providerSignInUtils //
					= new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);

			// If the user is signing in by using a social provider, this method
			// call stores the connection to the UserConnection table.
			// Otherwise, this method does not do anything.
			providerSignInUtils.doPostSignUp(registered.getId(), request);
		}
		// After register, Logs the user in.
		SecurityUtil.logInUser(registered);

		return "redirect:/userInfo";
	}

	@RequestMapping(value = { "/userInfo" }, method = RequestMethod.GET)
	public String userInfoPage(WebRequest request, Model model, HttpServletRequest httpServletRequest) {
		logger.info("userInfoPage ");
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String path = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath();
		model.addAttribute("path",path);

		if (object instanceof UserInformation) {
			UserInformation userInformation = (UserInformation) object;
			MyUserAccount userInfo = userInformation.getMyUserAccount();
			logger.info("UserInformation userInfo " + userInfo.getUserName());
			model.addAttribute("userInfo",userInfo);
		}
		else if (object instanceof MySocialUserDetails) {
			MySocialUserDetails myUserAccount = (MySocialUserDetails) object;
			MyUserAccount userInfo = myUserAccount.getMyUserAccount();
			logger.info("MySocialUserDetails userInfo " + userInfo.getUserName());
			model.addAttribute("userInfo",userInfo);
		}
		return "social/userinfo_custom";
	}

}