package com.Z_tasks.threads.task;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.generalutils.logger.ThreadLogger;

public class ExtendedThread extends Thread{
	
	private static final Logger logger = ThreadLogger.getLogger();;
	private long sleepTime;
	private boolean isRunning;
	
	public ExtendedThread() {
	}
	
	public ExtendedThread(String name) {
		super(name);
	}
	
	public ExtendedThread(String name, long sleepTime) {
        super(name);
        this.sleepTime = sleepTime;
    }
	
	public ExtendedThread(String name, long sleepTime,boolean isRunning) {
        super(name);
        this.sleepTime = sleepTime;
        this.isRunning = isRunning;
    }
	
	
	@Override
	public void run(){
		logger.info("Thread name: "+getName()+" Priority: "+ getPriority()+" State: "+getState());

		try {
			logger.info("Thread "+getName()+" going to sleep");
			Thread.sleep(sleepTime);
			logger.info("Thread "+getName()+" after sleeping");
			while(isRunning) {
				Thread.sleep(sleepTime);
			}
			
		} catch (InterruptedException e) {
			logger.log(Level.SEVERE,"Exception occurred",e);
		}
	}
	
	public void stopThread() {
		isRunning = false;
	}
	
}