package com.ssm.service;

import java.util.List;

import com.ssm.domain.Configuration;
import com.ssm.exception.BusinessException;
import com.ssm.util.Page;

public interface IConfigurationBiz {

	List<Configuration> listConfiguration(Configuration configuration) throws BusinessException;
	
	void getByPage(Page page);
	
	void save(Configuration configuration);
	
	void remove(Configuration configuration);
	
	void update(Configuration configuration);
	
	Configuration getById(String id);
	
	String getByKey(String key);
	
	Configuration getObByKey(String key);
	
	void setValue(String key,String value);
}
