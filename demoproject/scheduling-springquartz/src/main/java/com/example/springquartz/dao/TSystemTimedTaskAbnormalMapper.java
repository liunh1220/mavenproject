package com.example.springquartz.dao;

import com.example.springquartz.entity.TSystemTimedTaskAbnormal;

/**
 * 定时任务异常
 * @author Administrator
 *
 */
public interface TSystemTimedTaskAbnormalMapper {
	
    Integer deleteByPrimaryKey(String abnmId);

    Integer insert(TSystemTimedTaskAbnormal req);

    Integer insertSelective(TSystemTimedTaskAbnormal req);

    TSystemTimedTaskAbnormal selectByPrimaryKey(String abnmId);

    Integer updateByPrimaryKeySelective(TSystemTimedTaskAbnormal req);

    Integer updateByPrimaryKeyWithBLOBs(TSystemTimedTaskAbnormal req);

    Integer updateByPrimaryKey(TSystemTimedTaskAbnormal req);
}