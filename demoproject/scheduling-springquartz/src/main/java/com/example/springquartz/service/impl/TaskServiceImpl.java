package com.example.springquartz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.common.util.KeyGenerator;
import com.example.springquartz.dao.TSystemTimedTaskAbnormalMapper;
import com.example.springquartz.dao.TSystemTimedTaskManageMapper;
import com.example.springquartz.entity.MyQuartzJobBean;
import com.example.springquartz.entity.TSystemTimedTaskAbnormal;
import com.example.springquartz.entity.TSystemTimedTaskManage;
import com.example.springquartz.enums.TaskStatus;
import com.example.springquartz.manager.TaskManager;
import com.example.springquartz.service.ITaskService;

@Service
public class TaskServiceImpl implements ITaskService {

	private final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);    
    
	private TaskManager taskManager;
	@Autowired
	private TSystemTimedTaskManageMapper manageMapper;
	@Autowired
	private TSystemTimedTaskAbnormalMapper abnormalMapper;
	//@Autowired
	//private TSystemTimedTaskRunningTimeMapper runningTimeMapper;
	
	
	@Override
	public List<MyQuartzJobBean> getAllJobs() throws SchedulerException {
		return taskManager.getAllJobs(); 
	}

	@Override
	public MyQuartzJobBean getJob(String jobName, String jobGroup) throws SchedulerException {
        return taskManager.getJob(jobName, jobGroup);  
	}

	@Override
	public List<MyQuartzJobBean> getRunningJob() throws SchedulerException {
		return taskManager.getRunningJob();    
	}

	@Override
	public boolean addJob(MyQuartzJobBean job) throws SchedulerException {
		TSystemTimedTaskManage systemTimedTaskManage = setTSystemTimedTaskManage(job);
		systemTimedTaskManage.setJobId(KeyGenerator.getSignature());
		Integer result = manageMapper.insertSelective(systemTimedTaskManage);
		if(result != null && result > 0){
			taskManager.addJob(job); 
			logger.info("--------- addJob success ---------");
			return true;   
		}
		abnormalMapper.insert(setTSystemTimedTaskAbnormal(systemTimedTaskManage.getJobId(),"addJob fail"));
		logger.error("--------- addJob fail ---------");
		return false;   
	}

	@Override
	public boolean stopJob(MyQuartzJobBean scheduleJob) {
		scheduleJob.setJobStatus(TaskStatus.LOCKED.name());
		TSystemTimedTaskManage systemTimedTaskManage = setTSystemTimedTaskManage(scheduleJob);
		systemTimedTaskManage.setJobId(KeyGenerator.getUUID());
		Integer result = manageMapper.updateByPrimaryKeySelective(systemTimedTaskManage);
		if(result != null && result > 0){
			taskManager.deleteJob(scheduleJob);
			logger.info("--------- stopJob success ---------");
			return true;   
		}
		abnormalMapper.insert(setTSystemTimedTaskAbnormal(systemTimedTaskManage.getJobId(),"stopJob fail"));
		logger.error("--------- stopJob fail ---------");
		return false;
	}

	@Override
	public boolean deleteJob(MyQuartzJobBean scheduleJob) {
		Integer result = manageMapper.deleteByPrimaryKey(scheduleJob.getJobId());
		if(result != null && result > 0){
			taskManager.deleteJob(scheduleJob);
			logger.info("--------- deleteJob success ---------");
			return true;   
		}
		abnormalMapper.insert(setTSystemTimedTaskAbnormal(scheduleJob.getJobId(),"deleteJob fail"));
		logger.error("--------- deleteJob fail ---------");
		return false;
	}

	@Override
	public boolean excuteJob(MyQuartzJobBean scheduleJob) throws SchedulerException {
		scheduleJob.setJobStatus(TaskStatus.NORMAL.name());
		TSystemTimedTaskManage systemTimedTaskManage = setTSystemTimedTaskManage(scheduleJob);
		Integer result = manageMapper.updateByPrimaryKeySelective(systemTimedTaskManage);
		if(result != null && result > 0){
			taskManager.excuteJob(scheduleJob);
			logger.info("--------- excuteJob success ---------");
			return true;   
		}
		abnormalMapper.insert(setTSystemTimedTaskAbnormal(scheduleJob.getJobId(),"excuteJob fail"));
		logger.error("--------- excuteJob success ---------");
		return false;
	}

	@Override
	public boolean updateCronExpression(MyQuartzJobBean scheduleJob) throws SchedulerException {
		scheduleJob.setJobStatus(TaskStatus.NORMAL.name());
		TSystemTimedTaskManage systemTimedTaskManage = setTSystemTimedTaskManage(scheduleJob);
		Integer result = manageMapper.updateByPrimaryKeySelective(systemTimedTaskManage);
		if(result != null && result > 0){
			taskManager.updateCronExpression(scheduleJob);
			logger.info("--------- updateCronExpression success ---------");
			return true;   
		}
		abnormalMapper.insert(setTSystemTimedTaskAbnormal(scheduleJob.getJobId(),"updateCronExpression fail"));
		logger.error("--------- updateCronExpression success ---------");
		return false;
	}

	@Override
	public List<MyQuartzJobBean> getTaskList(MyQuartzJobBean req) {
		TSystemTimedTaskManage systemTimedTaskManage = setTSystemTimedTaskManage(req);
		List<MyQuartzJobBean> jobs = new ArrayList<MyQuartzJobBean>();  
		List<TSystemTimedTaskManage> findList = manageMapper.findList(systemTimedTaskManage);
		if(CollectionUtils.isEmpty(findList)){
			for(TSystemTimedTaskManage b : findList){
				jobs.add(setMyQuartzJobBean(b));
			}
		}
		return jobs;  
	}

	@Override
	public MyQuartzJobBean getTask(MyQuartzJobBean req) {
		MyQuartzJobBean job = null;    
		TSystemTimedTaskManage result = manageMapper.selectByPrimaryKey(req.getJobId());
		if(result != null){
			job = setMyQuartzJobBean(result);
		}
		return job; 
	}
	
	
	private TSystemTimedTaskManage setTSystemTimedTaskManage(MyQuartzJobBean req){
		TSystemTimedTaskManage resp = new TSystemTimedTaskManage();
		resp.setEnabledStatus(req.getJobStatus());
		resp.setEndTime(req.getEndTime());
		resp.setExecutionClazz(req.getJobClass());
		resp.setExecutionMathod(req.getMethodName());
		resp.setExecutionTime(req.getCronExpression());
		//resp.setExtendParam();
		resp.setJobId(req.getJobId());
		resp.setJobName(req.getJobName());
		resp.setRemark(req.getDescription());
		resp.setStartTime(req.getStartTime());
		return resp;
	}
	
	private TSystemTimedTaskAbnormal setTSystemTimedTaskAbnormal(String jobId,String abnmContent){
		TSystemTimedTaskAbnormal resp = new TSystemTimedTaskAbnormal();
		resp.setAbnmContent(abnmContent);
		//resp.setAbnmId(req);
		resp.setCreateTime(new Date());
		resp.setJobId(jobId);
		return resp;
	}
	
	private MyQuartzJobBean setMyQuartzJobBean(TSystemTimedTaskManage req){
		MyQuartzJobBean resp = new MyQuartzJobBean();
		resp.setCronExpression(req.getExecutionTime());
		resp.setDescription(req.getRemark());
		resp.setEndTime(req.getEndTime());
		//resp.setIsConcurrent(isConcurrent);
		resp.setJobClass(req.getExecutionClazz());
		//resp.setJobGroup(req.get);
		resp.setJobId(req.getJobId());
		resp.setJobName(req.getJobName());
		resp.setJobStatus(req.getEnabledStatus());
		resp.setMethodName(req.getExecutionMathod());
		//resp.setNextTime(req.get);
		//resp.setPreviousTime(previousTime);
		//resp.setSpringId(springId);
		resp.setStartTime(req.getStartTime());
		return resp;
	}

}
