package com.ssm.job;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssm.base.BaseJob;
import com.ssm.domain.Job;
import com.ssm.manager.IJobManager;

public class TestJob implements BaseJob{

	private static final Logger logger = LoggerFactory.getLogger(TestJob.class);
	@Autowired
    private IJobManager jobManager;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		List<Job> listJob = jobManager.list(new Job());
		if(CollectionUtils.isNotEmpty(listJob)){
			String param1 = null;
			for(Job job : listJob){
				param1 = dataMap.getString(job.getId());
				logger.info("执行任务，参数：" + param1);
			}
		}
	}

}
