package com.example.springquartz.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyQuartzJob  implements Job {
	
	//private final Logger logger = LoggerFactory.getLogger(MyQuartzJob.class);
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException 
	{
	    System.out.println("执行任务："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+ "★★★★★★★★★★★");  
	}
}
