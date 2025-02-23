package com.Z_tasks.threads.task.threadLocal;

import java.util.logging.Logger;

import com.generalutils.logger.ThreadLogger;

public class IntermediateClassTwo {
	private static final Logger LOGGER = ThreadLogger.getLogger();

	public void startProcess() {
		LOGGER.info(Thread.currentThread().getName()+" processing in Class - IntermediateClassTwo");
		new FinalClass().startProcess();
	}
}
