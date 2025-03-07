package com.session.executorservice.main;

public class MultiThreadTiming {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); // Start time

        System.out.println("Task 1 started...");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                task();
            }
        });

        System.out.println("Task 2 started...");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                task();
            }
        });

        t1.start(); // Start task 1
        t2.start(); // Start task 2

        try {
            t1.join(); // Wait for task 1 to complete
            t2.join(); // Wait for task 2 to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis(); // End time
        System.out.println("Total execution time (Multi-Threaded): " + (endTime - startTime) + "ms");
    }

    public static void task() {
        try {
            Thread.sleep(2000); // Simulating time-consuming task
            System.out.println("Task completed by " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

