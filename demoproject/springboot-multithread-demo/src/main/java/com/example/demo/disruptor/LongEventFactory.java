package com.example.demo.disruptor;


import com.lmax.disruptor.EventFactory;

/**
 * Created by liulanhua on 2018/7/19.
 */
public class LongEventFactory implements EventFactory {

    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}
