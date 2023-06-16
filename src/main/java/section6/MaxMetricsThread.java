package main.java.section6;

import java.util.List;

public class MaxMetricsThread extends Thread {
    List<Long> LongStream;
    MinMaxMetrics maxMaxMetrics;

    public MaxMetricsThread(List<Long> longStream, MinMaxMetrics maxMaxMetrics) {
        LongStream = longStream;
        this.maxMaxMetrics = maxMaxMetrics;
    }

    @Override
    public void run() {
        for (Long l:LongStream
        ) {
            maxMaxMetrics.addSample(l);
        }
        System.out.println(maxMaxMetrics.getMax());
        System.out.println(Thread.currentThread().getName());
    }
}
