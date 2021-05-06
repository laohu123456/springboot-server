package com.server.utils.delay;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayElement<T> implements Delayed {

    private T data;
    private Long expire;
    private Long delayTime;

    public DelayElement(T data, Long delayTime) {
        this.data = data;
        this.delayTime = delayTime;
        this.expire = System.currentTimeMillis() + this.delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire -System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "DelayElement{" +
                "data=" + data +
                ", expire=" + expire +
                ", delayTime=" + delayTime +
                '}';
    }


}
