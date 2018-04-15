package com.example.test.multithread.lock;

public class ReadWriteLockTest {

    public static void main(String[] args){
        final ReadWriteLockService service = new ReadWriteLockService();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                service.writeMethod();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                service.readMethod();
            }
        });

        t1.setName("A");
        t1.start();
        t2.setName("B");
        t2.start();

    }

}
