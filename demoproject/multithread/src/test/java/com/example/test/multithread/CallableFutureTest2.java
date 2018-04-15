package com.example.test.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *  假设在main线程启动一个线程，然后main线程需要等待子线程结束后，再继续下面的操作，我们会通过join方法阻塞main线程
 * @author liu.nh
 *
 */
public class CallableFutureTest2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("start main thread");  
        int threadCount = 5;  
        final ExecutorService exec = Executors.newFixedThreadPool(threadCount);  
  
        List<Future<Integer>> tasks = new ArrayList<Future<Integer>>();  
        for (int i = 0; i < threadCount; i++) {  
            Callable<Integer> call = new Callable<Integer>() {  
                public Integer call() throws Exception {  
                    Thread.sleep(1000);  
                    return 1;  
                }  
            };  
            tasks.add(exec.submit(call));  
        }  
        long total = 0;  
        for (Future<Integer> future : tasks) {  
            total += future.get();  
        }  
        exec.shutdown();  
        System.out.println("total: " + total);  
        System.out.println("end main thread");   

	}

}
