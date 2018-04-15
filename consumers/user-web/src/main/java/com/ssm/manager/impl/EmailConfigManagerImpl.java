package com.ssm.manager.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.domain.EmailConfig;
import com.ssm.exception.BusinessException;
import com.ssm.manager.IEmailConfigManager;
import com.ssm.service.IEmailConfigBiz;
import com.ssm.util.Page;

@Service
public class EmailConfigManagerImpl implements IEmailConfigManager {

    private static final Logger logger = LoggerFactory.getLogger(EmailConfigManagerImpl.class);
    
    @Autowired
    private IEmailConfigBiz emailConfigBiz;
    
    public List<EmailConfig> list(EmailConfig emailConfig) {
        List<EmailConfig> emailConfigs = emailConfigBiz.list(emailConfig);
        return emailConfigs;
    }
    
    public EmailConfig getById(String id)
    {
        EmailConfig emailConfig = null;
        try
        {
            emailConfig = emailConfigBiz.getById(id);
        }
        catch(Exception e)
        {
            logger.error("EmailConfigManagerImpl.getById()", e);
            throw new BusinessException("EmailConfigManagerImpl.getById()", e);
        }
        return emailConfig;
    }
    
    public void getByPage(Page page)
    {
        try
        {
            emailConfigBiz.getByPage(page);
        }
        catch(Exception e)
        {
            logger.error("EmailConfigManagerImpl.getByPage()", e);
            throw new BusinessException("EmailConfigManagerImpl.getByPage()", e);
        }
        
    }
    
    public void saveOrUpdate(EmailConfig emailConfig)
    {
        try
        {
            // 保存
            if(emailConfig != null && StringUtils.isEmpty(emailConfig.getId()))
            {
                emailConfigBiz.save(emailConfig);
            }
            // 修改
            else
            {
                emailConfigBiz.update(emailConfig);
            }
            
        }
        catch(Exception e)
        {
            logger.error("EmailConfigManagerImpl.saveOrUpdate()", e);
            throw new BusinessException("EmailConfigManagerImpl.saveOrUpdate()", e);
        }
    }
    
    public void remove(EmailConfig emailConfig)
    {
        try
        {
            emailConfigBiz.remove(emailConfig);
        }
        catch(Exception e)
        {
            logger.error("EmailConfigManagerImpl.remove()", e);
            throw new BusinessException("EmailConfigManagerImpl.remove()", e);
        }
    }
    
    public EmailConfig getEmailConfigByTypeId(String typeId) {
    	EmailConfig emailConfig = new EmailConfig();
        try
        {
        	emailConfig.setTypeId(typeId);
        	emailConfig.setFlag("0");
            List<EmailConfig> emailConfigList = emailConfigBiz.list(emailConfig);
            if (CollectionUtils.isNotEmpty(emailConfigList)) {
            	emailConfig = emailConfigList.get(0);
            }else {
            	emailConfig = null;
            }
        }
        catch(Exception e)
        {
            logger.error("EmailConfigManagerImpl.getEmailConfigByTypeId()", e);
            throw new BusinessException("EmailConfigManagerImpl.getEmailConfigByTypeId()", e);
        }
        return emailConfig;
    }
    
    public EmailConfig getEmailConfigByStoreId(String storeId) {
    	EmailConfig emailConfig = new EmailConfig();
        try
        {
        	emailConfig.setSellerId(storeId);
        	emailConfig.setFlag("0");
            List<EmailConfig> emailConfigList = emailConfigBiz.list(emailConfig);
            if (CollectionUtils.isNotEmpty(emailConfigList)) {
            	emailConfig = emailConfigList.get(0);
            } else {
            	emailConfig = null;
            }
        }
        catch(Exception e)
        {
            logger.error("EmailConfigManagerImpl.getEmailConfigByStoreId()", e);
            throw new BusinessException("EmailConfigManagerImpl.getEmailConfigByStoreId()", e);
        }
        return emailConfig;
    }
    
    public EmailConfig getEmailConfigByStoreIdAndTypeId(String storeId, String typeId) {
    	EmailConfig emailConfig = new EmailConfig();
        try
        {
        	emailConfig.setSellerId(storeId);
        	emailConfig.setTypeId(typeId);
        	emailConfig.setFlag("0");
            List<EmailConfig> emailConfigList = emailConfigBiz.list(emailConfig);
            if (CollectionUtils.isNotEmpty(emailConfigList)) {
            	emailConfig = emailConfigList.get(0);
            } else {
            	emailConfig = null;
            }
        }
        catch(Exception e)
        {
            logger.error("EmailConfigManagerImpl.getEmailConfigByStoreIdAndTypeId()", e);
            throw new BusinessException("EmailConfigManagerImpl.getEmailConfigByStoreIdAndTypeId()", e);
        }
        return emailConfig;
    }

	@Override
	public void removeEmailBySellerIdAndTypes(String sellerId) {
		try
        {
            emailConfigBiz.removeEmailBySellerIdAndTypes(sellerId);
        }
        catch(Exception e)
        {
            logger.error("EmailConfigManagerImpl.removeEmailBySellerIdAndTypes()", e);
            throw new BusinessException("EmailConfigManagerImpl.removeEmailBySellerIdAndTypes()", e);
        }
	}

	@Override
	public void updateEmailBySellerIdAndTypes(EmailConfig emailConfig) {
		try
        {
            emailConfigBiz.updateEmailBySellerIdAndTypes(emailConfig);
        }
        catch(Exception e)
        {
            logger.error("EmailConfigManagerImpl.updateEmailBySellerIdAndTypes()", e);
            throw new BusinessException("EmailConfigManagerImpl.updateEmailBySellerIdAndTypes()", e);
        }
	}
    
}
