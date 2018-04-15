package com.example.springquartz.dao;

import java.util.List;

import com.example.springquartz.entity.TSystemTimedTaskManage;

/**
 * 定时任务管理
 * @author Administrator
 *
 */
public interface TSystemTimedTaskManageMapper {
	
    Integer deleteByPrimaryKey(String jobId);

    Integer insert(TSystemTimedTaskManage req);

    Integer insertSelective(TSystemTimedTaskManage req);

    TSystemTimedTaskManage selectByPrimaryKey(String jobId);
    
    List<TSystemTimedTaskManage> findList(TSystemTimedTaskManage req);

    Integer updateByPrimaryKeySelective(TSystemTimedTaskManage req);

    Integer updateByPrimaryKey(TSystemTimedTaskManage req);
}