package com.Z_tasks.threads.runner;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.Z_tasks.threads.task.ExtendedThread;
import com.Z_tasks.threads.task.RunnableThread;
import com.Z_tasks.threads.task.threadLocal.ThreadLocalDemo;
import com.generalutils.logger.ThreadLogger;

public class ThreadRunner {
	private static Logger logger = ThreadLogger.getLogger();

	//helper methods
	
	private void printChoiceList(){
		System.out.println("\nSno.    Functions");
		System.out.println("1 Extended Thread details");
		System.out.println("2 Runnable Thread details");
		System.out.println("3 Specify name for threads");
		System.out.println("4 Sleep threads");
		System.out.println("5 Analyze thread states");
		System.out.println("6 Thread dumps-concurrent with main thread");
		System.out.println("7 analyze synchronization and locks using thread dump");
		System.out.println("8 ThreadLocal Demo");
		System.out.println("9 Exit");
		System.out.println("Please enter the corresponding serial number to perform the requied function: ");
	}
	
	private void printExtendedThread(ExtendedThread thread) {
		logger.info("Thread name: "+thread.getName()+" Priority: "+ thread.getPriority()+" State: "+thread.getState());
	}
	
	private void printRunnableThread(Thread thread) {
		logger.info("Thread name: "+thread.getName()+" Priority: "+ thread.getPriority()+" State: "+thread.getState());
	}
	
	private void dumpThreadsWithLocks() {
		logger.warning("<< =============================================================================================== >>");
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(true, true);
		for(ThreadInfo threadInfo : threadInfos) {
			logger.log(Level.WARNING,"{0}",threadInfo);
		}
		logger.warning("<< =============================================================================================== >>");
	}
	
	private void dumpIdGivenThreads(long[] threadIds) {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIds,true, true);
		for(ThreadInfo threadInfo : threadInfos) {
			logger.log(Level.WARNING,"{0}",threadInfo);
		}
	}
	
	private void dumpThreadStackTraces() {
		for(Thread thread :Thread.getAllStackTraces().keySet()) {
			System.out.println(thread+" ==> ");
			for(StackTraceElement element:Thread.getAllStackTraces().get(thread)) {
				System.out.println("    -"+element);
			}
		}
	}
	
	private void takeThreadDump(int dumpCount,long dumpInterval) throws InterruptedException {
		for(int i = 0;i<dumpCount;i++) {
			logger.warning("Thread dump "+(i+1)+" :");
			dumpThreadsWithLocks();
			logger.warning("<< ======================================================================= >>");
			Thread.sleep(dumpInterval);
		}
	}
	
	private void spawnThreads(int count,List<ExtendedThread> extendedThreads,List<RunnableThread> runnableThreads) {
		for(int i = 0;i<count;i++) {
			ExtendedThread thread = new ExtendedThread("ExtendedThread-"+(i+1) , (i+1)*1000 , true);
			extendedThreads.add(thread);
			thread.start();
			
			RunnableThread runnableThread = new RunnableThread("RunnableThread-" + (i+1), (i+1) * 1000,true); 
            runnableThreads.add(runnableThread);
            runnableThread.getThread().start();
		}
	}
	
	private Thread startMonitorThread() {
		Thread monitorThread = new Thread(()->{
			for(int i = 0;i<10;i++) {
				logger.warning("Thread dump "+(i+1)+" :");
				dumpThreadsWithLocks();
				logger.warning("<< ======================================================================= >>");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					logger.log(Level.SEVERE,"Exception occurred",e);
				}
			}
		});
		monitorThread.start();
		return monitorThread;
	}
	
	private void stopThreads(int count,long threadStopDelay,List<ExtendedThread> extendedThreads,List<RunnableThread> runnableThreads) throws InterruptedException {
		for (int i = 0; i < count; i++) {
            extendedThreads.get(i).stopThread();
            logger.info("Stopped: " + extendedThreads.get(i).getName());
            
            runnableThreads.get(i).stopThread();
            logger.info("Stopped: " + runnableThreads.get(i).getThread().getName());
            
            Thread.sleep(threadStopDelay); 
        }
	}
	
	private void waitForAllThreadsToFinish(List<ExtendedThread> extendedThreads,List<RunnableThread> runnableThreads) throws InterruptedException {
		for (ExtendedThread thread : extendedThreads) {
	        thread.join(); 
	    }
	    for (RunnableThread thread : runnableThreads) {
	        thread.getThread().join();
	    }
		logger.info("All tasks completed.");
	}
	
	public void printExtendedThreadDetails(Scanner sc) throws InterruptedException {
		ExtendedThread thread = new ExtendedThread();
		printExtendedThread(thread);
		thread.start();
//		thread.join();
		printExtendedThread(thread);
	}
	
	public void printRunnableThreadDetails(Scanner sc) throws InterruptedException {
		RunnableThread runnableThread = new RunnableThread();
		Thread thread = runnableThread.getThread();
		printRunnableThread(thread);
		thread.start();
//		thread.join();
		printRunnableThread(thread);
	}
	
	public void createNamedThreads(Scanner sc) throws InterruptedException {
		System.out.println("Enter the name for extended thread: ");
		String name = sc.nextLine();
		ExtendedThread thread = new ExtendedThread(name);
		printExtendedThread(thread);
		thread.start();
//		thread.join();
		printExtendedThread(thread);
		
		System.out.println("Enter the name for runnable thread: ");
		name = sc.nextLine();
		RunnableThread runnableThread = new RunnableThread(name);
		Thread runnableThreadHolder = runnableThread.getThread();
		printRunnableThread(runnableThreadHolder);
		runnableThreadHolder.start();
//		runnableThreadHolder.join();
		printRunnableThread(runnableThreadHolder);
	}
	
	public void sleepThreads(Scanner sc) throws InterruptedException {
		System.out.println("Enter the number of Extended threads to spawn: ");
		int count = sc.nextInt();
		sc.nextLine();
		for(int i = 0;i<count;i++) {
			System.out.println("Enter extended thread name: ");
			String name = sc.nextLine();
			System.out.println("Enter the milliseconds for the thread to sleep: ");
			long sleepTime = sc.nextInt();
			sc.nextLine();
			ExtendedThread thread = new ExtendedThread(name,sleepTime);
			thread.start();
//			thread.join();
		}
		
		System.out.println("Enter the number of Runnable threads to spawn: ");
		count = sc.nextInt();
		sc.nextLine();
		for(int i = 0;i<count;i++) {
			System.out.println("Enter Runnable thread name: ");
			String name = sc.nextLine();
			System.out.println("Enter the milliseconds for the thread to sleep: ");
			long sleepTime = sc.nextInt();
			sc.nextLine();
			RunnableThread runnableThread = new RunnableThread(name);
			Thread runnableThreadHolder = runnableThread.getThread();
			runnableThreadHolder.start();
//			runnableThreadHolder.join();
		}
	}

	
	public void analyzeThreadStates(Scanner sc) throws InterruptedException {
		List<ExtendedThread> extendedThreads = new ArrayList<>();
		List<RunnableThread> runnableThreads = new ArrayList<>();
		
		System.out.println("Enter the number of threads to spawn: ");
		int count = sc.nextInt();
		sc.nextLine();
		
		spawnThreads(count,extendedThreads,runnableThreads);
		
		//thread dump
		Thread.sleep(12000);
		takeThreadDump(3,30000);
		
		stopThreads(count,0,extendedThreads,runnableThreads);
	}
	
	public void takeThreadDumpsUsingMonitor(Scanner sc) throws InterruptedException {
		List<ExtendedThread> extendedThreads = new ArrayList<>();
		List<RunnableThread> runnableThreads = new ArrayList<>();
		
		System.out.println("Enter the number of threads to spawn: ");
		int count = sc.nextInt();
		sc.nextLine();
		
		spawnThreads(count,extendedThreads,runnableThreads);
		
		//taking thread dump 
		Thread.sleep(12000);
		Thread monitorThread = startMonitorThread();
		
		stopThreads(count,60000,extendedThreads,runnableThreads);
		
		waitForAllThreadsToFinish(extendedThreads,runnableThreads);
		
		monitorThread.join();
		
		logger.warning("Final thread dump");
		takeThreadDump(1,0);

	}
	
	public void analyzeSyncNature() throws InterruptedException {
		Thread[] threads = new Thread[4];
		for (int i = 0; i < threads.length; i++) {
	        threads[i] = new Thread(() -> {
	            try {
	                sleep(Thread.currentThread());
	            } catch (InterruptedException e) {
	                logger.log(Level.SEVERE, "Exception occurred", e);
	            }
	        });
	    }
		startMonitorThread(); 

	    for (Thread thread : threads) {
	        thread.start();
	    }
	}
	
	private synchronized void sleep(Thread thread) throws InterruptedException {
		Thread.sleep(10000);
	}
	
	public void threadLocalDemo() {
		for(int i = 0;i<2;i++) {
			new Thread(()->new ThreadLocalDemo().startProcess()).start();
		}
	}
	
	public static void main (String args[]) throws SecurityException, IOException  {
		Scanner sc = new Scanner(System.in);
	    
		ThreadRunner runner = new ThreadRunner();
		runner.printChoiceList();
		int choice = sc.nextInt();
		sc.nextLine();

		if(choice == 9){
			return;
		}	
		do{
			try{
				switch(choice){
					case 1:
						runner.printExtendedThreadDetails(sc);
						break;
					case 2:
						runner.printRunnableThreadDetails(sc);
						break;
					case 3:
						runner.createNamedThreads(sc);
						break;
					case 4:
						runner.sleepThreads(sc);
						break;
					case 5:
						runner.analyzeThreadStates(sc);
						break;
					case 6:
						runner.takeThreadDumpsUsingMonitor(sc);
						break;
					//swamy demo
					case 7:
						runner.analyzeSyncNature();
						break;
					case 8:
						runner.threadLocalDemo();
						break;
				}
			}
			catch(Exception e){
				logger.log(Level.SEVERE,"Exception occurred",e);
			}
			runner.printChoiceList();
			choice = sc.nextInt();
			sc.nextLine();			
		}while(choice!=9);
	}

}