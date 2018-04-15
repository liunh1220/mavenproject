package com.example.test.multithread.producerconsumer;

import java.util.List;

public class Producer {
    private List<String> list;
    private String lock;

    public Producer(List<String> list, String lock) {
        super();
        this.list = list;
        this.lock = lock;
    }

    public void addValue() {
        try {
            synchronized (lock) {
                while (list.size() > 0) {
                    System.out.println(Thread.currentThread().getName() + ": list有数据,Producer waiting");
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName() + ": list没有数据,Producer生产数据");
                list.add("data:" + System.currentTimeMillis());
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
