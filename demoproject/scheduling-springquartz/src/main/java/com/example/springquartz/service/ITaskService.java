package com.example.springquartz.service;

import java.util.List;

import org.quartz.SchedulerException;

import com.example.springquartz.entity.MyQuartzJobBean;

public interface ITaskService {

	/**
	 * 获取所有任务
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	List<MyQuartzJobBean> getAllJobs() throws SchedulerException;

	/**
	 * 获取单个任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 * @throws SchedulerException
	 */
	MyQuartzJobBean getJob(String jobName, String jobGroup) throws SchedulerException;

	/**
	 * 所有正在运行的job
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	List<MyQuartzJobBean> getRunningJob() throws SchedulerException;

	/**
	 * 查询任务列表
	 * 
	 * @return
	 */
	List<MyQuartzJobBean> getTaskList(MyQuartzJobBean req);

	/**
	 * 查询任务
	 * 
	 * @return
	 */
	MyQuartzJobBean getTask(MyQuartzJobBean req);

	/**
	 * 添加任务
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	boolean addJob(MyQuartzJobBean job) throws SchedulerException;

	/**
	 * 暂停任务
	 * 
	 * @param scheduleJob
	 * @return
	 */
	boolean stopJob(MyQuartzJobBean scheduleJob);

	/**
	 * 删除任务
	 */
	boolean deleteJob(MyQuartzJobBean scheduleJob);

	/**
	 * 立即执行一个任务
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	boolean excuteJob(MyQuartzJobBean scheduleJob) throws SchedulerException;

	/**
	 * 更新任务时间表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	boolean updateCronExpression(MyQuartzJobBean scheduleJob) throws SchedulerException;

}
