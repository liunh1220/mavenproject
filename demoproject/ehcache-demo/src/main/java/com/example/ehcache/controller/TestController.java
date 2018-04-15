package com.example.ehcache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.example.ehcache.entity.pojo.User;
import com.example.ehcache.service.IUserService;
import com.example.ehcache.util.ConsoleCacheUtil;
import com.example.ehcache.util.FrontCacheUtil;
import com.example.ehcache.util.LocalCacheUtil;

@Controller
@RequestMapping("/test/")
public class TestController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="{id}",method = RequestMethod.GET)
	public ModelAndView findUser(@PathVariable("id") String id){
		User r = userService.findUser(id);
		
		ConsoleCacheUtil.setValue("aaa", r);
		System.out.println(ConsoleCacheUtil.getValue("aaa"));
		
		LocalCacheUtil.setValue("sss", r);
		System.out.println(LocalCacheUtil.getValue("sss"));
		
		Object resultMsg = (Object)FrontCacheUtil.getValue("cacheUserInfo"+id);
		System.out.println(JSONObject.toJSON(resultMsg));
		return new ModelAndView("user/userDetail").addObject("result", r);
	}
	
    
}
