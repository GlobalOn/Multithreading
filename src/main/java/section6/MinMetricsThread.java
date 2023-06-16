package main.java.section6;

import java.util.List;

public class MinMetricsThread implements Runnable{
    List<Long> LongStream;
    MinMaxMetrics minMaxMetrics;

    public MinMetricsThread(List<Long> longStream, MinMaxMetrics minMaxMetrics) {
        LongStream = longStream;
        this.minMaxMetrics = minMaxMetrics;
    }

    @Override
    public void run() {
        for (Long l:LongStream
             ) {
            minMaxMetrics.addSample(l);
        }
        System.out.println(minMaxMetrics.getMin());
    }
}
