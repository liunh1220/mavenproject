package com.example.mybatis.base;

import javax.annotation.Resource;

public class BaseServiceImpl implements IBaseService {

	 @Resource
	 protected IBaseDao baseDao;

	 public IBaseDao getBaseDao() {
	  return baseDao;
	 }

	 public void setBaseDao(IBaseDao baseDao) {
	  this.baseDao = baseDao;
	 }

}
