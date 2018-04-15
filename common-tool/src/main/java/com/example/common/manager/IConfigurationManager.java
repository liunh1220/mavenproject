package com.example.common.manager;

import java.util.List;

import com.example.common.domain.Configuration;
import com.example.common.exception.BusinessException;
import com.example.common.util.Page;

public interface IConfigurationManager {

	List<Configuration> listConfiguration(Configuration configuration) throws BusinessException;
	
	void getByPage(Page page);
	
	void saveOrUpdate(Configuration configuration);
	
	void remove(Configuration configuration);
	
	Configuration getById(String id);
	
	String getByKey(String key);
	
	void setValue(String key,String value);
	
}
