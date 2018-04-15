package com.example.page.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.page.model.User;
import com.example.page.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	//private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    private IUserService userService; 
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ModelAndView getUser(User user) {
		ModelAndView modelAndView = new ModelAndView("userList");
		User u = userService.selectByPrimaryKey(user.getId());
		System.out.println(u);
		List<User> list = new ArrayList<User>();
		list.add(u);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView getUsers(com.example.page.model.UserPageReq user) {
		ModelAndView modelAndView = new ModelAndView("userList");
		user.setPageSize(5);
		user.setStart(0);
		List<com.example.page.model.UserPageReq> list = userService.list(user);
		System.out.println(list);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
}
