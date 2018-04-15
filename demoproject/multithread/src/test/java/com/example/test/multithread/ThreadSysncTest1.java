package com.example.test.multithread;

/**
 * 资源同步 - synchronized同步方法
 * 
 * 对于同步静态方法，对象锁就是该静态放发所在的类的Class实例，
 * 由于在JVM中，所有被加载的类都有唯一的类对象，具体到本例，
 * 就是唯一的 ThreadSysncTest1.class对象。不管我们创建了该类的多少实例，但是它的类实例仍然是一个！
 * 
 * @author Administrator
 *
 */
public class ThreadSysncTest1 extends Thread {  
	
	private int threadNo;     
    
    public ThreadSysncTest1(int threadNo) {     
        this.threadNo = threadNo;     
    }     
    
    @Override    
    public void run() {     
    	sycnMethod(threadNo);
    }
    
    /**
     * 同步方法: static synchronized
     */
    public static synchronized void sycnMethod(int threadNo) {     
        for (int i = 1; i < 10000; i++) {     
            System.out.println("No." + threadNo + ":" + i);             
        }     
    }   
    
    public static void main(String[] args) throws Exception {     
        for (int i = 1; i < 10; i++) {     
           new ThreadSysncTest1(i).start();     
            Thread.sleep(1);     
        }     
     }     
      
}
