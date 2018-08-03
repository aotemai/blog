package com.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.UserRepository;
import com.blog.po.User;
import com.blog.service.UserService;

import util.MD5Utils;

@Service
public class UserServiceImpl implements	UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User checkUser(String username, String password) {
		
		User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
		
		return user;
	}

}
