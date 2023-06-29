package main.java.section8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Some testing of compareAndSet() method functionality with AtomicReference class
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {

        String oldName = "OLD NAME";
        String newName = "NEW NAME";
        String myName = "YURYI";
        String newName1 = newName;
        AtomicReference<String> ar = new AtomicReference<>(newName);

        try (ExecutorService es = Executors.newFixedThreadPool(10)) {
            es.submit(() -> {
                if (ar.compareAndSet(newName1, myName)) {
                    System.out.println(ar.get());
                }
            });
        } finally {
            if (ar.compareAndSet(myName, newName1)) {
                System.out.println(ar.get());
            }
        }
    }
}

