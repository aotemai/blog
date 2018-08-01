package com.blog.handler;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ControllerExceptionHandler {

	//用来记录异常信息
	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	//ModelAndView这个对象可以用来返回我们的页面
	@ExceptionHandler(Exception.class)//@ExceptionHandler这个注解是用来表示下面的方法是可以用来做拦截的，拦截所有的Exception.class
	public ModelAndView exceptionHander(HttpServletRequest request,Exception e) throws Exception {
		//logger.error用法可以将request请求到的url传递到Request URL:{}里面
		logger.error("Request URL:{},Exception:{}",request.getRequestURL(),e);
				
			//因为上面的Exception已经拦截了所有的Exception，所以要做个判断区分开来方便自己选择抛出的异常页面
			if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null) {
				throw e;
			}
		
		//上面记录之后需要返回页面，返回页面需要用ModelAndView接收一下
		ModelAndView mv = new ModelAndView();
		//接收url和异常信息
		mv.addObject("url",request.getRequestURL());
		mv.addObject("exception",e);
		//返回到指定页面
		mv.setViewName("error/error");
		return mv;
	}
}






