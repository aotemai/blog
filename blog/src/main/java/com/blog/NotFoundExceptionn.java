package com.blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//指定返回状态码
@ResponseStatus(HttpStatus.NOT_FOUND)//NOT_FOUND作为一个找不到的页面状态
public class NotFoundExceptionn extends RuntimeException{

	public NotFoundExceptionn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotFoundExceptionn(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotFoundExceptionn(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
	
	
}
