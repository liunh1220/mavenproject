package com.example.springquartz.test;

import org.quartz.JobKey;
import org.quartz.TriggerKey;

import com.example.springquartz.job.MyQuartzJob;
import com.example.springquartz.manager.QuartzManager;

public class QuartzTest {
	 public static void main(String[] args) {
		    try {
		      String job_name = "动态任务调度";
		      String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";
		      String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
		      
		      System.out.println("【系统启动】开始(每1秒输出一次)..."); // 0/1 * * * * ?
		      QuartzManager.addJob(job_name, MyQuartzJob.class, "*/1 * * * * ?");  
		      QuartzManager.startJobs();
		      Thread.sleep(3000);  
		      
		      System.out.println("【修改时间】开始(每5秒输出一次)..."); // */5 * * * * ?
		      QuartzManager.pauseJob(job_name);
		      QuartzManager.modifyJobTime(job_name, "*/5 * * * * ?");  
		      QuartzManager.resumeJob(job_name);
		      Thread.sleep(20000);  
		      
		      //System.out.println("【修改时间】开始(每3秒输出一次)..."); // */3 * * * * ?
		      //QuartzManager.updateJobTime(job_name, "0/3 * * * * ?");  
		      //Thread.sleep(10000);  
		      
		      System.out.println("【移除定时】开始...");  
		      QuartzManager.removeJob(new TriggerKey(job_name, TRIGGER_GROUP_NAME),new JobKey(job_name, JOB_GROUP_NAME));  
		      System.out.println("【移除定时】成功");  
		      
		      System.out.println("【停止定时】停止...");
		      QuartzManager.shutdownJobs();
		      
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		  }
}
