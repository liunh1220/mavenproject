package com.example.shiro.service.user;

import java.util.List;

import com.example.shiro.entity.table.User;
import com.github.pagehelper.PageInfo;

public interface IUserService {
	
	PageInfo<User> mapperQueryList(User record,Integer pageNum,Integer pageSize);
    
    List<User> baseDaoQueryList(User record);
    
}
