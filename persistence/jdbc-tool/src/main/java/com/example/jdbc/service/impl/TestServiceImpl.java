package com.example.jdbc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.example.jdbc.dao.ITestDao;
import com.example.jdbc.service.ITestService;

@Service
public class TestServiceImpl implements ITestService {

	//private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
	
	@Autowired
	private ITestDao userDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Object deleteUser(String[] ids) {
		return userDao.deleteUser(ids);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Object deleteUser(JSONObject data) {
		return userDao.deleteUser(data);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Object saveOrUpdatUser(List<JSONObject> data) {
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Object saveOrUpdateAll(List<JSONObject> data) {
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Object insertUser(JSONObject data) {
		return userDao.insertUser(data);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Object updateUser(JSONObject data) {
		return userDao.updateUser(data);
	}

	@Override
	public Object findUser(String id) {
		return userDao.findUser(id);
	}

	@Override
	public Object listUser(JSONObject data) {
		return userDao.listUser(data);
	}

	@Override
	public Object countUser(JSONObject data) {
		return userDao.countUser(data);
	}

	@Override
	public boolean isExists(String username) {
		return userDao.isExists(username);
	}

	@Override
	public String generateKey() {
		return userDao.generateKey();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Object updateUserBatch(List<Object[]> batchArgs) {
		return userDao.updateUserBatch(batchArgs);
	}

}
