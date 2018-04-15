package com.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm.entity.table.User;
import com.ssm.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
    private IUserService userService; 
	
	@RequestMapping("userService")
	public void getUser(User user) {
		user = userService.selectByPrimaryKey("1");
		System.out.println(user);
	}
}
