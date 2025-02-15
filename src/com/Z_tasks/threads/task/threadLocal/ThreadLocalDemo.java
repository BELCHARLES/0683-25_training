package com.Z_tasks.threads.task.threadLocal;

import java.util.logging.Logger;

import com.generalutils.logger.ThreadLogger;

public class ThreadLocalDemo {
	private static final Logger logger = ThreadLogger.getLogger();
	
	public void startProcess() {
		ThreadLocalManager.setThreadLocal("I am "+Thread.currentThread().getName());
		logger.info(Thread.currentThread().getName()+" processing in Class - ThreadLocalDemo");
		new IntermediateClassOne().startProcess();
	}
}
