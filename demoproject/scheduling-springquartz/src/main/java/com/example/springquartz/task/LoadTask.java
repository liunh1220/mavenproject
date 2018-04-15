package com.example.springquartz.task;

import java.util.List;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.springquartz.entity.MyQuartzJobBean;
import com.example.springquartz.service.ITaskService;

public class LoadTask {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());     
    
	@Autowired     
	private ITaskService taskService;     
	     
	public void initTask() throws SchedulerException {		     
		// 可执行的任务列表        
		List<MyQuartzJobBean> taskList = taskService.getTaskList(new MyQuartzJobBean());     
		logger.info("初始化加载定时任务......");     
		for (MyQuartzJobBean job : taskList) {     
			taskService.addJob(job);     
		}     
	}	
}
