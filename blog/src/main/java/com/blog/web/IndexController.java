package com.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.blog.NotFoundExceptionn;



@Controller
public class IndexController {

	//@GetMapping("/{id}/{name}")
	@GetMapping("/")
//	public String index(@PathVariable Integer id,@PathVariable String name)  {
	public String index()  {
	//	int i = 9/0;
	/*	String blog = null;
		if(blog == null) {
			
			throw new NotFoundExceptionn("博客找不到");
		}*/
	//	System.out.println("-----index------");
		return "index";
	}
	
	@GetMapping("/blog")
	public String blog()  {
		return "blog";
	}
	
	@GetMapping("/tage")
	public String tage()  {
		return "tage";
	}
	
	@GetMapping("/types")
	public String types()  {
		return "types";
	}
}
