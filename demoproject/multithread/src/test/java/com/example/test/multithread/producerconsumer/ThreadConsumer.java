package com.example.test.multithread.producerconsumer;

public class ThreadConsumer extends Thread {

    private Consumer c;

    public ThreadConsumer(Consumer c) {
        super();
        this.c = c;
    }

    @Override
    public void run() {
        while (true){
            c.getValue();
        }
    }
}
