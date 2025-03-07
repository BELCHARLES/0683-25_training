package com.session.executorservice.main;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3); // Pool with 3 threads

        Runnable emailTask = () -> {
            System.out.println(Thread.currentThread().getName() + " - Sending email reminder...");
        };

        // Initial delay of 2 seconds, then runs every 3 seconds
        scheduler.scheduleAtFixedRate(emailTask, 2, 3, TimeUnit.SECONDS);

        // Shut down the executor after 20 seconds (for demo purposes)
		
		  scheduler.schedule(() -> { System.out.println("Shutting down scheduler...");
		  scheduler.shutdown(); }, 10, TimeUnit.SECONDS);
		 
    }
}
