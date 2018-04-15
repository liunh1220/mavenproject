package com.test.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 拿到信号量的线程可以进入代码，否则就等待。通过acquire()和release()获取和释放访问许可。下面的例子只允许5个线程同时进入执行acquire()和release()之间的代码
 * @author liu.nh
 *
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		  // 线程池  
        ExecutorService exec = Executors.newCachedThreadPool();  
        // 只能5个线程同时访问  
        final Semaphore semp = new Semaphore(5);  
        // 模拟20个客户端访问  
        for (int index = 0; index < 20; index++) {  
            final int NO = index;  
            Runnable run = new Runnable() {  
                public void run() {  
                    try {  
                        // 获取许可  
                        semp.acquire();  
                        System.out.println("Accessing: " + NO);  
                        Thread.sleep((long) (Math.random() * 10000));  
                        // 访问完后，释放  
                        semp.release();  
                    } catch (InterruptedException e) {  
                    }  
                }  
            };  
            exec.execute(run);  
        }  
        // 退出线程池  
        exec.shutdown(); 

	}

}
