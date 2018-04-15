package com.example.hibernate.dao;

import java.util.List;

import com.example.hibernate.base.IHibernateCrudDao;
import com.example.hibernate.entity.pojo.User;
import com.example.hibernate.entity.vo.UserVo;

public interface IUserDao extends IHibernateCrudDao<User,String> {
	
	void deleteUser(String id);

    void insertUser(User record);

    User findUser(String id);

    void updateUser(User record);

    int countUser(UserVo record);
    
    List<User> listUser(User record);
    
    boolean isExists(String username);
    
}
