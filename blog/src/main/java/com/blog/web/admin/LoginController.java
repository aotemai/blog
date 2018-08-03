package com.blog.web.admin;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.po.User;
import com.blog.service.UserService;


@Controller
@RequestMapping("/admin")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public String loginPage() {		
		return "admin/login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String username,
			@RequestParam String password,
			HttpSession session,
			RedirectAttributes attributes) {//重定向使用这种方式来接收，同时返回前台页面消息也能接收下
		User user = userService.checkUser(username, password);
		//如果用户不为空，将用户信息放入session里面
		if(user != null) {
			user.setPassword(null);//不要将密码传到页面
			session.setAttribute("user", user);
			return "admin/index";
		}else {
			attributes.addAttribute("message", "用户名和密码错误");
			return "redirect:/admin";//使用这种重定向的方法返回，（如果直接返回可能再次登录会发生错误）
		}		
	}
	
	//注销用户
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/admin";
	}
	
}
