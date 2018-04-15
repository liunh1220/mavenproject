package com.example.test.multithread.producerconsumer;

import java.util.List;

public class Consumer {

    private List<String> list;
    private String lock;

    public Consumer(List<String> list, String lock) {
        super();
        this.list = list;
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                while (list.size() <= 0) {
                    System.out.println(Thread.currentThread().getName() + ": list没有数据,Consumer等待Producer生产数据");
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName() + ": list有数据,Consumer消费数据,list.size:" + list.size());
                list.remove(0);
                lock.notifyAll();
            }
        } catch (Exception e) {

        }
    }
}
