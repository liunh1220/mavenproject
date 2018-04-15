package com.example.multithread;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DataConsumers {
	
	public static void main(String[] args) {
		
        // 构造一个线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3, 
        		TimeUnit.SECONDS, 
        		new LinkedBlockingQueue<Runnable>(10), // 声明一个容量为10的缓存队列
                new ThreadPoolExecutor.DiscardOldestPolicy());
        /*ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS, 
        		new ArrayBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.DiscardOldestPolicy());*/
        
		//ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 12; i++) { 
            //threadPool.execute(new MyThread());  
        	threadPool.execute(new DataConsumers().new MyThreadInner(10));  
        }  
        threadPool.shutdown();
        
	}
	
	class MyThreadInner implements Runnable , Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 9093068734654985332L;
		/**
		 * 模拟传参数
		 */
		private int para;
		 
		public MyThreadInner(int para) {
			super();
			this.para = para;
		}

		@Override
		public void run() {
			System.out.println("线程调用DataProducers.dataList()");
			
			List<String[]> list = DataProducers.dataList(para);
			
			System.out.println("list.size:"+list.size());
			
		}
	}
	
}
