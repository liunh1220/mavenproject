package com.example.springquartz.controller;

import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.springquartz.entity.MyQuartzJobBean;
import com.example.springquartz.service.ITaskService;

/**
 * 定时任务
 * @author Administrator
 *
 */
@RequestMapping("scheduling/")
public class SchedulingController {

	@Autowired
	private  ITaskService taskService;
	
	/**
	 * excuting
	 * @throws SchedulerException 
	 */
	@RequestMapping(value="excuteJob",method=RequestMethod.POST)
	public String excuteJob(MyQuartzJobBean scheduleJob) throws SchedulerException{
		return Boolean.toString(taskService.excuteJob(scheduleJob));
	}
	
	/**
	 * add
	 * @throws SchedulerException 
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(MyQuartzJobBean scheduleJob) throws SchedulerException{
		return Boolean.toString(taskService.addJob(scheduleJob));
	}
	
	/**
	 * dele
	 * @throws SchedulerException 
	 */
	@RequestMapping(value="dele",method=RequestMethod.POST)
	public String dele(MyQuartzJobBean scheduleJob) throws SchedulerException{
		return Boolean.toString(taskService.deleteJob(scheduleJob));
	}
	
	/**
	 * stop
	 * @throws SchedulerException 
	 */
	@RequestMapping(value="stop",method=RequestMethod.POST)
	public String stop(MyQuartzJobBean scheduleJob) throws SchedulerException{
		return Boolean.toString(taskService.stopJob(scheduleJob));
	}
	
	/**
	 * detail
	 * @throws SchedulerException 
	 */
	@RequestMapping(value="detail",method=RequestMethod.GET)
	public MyQuartzJobBean detail(MyQuartzJobBean scheduleJob) throws SchedulerException{
		return taskService.getTask(scheduleJob);
	}
	
	/**
	 * list
	 * @throws SchedulerException 
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	public List<MyQuartzJobBean> list(MyQuartzJobBean scheduleJob) throws SchedulerException{
		return taskService.getTaskList(scheduleJob);
	}
	
}
