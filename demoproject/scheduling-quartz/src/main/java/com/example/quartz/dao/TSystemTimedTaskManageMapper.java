package com.example.quartz.dao;

import java.util.List;

import com.example.quartz.entity.TSystemTimedTaskManage;

public interface TSystemTimedTaskManageMapper {
	
	Integer deleteByPrimaryKey(String jobId);

    Integer insert(TSystemTimedTaskManage record);

    Integer insertSelective(TSystemTimedTaskManage record);

    TSystemTimedTaskManage selectByPrimaryKey(String jobId);

    Integer updateByPrimaryKeySelective(TSystemTimedTaskManage record);

    Integer updateByPrimaryKey(TSystemTimedTaskManage record);
    
    Integer isExistTimedTask(TSystemTimedTaskManage req) throws Exception;
    
    List<TSystemTimedTaskManage> fineList(TSystemTimedTaskManage req) throws Exception;
}