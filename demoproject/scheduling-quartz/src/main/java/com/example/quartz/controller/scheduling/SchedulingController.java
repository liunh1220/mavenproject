package com.example.quartz.controller.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.quartz.entity.OperationByIdReq;
import com.example.quartz.entity.TSystemTimedTaskManage;
import com.example.quartz.enums.EnabledStatus;
import com.example.quartz.excute.SchedulerContainer;
import com.example.quartz.service.ITimedTaskManageService;

/**
 * 定时任务
 * @author Administrator
 *
 */
@RequestMapping("schedul/")
public class SchedulingController {

	@Autowired
	private ITimedTaskManageService taskManageService;
	@Autowired
	private SchedulerContainer schedulerContainer;
	
	/**
	 * 执行
	 * @throws Exception 
	 */
	@RequestMapping("perform")
	public String perform(OperationByIdReq req) throws Exception{
		schedulerContainer.handExecuteJob(req.getId());
		return null;
	}
	
	/**
	 * add
	 * @throws Exception 
	 */
	@RequestMapping("add")
	public String add(TSystemTimedTaskManage req) throws Exception{
		Integer num = taskManageService.insertSelective(req);
		num = num==null? 0: num;
		return String.valueOf(num);
	}
	
	/**
	 * dele
	 * @throws Throwable 
	 */
	@RequestMapping("dele")
	public String dele(TSystemTimedTaskManage req) throws Throwable{
		req.setEnabledStatus(EnabledStatus.NONE.name());
		schedulerContainer.updateStatus(req);
		
		OperationByIdReq retem = new OperationByIdReq();
		retem.setId(req.getJobId());
		Integer num = taskManageService.deleteById(retem);
		num = num==null? 0: num;
		return String.valueOf(num);
	}
	
	/**
	 * stop
	 * @throws Throwable 
	 */
	@RequestMapping("stop")
	public String stop(TSystemTimedTaskManage req) throws Throwable{
		schedulerContainer.updateStatus(req);
		Integer num = taskManageService.updateBySelective(req);
		num = num==null? 0: num;
		return String.valueOf(num);
	}
	
}
