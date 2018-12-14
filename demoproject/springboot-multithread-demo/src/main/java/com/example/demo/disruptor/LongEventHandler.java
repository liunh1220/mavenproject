package com.example.demo.disruptor;


import com.lmax.disruptor.EventHandler;

/**
 * 消费者
 * Created by liulanhua on 2018/7/19.
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("消费者消费数据：" + longEvent.getValue());
    }

}
