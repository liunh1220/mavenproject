package com.example.dubbocustomer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.dubboapi.entity.user.User;
import com.example.dubboapi.service.console.user.IDubboUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
    private IDubboUserService userService; 
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ModelAndView getUser(User userReq) {
		ModelAndView modelAndView = new ModelAndView("user/userList");
		User user = userService.findUser(userReq);
		System.out.println(user);
		List<User> list = new ArrayList<User>();
		list.add(user);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
}
