package com.example.hibernate.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.hibernate.entity.pojo.User;
import com.example.hibernate.entity.vo.UserVo;
import com.example.hibernate.service.ITestService;
import com.example.hibernate.dao.ITestDao;

@Service
public class TestServiceImpl implements ITestService {

	@Resource
	ITestDao IUserDao;
	
	@Override
	@Transactional
	public Object deleteUser(String id){
		return IUserDao.deleteUser(id);
	}
	
	@Override
	@Transactional
	public Object insertUser(User record){
		return IUserDao.insertUser(record);
    }
	
	@Override
	@Transactional(readOnly=true)
	@Cacheable(value="frontCache",key="'cacheUserInfoTest'+#id") 
	public User findUser(String id){
		return IUserDao.findUser(id);
    }
	
	@Override
	@Transactional
	public Object updateUser(User record){
		return IUserDao.updateUser(record);
    }
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Object countUser(UserVo record){
		return IUserDao.countUser(record);
    }
	
	@Override
	@Transactional(readOnly=true)
	public Object listUser(User record){
		return IUserDao.listUser(record);
    }

	@Override
	@Transactional
	public Object saveOrUpdatUser(User record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Object saveOrUpdateAll(List<User> record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public boolean isExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
