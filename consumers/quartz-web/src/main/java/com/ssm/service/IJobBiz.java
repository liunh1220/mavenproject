package com.ssm.service;

import java.util.List;
import java.util.Map;

import com.ssm.domain.Job;
import com.ssm.exception.BusinessException;

public interface IJobBiz 
{

    List<Job> list(Job job) throws BusinessException;

    Job getById(String id);
    
    List<Job> getByPage(Job job);
    
    void save(Job job);
    
    void remove(Job job);
    
    void update(Job job);
    
    List<Job> listByIplist(Map<String, Object> param);
    
}
