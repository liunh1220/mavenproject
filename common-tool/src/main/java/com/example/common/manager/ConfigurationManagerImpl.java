package com.example.common.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.common.domain.Configuration;
import com.example.common.exception.BusinessException;
import com.example.common.util.Page;


@Service
public class ConfigurationManagerImpl implements IConfigurationManager {

	@Override
	public List<Configuration> listConfiguration(Configuration configuration) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getByPage(Page page) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(Configuration configuration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Configuration configuration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Configuration getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getByKey(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(String key, String value) {
		// TODO Auto-generated method stub
		
	}


}
