package com.session.executorservice.test;

import java.util.concurrent.*;

public class CallableWithoutExecutor {
    public static void main(String[] args) {
        // Step 1: Create a Callable task
        Callable<Integer> task = () -> {
            System.out.println("Task is running...");
            Thread.sleep(2000); // Simulate work (2 seconds)
            return 42; // Returning an integer
        };

        // Step 2: Wrap Callable in a FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(task);

        // Step 3: Create a thread and start it
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            // Step 4: Get the result from FutureTask (blocks until task completes)
            Integer result = futureTask.get();
            System.out.println("Task result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
