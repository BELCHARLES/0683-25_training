package com.ztasks.filehandling.task.singleton;

import java.io.Serializable;

public class BillPughSingleton implements Serializable,Cloneable {

	private static final long serialVersionUID = 1L;

	private BillPughSingleton() {
		if (SingletonHelper.INSTANCE != null) {
	        throw new RuntimeException("Instance already created!");
	    }
	}
	
	private static class SingletonHelper{
		private static final BillPughSingleton INSTANCE = new  BillPughSingleton();
	}
	
	public static BillPughSingleton getInstance() {
		return SingletonHelper.INSTANCE;
	} 
	
	protected Object readResolve() {
        return getInstance();
    }
	
	@Override
	public Object clone() throws CloneNotSupportedException {  
        throw new CloneNotSupportedException();  
	}

}
