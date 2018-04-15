package com.test.multithread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * Param:  
 * corePoolSize - 池中所保存的线程数，包括空闲线程。  
 * maximumPoolSize - 池中允许的最大线程数(采用LinkedBlockingQueue时没有作用)。  
 * keepAliveTime -当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间，线程池维护线程所允许的空闲时间。  
 * unit - keepAliveTime参数的时间单位，线程池维护线程所允许的空闲时间的单位:秒 。  
 * workQueue - 执行前用于保持任务的队列（缓冲队列）。此队列仅保持由execute 方法提交的 Runnable 任务。  
 * RejectedExecutionHandler -线程池对拒绝任务的处理策略(重试添加当前的任务，自动重复调用execute()方法) 
 */ 
public class ThreadPoolExecutorTest {
	
	public static void main(String[] args) {
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();  
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 1, TimeUnit.DAYS, queue);  
        
        for (int i = 0; i < 20; i++) {  
            final int index = i;  
            executor.execute(new Runnable() {  
                public void run() {  
                    try {  
                        Thread.sleep(4000);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                    System.out.println(String.format("thread %d finished", index));  
                }  
            });  
        }  
        executor.shutdown(); 
	}
	
	class PausableThreadPoolExecutor extends ThreadPoolExecutor {

		private boolean isPaused;
	    private ReentrantLock pauseLock = new ReentrantLock();
	    private Condition unpaused = pauseLock.newCondition();
		
	    public PausableThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
	    	super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		}

		   protected void beforeExecute(Thread t, Runnable r) {
		     super.beforeExecute(t, r);
		     pauseLock.lock();
		     try {
		       while (isPaused) unpaused.await();
		     } catch (InterruptedException ie) {
		       t.interrupt();
		     } finally {
		       pauseLock.unlock();
		     }
		   }

		   public void pause() {
		     pauseLock.lock();
		     try {
		       isPaused = true;
		     } finally {
		       pauseLock.unlock();
		     }
		   }

		   public void resume() {
		     pauseLock.lock();
		     try {
		       isPaused = false;
		       unpaused.signalAll();
		     } finally {
		       pauseLock.unlock();
		     }
		   }
		 }

}
