package com.ssm.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

import com.ssm.entity.bo.JobConstant;
import com.ssm.entity.bo.JobLog;
import com.ssm.util.DateUtil;

public class BaseJobListener implements JobListener{

	private String name;
	
	public BaseJobListener(String name){
		this.name = name;
	};
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext arg0) {
		
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext ctx) {
		String jobId = ctx.getJobDetail().getKey().getName();
		String fireInstanceId = ctx.getFireInstanceId();
		JobLog jobLog = new JobLog();
		jobLog.setJobId(ctx.getJobDetail().getKey().getName());
		jobLog.setFireInstanceId(fireInstanceId);
		jobLog.setFireTime(DateUtil.DateToString(ctx.getFireTime(), DateUtil.DEFAULT_FORMAT));
		Date next = ctx.getNextFireTime();
		if(next != null)
		{
			jobLog.setNextFireTime(DateUtil.DateToString(ctx.getNextFireTime(), DateUtil.DEFAULT_FORMAT));
		}
		LinkedHashMap<String,JobLog> map = JobConstant.getJobLogMap(jobId);
		if(map == null)
		{
			map = new LinkedHashMap<String,JobLog>();
		}
		if(map.size() >= JobConstant.DEFAULT_STORE_LOG_NUM)
		{
//			map.clear();.
			this.removeKey(map);
		}
		map.put(fireInstanceId, jobLog);
		JobConstant.setJobLogMap(jobId, map);
	}

	@Override
	public void jobWasExecuted(JobExecutionContext ctx, JobExecutionException e) {
		String jobId = ctx.getJobDetail().getKey().getName();
		String fireInstanceId = ctx.getFireInstanceId();
		
		LinkedHashMap<String,JobLog> map = JobConstant.getJobLogMap(jobId);
		if(map != null)
		{
			JobLog jobLog = map.get(fireInstanceId);
			jobLog.setFinishTime(DateUtil.DateToString(new Date(), DateUtil.DEFAULT_FORMAT));
			if(e != null)
			{
				jobLog.setDesc(e.getMessage());
			}
			map.put(fireInstanceId, jobLog);
			JobConstant.setJobLogMap(jobId, map);
		}
		JobConstant.setJobLogMap(jobId, map);
	}
	
	// 到达JobConstant.DEFAULT_STORE_LOG_NUM时，清Map剩下10个
	public void removeKey(LinkedHashMap<String,JobLog> map)
	{
		int i=0;
		List<String> removingKey = new ArrayList<String>();
		for (Entry<String, JobLog> entry : map.entrySet()) {
			i++;
			if(i <= 20){
				removingKey.add(entry.getKey());
			}
		}
		for (String key : removingKey) {
			map.remove(key);
		}
	}

}
