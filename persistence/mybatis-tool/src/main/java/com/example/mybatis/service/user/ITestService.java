package com.example.mybatis.service.user;

import java.util.List;

import com.example.mybatis.entity.table.TestUser;
import com.github.pagehelper.PageInfo;

public interface ITestService {
	
	PageInfo<TestUser> mapperQueryList(TestUser record,Integer pageNum,Integer pageSize);
    
    List<TestUser> baseDaoQueryList(TestUser record);
    
}
