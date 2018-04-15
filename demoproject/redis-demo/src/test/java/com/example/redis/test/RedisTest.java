package com.example.redis.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.redis.util.RedisCacheUtil;

import redis.clients.jedis.Jedis;

public class RedisTest {
	
	public static void main(String[] args) {
		
		Jedis jedis = new Jedis("127.0.0.1",6379);
		jedis.set("name","lisi");
		jedis.set("age","1");
		//System.out.println(j.get("name"));
		List<String> list = jedis.mget("name","age");
		jedis.close();
		for(Iterator iterator = list.iterator();iterator.hasNext();){
			System.out.println(iterator.next());
		}
		
		
		Map map = new HashMap();
		map.put("mapkey1", "mapvalue1");
		map.put("mapkey2", "mapvalue2");
		RedisCacheUtil cache = new RedisCacheUtil();
		cache.set("map111", map);
		System.out.println(cache.hasKey("key111"));
		System.out.println(cache.get("key111"));
		System.out.println(cache.get("map111"));
		
	}

}
