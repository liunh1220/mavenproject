package com.example.test.multithread.producerconsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * 多生产者 消费者
 * 对象锁实现wait/notify、notifyAll
 */
public class MainTest {

    public static void main(String[] args) throws InterruptedException {
        String lock = new String("lock123");
        List<String> list = new ArrayList<>();
        Producer p = new Producer(list, lock);
        Consumer c = new Consumer(list, lock);

        int threadCount = 2;
        ThreadProducer[] threadProducers = new ThreadProducer[threadCount];
        ThreadConsumer[] threadConsumers = new ThreadConsumer[1];

        /*
        //1生产者 多消费者
        threadProducers[0] = new ThreadProducer(p);
        threadProducers[0].setName("producer"+0);
        threadProducers[0].start();
        for (int i = 0; i < threadCount; i++) {
            threadConsumers[i] = new ThreadConsumer(c);
            threadConsumers[i].setName("consumer"+i);
            threadConsumers[i].start();
        }*/

        //多生产者 1消费者
        threadConsumers[0] = new ThreadConsumer(c);
        threadConsumers[0].setName("consumer"+0);
        threadConsumers[0].start();
        for (int i = 0; i < threadCount; i++) {
            threadProducers[i] = new ThreadProducer(p);
            threadProducers[i].setName("producer"+i);
            threadProducers[i].start();
        }

        /*
        //多生产者 多消费者
        for (int i = 0; i < threadCount; i++) {
            threadProducers[i] = new ThreadProducer(p);
            threadProducers[i].setName("producer"+i);
            threadProducers[i].start();

            threadConsumers[i] = new ThreadConsumer(c);
            threadConsumers[i].setName("consumer"+i);
            threadConsumers[i].start();
        }*/
    }
}
