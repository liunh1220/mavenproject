package com.example.test.multithread.producerconsumer;

public class ThreadProducer extends Thread {
    private Producer p;

    public ThreadProducer(Producer p) {
        super();
        this.p = p;
    }

    @Override
    public void run() {
        while (true){
            p.addValue();
        }
    }
}
