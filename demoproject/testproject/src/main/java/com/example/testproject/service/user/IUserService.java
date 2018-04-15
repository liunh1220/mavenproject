package com.example.testproject.service.user;

import java.util.List;

import com.example.testproject.entity.table.User;
import com.github.pagehelper.PageInfo;

public interface IUserService {
	
	int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int count(User record);
    
    List<User> list(User record);
    
    PageInfo<User> mapperQueryList(User record,Integer pageNum,Integer pageSize);
}
