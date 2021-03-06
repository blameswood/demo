package com.sincetimes.website.app.security.interfaces;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.sincetimes.website.app.security.vo.UserVO;


public interface SecureControllerInterface {
	/** 设置登录用  */
	default void setUser(Model model, HttpServletRequest req) {
		Object _user = req.getSession().getAttribute("user");
		if(_user instanceof UserVO){
			model.addAttribute("user", _user);
		}
	}
}
