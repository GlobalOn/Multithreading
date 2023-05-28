package main.java.section3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* When we run more than one threads in parallel the order of their execution is undefined.
 *  Without coordinating them, we can`t predict which one of threads A and B will be executed first.
 *  So in case we want to rule them, we can use Thread.join() method. */
public class JoiningThreads {
    public static void main(String[] args) {

        List<Long> inputNumbers = Arrays.asList(12L, 111L, 5439869L, 666L, 1703L);
        //We want to calculate !inputNumber each one in parallel

        List<FactorialThread> threads = new ArrayList<>();

        for (long inputNumber:inputNumbers
             ) {
            threads.add(new FactorialThread(inputNumber));
        }


        for (Thread thread:threads
             ) {
            try {
                thread.setDaemon(true);
                thread.start();
                thread.join(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished){
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            }
            else{
                System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }

    }

    public static class FactorialThread extends Thread {
        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(long inputNumber) {
            BigInteger tempResult = BigInteger.ONE;
            for (long i = inputNumber; i > 0; i--) {
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }
            return tempResult;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }
    }
}
