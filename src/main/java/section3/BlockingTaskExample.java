package main.java.section3;

/* We can send interrupting signal from one thread (Main) to another (new created Thread).
 * In this case, if thread was running and interrupted by Main thread, it will be interrupted if Interrupted exception handling.
 * Catch block will be executed.*/
public class BlockingTaskExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new BlockingTask());
        thread.start();
        thread.interrupt();
    }

    private static class BlockingTask implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("This thread " + Thread.currentThread().getName() + " has started, now it will sleep for a long time.");
                Thread.sleep(5000000); // ----->thread.interrupt() from main will lead to   -   throw new InterruptedException();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted by Main class while executing. Interrupted exception had been thrown.");
            }
        }
    }
}
