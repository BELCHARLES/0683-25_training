package com.session.executorservice.main;

import java.util.concurrent.*;

public class CallableFutureDemo {
    public static void main(String[] args) {
        // Step 1: Create ExecutorService with a fixed thread pool
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Step 2: Create a Callable task that returns a value
        Callable<Integer> task = () -> {
            System.out.println("Task is running...");
            Thread.sleep(2000); // Simulate work (2 seconds)
            return 42; // Returning an integer
        };

        // Step 3: Submit Callable to ExecutorService and get a Future
        Future<Integer> future = executor.submit(task);

        try {
            // Step 4: Get the result from the Future (blocks until task completes)
            Integer result = future.get();
            System.out.println("Task result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // Step 5: Shutdown the executor
            executor.shutdown();
        }
    }
}
