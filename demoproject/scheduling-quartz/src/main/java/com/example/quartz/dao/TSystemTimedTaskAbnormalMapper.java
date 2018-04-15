package com.example.quartz.dao;

import java.util.List;

import com.example.quartz.entity.TSystemTimedTaskAbnormal;

public interface TSystemTimedTaskAbnormalMapper {
	
	Integer deleteByPrimaryKey(String abnmId);

	Integer insert(TSystemTimedTaskAbnormal record);

	Integer insertSelective(TSystemTimedTaskAbnormal record);
    
    List<TSystemTimedTaskAbnormal> fineList(TSystemTimedTaskAbnormal record);

    TSystemTimedTaskAbnormal selectByPrimaryKey(String abnmId);

    Integer updateByPrimaryKeySelective(TSystemTimedTaskAbnormal record);

    Integer updateByPrimaryKeyWithBLOBs(TSystemTimedTaskAbnormal record);

    Integer updateByPrimaryKey(TSystemTimedTaskAbnormal record);
}