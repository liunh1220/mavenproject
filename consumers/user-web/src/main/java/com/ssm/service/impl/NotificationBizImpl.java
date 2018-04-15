package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.dao.NotificationMapper;
import com.ssm.domain.Notification;
import com.ssm.exception.BusinessException;
import com.ssm.service.INotificationBiz;
import com.ssm.util.Page;

@Service
public class NotificationBizImpl implements INotificationBiz{

	@Autowired
    private NotificationMapper notificationDao;
    
    public List<Notification> list(Notification notification) throws BusinessException {
        return notificationDao.getList("com.ssm.dao.NotificationMapper.getAll",notification);
    }
    
    public Notification getById(String id)
    {
        return notificationDao.get("com.ssm.dao.NotificationMapper.getById", id);
    }
    
    public void getByPage(Page page)
    {
        notificationDao.getByPage("com.ssm.dao.NotificationMapper.getByPage", page);
    }
    
    public void save(Notification notification)
    {
        notificationDao.save("com.ssm.dao.NotificationMapper.save", notification);
    }
    
    public void remove(Notification notification)
    {
        notificationDao.remove("com.ssm.dao.NotificationMapper.remove", notification);
    }
    
    public void update(Notification notification)
    {
        notificationDao.update("com.ssm.dao.NotificationMapper.update", notification);
    }
    
}
