package com.session.executorservice.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Runnable logTask1 = () -> {
            System.out.println(Thread.currentThread().getName() + " - Logging: User logged in");
        };

        Runnable logTask2 = () -> {
            System.out.println(Thread.currentThread().getName() + " - Logging: User clicked on profile");
        };

        Runnable logTask3 = () -> {
            System.out.println(Thread.currentThread().getName() + " - Logging: User made a purchase");
        };

        executor.submit(logTask1);
        executor.submit(logTask2);
        executor.submit(logTask3);

        executor.shutdown();
    }
}
