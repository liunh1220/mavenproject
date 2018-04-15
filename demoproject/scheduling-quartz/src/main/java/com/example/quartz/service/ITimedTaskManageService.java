package com.example.quartz.service;

import java.util.List;

import com.example.quartz.entity.OperationByIdReq;
import com.example.quartz.entity.TSystemTimedTaskManage;

/**
 * 
 * <定时任务管理表>
 * <功能详细描述>
 * 
 * @author  liunanhua
 * @version  [版本号, 2016年7月4日]
 */
public interface ITimedTaskManageService
{
	Integer deleteById(OperationByIdReq req) throws Exception;

	Integer insert(TSystemTimedTaskManage req) throws Exception;

	Integer insertSelective(TSystemTimedTaskManage req) throws Exception;

    boolean isExistTimedTask(TSystemTimedTaskManage req) throws Exception;
    
    TSystemTimedTaskManage findById(OperationByIdReq req) throws Exception;
    
    List<TSystemTimedTaskManage> fineList(TSystemTimedTaskManage req) throws Exception;
    
    List<TSystemTimedTaskManage> findListEnable(TSystemTimedTaskManage req) throws Exception;
    
    Integer updateBySelective(TSystemTimedTaskManage req) throws Exception;

    Integer updateById(TSystemTimedTaskManage req) throws Exception;
    
}
