package com.ssm.job;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssm.base.BaseJob;

public class TestJob implements BaseJob{

	private static final Logger logger = LoggerFactory.getLogger(TestJob.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String param1 = dataMap.getString("param");
		logger.info("执行任务，参数：" + param1);
	}

}
