package com.example.springquartz.dao;

import com.example.springquartz.entity.TSystemTimedTaskRunningTime;

/**
 * 定时任务运行时间
 * @author Administrator
 *
 */
public interface TSystemTimedTaskRunningTimeMapper {
	
    Integer deleteByPrimaryKey(String runId);

    Integer insert(TSystemTimedTaskRunningTime req);

    Integer insertSelective(TSystemTimedTaskRunningTime req);

    TSystemTimedTaskRunningTime selectByPrimaryKey(String runId);

    Integer updateByPrimaryKeySelective(TSystemTimedTaskRunningTime req);

    Integer updateByPrimaryKey(TSystemTimedTaskRunningTime req);
}