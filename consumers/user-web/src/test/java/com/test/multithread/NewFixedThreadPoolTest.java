package com.test.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPoolTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(2);  
        for (int i = 0; i < 4; i++) {  
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
