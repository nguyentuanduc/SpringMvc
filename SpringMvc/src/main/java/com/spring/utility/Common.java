package com.spring.utility;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service("common")
public class Common {

	private String path = null;

	private String loginType = null;
	
	public String getPath(HttpServletRequest request) {
		if(path == null) {
			path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		}
		return path;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	
	
	
}
