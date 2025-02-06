package com.Z_tasks.inheritance.Birds;

// Bird is not abstract and does not override abstract method fly() in Bird
public abstract class Bird{
	
	public abstract void fly();
	
	public void speak(){
		System.out.println("Bird-speak method");
	}
}