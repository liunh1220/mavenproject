package com.example.test.multithread.lock;

/**
 * ReentrantLock Condition实现wait/notify、notifyAll
 * Condition唤醒部分线程
 */
public class ReentrantMainTestA {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockService reentrantLockService = new ReentrantLockService();
        ReentrantLockTreadA threadA = new ReentrantLockTreadA(reentrantLockService);
        ReentrantLockTreadB threadB = new ReentrantLockTreadB(reentrantLockService);
        threadA.setName("A");
        threadA.start();
        threadB.setName("B");
        threadB.start();

        Thread.sleep(5000);
        reentrantLockService.signalA();// 只唤醒threadA
    }
}
