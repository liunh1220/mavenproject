package com.example.http.manager.impl;

import com.example.http.exception.BusinessException;
import com.example.http.manager.IConfigurationManager;
import com.example.http.model.Configuration;
import com.example.http.service.IConfigurationBiz;
import com.example.http.util.LocalCacheUtil;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigurationManagerImpl implements IConfigurationManager {

	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationManagerImpl.class);
	
	@Autowired
	private IConfigurationBiz configurationBiz;
	
	/**
	 * 查询列表
	 */
	public List<Configuration> listConfiguration(Configuration configuration) {
		if(logger.isDebugEnabled()){
			logger.debug("查询列表");
		}
		List<Configuration> configurations = configurationBiz.listConfiguration(configuration);
		if(logger.isDebugEnabled()){
			logger.debug("查询列表");
		}
		return configurations;
	}
	
	public void getByPage(Page<Configuration> page)
	{
		try
		{
			configurationBiz.getByPage(page);
		}
		catch(Exception e)
		{
			logger.error("", e);
			throw new BusinessException("", e);
		}
		
	}
	
	public void saveOrUpdate(Configuration configuration)
	{
		try
		{
			String md5Key = LocalCacheUtil.getMd5Key("configuration:" + configuration.getKey());
			// 保存
			if(configuration != null && StringUtils.isEmpty(configuration.getId()))
			{
				configurationBiz.save(configuration);
			}
			//
			else
			{
				configurationBiz.update(configuration);
			}
			LocalCacheUtil.setValue(md5Key, configuration.getValue());
		}
		catch(Exception e)
		{
			logger.error("", e);
			throw new BusinessException("", e);
		}
	}
	
	public void remove(Configuration configuration)
	{
		try
		{
			configuration = this.getById(configuration.getId());
			String md5Key = LocalCacheUtil.getMd5Key("configuration:" + configuration.getKey());
			configurationBiz.remove(configuration);
			LocalCacheUtil.remove(md5Key);
		}
		catch(Exception e)
		{
			logger.error("", e);
			throw new BusinessException("", e);
		}
	}

	@Override
	public Configuration getById(String id)
	{
		try
		{
			return configurationBiz.getById(id);
		}
		catch(Exception e)
		{
			logger.error("", e);
			throw new BusinessException("", e);
		}
	}
	
	@Override
	public String getByKey(String key)
	{
		try
		{
			String md5Key = LocalCacheUtil.getMd5Key("configuration:" + key);
			String value = (String)LocalCacheUtil.getValue(md5Key);
			if(value == null)
			{
				value = configurationBiz.getByKey(key);
				logger.debug("从数据库取");
				if(value != null)
				{
					LocalCacheUtil.setValue(md5Key, value);
				}
			}
			else
			{
				logger.debug("从缓存取");
			}
			
			return value;
		}
		catch(Exception e)
		{
			logger.error("", e);
			throw new BusinessException("", e);
		}
	}
	
	@Override
	public void setValue(String key,String value)
	{
		try
		{
			String md5Key = LocalCacheUtil.getMd5Key("configuration:" + key);
			configurationBiz.setValue(key, value);
			LocalCacheUtil.setValue(md5Key, value);
		}
		catch(Exception e)
		{
			logger.error("", e);
			throw new BusinessException("", e);
		}
	}
}
