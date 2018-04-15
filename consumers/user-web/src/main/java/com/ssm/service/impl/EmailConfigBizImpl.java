package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.dao.EmailConfigMapper;
import com.ssm.domain.EmailConfig;
import com.ssm.exception.BusinessException;
import com.ssm.service.IEmailConfigBiz;
import com.ssm.util.Page;

@Service
public class EmailConfigBizImpl implements IEmailConfigBiz{

	@Autowired
    private EmailConfigMapper emailConfigMapper;
    
    public List<EmailConfig> list(EmailConfig emailConfig) throws BusinessException {
        return emailConfigMapper.getList("com.ssm.dao.EmailConfigMapper.getAll",emailConfig);
    }
    
    public EmailConfig getById(String id)
    {
        return emailConfigMapper.get("com.ssm.dao.EmailConfigMapper.getById", id);
    }
    
    public void getByPage(Page page)
    {
        emailConfigMapper.getByPage("com.ssm.dao.EmailConfigMapper.getByPage", page);
    }
    
    public void save(EmailConfig emailConfig)
    {
        emailConfigMapper.save("com.ssm.dao.EmailConfigMapper.save", emailConfig);
    }
    
    public void remove(EmailConfig emailConfig)
    {
        emailConfigMapper.remove("com.ssm.dao.EmailConfigMapper.remove", emailConfig);
    }
    
    public void update(EmailConfig emailConfig)
    {
        emailConfigMapper.update("com.ssm.dao.EmailConfigMapper.update", emailConfig);
    }
    
    public void updateEmailBySellerIdAndTypes(EmailConfig emailConfig)
    {
        emailConfigMapper.updateEmailBySellerIdAndTypes("com.ssm.dao.EmailConfigMapper.updateEmailBySellerIdAndTypes", emailConfig);
    }
    
    public void removeEmailBySellerIdAndTypes(String sellerId)
    {
        emailConfigMapper.removeEmailBySellerIdAndTypes("com.ssm.dao.EmailConfigMapper.removeEmailBySellerIdAndTypes", sellerId);
    }
}
