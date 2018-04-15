package com.example.hibernate.test;

import com.example.hibernate.util.RedisCacheUtil;

public class RedisTest{
	
	
	public static void main(String[] args) {
		
		RedisCacheUtil cache = new RedisCacheUtil();
		System.out.println(cache.hasKey("key111"));
		System.out.println(cache.get("key111"));
		
	}

}
