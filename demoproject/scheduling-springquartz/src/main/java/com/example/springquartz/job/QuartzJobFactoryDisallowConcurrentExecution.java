package com.example.springquartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.springquartz.entity.MyQuartzJobBean;
import com.example.springquartz.util.TaskUtils;

/**     
 * Job有状态实现类，不允许并发执行     
 * 	若一个方法一次执行不完下次轮转时则等待该方法执行完后才执行下一次操作     
 * 	主要是通过注解：@DisallowConcurrentExecution     
 * @author root     
 *     
 */     
@DisallowConcurrentExecution     
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {     
     
	private final Logger logger = LoggerFactory.getLogger(QuartzJobFactoryDisallowConcurrentExecution.class);     
	     
	@Override     
    public void execute(JobExecutionContext context) throws JobExecutionException {     
		MyQuartzJobBean scheduleJob = (MyQuartzJobBean)context.getMergedJobDataMap().get("scheduleJob");     
        logger.info("运行任务名称 = [" + scheduleJob.getJobName() + "]");     
        TaskUtils.invokMethod(scheduleJob);     
    }     
     
}     
