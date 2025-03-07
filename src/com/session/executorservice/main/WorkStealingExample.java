package com.session.executorservice.main;


import java.util.concurrent.*;

public class WorkStealingExample {
    public static void main(String[] args) throws InterruptedException {
        // Create a Work-Stealing Thread Pool with available processors
        ExecutorService executor = Executors.newWorkStealingPool(3);

        // Submit multiple tasks
        for (int i = 1; i <= 6; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate work
                    System.out.println("Task " + taskId + " FINISHED by " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Allow tasks to complete before shutting down
        Thread.sleep(3000);
        executor.shutdown();
    }
}

/*
 * 
 * import java.util.Arrays; import java.util.List; import
 * java.util.concurrent.*;
 * 
 * public class WorkStealingExample { public static void main(String[] args)
 * throws InterruptedException, ExecutionException { ExecutorService executor =
 * Executors.newWorkStealingPool(3);
 * 
 * System.out.println("Submitting tasks...");
 * 
 * // Assign tasks to specific threads List<Future<String>> results =
 * executor.invokeAll(Arrays.asList( createTask("Image 1"),
 * createTask("Image 2"), createTask("Image 3"), createTask("Image 4"),
 * createTask("Image 5"), createTask("Image 6"), createTask("Image 7") ));
 * 
 * for (Future<String> result : results) { System.out.println(result.get()); //
 * Blocks until task completes }
 * 
 * executor.shutdown(); }
 * 
 * private static Callable<String> createTask(String imageName) { String
 * assignedThread = Thread.currentThread().getName(); // Initially assigned
 * thread
 * 
 * return () -> { String executingThread = Thread.currentThread().getName(); //
 * Actual executing thread
 * 
 * System.out.println(executingThread + " STARTED processing " + imageName +
 * " (Assigned: " + assignedThread + ")");
 * 
 * int processingTime = (int) (Math.random() * 4000) + 1000; // Random delay 1-5
 * sec Thread.sleep(processingTime);
 * 
 * // Detect work stealing if (!assignedThread.equals(executingThread)) {
 * System.out.println("⚠️ " + executingThread + " STEALED " + imageName +
 * " from " + assignedThread); }
 * 
 * System.out.println(executingThread + " FINISHED processing " + imageName +
 * " (Time: " + processingTime + " ms, Assigned: " + assignedThread + ")");
 * return executingThread + " processed " + imageName; }; } }
 */