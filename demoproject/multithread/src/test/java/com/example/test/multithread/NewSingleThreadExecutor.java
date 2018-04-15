package com.example.test.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 单线程线程池
 * 
 * 线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。
 * 如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。
 * 此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
 * 
 * @author Administrator
 *
 */
public class NewSingleThreadExecutor {

	public static void main(String[] args) throws InterruptedException {
		
		int count = 1000;
		// 开始的倒数锁  
        final CountDownLatch begin = new CountDownLatch(1);  
        // 结束的倒数锁  
        final CountDownLatch end = new CountDownLatch(count);  
		
		ExecutorService service = Executors.newSingleThreadExecutor();  
        for (int i = 0; i < count; i++) {  
            Runnable run = new Runnable() {  
                @Override  
                public void run() {  
                	try {  
	                	begin.await();
	                    System.out.println("thread start");  
                	} catch (InterruptedException e) {  
                    } finally {  
                        end.countDown();  
                    } 
                }  
            };  
            service.execute(run);  
        }  
        service.shutdown();  
        
        begin.countDown();
        end.await();
        //service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);  
        System.out.println("all thread complete");  

	}

}
