package main.java.section6;

import java.util.Arrays;
import java.util.List;

public class MinMaxMetricsTest {
    public static void main(String[] args) throws InterruptedException {
        MinMaxMetrics minMaxMetrics = new MinMaxMetrics();
        List<Long> List1 = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        List<Long> List2 = Arrays.asList(-1L, -2L, -3L, -4L, -5L);
        List<Long> List3 = Arrays.asList(10L, 20L, 30L, 40L, 50L);
        List<Long> List4 = Arrays.asList(-10L, -20L, -30L, -40L, -50L);

        Thread maxThread1 = new MaxMetricsThread(List1, minMaxMetrics);
        Thread minThread1 = new Thread(new MinMetricsThread(List2, minMaxMetrics));
        Thread maxThread2 = new MaxMetricsThread(List3, minMaxMetrics);
        Thread minThread2 = new Thread(new MinMetricsThread(List4, minMaxMetrics));

        maxThread1.start();
//        maxThread1.join();
        minThread1.start();
//        minThread1.join();
        maxThread2.start();
//        maxThread2.join();
        minThread2.start();
//        minThread2.join();

    }
}
