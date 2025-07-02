package org.lrucache;

import org.lrucache.cache.LRUCache;

import static java.lang.Thread.sleep;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        LRUCache<Integer, Integer> cache = new LRUCache<>(3);
        // Multithreaded calls

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                cache.put(i, i);
                System.out.println("Thread-1 put(" + i + ", T1-Val" + i + ")");
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                String value = String.valueOf(cache.get(i));
                System.out.println("Thread-2 get(" + i + ") -> " + value);
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 3; i < 8; i++) {
                cache.put(i, 1);
                System.out.println("Thread-3 put(" + i + ", T3-Val" + i + ")");
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();

        // Wait for all threads to complete
        t1.join();
        t2.join();
        t3.join();

        System.out.println("\nFinal Cache State:");
        for (int i = 0; i < 10; i++) {
            String val = String.valueOf(cache.get(i));
            if (val != null) {
                System.out.println("Key: " + i + ", Value: " + val);
            }
        }
    }
}