package com.session.executorservice.main;

import java.util.concurrent.*;

public class CachedThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        String[] departments = {"CSE", "ECE", "MECH", "IT", "CIVIL"};

        for (String dept : departments) {
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " - Fetching data for: " + dept);
                try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + " - Completed: " + dept);
            });
        }

        executor.shutdown();
    }
}
