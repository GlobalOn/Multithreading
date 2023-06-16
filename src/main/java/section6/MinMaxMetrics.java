package main.java.section6;

import java.util.ArrayList;
import java.util.List;

/**
 Min - Max Metrics
 In this exercise, we are going to implement a class called MinMaxMetrics .

 A single instance of this class will be passed to multiple threads in our application.

 MinMaxMetrics is an analytics class used to keep track of the minimum and the maximum of a particular business or performance metric in our application.

 Example:

 A stock trading application that keeps track of the minimum and maximum price of the stock daily.

 The class will have 3 methods:

 addSample(..) - Takes a new sample.

 getMin() - Returns the sample with the minimum value we have seen so far.

 getMax() - Returns the sample with the maximum value we have seen so far.

 Notes:

 - Each method can be called concurrently by any given number of threads, so the class needs to be thread-safe.

 - In addition, this class is used for analytics, so it needs to be as performant as possible, as we don't want it to slow down our business logic threads too much.

 - Threads that call getMin() or getMax() are interested in only one of the values and are never interested in both the min and the max at the same time


 Please implement MinMaxMetrics below:

 Important Note:

 Only the logic of the class is unit tested, and it is impossible to test a class's thread safety.

 The solution to the exercise is provided in the next lecture.
 */

public class MinMaxMetrics {

    // Add all necessary member variables
        public static final List<Long> allSamples = new ArrayList<>(1000);

    /** Initializes all member variables*/
    public MinMaxMetrics() {
        // Add code here
    }

    /** Adds a new sample to our metrics.*/
    public void addSample(long newSample) {
        // Add code here
        synchronized (allSamples){
            allSamples.add(newSample);
        }
    }

    /** Returns the smallest sample we've seen so far.*/
    public long getMin() {
        // Add code here
        synchronized (allSamples){
           return allSamples.stream().min(Long::compare).orElse(Long.MIN_VALUE);
        }
    }

    /** Returns the biggest sample we've seen so far.*/
    public long getMax() {
        // Add code here
        synchronized (allSamples){
            return allSamples.stream().max(Long::compare).orElse(Long.MAX_VALUE);
        }
    }
}
