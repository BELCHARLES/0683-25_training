package com.ztasks.filehandling.task.singleton;

public class EarlyInitializedSingleton {
	
	private static EarlyInitializedSingleton instance = new EarlyInitializedSingleton();
	
	private EarlyInitializedSingleton(){
	}
	
	public static EarlyInitializedSingleton getInstance(){
		return instance;
	}
	
}
