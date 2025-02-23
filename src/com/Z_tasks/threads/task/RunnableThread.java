package com.Z_tasks.threads.task;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.generalutils.logger.ThreadLogger;

public class RunnableThread implements Runnable {
	private static final Logger logger = ThreadLogger.getLogger();
	private Thread thread;
	private long sleepTime;
	private boolean isRunning;
	
	public RunnableThread() {
		this.thread = new Thread(this);
	}
	
	public RunnableThread(String name) {
		this.thread = new Thread(this,name);
	}
	
	public RunnableThread(String name,long sleepTime) {
		this.thread = new Thread(this,name);
		this.sleepTime = sleepTime;
	}
	
	public RunnableThread(String name,long sleepTime,boolean isRunning) {
		this.thread = new Thread(this,name);
		this.sleepTime = sleepTime;
		this.isRunning = isRunning;
	}
	
	@Override
	public void run(){
		logger.info("Thread name: "+thread.getName()+" Priority: "+thread.getPriority()+" State: "+thread.getState());
		
		
		try {
			logger.info("Thread "+thread.getName()+" going to sleep");
			Thread.sleep(sleepTime);
			logger.info("Thread "+thread.getName()+" after sleeping");
			while(isRunning) {
				Thread.sleep(sleepTime);
			}
			
		} catch (InterruptedException e) {
			logger.log(Level.SEVERE,"Exception occurred",e);
		}
		
	}
	
	public Thread getThread() {
		return thread;
	}
	
	public void stopThread() {
		isRunning = false;
	}
}