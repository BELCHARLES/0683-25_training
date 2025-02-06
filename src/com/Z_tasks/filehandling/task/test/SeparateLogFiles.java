package com.Z_tasks.filehandling.task.test;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.Handler;

public class SeparateLogFiles {

    private static final Logger logger = Logger.getLogger(SeparateLogFiles.class.getName());

    public static void main(String[] args) throws IOException {

         // Remove console handler (if you don't want console output)
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        for (Handler handler : handlers) {
            if (handler instanceof java.util.logging.ConsoleHandler) {
                rootLogger.removeHandler(handler);
            }
        }


        try {
            // 1. FileHandler for INFO and above (INFO, WARNING, SEVERE)
            FileHandler infoHandler = new FileHandler("info_log%u.log", true); // Append mode
            infoHandler.setFormatter(new SimpleFormatter());
            infoHandler.setLevel(Level.INFO); // INFO and above will be handled by this handler
            logger.addHandler(infoHandler);


            // 2. FileHandler ONLY for SEVERE
            FileHandler severeHandler = new FileHandler("severe_log%u.log", true); // Append mode
            severeHandler.setFormatter(new SimpleFormatter());
            severeHandler.setLevel(Level.SEVERE); // Only SEVERE will be handled by this handler

            // Add a filter to the severeHandler to ONLY allow SEVERE messages
            severeHandler.setFilter(new Filter() {
                @Override
                public boolean isLoggable(LogRecord record) {
                    return record.getLevel() == Level.SEVERE; // Only allow SEVERE
                }
            });
            logger.addHandler(severeHandler);



            logger.info("This is an info message.");
            logger.warning("This is a warning message.");
            logger.severe("This is a severe error message.");

            try {
                int result = 10 / 0;
            } catch (ArithmeticException e) {
                logger.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
            }

            infoHandler.close();
            severeHandler.close();

        } catch (IOException e) {
            System.err.println("Error creating log files: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
