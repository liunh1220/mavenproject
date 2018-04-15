package com.example.test.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 可缓存线程池
 * 
 * 如果线程池的大小超过了处理任务所需要的线程，
 * 那么就会回收部分空闲（60秒不执行任务）的线程，当任务数增加时，此线程池又可以智能的添加新线程来处理任务。
 * 此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
 * 
 * @author Administrator
 *
 */
public class NewCachedThreadPoolTest {

	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService service = Executors.newCachedThreadPool();  
        for (int i = 0; i < 1000; i++) {  
            Runnable run = new Runnable() {  
                @Override  
                public void run() {  
                    System.out.println("thread start");  
                }  
            };  
            service.execute(run);  
        }  
        service.shutdown();  
        //service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);  
        System.out.println("all thread complete");  

	}

}
