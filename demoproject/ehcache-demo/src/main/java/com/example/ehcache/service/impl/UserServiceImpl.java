package com.example.ehcache.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ehcache.entity.pojo.User;
import com.example.ehcache.service.IUserService;
import com.example.ehcache.dao.IUserDao;


@Service
public class UserServiceImpl implements IUserService {

	@Resource
	IUserDao IUserDao;
	
	@Override
	@Transactional
	public int deleteUser(String id){
		
		return IUserDao.deleteUser(id);
	}
	
	@Override
	@Transactional
	public int insertUser(User record){
		
		return IUserDao.insertUser(record);
    }
	
	@Override
	@Cacheable(value="frontCache",key="'cacheUserInfo'+#id") 
	public User findUser(String id){
		
		return IUserDao.findUser(id);
    }
	
	@Override
	@Transactional
	public int updateUser(User record){
		
		return IUserDao.updateUser(record);
    }
	
	@Override
	public int countUser(User record){
		
		return IUserDao.countUser(record);
    }
	
	@Override
	public List<User> listUser(User record){
		
		return IUserDao.listUser(record);
    }
	
}
