package com.Z_tasks.filehandling.task;

public class ReflectionClass{
	
	private String name;
	private int age;
	
	public ReflectionClass(){
		this.name = "Default";
		this.age = 10;
	}
	
	public ReflectionClass(String name,int age){
		this.name = name;
		this.age = age;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getAge(){
		return age;
	}
	
	public void setAge(int age){
		this.age = age;
	}
	
	@Override
	public String toString(){
		return "Name: "+this.name+" Age: "+this.age;
	}
}