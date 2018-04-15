package com.example.quartz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.quartz.dao.TSystemTimedTaskAbnormalMapper;
import com.example.quartz.entity.OperationByIdReq;
import com.example.quartz.entity.TSystemTimedTaskAbnormal;
import com.example.quartz.service.ITimedTaskAbnormalService;

@Service
public class TimedTaskAbnormalServiceImpl implements ITimedTaskAbnormalService{
	
	@Deprecated
	private TSystemTimedTaskAbnormalMapper systemTimedTaskAbnormalMapper;

	@Override
	public Integer deleteById(OperationByIdReq req) throws Exception {
		return systemTimedTaskAbnormalMapper.deleteByPrimaryKey(req.getId());
	}

	@Override
	public Integer insert(TSystemTimedTaskAbnormal req) throws Exception {
		return systemTimedTaskAbnormalMapper.insert(req);
	}

	@Override
	public Integer insertSelective(TSystemTimedTaskAbnormal req) throws Exception {
		return systemTimedTaskAbnormalMapper.insertSelective(req);
	}

	@Override
	public TSystemTimedTaskAbnormal findById(OperationByIdReq req) throws Exception {
		return systemTimedTaskAbnormalMapper.selectByPrimaryKey(req.getId());
	}

	@Override
	public List<TSystemTimedTaskAbnormal> fineList(TSystemTimedTaskAbnormal req) throws Exception {
		return systemTimedTaskAbnormalMapper.fineList(req);
	}

	@Override
	public Integer updateBySelective(TSystemTimedTaskAbnormal req) throws Exception {
		return systemTimedTaskAbnormalMapper.updateByPrimaryKeySelective(req);
	}

	@Override
	public Integer updateByIdWithBLOBs(TSystemTimedTaskAbnormal req) throws Exception {
		return systemTimedTaskAbnormalMapper.updateByPrimaryKeyWithBLOBs(req);
	}

	@Override
	public Integer updateById(TSystemTimedTaskAbnormal req) throws Exception {
		return null;
	}

}
