package com.example.test.multithread;

/**
 * 资源同步 - synchronized同步代码块
 * 
 * 这个同步块的对象锁，就是 main方法中创建的那个String对象。
 * 换句话说，他们指向的是同一个String类型的对象，对象锁是共享且唯一的！
 * 注：同步是一种高开销的操作，因此应该尽量减少同步的内容。 通常没有必要同步整个方法，使用synchronized代码块同步关键代码即可。
 * @author Administrator
 *
 */
public class ThreadSysncTest2 extends Thread {  
	
	 private int threadNo; 
	 private String lock; 
	  
	 public ThreadSysncTest2(int threadNo, String lock) {     
	     this.threadNo = threadNo;     
	     this.lock = lock;   
	 }     
	 
	public void run() {   
		/**
		 * 同步代码块
		 */
	    synchronized (lock) {     
	        for (int i = 1; i < 10000; i++) {     
		        System.out.println("No." + threadNo + ":" + i);     
		    }        
		}  
		/*for (int i = 1; i < 10000; i++) {     
	        System.out.println("No." + threadNo + ":" + i);     
	    }*/
	}    
	
	public static void main(String[] args) throws Exception {     
	     String lock = new String("lock");     
	     for (int i = 1; i < 10; i++) {       
	    	 new ThreadSysncTest2(i, lock).start();     
	         Thread.sleep(1);     
	     }     
	}
	 
}    
