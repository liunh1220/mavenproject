package com.example.quartz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.quartz.dao.TSystemTimedTaskRunningTimeMapper;
import com.example.quartz.entity.OperationByIdReq;
import com.example.quartz.entity.TSystemTimedTaskRunningTime;
import com.example.quartz.service.ITimedTaskRunningTimeService;

@Service
public class TimedTaskRunningTimeServiceImpl implements ITimedTaskRunningTimeService {
	
	private TSystemTimedTaskRunningTimeMapper systemTimedTaskRunningTimeMapper;
	
	@Override
	public Integer deleteById(OperationByIdReq req) throws Exception {
		return systemTimedTaskRunningTimeMapper.deleteByPrimaryKey(req.getId());
	}

	@Override
	public Integer insert(TSystemTimedTaskRunningTime req) throws Exception {
		return systemTimedTaskRunningTimeMapper.insert(req);
	}

	@Override
	public Integer insertSelective(TSystemTimedTaskRunningTime req) throws Exception {
		return systemTimedTaskRunningTimeMapper.insertSelective(req);
	}

	@Override
	public TSystemTimedTaskRunningTime findById(OperationByIdReq req) throws Exception {
		return systemTimedTaskRunningTimeMapper.selectByPrimaryKey(req.getId());
	}

	@Override
	public List<TSystemTimedTaskRunningTime> findList(TSystemTimedTaskRunningTime req) throws Exception {
		return systemTimedTaskRunningTimeMapper.findList(req);
	}

	@Override
	public Integer updateSelective(TSystemTimedTaskRunningTime req) throws Exception {
		return systemTimedTaskRunningTimeMapper.updateByPrimaryKeySelective(req);
	}

	@Override
	public Integer updateById(TSystemTimedTaskRunningTime req) throws Exception {
		return systemTimedTaskRunningTimeMapper.updateByPrimaryKey(req);
	}

}
