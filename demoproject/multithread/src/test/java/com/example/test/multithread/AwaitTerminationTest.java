package com.example.test.multithread;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *一个读线程，用于将要处理的文件对象添加到阻塞队列中，另外四个写线程用于取出文件对象，为了模拟写操作耗时长的特点，特让线程睡眠一段随机长度的时间。
 *另外，该Demo也使用到了线程池和原子整型（AtomicInteger），AtomicInteger可以在并发情况下达到原子化更新，
 *避免使用了synchronized，而且性能非常高。由于阻塞队列的put和take操作会阻塞，为了使线程退出，
 *在队列中添加了一个“标识”，算法中也叫“哨兵”，当发现这个哨兵后，写线程就退出。
 *
 */
public class AwaitTerminationTest {
	
	static long randomTime() {  
        return (long) (Math.random() * 1000);  
    }  
	
	public static void main(String[] args) throws InterruptedException {
		 // 能容纳100个文件  
        final BlockingQueue<File> queue = new LinkedBlockingQueue<File>(100);  
        // 线程池  
        final ExecutorService exec = Executors.newFixedThreadPool(5);  
        final File root = new File("D:\\dist\\blank");  
        // 完成标志  
        final File exitFile = new File("");  
        // 读个数  
        final AtomicInteger rc = new AtomicInteger();  
        // 写个数  
        final AtomicInteger wc = new AtomicInteger();  
        // 读线程  
        Runnable read = new Runnable() {  
            public void run() {  
                scanFile(root);  
                scanFile(exitFile);  
            }  
  
            public void scanFile(File file) {  
                if (file.isDirectory()) {  
                    File[] files = file.listFiles(new FileFilter() {  
                        public boolean accept(File pathname) {  
                            return pathname.isDirectory() || pathname.getPath().endsWith(".log");  
                        }  
                    });  
                    for (File one : files)  
                        scanFile(one);  
                } else {  
                    try {  
                        int index = rc.incrementAndGet();  
                        System.out.println("Read0: " + index + " " + file.getPath());  
                        queue.put(file);  
                    } catch (InterruptedException e) {  
                    }  
                }  
            }  
        };  
        exec.submit(read);  
        // 四个写线程  
        for (int index = 0; index < 4; index++) {  
            // write thread  
            final int num = index;  
            Runnable write = new Runnable() {  
                String threadName = "Write" + num;  
  
                public void run() {  
                    while (true) {  
                        try {  
                            Thread.sleep(randomTime());  
                            int index = wc.incrementAndGet();  
                            File file = queue.take();  
                            // 队列已经无对象  
                            if (file == exitFile) {  
                                // 再次添加"标志"，以让其他线程正常退出  
                                queue.put(exitFile);  
                                break;  
                            }  
                            System.out.println(threadName + ": " + index + " " + file.getPath());  
                        } catch (InterruptedException e) {  
                        }  
                    }  
                }  
  
            };  
            exec.submit(write);  
        }  
        exec.shutdown();  
	}

}
