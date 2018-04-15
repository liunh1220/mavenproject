package com.example.test.multithread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockService {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void readMethod(){
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+" readMethodA");
            Thread.sleep(1000);
        }catch (Exception e){
        }finally {
            lock.readLock().unlock();
            System.out.println(Thread.currentThread().getName()+" readMethod lock.readLock().unlock");
        }
    }

    public void writeMethod(){
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName()+" writeMethod");
            Thread.sleep(1000);
        }catch (Exception e){
        }finally {
            lock.writeLock().unlock();
            System.out.println(Thread.currentThread().getName()+" writeMethod lock.writeLock().unlock");
        }
    }

}
