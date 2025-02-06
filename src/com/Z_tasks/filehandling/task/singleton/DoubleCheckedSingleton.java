package com.Z_tasks.filehandling.task.singleton;

public class DoubleCheckedSingleton {

private volatile static DoubleCheckedSingleton instance;
	
	private DoubleCheckedSingleton(){}
	
	public static DoubleCheckedSingleton getInstance(){
		if(instance == null){
			synchronized (DoubleCheckedSingleton.class){
				if(instance == null){
					instance = new DoubleCheckedSingleton();
				}
			}
		}
		return instance;
	}
	
}
