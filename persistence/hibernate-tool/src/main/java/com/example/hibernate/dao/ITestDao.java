package com.example.hibernate.dao;

import java.util.List;

import com.example.hibernate.entity.pojo.User;
import com.example.hibernate.entity.vo.UserVo;

public interface ITestDao extends IHibernateDao<User,String>{
	
	int deleteUser(String id);

    int insertUser(User record);

    User findUser(String id);

    int updateUser(User record);

    int countUser(UserVo record);
    
    List<User> listUser(User record);
    
}
