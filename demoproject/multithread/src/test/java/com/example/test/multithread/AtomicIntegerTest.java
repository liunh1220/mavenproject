package com.example.test.multithread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 资源同步  - AtomicInteger
 * AtomicInteger类可以用原子方式更新的
 * int 值，主要用于在高并发环境下的高效程序处理，使用非阻塞算法来实现并发控制
 * @author Administrator
 *
 */
public class AtomicIntegerTest {
	
    static AtomicInteger count = new AtomicInteger(0);  
    
    public static void main(String[] args){  
    	
        final AtomicIntegerTest data  = new AtomicIntegerTest();  
        //10个线程，每个线程addCount 100次:10 * 100 = 1000
        for(int i=0;i<10;i++){  
            new Thread(new Runnable(){
            	@Override
                public void run(){  
                    try{  
                        Thread.sleep(1);  
                    }catch(InterruptedException e){  
                        e.printStackTrace();  
                    }  
                    for(int j=0;j<100;j++){  
                        data.addCount();  
                    }  
                    System.out.print(count.get()+" ");  
                }}).start();                
        }  
        try{  
            Thread.sleep(3000);  
        }catch(InterruptedException e){  
            e.printStackTrace();  
        }  
        System.out.print("count="+count.get());
    }  
    
    public void addCount(){  
        count.getAndIncrement();  
    }  
    
}  
