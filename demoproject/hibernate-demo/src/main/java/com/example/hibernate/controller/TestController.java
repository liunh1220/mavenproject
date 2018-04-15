package com.example.hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.example.hibernate.entity.pojo.User;
import com.example.hibernate.service.IUserService;
import com.example.hibernate.util.ConsoleCacheUtil;
import com.example.hibernate.util.FrontCacheUtil;
import com.example.hibernate.util.LocalCacheUtil;
import com.example.hibernate.util.RedisCacheUtil;
import com.example.hibernate.util.ResultMsg;

@Controller
@RequestMapping("/test/")
public class TestController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="{id}",method = RequestMethod.GET)
	public ModelAndView findUser(@PathVariable("id") String id){
		ResultMsg r = userService.findUser(id);
		
		ConsoleCacheUtil.setValue("aaa", r.getData());
		System.out.println(ConsoleCacheUtil.getValue("aaa"));
		
		LocalCacheUtil.setValue("sss", r.getData());
		System.out.println(LocalCacheUtil.getValue("sss"));
		
		Object resultMsg = (Object)FrontCacheUtil.getValue("cacheUserInfo"+id);
		System.out.println(JSONObject.toJSON(resultMsg));
		return new ModelAndView("user/userDetail").addObject("result", r);
	}
	
	@RequestMapping(value="{id}",method = RequestMethod.DELETE)
	public ResultMsg deleteUser(String id){
		return userService.deleteUser(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResultMsg insertUser(User record){
		return userService.insertUser(record);
    }

	@RequestMapping(method = RequestMethod.PUT)
	public ResultMsg updateUser(User record){
		return userService.updateUser(record);
    }
    
	@RequestMapping(method = RequestMethod.GET)
	public ResultMsg listUser(User record){
		return userService.listUser(record);
    }
	
	@RequestMapping(value = "redisTest", method = RequestMethod.GET)
	public void redisTest(User record){
		RedisCacheUtil cache = new RedisCacheUtil();
		System.out.println(cache.hasKey("name"));
		System.out.println(cache.get("name"));
	}
    
}
