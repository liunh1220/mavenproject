package com.example.http.service;

import com.example.http.exception.BusinessException;
import com.example.http.model.Configuration;
import com.github.pagehelper.Page;

import java.util.List;

public interface IConfigurationBiz {

	List<Configuration> listConfiguration(Configuration configuration) throws BusinessException;
	
	void getByPage(Page<Configuration> page);
	
	void save(Configuration configuration);
	
	void remove(Configuration configuration);
	
	void update(Configuration configuration);
	
	Configuration getById(String id);
	
	String getByKey(String key);
	
	Configuration getObByKey(String key);
	
	void setValue(String key,String value);
}
