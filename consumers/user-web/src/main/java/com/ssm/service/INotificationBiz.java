package com.ssm.service;

import java.util.List;

import com.ssm.domain.Notification;
import com.ssm.exception.BusinessException;
import com.ssm.util.Page;

public interface INotificationBiz 
{

    List<Notification> list(Notification notification) throws BusinessException;

    Notification getById(String id);
    
    void getByPage(Page page);
    
    void save(Notification notification);
    
    void remove(Notification notification);
    
    void update(Notification notification);
    
}
