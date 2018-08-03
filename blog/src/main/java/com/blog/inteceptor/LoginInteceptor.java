package com.blog.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInteceptor extends HandlerInterceptorAdapter{

	@Override//alt+shift+s可以找到重写的方法
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getSession().getAttribute("user")==null) {
			response.sendRedirect("/admin");
			return false;
		}
		
		return true;
	}

	
}
