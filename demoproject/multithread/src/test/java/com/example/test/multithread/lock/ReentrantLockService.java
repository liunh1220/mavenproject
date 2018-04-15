package com.example.test.multithread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockService {
    private ReentrantLock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();

    public void waitMethodA(){
        try {
            lock.lock();
            System.out.println("waitMethodA condition.await 前:"+System.currentTimeMillis());
            conditionA.await();
            System.out.println("waitMethodA condition.await 后");
        }catch (Exception e){
        }finally {
            lock.unlock();
            System.out.println("waitMethodA lock.unlock");
        }
    }

    public void waitMethodB(){
        try {
            lock.lock();
            System.out.println("waitMethodB condition.await 前:"+System.currentTimeMillis());
            conditionB.await();
            System.out.println("waitMethodB condition.await 后");
        }catch (Exception e){
        }finally {
            lock.unlock();
            System.out.println("waitMethodB lock.unlock");
        }
    }

    public void signalA(){
        try {
            lock.lock();
            System.out.println("signalA:"+System.currentTimeMillis());
            conditionA.signalAll();
        }catch (Exception e){
        }finally {
            lock.unlock();
            System.out.println("signalA lock.unlock");
        }
    }

    public void signalB(){
        try {
            lock.lock();
            System.out.println("signalB:"+System.currentTimeMillis());
            conditionA.signalAll();
        }catch (Exception e){
        }finally {
            lock.unlock();
            System.out.println("signalB lock.unlock");
        }
    }

}
