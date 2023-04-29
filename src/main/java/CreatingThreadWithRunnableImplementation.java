package main.java;

public class CreatingThreadWithRunnableImplementation {
    public static void main(String[] args) {

        //Creating a thread with passing Runnable run() method implementation in thread constructor
        Thread thread = new Thread(() ->
                System.out.println(Thread.currentThread().getName() + " is running!")
                /*Here is runnable implementation passed in constructor of Thread by Lambda expression*/);

        thread.setName("New Worker Thread");

        System.out.println(Thread.currentThread().getName() + " is running before new thread starts!");
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName() + " is running after new thread starts!");
    }
}