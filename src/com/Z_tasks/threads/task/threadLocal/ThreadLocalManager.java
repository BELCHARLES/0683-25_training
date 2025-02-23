package com.Z_tasks.threads.task.threadLocal;

public class ThreadLocalManager {
	private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	
	public static void setThreadLocal(String value) {
		threadLocal.set(value);
	}
	
	public static String getThreadLocal() {
		return threadLocal.get();
	}
}
