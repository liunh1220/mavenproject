package com.ssm.manager;

import java.util.List;

import com.ssm.domain.Configuration;
import com.ssm.exception.BusinessException;
import com.ssm.util.Page;

public interface IConfigurationManager {

	List<Configuration> listConfiguration(Configuration configuration) throws BusinessException;
	
	void getByPage(Page page);
	
	void saveOrUpdate(Configuration configuration);
	
	void remove(Configuration configuration);
	
	Configuration getById(String id);
	
	String getByKey(String key);
	
	void setValue(String key,String value);
	
}
