package com.generalutils.logger;

import java.io.IOException;
import java.util.logging.*;

public class CustomLogger {
    private static final Logger rootLogger = Logger.getLogger("");

    static {

    	for (Handler handler : rootLogger.getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                rootLogger.removeHandler(handler);
            }
        }
    }

    public static Logger getLogger(String loggerName, String logFilePrefix) {
        Logger logger = Logger.getLogger(loggerName);
        logger.setUseParentHandlers(false); 

        try {

        	 String infoLog = "logs/" + logFilePrefix + "_info_%u.log";
             String warningLog = "logs/" + logFilePrefix + "_warning_%u.log";
             String severeLog = "logs/" + logFilePrefix + "_severe_%u.log";

            logger.addHandler(createFileHandler(infoLog, Level.INFO));
            logger.addHandler(createFileHandler(warningLog, Level.WARNING));
            logger.addHandler(createFileHandler(severeLog, Level.SEVERE));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Exception occurred while setting up logger", e);
        }

        return logger;
    }

    private static FileHandler createFileHandler(String filePattern, Level level) throws IOException {
        FileHandler fileHandler = new FileHandler(filePattern);
        fileHandler.setFormatter(new SimpleFormatter());
        fileHandler.setLevel(level);
        fileHandler.setFilter(record -> record.getLevel() == level);
        return fileHandler;
    }
}
