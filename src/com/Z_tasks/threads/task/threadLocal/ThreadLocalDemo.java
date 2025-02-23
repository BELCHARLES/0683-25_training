package com.Z_tasks.threads.task.threadLocal;

import java.util.logging.Logger;

import com.generalutils.logger.ThreadLogger;

public class ThreadLocalDemo {
	private static final Logger LOGGER = ThreadLogger.getLogger();
	
	public ThreadLocalDemo(String mailId){
		startProcess(mailId);
	}
	
	public void startProcess(String mailId) {
		ThreadLocalManager.setThreadLocal(mailId);
		LOGGER.info(Thread.currentThread().getName()+" processing in Class - ThreadLocalDemo");
		new IntermediateClassOne().startProcess();
	}
}
