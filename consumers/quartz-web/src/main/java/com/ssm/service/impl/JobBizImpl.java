package com.ssm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.dao.JobMapper;
import com.ssm.domain.Job;
import com.ssm.exception.BusinessException;
import com.ssm.service.IJobBiz;

@Service
public class JobBizImpl implements IJobBiz{

	@Autowired
    private JobMapper jobDao;
	
	@Override
    public List<Job> list(Job job) throws BusinessException {
        return jobDao.getAll(job);
    }
	
    @Override
    public Job getById(String id)
    {
        return jobDao.getById(id);
    }
    
    @Override
    public List<Job> getByPage(Job job)
    {
        return jobDao.getByPage(job);
    }
    
    @Override
    public void save(Job job)
    {
        jobDao.save(job);
    }
    
    @Override
    public void remove(Job job)
    {
        jobDao.remove(job);
    }
    
    @Override
    public void update(Job job)
    {
        jobDao.update(job);
    }

	@Override
	public List<Job> listByIplist(Map<String, Object> param) {
		return jobDao.listByIplist(param);
	}
    
}
