package com.example.test.multithread.lock;

public class ReentrantLockTreadB extends Thread{
    private ReentrantLockService service;

    public ReentrantLockTreadB(ReentrantLockService service){
        super.run();
        this.service = service;
    }

    @Override
    public void run() {
        service.waitMethodB();
    }
}
