package com.example.test.multithread;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 大小无限的线程池
 * 
 * 此线程池支持定时以及周期性执行任务的需求。
 * 
 * @author Administrator
 *
 */
public class ScheduledThreadPoolExecutorTest {

	public static void main(String[] args) throws InterruptedException {

		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		
		exec.scheduleAtFixedRate(new Runnable() {
			 // 每隔一段时间就触发异常
			 @Override  
             public void run() {  
				 System.out.println("================");
             } 
		}, 1000, 5000, TimeUnit.MILLISECONDS);

		exec.scheduleAtFixedRate(new Runnable() {
			// 每隔一段时间打印系统时间，证明两者是互不影响的
			@Override
			public void run() {  
				System.out.println(System.nanoTime());
            } 
		}, 1000, 2000, TimeUnit.MILLISECONDS);
	}

}
