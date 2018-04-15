package com.example.test.multithread;

/**
 * 资源同步 - synchronized同步run方法,不能实现资源同步
 * 如果要对这些线程进行同步，那么这些线程所持有的对象锁应当是共享且唯一的
 * 
 * @author Administrator
 *
 */
public class ThreadSysncTest3 extends Thread {  
	
	private int threadNo;     
    
    public ThreadSysncTest3(int threadNo) {     
        this.threadNo = threadNo;     
    }     
    
    /**
     * 同步方法: synchronized run()不能实现资源同步
     */
    @Override    
    public synchronized void run() {     
    	for (int i = 1; i < 10000; i++) {     
            System.out.println("No." + threadNo + ":" + i);             
        }  
    }
    
    public static void main(String[] args) throws Exception {     
        for (int i = 1; i < 10; i++) {     
            new ThreadSysncTest3(i).start();     
            Thread.sleep(1);     
        }     
     }     
      
}
