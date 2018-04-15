package com.example.jdbc.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface ITestService {
	
	Object deleteUser(String[] ids);
	
	Object deleteUser(JSONObject data);
	
	Object saveOrUpdatUser(List<JSONObject> data);
	
	Object saveOrUpdateAll(List<JSONObject> data);
	
	Object insertUser(JSONObject data);

	Object updateUser(JSONObject data);
	
	Object updateUserBatch(List<Object[]> batchArgs);
	
	Object findUser(String id);

	Object listUser(JSONObject data);

	Object countUser(JSONObject data);
	
	String generateKey();
    
    boolean isExists(String username);
    
}
