package com.session.executorservice.main;

public class SingleThreadTiming {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); // Start time

        System.out.println("Task 1 started...");
        task(); // Runs one task

        System.out.println("Task 2 started...");
        task(); // Runs second task

        long endTime = System.currentTimeMillis(); // End time
        System.out.println("Total execution time (Single Threaded): " + (endTime - startTime) + "ms");
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

