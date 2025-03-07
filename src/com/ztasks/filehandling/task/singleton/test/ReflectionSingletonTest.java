package com.ztasks.filehandling.task.singleton.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.ztasks.filehandling.task.singleton.BillPughSingleton;
import com.ztasks.filehandling.task.singleton.EnumSingleton;

public class ReflectionSingletonTest {
	
	public static void main(String args[]) {
//		BillPughSingleton instance1 = BillPughSingleton.getInstance();
//		BillPughSingleton instance2 = null;
//		try {
//			Constructor<?>[] constructors = BillPughSingleton.class.getDeclaredConstructors();
//			for(Constructor<?> cons :constructors) {
//				System.out.print("cons "+cons);
//				System.out.println("Is Synthetic : "+cons.isSynthetic());
//			}
//			Constructor<?> constructor = constructors[0];
//			constructor.setAccessible(true);
//			instance2 = (BillPughSingleton) constructor.newInstance();
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("Instance 1 hashcode : "+instance1.hashCode());
//		System.out.println("Instance 2 hashcode : "+instance2.hashCode());
		
		EnumSingleton instance1 = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = null;

        try {
            Constructor<?>[] constructors = EnumSingleton.class.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                constructor.setAccessible(true);
                instance2 = (EnumSingleton) constructor.newInstance(); 
            }
        } catch (InvocationTargetException e) {
            System.out.println("Reflection attack prevented: " + e.getCause());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Instance 1 hashcode: " + instance1.hashCode());
        System.out.println("Instance 2: " + instance2);  
	}
	
}
