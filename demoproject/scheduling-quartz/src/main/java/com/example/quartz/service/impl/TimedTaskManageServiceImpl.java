package com.example.quartz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quartz.dao.TSystemTimedTaskManageMapper;
import com.example.quartz.entity.OperationByIdReq;
import com.example.quartz.entity.TSystemTimedTaskManage;
import com.example.quartz.service.ITimedTaskManageService;

@Service
public class TimedTaskManageServiceImpl implements ITimedTaskManageService {
	
	@Autowired
	private TSystemTimedTaskManageMapper systemTimedTaskManageMapper;
	
	@Override
	public Integer deleteById(OperationByIdReq req) throws Exception {
		return systemTimedTaskManageMapper.deleteByPrimaryKey(req.getId());
	}

	@Override
	public Integer insert(TSystemTimedTaskManage req) throws Exception {
		return systemTimedTaskManageMapper.insert(req);
	}

	@Override
	public Integer insertSelective(TSystemTimedTaskManage req) throws Exception {
		return systemTimedTaskManageMapper.insertSelective(req);
	}

	@Override
	public boolean isExistTimedTask(TSystemTimedTaskManage req) throws Exception {
		Integer i = systemTimedTaskManageMapper.isExistTimedTask(req);
		boolean b = false;
		if(i!=null && i > 0){
			b = true;
		}
		return b;
	}

	@Override
	public TSystemTimedTaskManage findById(OperationByIdReq req) throws Exception {
		return systemTimedTaskManageMapper.selectByPrimaryKey(req.getId());
	}

	@Override
	public List<TSystemTimedTaskManage> fineList(TSystemTimedTaskManage req) throws Exception {
		return systemTimedTaskManageMapper.fineList(req);
	}

	@Override
	public Integer updateBySelective(TSystemTimedTaskManage req) throws Exception {
		return systemTimedTaskManageMapper.updateByPrimaryKeySelective(req);
	}

	@Override
	public Integer updateById(TSystemTimedTaskManage req) throws Exception {
		return systemTimedTaskManageMapper.updateByPrimaryKey(req);
	}

	@Override
	public List<TSystemTimedTaskManage> findListEnable(TSystemTimedTaskManage req) throws Exception {
		req.setEnabledStatus("ENABLE");
		return systemTimedTaskManageMapper.fineList(req);
	}

}
