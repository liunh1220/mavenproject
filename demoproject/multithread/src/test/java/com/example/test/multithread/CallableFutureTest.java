package com.example.test.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *  假设在main线程启动一个线程，然后main线程需要等待子线程结束后，再继续下面的操作，我们会通过join方法阻塞main线程
 * @author liu.nh
 *
 */
public class CallableFutureTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("start main thread");  
        final ExecutorService exec = Executors.newFixedThreadPool(5);  
          
        Callable<String> call = new Callable<String>() {  
            public String call() throws Exception {  
                System.out.println("  start new thread.");  
                Thread.sleep(1000 * 5);  
                System.out.println("  end new thread.");  
                return "some value.";  
            }  
        };  
        Future<String> task = exec.submit(call);  
        Thread.sleep(1000 * 2);  
        task.get(); // 阻塞，并待子线程结束，  
        exec.shutdown();  
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);  
        System.out.println("end main thread");  

	}

}
