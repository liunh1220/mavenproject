package com.example.test.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定大小线程池
 * 
 * 线程池大小根据方法传入参数而定，超过线程池大小的线程会线程池外队列中排队等待
 * 每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。
 * 线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
 * 
 * @author Administrator
 *
 */
public class NewFixedThreadPoolTest {

	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService service = Executors.newFixedThreadPool(4);
		
        for (int i = 0; i < 100; i++) {  
            Runnable run = new Runnable() {  
                @Override  
                public void run() {  
                    System.out.println("thread start");  
                }  
            };  
            service.execute(run);  
        }  
        service.shutdown();  
        
        System.out.println("all thread complete");  
	}

}
