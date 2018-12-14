package com.example.demo.disruptor;


import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created by liulanhua on 2018/7/19.
 */
public class LongEventProducerWithTranslator {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
        public void translateTo(LongEvent event, long sequence, ByteBuffer bb) {
            event.setValue(bb.getLong(0));
        }
    };

    public void product(ByteBuffer bb) {
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }

}