package com.ztasks.threads.task.threadLocal;

import java.util.logging.Logger;

import com.generalutils.logger.CustomLogger;

public class ThreadLocalDemo {
	private static final Logger LOGGER = CustomLogger.getLogger("com.ztasks.threads.runner.ThreadRunner", "thread");
	
	public ThreadLocalDemo(String mailId){
		startProcess(mailId);
	}
	
	public void startProcess(String mailId) {
		ThreadLocalManager.setThreadLocal(mailId);
		LOGGER.info(Thread.currentThread().getName()+" processing in Class - ThreadLocalDemo");
		new IntermediateClassOne().startProcess();
	}
}
