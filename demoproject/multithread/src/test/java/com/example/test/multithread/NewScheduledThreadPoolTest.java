package com.example.test.multithread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 限制大小的定时任务线程池
 * 
 * 此线程池支持定时以及周期性执行任务的需求。
 * 
 * @author Administrator
 *
 */
public class NewScheduledThreadPoolTest  implements Runnable {
	
	private String jobName = "";
	
	public NewScheduledThreadPoolTest(String jobName) {
		super();
		this.jobName = jobName;
	}
	
	@Override
	public void run() {
		System.out.println("execute " + jobName);
	}
	
	public static void main(String[] args) {
		
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		
		long initialDelay1 = 1;
		long period1 = 1;
		// 从现在开始1秒钟之后，每隔1秒钟执行一次job1
		service.scheduleAtFixedRate(new NewScheduledThreadPoolTest("job1"), initialDelay1, period1, TimeUnit.SECONDS);
		
		long initialDelay2 = 1;
		long delay2 = 1;
		// 从现在开始2秒钟之后，每隔2秒钟执行一次job2
		service.scheduleWithFixedDelay(new NewScheduledThreadPoolTest("job2"), initialDelay2, delay2, TimeUnit.SECONDS);
		
	}

}
