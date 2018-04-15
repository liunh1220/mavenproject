package com.example.test.multithread.lock;

public class ReentrantLockTreadA extends Thread{
    private ReentrantLockService service;

    public ReentrantLockTreadA(ReentrantLockService service){
        super.run();
        this.service = service;
    }

    @Override
    public void run() {
        service.waitMethodA();
    }
}
