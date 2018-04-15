package com.ssm.dao;


import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.DataAccessException;

import com.ssm.domain.Job;

public interface JobMapper{
	
	List<Job> listByIplist(Map<String, Object> param) throws DataAccessException;

	void save(Job entity) throws DataAccessException;
	
	int update(Job entity) throws DataAccessException;
	
	Job getById(String id) throws DataAccessException;
	
	Object getObject(Object param) throws DataAccessException;
	
	List<Job> getObjectList(Object param) throws DataAccessException;
	
	List<Job> getAll(Job entity) throws DataAccessException;
	
	List<Job> getList(Object entity) throws DataAccessException;
	
	void remove(Job entity) throws DataAccessException;
	
	SqlSessionTemplate getSqlSessionTemplate();
	
	List<Job> getByPage(Job job);
}
