package com.session.executorservice.main;

import java.util.concurrent.*;

class FundTransfer implements Runnable {
    private final int transactionId;

    public FundTransfer(int transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public void run() {
        System.out.println("Processing transaction " + transactionId + " by " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000); // Simulating time taken for fund transfer
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Transaction " + transactionId + " completed.");
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3); // 3 threads for handling transactions

        for (int i = 1; i <= 5; i++) {
            executor.execute(new FundTransfer(i)); // Submitting transactions
        }

        executor.shutdown();
    }
}


