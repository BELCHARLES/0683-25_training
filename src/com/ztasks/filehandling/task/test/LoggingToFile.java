package com.ztasks.filehandling.task.test;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingToFile {

    private static final Logger logger = Logger.getLogger(LoggingToFile.class.getName());

    public static void main(String[] args) {
        try {
//        	FileHandler fh = new FileHandler("my_log_file%u.log", true);
            FileHandler fh = new FileHandler(); 
            Logger rootLogger = Logger.getLogger(""); // Get the root logger
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                if (handler instanceof java.util.logging.ConsoleHandler) {
                    rootLogger.removeHandler(handler);
                }
            }

            logger.addHandler(fh);
           
            logger.setLevel(Level.INFO); // Log INFO level and above

            // Now you can log messages:
            logger.info("Application started.");

            try {

                int result = 10 / 0;
            } catch (ArithmeticException e) {
                logger.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e); // Log the exception
            }

            logger.warning("This is a warning message.");
            logger.info("This is an info message.");
            logger.fine("This is a fine message (will not be logged due to level)"); // FINE is below INFO

            logger.info("Application finished.");

            // 5. Close the handler (important to release file resources)
            fh.close(); // Important to close the handler to flush and close the file.

        } catch (IOException e) {
            System.err.println("Error creating or accessing log file: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }
}