package com.generalutils.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ThreadLogger {
	private static final Logger logger = Logger.getLogger("com.runner.ThreadRunner");
	static {
		try {
			Logger rootLogger = Logger.getLogger("");
	        Handler[] handlers = rootLogger.getHandlers();
	        for (Handler handler : handlers) {
	            if (handler instanceof java.util.logging.ConsoleHandler) {
	                rootLogger.removeHandler(handler);
	            }
	        }

			FileHandler infoHandler,warningHandler,severeHandler;
			infoHandler = new FileHandler("threadInfoLog%u.log");
			infoHandler.setFormatter(new SimpleFormatter());
			infoHandler.setFilter(record -> record.getLevel() == Level.INFO);
		    logger.addHandler(infoHandler);
		    
		    warningHandler = new FileHandler("threadDumpLog%u.log");
		    warningHandler.setFormatter(new SimpleFormatter());
		    warningHandler.setFilter(record -> record.getLevel() == Level.WARNING);
		    logger.addHandler(warningHandler);
		        
		    severeHandler = new FileHandler("threadSevereLog%u.log");
		    severeHandler.setFormatter(new SimpleFormatter());
			severeHandler.setLevel(Level.SEVERE);
		    logger.addHandler(severeHandler);
		}catch (IOException e) {
			logger.log(Level.SEVERE,"Exception occurred from ThreadLogger",e);
		}
	}
	
	public static Logger getLogger() {
		return logger;
	}
}
