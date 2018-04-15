package com.ssm.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ssm.domain.Configuration;
import com.ssm.util.Page;

public interface ConfigurationMapper{

    void save(Configuration entity) throws DataAccessException;
	
	int update(Configuration entity) throws DataAccessException;
	
	Configuration getById(String id) throws DataAccessException;
	
	Object getByKey(Object param) throws DataAccessException;
	
	Object getObByKey(Object param) throws DataAccessException;
	
	List<Configuration> getList(Object entity) throws DataAccessException;
	
	void remove(Configuration entity) throws DataAccessException;
	
	void getByPage(Page page);
	
}
