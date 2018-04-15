package com.example.jdbc.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface ITestDao {
	
	Object deleteUser(String[] ids);
	
	Object deleteUser(JSONObject data);

	Object insertUser(JSONObject data);

    Object findUser(String id);

    Object updateUser(JSONObject data);
    
    Object updateUserBatch(List<Object[]> batchArgs);

    Object countUser(JSONObject data);
    
    Object listUser(JSONObject data);
    
    String generateKey();
    
    boolean isExists(String username);
    
}
