package main.java.section5;

/**
 * Resource Sharing & Introduction to Critical Sections
 * In this section we can see, how NOT atomic operations (like ++ or --) without any synchronization and wrong
 * placed join() can affect to result of execution and make it unpredictable.
 * '++' -----> is 3 step operation (get value, increase value, save new value) and
 * while ++ is increasing value or saving new value, OS can invoke --, execute it, and only after --,
 * invoke saving of ++
 * In explained above situation, we will lose the result of -- operation, by overriding it
 * with ++ result.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);
        long x = System.nanoTime();

        incrementingThread.start();
        decrementingThread.start();

        //      incrementingThread.join();
        //      decrementingThread.join();
        int mainThreadSleepTime = 5;
        Thread.sleep(mainThreadSleepTime);
        System.out.println(System.nanoTime() - x - mainThreadSleepTime * 1000000);

        System.out.println("We currently have " + inventoryCounter.getItems() + " items");

    }

    public static class DecrementingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            synchronized (inventoryCounter) {
                for (int i = 0; i < 10000; i++) {
                    inventoryCounter.decrement();
                }
            }
        }
    }

    public static class IncrementingThread extends Thread {
        private InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            synchronized (inventoryCounter) {
                for (int i = 0; i < 10000; i++) {
                    inventoryCounter.increment();
                }
            }
        }
    }

    private static class InventoryCounter {
        private int items = 0;

        public void increment() {
            items++;
        }

        public void decrement() {
            items--;
        }

        public int getItems() {
            return items;
        }
    }
}