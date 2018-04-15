package com.example.ehcache.dao;

import java.util.List;

import com.example.ehcache.base.IHibernateEntityDao;
import com.example.ehcache.entity.pojo.User;


public interface IUserDao extends IHibernateEntityDao<User>{
	
	int deleteUser(String id);

    int insertUser(User record);

    User findUser(String id);

    int updateUser(User record);

    int countUser(User record);
    
    List<User> listUser(User record);
    
}
