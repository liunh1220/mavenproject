package com.ssm.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.dao.ConfigurationMapper;
import com.ssm.domain.Configuration;
import com.ssm.exception.BusinessException;
import com.ssm.service.IConfigurationBiz;
import com.ssm.util.Page;

@Service
public class ConfigurationBizImpl implements IConfigurationBiz{

	/**
	 * 日志对象
	 */
	private static final Logger logger =  LoggerFactory.getLogger(ConfigurationBizImpl.class);
	
	@Autowired
	private ConfigurationMapper configurationDao;
	
	public List<Configuration> listConfiguration(Configuration configuration) throws BusinessException {
		return configurationDao.getList(configuration);
	}
	
	public Configuration getById(String id)
	{
		if(logger.isDebugEnabled()){
			logger.debug("");
		}
		
		return configurationDao.getById(id);
	
	}
	
	public void getByPage(Page page)
	{
		configurationDao.getByPage(page);
	}
	
	public void save(Configuration configuration)
	{
		configurationDao.save( configuration);
	}
	
	public void remove(Configuration configuration)
	{
		configurationDao.remove(configuration);
	}
	
	public void update(Configuration configuration)
	{
		configurationDao.update(configuration);
	}
	
	public String getByKey(String key)
	{
		return (String)configurationDao.getByKey(key);
	}
	
	public Configuration getObByKey(String key)
	{
		return (Configuration)configurationDao.getById(key);
	}
	
	public void setValue(String key,String value)
	{
		Configuration configuration = new Configuration();
		configuration.setKey(key);
		configuration.setValue(value);
		configuration.setUpdateTime(new Date());
		configuration.setUpdateBy("");
		configurationDao.update(configuration);
	}
	
}
