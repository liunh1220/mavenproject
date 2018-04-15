package com.example.quartz.dao;

import java.util.List;

import com.example.quartz.entity.TSystemTimedTaskRunningTime;

public interface TSystemTimedTaskRunningTimeMapper {
	
	Integer deleteByPrimaryKey(String runId);

	Integer insert(TSystemTimedTaskRunningTime record);

	Integer insertSelective(TSystemTimedTaskRunningTime record);

    TSystemTimedTaskRunningTime selectByPrimaryKey(String runId);

    Integer updateByPrimaryKeySelective(TSystemTimedTaskRunningTime record);

    Integer updateByPrimaryKey(TSystemTimedTaskRunningTime record);
    
    List<TSystemTimedTaskRunningTime> findList(TSystemTimedTaskRunningTime req) throws Exception;

}