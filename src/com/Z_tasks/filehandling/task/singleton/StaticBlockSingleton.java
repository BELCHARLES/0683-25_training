package com.Z_tasks.filehandling.task.singleton;

public class StaticBlockSingleton {
	
	private static StaticBlockSingleton instance;
	
	private StaticBlockSingleton() {}
	
	static {
		try {
			instance = new StaticBlockSingleton();
		}
		catch(Exception e) {
			throw new RuntimeException("Exception occurred in creating singleton instance");
		}
	}

	public static StaticBlockSingleton getInstance() {
		return instance;
	}
}
