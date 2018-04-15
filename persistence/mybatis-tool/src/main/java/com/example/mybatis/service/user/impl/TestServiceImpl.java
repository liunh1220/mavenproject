package com.example.mybatis.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mybatis.base.BaseServiceImpl;
import com.example.mybatis.dao.TestUserMapper;
import com.example.mybatis.entity.table.TestUser;
import com.example.mybatis.service.user.ITestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TestServiceImpl extends BaseServiceImpl implements ITestService {

	@Autowired
	private TestUserMapper testUserMapper;

	@Override
	public PageInfo<TestUser> mapperQueryList(TestUser record,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);// 开始分页,物理分页
		List<TestUser> list =testUserMapper.userList(record);//DB操作查询数据
		PageInfo<TestUser> pageInfo=new PageInfo<TestUser>(list);//将查询到的数据封装到List中
		return pageInfo;
	}
	
	@Override
	public List<TestUser> baseDaoQueryList(TestUser record) {
		/*List<TestUser> list = baseDao.queryList("com.example.mybatis.dao.TestUserMapper.userList", record);
		return list;*/
		return null;
	}

}
