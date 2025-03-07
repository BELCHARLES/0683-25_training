package com.ztasks.filehandling.task.singleton.test;

import com.ztasks.filehandling.task.singleton.BillPughSingleton;
import com.ztasks.filehandling.task.singleton.DoubleCheckedSingleton;
import com.ztasks.filehandling.task.singleton.EarlyInitializedSingleton;
import com.ztasks.filehandling.task.singleton.EnumSingleton;
import com.ztasks.filehandling.task.singleton.LazyInitializedSingleton;
import com.ztasks.filehandling.task.singleton.ThreadSafeSingleton;

public class MultiThreadSingletonTest implements Runnable{

	public static void main (String args[]) {
		Thread thread1 = new Thread(new MultiThreadSingletonTest());
		Thread thread2 = new Thread(new MultiThreadSingletonTest());
		Thread thread3 = new Thread(new MultiThreadSingletonTest());
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		try {
			thread1.join();
			thread2.join();
			thread3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		
		EarlyInitializedSingleton earlyInstance = EarlyInitializedSingleton.getInstance();
		System.out.println(Thread.currentThread().getName() + " - Early Instance HashCode: " + earlyInstance.hashCode());
		
		LazyInitializedSingleton lazyInstance = LazyInitializedSingleton.getInstance();
		System.out.println(Thread.currentThread().getName() + " - Lazy Instance HashCode: " + lazyInstance.hashCode());
		
		ThreadSafeSingleton threadSafeInstance = ThreadSafeSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + " - THread safe Instance HashCode: " + threadSafeInstance.hashCode());
        
        DoubleCheckedSingleton doubleCheckedInstance = DoubleCheckedSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + " - Double checked Instance HashCode: " + doubleCheckedInstance.hashCode());
        
        BillPughSingleton billPughInstance = BillPughSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + " - Bill Pugh Instance HashCode: " + billPughInstance.hashCode());
        
        EnumSingleton enumInstance = EnumSingleton.INSTANCE;
        System.out.println(Thread.currentThread().getName() + " - Enum singleton Instance HashCode: " + enumInstance.hashCode());
	}
	
}
