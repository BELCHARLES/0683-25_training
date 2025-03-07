package com.ztasks.threads.task.threadLocal;

import java.util.logging.Logger;

import com.generalutils.logger.CustomLogger;

public class IntermediateClassTwo {
	private static final Logger LOGGER = CustomLogger.getLogger("com.ztasks.threads.runner.ThreadRunner", "thread");

	public void startProcess() {
		LOGGER.info(Thread.currentThread().getName()+" processing in Class - IntermediateClassTwo");
		new FinalClass().startProcess();
	}
}
