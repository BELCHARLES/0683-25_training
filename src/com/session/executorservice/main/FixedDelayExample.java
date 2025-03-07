package com.session.executorservice.main;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FixedDelayExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2); // Pool with 2 threads

        Runnable cleanupTask = () -> {
            System.out.println(Thread.currentThread().getName() + " - Cleaning up log files...");
            try {
                Thread.sleep(3000); // Simulate variable task duration
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        

        // Initial delay of 2 sec, then runs 2 sec after each task completes
        scheduler.scheduleWithFixedDelay(cleanupTask, 2, 2, TimeUnit.SECONDS);

        // Shut down the scheduler after 20 seconds (for demo purposes)
        scheduler.schedule(() -> {
            System.out.println("Shutting down scheduler...");
            scheduler.shutdown();
        }, 20, TimeUnit.SECONDS);
    }
}
