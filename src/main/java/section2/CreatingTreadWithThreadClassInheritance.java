package main.java.section2;

public class CreatingTreadWithThreadClassInheritance {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " is running before new thread starts!");
        Thread thread = new NewThread();
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " is running after new thread starts!");

    }

    //Creating a thread with extending Thread class directly and implementing its run() method
    private static class NewThread extends Thread {
        @Override
        public void run() {
            this.setName("New Worker Thread");
           //instead of Thread.currentThread().getName() here we can use this.getName(), calling directly current method
            System.out.println(this.getName() + " is running!");
        }
    }
}