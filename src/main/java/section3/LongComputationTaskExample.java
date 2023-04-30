package main.java.section3;

import java.math.BigInteger;
/*Here we have an example of huge computation.
* So we can terminate thread leading to this problem.
* To do it, we need to use thread.interrupt() method from Main thread.
* It Runnable class we need to check if someone externally interrupted our process.
* And if so, we need to exit the loop and return 0 as the result to skip huge calculations.*/

public class LongComputationTaskExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputationTask(new BigInteger("202348485938457"), new BigInteger("95493465934659747")));

        thread.start();
        thread.interrupt();

    }

    private static class LongComputationTask implements Runnable {
        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) { // -----> Checking if we were interrupted externally by another Thread and if so, returning 0
                    System.out.println("This thread had been interrupted by Main thread");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }
            return result;
        }
    }
}
