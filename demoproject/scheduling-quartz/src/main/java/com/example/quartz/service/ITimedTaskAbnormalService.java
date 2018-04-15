package com.example.quartz.service;

import java.util.List;

import com.example.quartz.entity.OperationByIdReq;
import com.example.quartz.entity.TSystemTimedTaskAbnormal;

/**
 * 
 * <定时任务异常表>
 * <功能详细描述>
 * 
 * @author  liunanhua
 * @version  [版本号, 2016年7月4日]
 */
public interface ITimedTaskAbnormalService
{
    Integer deleteById(OperationByIdReq req) throws Exception;

    Integer insert(TSystemTimedTaskAbnormal req) throws Exception;

    Integer insertSelective(TSystemTimedTaskAbnormal req) throws Exception;

    TSystemTimedTaskAbnormal findById(OperationByIdReq req) throws Exception;
    
    List<TSystemTimedTaskAbnormal> fineList(TSystemTimedTaskAbnormal req) throws Exception;

    Integer updateBySelective(TSystemTimedTaskAbnormal req) throws Exception;

    Integer updateByIdWithBLOBs(TSystemTimedTaskAbnormal req) throws Exception;

    Integer updateById(TSystemTimedTaskAbnormal req) throws Exception;
    
}
