package com.example.test.multithread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 先执行thread1，再执行thread2...
 * @author liu.nh
 *
 */
public class ThreadJionTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ThreadJionTest.class);
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
					System.out.println("先执行thread1");
				} catch (Exception e) {
					logger.error("分销更新预留出错：", e);
				}
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("再执行thread2");
				} catch (Exception e) {
					logger.error("直销更新预留出错：", e);
				}
			}
		});
		
		thread1.start();
		thread1.join();//次方法不能放到thread2.start()后
		
		thread2.start();
		thread2.join();
		
	}

}
