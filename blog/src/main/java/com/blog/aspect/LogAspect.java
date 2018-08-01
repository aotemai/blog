package com.blog.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 日志处理
 * @author huajun
 *
 */
/*
 * 两个必须的注解
 */
/**
 * @author huajun
 *
 */
@Aspect//进行切面操作的注解
@Component//组件扫描
public class LogAspect {

	//用来记录异常信息
	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("execution(* com.blog.web.*.*(..))")//通过@Pointcut注解声明下面的方法是个切面，execution(*com.blog.web.*.*(..))")规定切面拦截web下的所有类的方法
	public void log() {
		
	}
	
	//拦截方法之前
	@Before("log()")
	public void dobefore(JoinPoint joinPoint) {//JoinPoint切面对象
		//通过request请求获取
		ServletRequestAttributes attribuite = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attribuite.getRequest();
		
		String url = request.getRequestURI();
		String ip = request.getRemoteAddr();
		//获取类名+方法名
		String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
		//获取请求参数
		Object[] args = joinPoint.getArgs();
		
		RequestLogs requestLogs = new RequestLogs(url,ip,classMethod,args);
		
		logger.info("Request:{}",requestLogs);
	}
	
	//拦截方法之后
	@After("log()")
	public void doafter() {
	//	logger.info("-----doafter------");
	}
	
	//拦截方法返回
	@AfterReturning(returning = "result",pointcut = "log()")
	public void doAfterRuturn(Object result) {
		logger.info("Result:{} "+result); 
	}
	
	/*
	 * 根据需求记录1.请求的url 2.访问者ip 3.调用的方法  4.参数args 
	 */
	private class RequestLogs {
		private String url;
		private String ip;
		private String classMethod;
		private Object[] args;
		
		public RequestLogs(String url, String ip, String classMethod, Object[] args) {
			super();
			this.url = url;
			this.ip = ip;
			this.classMethod = classMethod;
			this.args = args;
		}

		@Override
		public String toString() {
			return "RequestLogs [url=" + url + ", ip=" + ip + ", classMethod=" + classMethod + ", args="
					+ Arrays.toString(args) + "]";
		}
		
		
	}
	

	
}
