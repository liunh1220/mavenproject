package com.example.test.multithread.list;

public class MainTest {

    public static void main(String[] args) throws InterruptedException {
        OneList list = new OneList();

        MyTread1 thread1 = new MyTread1(list);
        thread1.setName("A");
        thread1.start();

        MyTread2 thread2 = new MyTread2(list);
        thread2.setName("B");
        thread2.start();

        Thread.sleep(1000);
        System.out.println(list.getSize());
    }
}
