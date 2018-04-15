package com.ssm.service;

import java.util.List;

import com.ssm.domain.EmailConfig;
import com.ssm.exception.BusinessException;
import com.ssm.util.Page;

public interface IEmailConfigBiz 
{
    List<EmailConfig> list(EmailConfig emailConfig) throws BusinessException;

    EmailConfig getById(String id);
    
    void getByPage(Page page);
    
    void save(EmailConfig emailConfig);
    
    void remove(EmailConfig emailConfig);
    
    void update(EmailConfig emailConfig);
    
    void updateEmailBySellerIdAndTypes(EmailConfig emailConfig);
    
    void removeEmailBySellerIdAndTypes(String sellerId);
    
    
}
