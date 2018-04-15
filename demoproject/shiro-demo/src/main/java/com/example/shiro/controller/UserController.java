package com.example.shiro.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.example.shiro.service.user.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService testService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@RequestMapping(value="/id", method = RequestMethod.GET)
	public ModelAndView getUser(String userId) {
		ModelAndView modelAndView = new ModelAndView("userList");
		Object o = null;//userService.findUser(userId);
		System.out.println(o);
		return modelAndView;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView getUsers(String userName) {
		ModelAndView modelAndView = new ModelAndView("userList");
		
		JSONObject data = new JSONObject();
		data.put("name", userName);
		Object list = null;//userService.listUser(data);
		JSONObject json = JSONObject.parseObject(String.valueOf(list));
		System.out.println(json);
		
		modelAndView.addObject("list", json);
		return modelAndView;
	}
	
}
