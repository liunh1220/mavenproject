package com.example.http.manager;

import com.example.http.exception.BusinessException;
import com.example.http.model.Configuration;
import com.github.pagehelper.Page;

import java.util.List;

public interface IConfigurationManager {

	List<Configuration> listConfiguration(Configuration configuration) throws BusinessException;
	
	void getByPage(Page<Configuration> page);
	
	void saveOrUpdate(Configuration configuration);
	
	void remove(Configuration configuration);
	
	Configuration getById(String id);
	
	String getByKey(String key);
	
	void setValue(String key,String value);
	
}
